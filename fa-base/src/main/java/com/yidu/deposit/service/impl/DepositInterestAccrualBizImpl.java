package com.yidu.deposit.service.impl;

import com.yidu.deposit.dao.DepositInterestAccrualDao;
import com.yidu.deposit.domain.DepositTrade;
import com.yidu.deposit.service.DepositInterestAccrualBiz;
import com.yidu.index.domain.SecuritiesArap;
import com.yidu.index.domain.SecuritiesInventory;
import com.yidu.index.domain.SecuritiesarapInventory;
import com.yidu.index.paging.SecuritiesInventoryPaging;
import com.yidu.utils.DateUtils;
import com.yidu.utils.IDUtil;
import com.yidu.utils.NoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类的描述：存款计息业务逻辑实现类
 *
 * @author 蔡任骋
 * @version 1.0
 * @since 2020/09/21
 */
@Service
public class DepositInterestAccrualBizImpl implements DepositInterestAccrualBiz {
    @Autowired
    private DepositInterestAccrualDao depositInterestAccrualDao;


    @Override
    public Long countDepositInterestAccrualByCondition(SecuritiesInventoryPaging securitiesInventoryPaging) {
        return depositInterestAccrualDao.countDepositInterestAccrualByCondition(securitiesInventoryPaging);
    }

    @Override
    public List<SecuritiesInventory> findSecuritiesInventoryByCondition(SecuritiesInventoryPaging securitiesInventoryPaging) {
        //处理POJO类中的数据
        //将页数转换为起始行号
        securitiesInventoryPaging.setPage((securitiesInventoryPaging.getPage()-1)*securitiesInventoryPaging.getLimit());
        //securitiesType
        securitiesInventoryPaging.setSecuritiesType(3);
        return depositInterestAccrualDao.findSecuritiesInventoryByCondition(securitiesInventoryPaging);
    }

