package com.wefive.goverment.entity.gps;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

@Data
public class transitsEntity implements Serializable {
    float cost;
   int Duration;
   int walk_distance;
   int distance;
   String time;
   String distances;
   ArrayList<segmentEntity> segments;
}
