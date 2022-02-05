package com.learn.cabbooking.strategies;

import com.learn.cabbooking.model.Location;

public interface PricingStrategy {

    Double findPrice(Location fromPoint, Location toPoint);
}
