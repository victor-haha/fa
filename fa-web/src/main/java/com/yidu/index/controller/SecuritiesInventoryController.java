package com.yidu.index.controller;

import com.yidu.format.LayuiFormat;
import com.yidu.index.domain.SecuritiesInventory;
import com.yidu.index.paging.SecuritiesInventoryPaging;
import com.yidu.index.service.SecuritiesInventoryService;
import com.yidu.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * 类的描述:
 *
 * @author wh
 * @since 2020/9/14 9:15
 */
@RestController
@RequestMapping("/securities")
public class SecuritiesInventoryController {
    @Autowired
    private SecuritiesInventoryService securitiesInventoryService;
    @Autowired
    private LayuiFormat layuiFormat;
    @RequestMapping("/list")
    public LayuiFormat findAll(SecuritiesInventoryPaging securitiesInventoryPaging){
        //计算分页
        int limit = securitiesInventoryPaging.getLimit();
        int page = (securitiesInventoryPaging.getPage()-1)*limit;
        securitiesInventoryPaging.setPage(page);
        securitiesInventoryPaging.setLimit(limit);
        if(null != securitiesInventoryPaging.getEndStatisticalDate() && null != securitiesInventoryPaging.getStartStatisticalDate() ){
            if(securitiesInventoryPaging.getStartStatisticalDate().getTime()>securitiesInventoryPaging.getEndStatisticalDate().getTime()){
                //起始值大于终止值，则交换
                Date param = securitiesInventoryPaging.getStartStatisticalDate();
                securitiesInventoryPaging.setStartStatisticalDate(securitiesInventoryPaging.getEndStatisticalDate());
                securitiesInventoryPaging.setEndStatisticalDate(param);
            }
        }else{
            securitiesInventoryPaging.setEndStatisticalDate(new Date());
            try {
                securitiesInventoryPaging.setStartStatisticalDate(DateUtils.stringToDate("1900-01-01","yyyy-MM-dd"));
            } catch (ParseException e) {
                layuiFormat.setCode(1);  //状态码0为查询到数据
                layuiFormat.setCount(0L);
                layuiFormat.setMsg("未查询到指定数据哦!");
                layuiFormat.setData(null);
                return layuiFormat;
            }
        }

        List<SecuritiesInventory> securitiesInventorys = securitiesInventoryService.findAll(securitiesInventoryPaging);
        if (CollectionUtils.isEmpty(securitiesInventorys)){   //集合为空
            layuiFormat.setCode(1);  //状态码0为查询到数据
            layuiFormat.setCount(0L);
            layuiFormat.setMsg("未查询到指定数据哦!");
            layuiFormat.setData(null);
        }else{
            layuiFormat.setCode(0);  //状态码0为查询到数据
            layuiFormat.setCount(securitiesInventoryService.findCount(securitiesInventoryPaging));
            layuiFormat.setMsg("成功找到数据");
            layuiFormat.setData(securitiesInventorys);
        }
        return layuiFormat;

    }
}
