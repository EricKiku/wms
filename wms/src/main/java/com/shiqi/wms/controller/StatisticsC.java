package com.shiqi.wms.controller;

import com.shiqi.wms.mapper.TjM;
import com.shiqi.wms.tools.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/tj")
public class StatisticsC {
    @Autowired
    private TjM tjM;
    @RequestMapping("/inSteam")
    public Res inSteam() {
        List<Map<String, Object>> inSteam = tjM.inSteam();
        return  new Res(200,"success",inSteam) ;
    }

    @RequestMapping("/outSteam")
    public Res outSteam() {
        List<Map<String, Object>> outSteam = tjM.outSteam();
        return  new Res(200,"success",outSteam) ;
    }

}
