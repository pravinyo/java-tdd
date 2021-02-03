package com.pluralsight.tddjunit5.milage;

import com.pluralsight.tddjunit5.airport.Flight;
import com.pluralsight.tddjunit5.airport.Passenger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MileageTest {

    private Mileage mileage;

    @BeforeAll
    void setupBeforeAll() {
        mileage = new Mileage();
    }

    @ParameterizedTest
    @Disabled
    @ValueSource(strings = {
            "1; e; Mike; false; 349", "2; b; John; true; 278",
            "3; e; Mike; false; 319", "4; p; John; true; 817",
            "5; e; Mike; false; 623", "6; e; John; true; 978"
    })
    void checkGivenPoints(@ConvertWith(FlightArgumentConverter.class) Flight flight) {
        flight.getPassengersSet()
                .forEach( passenger -> mileage.addMileage(passenger,flight.getDistance()));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/flight_information.csv")
    void checkGivenPointsWithCSVInput(@ConvertWith(FlightArgumentConverter.class) Flight flight) {
        flight.getPassengersSet()
                .forEach( passenger -> mileage.addMileage(passenger,flight.getDistance()));
    }

    @AfterAll
    void afterAll() {
        mileage.calculateGivenPoints();
        assertEquals(64, mileage.getPassengerMileageMap().get(new Passenger("Mike", false)).intValue());
        assertEquals(207, mileage.getPassengerMileageMap().get(new Passenger("John", true)).intValue());
        System.out.println(mileage.getPassengerMileageMap());
    }
}