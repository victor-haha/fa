package com.yidu.bond.controller;

import com.yidu.bond.paging.BondPaging;
import com.yidu.bond.service.BondService;
import com.yidu.format.LayuiFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 类的描述:
 *
 * @author wh
 * @since 2020/9/3 13:16
 */
@RestController
@RequestMapping("/bond")
public class BondController {
    @Autowired
    private BondService bondService;

    @RequestMapping("/list")
    public LayuiFormat findAll(BondPaging bondPaging){
        return bondService.findAll(bondPaging);
    }
}
