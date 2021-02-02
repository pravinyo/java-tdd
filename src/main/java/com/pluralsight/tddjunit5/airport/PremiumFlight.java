package com.pluralsight.tddjunit5.airport;

public class PremiumFlight extends Flight{

    public PremiumFlight(String id) {
        super(id);
    }

    @Override
    public boolean addPassenger(Passenger passenger) {
        if (passenger.isVip()) {
            passengersList.add(passenger);
            return true;
        }
        return false;
    }

    @Override
    public boolean removePassenger(Passenger passenger) {
        if (passenger.isVip()) {
            passengersList.remove(passenger);
            return true;
        }
        return false;
    }
}
