package com.learn.cabbooking.controllers;

import com.learn.cabbooking.database.RidersManager;
import com.learn.cabbooking.database.TripsManager;
import com.learn.cabbooking.model.Location;
import com.learn.cabbooking.model.Rider;
import com.learn.cabbooking.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RidersController {

    @Autowired
    private RidersManager ridersManager;
    @Autowired
    private TripsManager tripsManager;

    public RidersController(RidersManager ridersManager,TripsManager tripsManager){
        this.ridersManager = ridersManager;
        this.tripsManager = tripsManager;
    }

    @RequestMapping(value = "/register/rider",method = RequestMethod.POST)
    public ResponseEntity registerRider(final String riderId,final String riderName){
        ridersManager.createRider(new Rider(riderId,riderName));
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/book",method = RequestMethod.POST)
    public ResponseEntity book(final String riderId,final Double sourceX,final Double sourceY,final Double destX,final Double destY){
        tripsManager.createTrip(
                ridersManager.getRider(riderId),
                new Location(sourceX,sourceY),
                new Location(destX,destY)
        );
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/book",method = RequestMethod.GET)
    public ResponseEntity fetchHistory(final String riderId){
        List<Trip> trips = tripsManager.tripHistory(ridersManager.getRider(riderId));
        return ResponseEntity.ok("");
    }
}