    @Override
    public boolean depositInterestAccrualByCondition(String securitiesInventoryIds, String tradeStatus) {
        //1.修改证券库存中银行存款的计息状态
        boolean status = updateDepositInterestAccrualStatus(securitiesInventoryIds,tradeStatus);
        //将securitiesInventoryIds字符串用‘,’分割
        String[] Ids = securitiesInventoryIds.split(",");
        //定义证券库存数据集合
        List<SecuritiesInventory> securitiesInventoryList = new ArrayList<>();
        //循环查询证券库存
        for (String securitiesInventoryId : Ids){
            //将查询到的证券库存数据添加到集合中
            securitiesInventoryList.add(depositInterestAccrualDao.findSecuritiesInventoryById(securitiesInventoryId));
            //没有查询到银行交易数据，发生异常
            if (depositInterestAccrualDao.findSecuritiesInventoryById(securitiesInventoryId)==null) throw new RuntimeException(securitiesInventoryId+"对应的数据没有");
        }
        //2.往证券应收应付表中添加数据
        boolean a = addDepositInterestAccrual(securitiesInventoryList);
        //3.往证券应收应付库存表中添加或修改数据
        boolean b = addOrUpdateSecuritiesarapInventory(securitiesInventoryList);
        if (status && a && b){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 2.3.添加或修改证券应收应付库存数据
     * @param securitiesInventoryList
     * @return
     */
    private boolean addOrUpdateSecuritiesarapInventory(List<SecuritiesInventory> securitiesInventoryList) {
        //定义添加或修改结果
        boolean result = false;
        SecuritiesarapInventory securitiesarapInventory1 = null;
        SecuritiesarapInventory securitiesarapInventory2 = null;
        //声明一个证券应收应付库存对象
        SecuritiesarapInventory securitiesarapInventory = new SecuritiesarapInventory();
        Map<String,Object> paramMap1 = new HashMap<>();
        Map<String,Object> paramMap2 = new HashMap<>();
        DepositTrade depositTrade = null;
        BigDecimal money1 = null;
        BigDecimal money2 = null;
        for (SecuritiesInventory securitiesInventory : securitiesInventoryList){
            paramMap1.put("securitiesId",securitiesInventory.getSecuritiesId());
            paramMap1.put("fundId",securitiesInventory.getFundId());
            paramMap1.put("accountId",securitiesInventory.getAccountId());
            paramMap1.put("businessDate",new Date(DateUtils.ZeroDate(new Date())));
            securitiesarapInventory1 = depositInterestAccrualDao.findSecuritiesarapInventoryByCondition(paramMap1);
            //查询银行交易数据
            depositTrade = depositInterestAccrualDao.findDepositTradeById(securitiesInventory.getSecuritiesId());
            money1 = depositTrade.getInterestRate().multiply(securitiesInventory.getTurnover());
            money2= money1.divide(BigDecimal.valueOf(36500),4,BigDecimal.ROUND_HALF_UP);
            if (securitiesarapInventory1 == null){
                //添加数据
                securitiesarapInventory2 = depositInterestAccrualDao.findSecuritiesarapInventoryById(securitiesInventory.getSecuritiesId(),
                        securitiesInventory.getAccountId(),securitiesInventory.getFundId());
                securitiesarapInventory.setSecuritiesARAPInventoryId(IDUtil.getUuid());
                securitiesarapInventory.setSecuritiesARAPInventoryNo(NoUtils.getNo("ZQYSYFKC"));
                securitiesarapInventory.setSecuritiesId(securitiesInventory.getSecuritiesId());
                securitiesarapInventory.setSecuritiesNo(securitiesInventory.getSecuritiesNo());
                securitiesarapInventory.setSecuritiesName(securitiesInventory.getSecuritiesName());
                securitiesarapInventory.setFundId(securitiesInventory.getFundId());
                securitiesarapInventory.setFundNo(securitiesInventory.getFundNo());
                securitiesarapInventory.setFundName(securitiesInventory.getFundName());
                securitiesarapInventory.setAccountId(securitiesInventory.getAccountId());
                securitiesarapInventory.setAccountNo(securitiesInventory.getAccountNo());
                securitiesarapInventory.setAccountName(securitiesInventory.getAccountName());
                if(securitiesarapInventory2 == null){
                    securitiesarapInventory.setBalance(money2);
                }else {
                    securitiesarapInventory.setBalance(money2.add(securitiesarapInventory2.getBalance()));
                }
                securitiesarapInventory.setBusinessDate(new Date());
                securitiesarapInventory.setBusinessType(3);
                securitiesarapInventory.setFlag(1);
                securitiesarapInventory.setDescription(securitiesInventory.getDescription());
                result = depositInterestAccrualDao.addSecuritiesarapInventory(securitiesarapInventory);
            }else {
                //有修改当天数据
                paramMap2.put("securitiesARAPInventoryId",securitiesarapInventory1.getSecuritiesARAPInventoryId());
                paramMap2.put("balance",securitiesarapInventory1.getBalance().add(money2));
                paramMap2.put("businessDate",securitiesarapInventory1.getBusinessDate());
                result = depositInterestAccrualDao.updateSecuritiesarapInventory(paramMap2);
            }
        }
        return result;
    }

    /**
     * 2.2.添加证券应收应付表数据
     * @param securitiesInventoryList 证券库存数据
     * @return 成功返回true，否则返回false
     */
    private boolean addDepositInterestAccrual(List<SecuritiesInventory> securitiesInventoryList) {
        //定义修改结果
        boolean result = false;
        //声明一个证券应收应付对象
        SecuritiesArap securitiesArap = new SecuritiesArap();
        DepositTrade depositTrade = null;
        BigDecimal money1 = null;
        BigDecimal money2 = null;
        //循环往证券应收应付表中添加数据
        for (SecuritiesInventory securitiesInventory : securitiesInventoryList){
            //为证券应收应付对象赋值
            securitiesArap.setSecuritiesARAPId(IDUtil.getUuid());
            securitiesArap.setSecuritiesARAPNo(NoUtils.getNo("ZQYSYF"));
            securitiesArap.setFundId(securitiesInventory.getFundId());
            securitiesArap.setFundNo(securitiesInventory.getFundNo());
            securitiesArap.setFundName(securitiesInventory.getFundName());
            securitiesArap.setAccountId(securitiesInventory.getAccountId());
            securitiesArap.setAccountNo(securitiesInventory.getAccountNo());
            securitiesArap.setAccountName(securitiesInventory.getAccountName());
            securitiesArap.setSecuritiesId(securitiesInventory.getSecuritiesId());
            securitiesArap.setSecuritiesNo(securitiesInventory.getSecuritiesNo());
            securitiesArap.setSecuritiesName(securitiesInventory.getSecuritiesName());
            //查询银行交易数据
            depositTrade = depositInterestAccrualDao.findDepositTradeById(securitiesInventory.getSecuritiesId());
            money1 = depositTrade.getInterestRate().multiply(securitiesInventory.getTurnover());
            money2= money1.divide(BigDecimal.valueOf(36500),4,BigDecimal.ROUND_HALF_UP);
            securitiesArap.setArapAmount(money2);
            securitiesArap.setArapDate(new Date());
            securitiesArap.setArapFlag(1);
            securitiesArap.setArapType("3");
            //添加证券应收应付表数据
            result = depositInterestAccrualDao.addDepositInterestAccrual(securitiesArap);
        }

        return result;
    }

    /**
     * 2.1修改证券库存中银行存款数据计息状态
     * @param securitiesInventoryIds
     * @param tradeStatus
     * @return
     */
    private boolean updateDepositInterestAccrualStatus(String securitiesInventoryIds, String tradeStatus){
        //将securitiesInventoryIds字符串用‘,’分割
        String[] Ids = securitiesInventoryIds.split(",");
        //定义修改状态的参数Map集合
        Map<String,Object> paramMap = new HashMap<>();
        //定义修改结果
        boolean result = false;
        paramMap.put("tradeStatus",tradeStatus);
        for (String securitiesInventoryId : Ids) {
            paramMap.put("securitiesInventoryId",securitiesInventoryId);
            result = depositInterestAccrualDao.updateDepositInterestAccrualStatus(paramMap);
            if (!result) throw new RuntimeException(paramMap.get(securitiesInventoryId) + "对应的数据修改失败");
        }
        return result;
    };
}
