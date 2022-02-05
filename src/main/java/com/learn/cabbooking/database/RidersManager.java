package com.learn.cabbooking.database;

import com.learn.cabbooking.exceptions.RiderAlreadyExistException;
import com.learn.cabbooking.exceptions.RiderNotFoundException;
import com.learn.cabbooking.model.Rider;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RidersManager {

    Map<String, Rider> riders = new HashMap<>();

    public void createRider(@NonNull final Rider newRider){
        if(riders.containsKey(newRider.getId())){
            throw new RiderAlreadyExistException();
        }
        riders.put(newRider.getId(),newRider);
    }

    public Rider getRider(@NonNull final String riderId){
        if(!riders.containsKey(riderId)){
            throw new RiderNotFoundException();
        }
        return riders.get(riderId);
    }
}
