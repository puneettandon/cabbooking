package com.learn.cabbooking.database;

import com.learn.cabbooking.exceptions.NoCabsAvailableException;
import com.learn.cabbooking.exceptions.TripNotFoundException;
import com.learn.cabbooking.model.Cab;
import com.learn.cabbooking.model.Location;
import com.learn.cabbooking.model.Rider;
import com.learn.cabbooking.model.Trip;
import com.learn.cabbooking.strategies.CabMatchingStrategy;
import com.learn.cabbooking.strategies.PricingStrategy;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class TripsManager {

    public static final Double MAX_ALLOWED_TRIP_MATCHING_DISTANCE = 10.0;

    private Map<String, List<Trip>> trips = new HashMap<>();

    private CabsManager cabsManager;
    private RidersManager ridersManager;
    private CabMatchingStrategy cabMatchingStrategy;
    private PricingStrategy pricingStrategy;

    public  TripsManager(CabsManager cabsManager, RidersManager ridersManager,
                         CabMatchingStrategy cabMatchingStrategy , PricingStrategy pricingStrategy){
        this.cabsManager = cabsManager;
        this.ridersManager = ridersManager;
        this.cabMatchingStrategy = cabMatchingStrategy;
        this.pricingStrategy = pricingStrategy;
    }

    public void createTrip(@NonNull final Rider rider ,
                           @NonNull final Location fromPoint,
                           @NonNull final Location toPoint){
        final List<Cab> closeByCabs = cabsManager.getCabs(fromPoint, MAX_ALLOWED_TRIP_MATCHING_DISTANCE);

        final Optional<Cab> selectedCabOpt = cabMatchingStrategy.matchCabToRider(rider,closeByCabs,fromPoint,toPoint);
        if(!selectedCabOpt.isPresent()){
            throw  new NoCabsAvailableException();
        }

        final Cab selectedCab = selectedCabOpt.get();
        final Double price = pricingStrategy.findPrice(fromPoint,toPoint);
        final Trip newTrip = new Trip(rider,selectedCab,price,fromPoint,toPoint) ;
        if(!trips.containsKey(rider.getId())){
            trips.put(rider.getId(),new ArrayList<>());
        }

        trips.get(rider.getId()).add(newTrip);
        selectedCab.setCurrentTrip(newTrip);
    }

    public List<Trip> tripHistory(@NonNull final Rider rider){ return  trips.get(rider.getId()); }

    public void endTrip(@NonNull final Cab cab){
        if(cab.getCurrentTrip() == null){
            throw new TripNotFoundException();
        }
        cab.getCurrentTrip().endTrip();
        cab.setCurrentTrip(null);
    }
}
