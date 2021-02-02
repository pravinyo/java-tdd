package com.pluralsight.tddjunit5.airport;

import java.util.*;

public abstract class Flight {

	private final String id;
	Set<Passenger> passengersList = new HashSet<>();

	public Flight(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public Set<Passenger> getPassengersSet() {
		return Collections.unmodifiableSet(passengersList);
	}

	public abstract boolean addPassenger(Passenger passenger);

	public abstract boolean removePassenger(Passenger passenger);

}
