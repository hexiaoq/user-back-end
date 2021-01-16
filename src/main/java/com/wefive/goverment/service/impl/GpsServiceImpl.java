package com.wefive.goverment.service.impl;

import com.alibaba.fastjson.JSON;
import com.wefive.goverment.common.utils.R;
import com.wefive.goverment.entity.DepartmentEntity;
import com.wefive.goverment.entity.gps.ipEntity;
import com.wefive.goverment.entity.gps.routeEntity;
import com.wefive.goverment.entity.gps.transitsEntity;
import com.wefive.goverment.entity.gps.transportEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service("Gpsservice")
public class GpsServiceImpl {
    @Autowired
    RestTemplate restTemplate;
    public String[]  getlocation(String ip)
    {

//        JSON forObject = restTemplate.getForObject("http://restapi.amap.com/v3/ip?key=4b989a60585420471b25cec7dcccb394",JSON.class);
        ipEntity forEntity = restTemplate.getForObject("http://restapi.amap.com/v3/ip?key=4b989a60585420471b25cec7dcccb394&ip="+ip, ipEntity.class);
        System.out.println(forEntity);

        String[] split = forEntity.getRectangle().split(";");
        String[] split1 = split[0].split(",");
        return split1;

    }
    public R getroutebylocation(String orignla,String originlong, String latitude, String longitude)
    {
        String s = "https://restapi.amap.com/v3/direction/transit/integrated?key=4b989a60585420471b25cec7dcccb394&origin=" +
                orignla + "," + originlong + "&destination=" + latitude + "," + longitude + "&city=420100";

        JSON forEntity = restTemplate.getForObject(s, JSON.class);
//        System.out.println(forEntity);
//        System.out.println("------分割线-------");
        transportEntity transportEntity = forEntity.toJavaObject(transportEntity.class);
//        JsonsRootBean jsonsRootBean = forEntity.toJavaObject(JsonsRootBean.class);
//        System.out.println(transportEntity);
        routeEntity route = transportEntity.getRoute();
        int taxi_cost = route.getTaxi_cost();
        ArrayList<transitsEntity> transits = route.getTransits();
        transitsEntity costmin = Collections.max(transits, new Comparator<transitsEntity>() {
            @Override
            public int compare(transitsEntity o1, transitsEntity o2) {
                return (int) ((int) o2.getCost() - o1.getCost());
            }
        });
        transitsEntity timemin = Collections.max(transits, new Comparator<transitsEntity>() {
            @Override
            public int compare(transitsEntity o1, transitsEntity o2) {
                return o2.getDuration() - o1.getDuration();
            }
        });
        System.out.println(costmin);
        System.out.println(timemin);




        return new R().ok().put("打车花费",taxi_cost).put("最小花费路线",costmin).put("最短时间路线",timemin);




    }
    public R getroutebylocation(String latitude, String longitude)
    {String orignla="114.295006" ;
            String originlong="30.659858";
        String s = "https://restapi.amap.com/v3/direction/transit/integrated?key=4b989a60585420471b25cec7dcccb394&origin=" +
                orignla + "," + originlong + "&destination=" + latitude + "," + longitude + "&city=420100";

        JSON forEntity = restTemplate.getForObject(s, JSON.class);
//        System.out.println(forEntity);
//        System.out.println("------分割线-------");
        transportEntity transportEntity = forEntity.toJavaObject(transportEntity.class);
//        JsonsRootBean jsonsRootBean = forEntity.toJavaObject(JsonsRootBean.class);
//        System.out.println(transportEntity);
        routeEntity route = transportEntity.getRoute();
        int taxi_cost = route.getTaxi_cost();
        ArrayList<transitsEntity> transits = route.getTransits();
        transitsEntity costmin = Collections.max(transits, new Comparator<transitsEntity>() {
            @Override
            public int compare(transitsEntity o1, transitsEntity o2) {
                return (int) ((int) o2.getCost() - o1.getCost());
            }
        });
        transitsEntity timemin = Collections.max(transits, new Comparator<transitsEntity>() {
            @Override
            public int compare(transitsEntity o1, transitsEntity o2) {
                return o2.getDuration() - o1.getDuration();
            }
        });
        int distance = costmin.getDistance();
        int distance1 = timemin.getDistance();

        int duration = costmin.getDuration();
        int duration1 = timemin.getDuration();
        costmin.setTime(duration/3600+"h"+duration%3600/60+"min");
        timemin.setTime(duration1/3600+"h"+duration1%3600/60+"min");
        costmin.setDistances(distance/1000+"公里"+distance%1000+"米");
        timemin.setDistances(distance1/1000+"公里"+distance1%1000+"米");





        return new R().ok().put("打车花费",taxi_cost).put("最小花费路线",costmin).put("最短时间路线",timemin).put("mintime",(long)timemin.getDuration());




    }
    public R getroutebyip(String orignip, String latitude, String longitude)
    {
        String[] getlocation = getlocation(orignip);
        String weidu= getlocation[0];
        String jingdu=getlocation[1];
        String s = "https://restapi.amap.com/v3/direction/transit/integrated?key=4b989a60585420471b25cec7dcccb394&origin=" +
                weidu + "," + jingdu + "&destination=" + latitude + "," + longitude + "&city=420100";
//        String s="https://restapi.amap.com/v3/direction/transit/integrated?origin=114.317261,30.52809&destination=114.343925,30.51379&key=4b989a60585420471b25cec7dcccb394&city=420100";

//        System.out.println(s);

//        transportEntity forObject = restTemplate.getForObject("https://restapi.amap.com/v3/direction/transit/integrated?key=4b989a60585420471b25cec7dcccb394&orgin=" +
//                weidu + "," + jingdu + "&destination=" + latitude + "," + longitude+"&city=420100", transportEntity.class);
        JSON forEntity = restTemplate.getForObject(s, JSON.class);
//        System.out.println(forEntity);
//        System.out.println("------分割线-------");
        transportEntity transportEntity = forEntity.toJavaObject(transportEntity.class);
//        JsonsRootBean jsonsRootBean = forEntity.toJavaObject(JsonsRootBean.class);
//        System.out.println(transportEntity);
        routeEntity route = transportEntity.getRoute();
        int taxi_cost = route.getTaxi_cost();
        ArrayList<transitsEntity> transits = route.getTransits();
        transitsEntity costmin = Collections.max(transits, new Comparator<transitsEntity>() {
            @Override
            public int compare(transitsEntity o1, transitsEntity o2) {
                return (int) ((int) o2.getCost() - o1.getCost());
            }
        });
        transitsEntity timemin = Collections.max(transits, new Comparator<transitsEntity>() {
            @Override
            public int compare(transitsEntity o1, transitsEntity o2) {
                return o2.getDuration() - o1.getDuration();
            }
        });
        System.out.println(costmin);
        System.out.println(timemin);






        return new R().ok().put("打车花费",taxi_cost).put("最小花费路线",costmin).put("最短时间路线",timemin);



    }
    public ArrayList<DepartmentEntity> getdepbyduration(List<Map<String, Object>> json)
    {//114.295006 //30.659858
        ArrayList<DepartmentEntity> departmentEntities = new ArrayList<>();
        for (int i = 0; i < json.size(); i++) {
            Map<String, Object> map = json.get(i);
         departmentEntities.add(JSON.parseObject(JSON.toJSONString(map),DepartmentEntity.class));
//            R getroutebylocation = getroutebylocation((String)latitude, (String)longtitude);
//            transitsEntity timemin= (transitsEntity) getroutebylocation.get("最小花费路线");
//            timemin

        }
        DepartmentEntity departmentEntity = departmentEntities.get(0);
        long mintime1 = (long)getroutebylocation( departmentEntity.getLongtitude(),departmentEntity.getLatitude()).get("mintime");
        departmentEntity.setMintime(mintime1/3600+"h"+mintime1%3600/60+"min");


        Collections.sort(departmentEntities, new Comparator<DepartmentEntity>() {
            @Override
            public int compare(DepartmentEntity o1, DepartmentEntity o2) {
                long mintime1 = (long)getroutebylocation( o1.getLongtitude(),o1.getLatitude()).get("mintime");
                long mintime2 = (long)getroutebylocation(o2.getLongtitude(),o2.getLatitude()).get("mintime");
                o1.setMintime(mintime1/3600+"h"+mintime1%3600/60+"min");
                o2.setMintime(mintime2/3600+"h"+mintime2%3600/60+"min");
                return (int) ((int)mintime1-mintime2);

            }
        });
        return departmentEntities;
    }
}
