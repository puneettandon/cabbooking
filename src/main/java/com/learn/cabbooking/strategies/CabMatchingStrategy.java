package com.learn.cabbooking.strategies;

import com.learn.cabbooking.model.Cab;
import com.learn.cabbooking.model.Location;
import com.learn.cabbooking.model.Rider;

import java.util.List;
import java.util.Optional;

public interface CabMatchingStrategy {

    Optional<Cab> matchCabToRider(Rider rider, List<Cab> candidateCabs, Location fromPoint,Location toPoint);
}
