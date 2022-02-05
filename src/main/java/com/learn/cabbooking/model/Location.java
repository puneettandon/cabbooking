package com.learn.cabbooking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class Location {

    private Double x;
    private Double y;

    public Double distance(Location location){
        return Math.sqrt(Math.pow(this.x - location.x,2) + Math.pow(this.y - location.y,2));
    }
}
