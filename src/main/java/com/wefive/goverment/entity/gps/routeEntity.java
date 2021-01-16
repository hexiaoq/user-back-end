package com.wefive.goverment.entity.gps;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

@Data
public class routeEntity implements Serializable {
    int distance;
    int taxi_cost;
    private ArrayList<transitsEntity> transits;

}
