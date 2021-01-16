package com.wefive.goverment.entity.gps;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;

@Data
public class busline implements Serializable {
    String name;
   int  via_num;
   HashMap<String,String> departure_stop;
    HashMap<String,String> arrival_stop;


}
