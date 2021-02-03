package com.pluralsight.tddjunit5.milage;

import com.pluralsight.tddjunit5.airport.Passenger;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Mileage {
    public static final int USUAL_PASSENGER_FACTOR = 20;
    public static final int VIP_PASSENGER_FACTOR = 10;

    private final Map<Passenger, Integer> passengerMileageMap = new HashMap<>();
    private final Map<Passenger, Integer> passengerPointsMap = new HashMap<>();

    public void addMileage(Passenger p, int miles) {
        if (passengerMileageMap.containsKey(p)){
            passengerMileageMap.put(p, passengerMileageMap.get(p) + miles);
        }else {
            passengerMileageMap.put(p, miles);
        }
    }

    public void calculateGivenPoints() {
        passengerMileageMap.keySet()
                .forEach( passenger -> {
                    if (passenger.isVip()){
                        passengerMileageMap.put(passenger, passengerMileageMap.get(passenger)/VIP_PASSENGER_FACTOR);
                    }else {
                        passengerMileageMap.put(passenger, passengerMileageMap.get(passenger)/USUAL_PASSENGER_FACTOR);
                    }
                });
    }

    public Map<Passenger, Integer> getPassengerMileageMap() {
        return Collections.unmodifiableMap(passengerMileageMap);
    }
}
