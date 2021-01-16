package com.wefive.goverment.entity.gps;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
@Data
public class bus implements Serializable {
    ArrayList<busline> buslines;
}
