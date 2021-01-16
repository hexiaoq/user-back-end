package com.wefive.goverment.entity.gps;

import lombok.Data;

import java.io.Serializable;

@Data
public class transportEntity implements Serializable {
    int count;
    routeEntity route;
}
