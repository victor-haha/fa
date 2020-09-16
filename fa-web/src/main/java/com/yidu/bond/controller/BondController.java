package com.yidu.bond.controller;

import com.yidu.bond.domain.Bond;
import com.yidu.bond.paging.BondPaging;
import com.yidu.bond.service.BondService;
import com.yidu.format.LayuiFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 类的描述: 债券基础操作
 *
 * @author wh
 * @since 2020/9/3 13:16
 */
@RestController
@RequestMapping("/bond")
public class BondController {
    @Autowired
    private BondService bondService;

    /**
     * 搜索查询债券+分页
     * @param bondPaging 分页搜索查询
     * @return 债券数据及相关数据
     */
    @RequestMapping("/list")
    public LayuiFormat findAll(BondPaging bondPaging){
        return bondService.findAll(bondPaging);
    }

    /**
     * 添加债券
     * @param bond 债券对象
     * @return 1:添加成功，0：添加失败
     */
    @RequestMapping("/add")
    public String addBond(Bond bond){
        int flag = bondService.addBond(bond);
        //返回修改成功与否1：成功，0：失败
        return flag == 1 ? "1" : "0";
    }

    /**
     * 修改债券状态
     * @param usable 1：可用，0：禁用
     * @param bondIds 修改的债券ids
     * @return 是否修改成功1：成功，0失败
     */
    @RequestMapping("/updateUsable")
    public String updateUsable(@RequestParam("usable") String usable,@RequestParam("bondIds") String bondIds){
        int flag = bondService.updateUsable(usable,bondIds);
        //返回修改成功与否1：成功，0：失败
        return flag == 1 ? "1" : "0";
    }

    /**
     * 修改债券
     * @param bond 修改对象
     * @return 1:修改成功，0：修改失败
     */
    @RequestMapping("/update")
    public String updateBond(Bond bond){
        int flag = bondService.updateBond(bond);
        return flag == 1 ? "1" : "0";
    }

}
