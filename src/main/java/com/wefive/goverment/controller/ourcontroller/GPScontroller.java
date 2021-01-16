package com.wefive.goverment.controller.ourcontroller;


import com.wefive.goverment.common.utils.R;
import com.wefive.goverment.entity.DepartmentEntity;
import com.wefive.goverment.service.DepartmentService;
import com.wefive.goverment.service.impl.GpsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("gps")
public class GPScontroller {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    GpsServiceImpl gpsService;
    @Autowired
    DepartmentService departmentService;

    @RequestMapping("/getroutebyip")
    public R getroute(String orignip,String latitude,String longitude)
    {
        R getroutebyip = gpsService.getroutebyip(orignip, latitude, longitude);
        return getroutebyip;

    }
    @RequestMapping("/getroutebylocation")
    public R getroute2(Integer id)
    {
        DepartmentEntity byId = departmentService.getById(id);
        R getroutebyip = gpsService.getroutebylocation(byId.getLongtitude(),byId.getLatitude());
        return getroutebyip;

    }







}
