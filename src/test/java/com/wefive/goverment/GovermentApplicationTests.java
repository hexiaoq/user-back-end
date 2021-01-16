package java.com.wefive.goverment;

import com.alibaba.fastjson.JSON;
import com.wefive.goverment.entity.gps.ipEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


class GovermentApplicationTests {
    @Autowired
    RestTemplate restTemplate;

    @Test
    void contextLoads() {

        ipEntity forEntity = restTemplate.getForObject("http://restapi.amap.com/v3/ip?key=4b989a60585420471b25cec7dcccb394&ip=202.114.88.20", ipEntity.class);
            String[] split = forEntity.getRectangle().split(";");
            String[] split1 = split[0].split(",");

        System.out.println(split1[0].toString());

    }
    @Test
    void test(){
        ResponseEntity<JSON> forEntity = restTemplate.getForEntity("https://restapi.amap.com/v3/direction/transit/integrated?key=4b989a60585420471b25cec7dcccb394&origin=114.343925,30.51379&destination=114.317261,30.52809&city=420100", JSON.class);
        System.out.println(forEntity);


    }

}

