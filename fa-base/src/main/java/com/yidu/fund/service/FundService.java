package com.yidu.fund.service;

import com.yidu.deposit.domain.FundInventory;
import com.yidu.format.LayuiFormat;
import com.yidu.fund.domain.Fund;
import com.yidu.fund.paging.FundPaging;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Map;

/**
 * 类的描述：
 *
 * @Author 李昊林
 * @Date 2020/9/3 9:01
 */
public interface FundService {
    /**
     * 查询所有基金信息并分页
     * @return 基金对象集合
     */
    LayuiFormat findAllFundWithPage(FundPaging fundPaging);

    /**
     * 根据条件查询基金信息
     * @param fundPaging 查询条件
     * @return 基金对象集合
     */
    LayuiFormat findFundByCondition(FundPaging fundPaging);

    /**
     * 根据条件统计基金信息条数
     * @param fundPaging 查询条件
     * @return 基金对象
     */
    Long countFundByCondition(FundPaging fundPaging);

    /**
     * 保存基金信息
     * @param fund 基金信息
     * @return 返回layui格式对象
     */
    Map<String,Object> addFund(Fund fund);

    /**
     * 修改基金信息
     * @param fund 基金对象
     * @return 成功修改返回true,否则返回false
     */
    int updateFund(Fund fund);
    /**
     * 修改基金可用状态
     * @param fundId 基金Id
     * @param usable 基金状态
     * @return 成功修改返回true,否则返回false
     */
    boolean updateFundStatus(String fundId,String usable);

    /**
     * 获取基金经理信息及基金管理公司信息
     * @return 返回包含基金经理信息及基金管理公司信息的集合
     */
    Map<String,Object> findUserAndFundCompany();
    /**
     * 添加基金库存信息
     * @param fund 基金对象
     * @return 添加成功返回true,否则返回false
     */
    boolean addFundInventory(Fund fund);

    /**
     * 添加现金库存信息
     * @param fundInventory 基金库存对象
     * @return 添加成功返回true,否则返回false
     */
    boolean addCachInventory(FundInventory fundInventory);


}
