package com.learn.cabbooking.strategies;

import com.learn.cabbooking.model.Cab;
import com.learn.cabbooking.model.Location;
import com.learn.cabbooking.model.Rider;
import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DefaultCabMatchingStrategy  implements  CabMatchingStrategy{

    @Override
    public Optional<Cab> matchCabToRider(@NonNull final Rider rider,
                                         @NonNull final List<Cab> candidateCabs,
                                         @NonNull final Location fromPoint,
                                         @NonNull final Location toPoint) {
        return candidateCabs.stream().filter(cab -> cab.getCurrentTrip() == null).findAny();
    }


}
