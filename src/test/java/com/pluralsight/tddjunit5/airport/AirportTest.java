package com.pluralsight.tddjunit5.airport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AirportTest {

    @DisplayName("Given there is economy flight")
    @Nested
    class EconomyFlightTest {
        private Flight economyFlight;

        @BeforeEach
        void setup() {
            economyFlight = new EconomyFlight("1");
        }

        @Test
        public void testEconomyFlightUsualPassenger() {
            Passenger mike = new Passenger("Mike", false);

            assertEquals("1", economyFlight.getId());
            assertTrue(economyFlight.addPassenger(mike));
            assertEquals(1, economyFlight.getPassengersList().size());
            assertEquals("Mike", economyFlight.getPassengersList().get(0).getName());

            assertTrue(economyFlight.removePassenger(mike));
            assertEquals(0, economyFlight.getPassengersList().size());
        }

        @Test
        public void testEconomyFlightVipPassenger() {
            Passenger john = new Passenger("John", true);

            assertEquals("1", economyFlight.getId());
            assertTrue(economyFlight.addPassenger(john));
            assertEquals(1, economyFlight.getPassengersList().size());
            assertEquals("John", economyFlight.getPassengersList().get(0).getName());

            assertFalse(economyFlight.removePassenger(john));
            assertEquals(1, economyFlight.getPassengersList().size());
        }
    }

    @DisplayName("Given there is business flight")
    @Nested
    class BusinessFlightTest {
        Flight businessFlight;

        @BeforeEach
        void setup() {
           businessFlight = new BusinessFlight("2");
        }

        @Test
        public void testBusinessFlightUsualPassenger() {
            Passenger mike = new Passenger("Mike", false);

            assertFalse(businessFlight.addPassenger(mike));
            assertEquals(0, businessFlight.getPassengersList().size());
            assertFalse(businessFlight.removePassenger(mike));
            assertEquals(0, businessFlight.getPassengersList().size());
        }

        @Test
        public void testBusinessFlightVipPassenger() {
            Passenger john = new Passenger("John", true);

            assertTrue(businessFlight.addPassenger(john));
            assertEquals(1, businessFlight.getPassengersList().size());
            assertFalse(businessFlight.removePassenger(john));
            assertEquals(1, businessFlight.getPassengersList().size());
        }
    }

    @DisplayName("Given there is premium flight")
    @Nested
    class PremiumFlightTest {
        private Flight premiumFlight;
        private Passenger mike;
        private Passenger john;

        @BeforeEach
        void setup(){
            premiumFlight = new PremiumFlight("3");
            mike = new Passenger("Mike", false);
            john = new Passenger("John", true);
        }

        @DisplayName("When we have a usual passenger")
        @Nested
        class UsualPassenger {

            @Test
            @DisplayName("Then you can't add or remove him from flight")
            public void testPremiumFlightUsualPassenger() {
                assertAll("Verify All condition for usual passenger and premium flight",
                        () -> assertFalse(premiumFlight.addPassenger(mike)),
                        () -> assertEquals(0, premiumFlight.getPassengersList().size()),
                        () -> assertFalse(premiumFlight.removePassenger(mike)),
                        () -> assertEquals(0, premiumFlight.getPassengersList().size())
                );
            }
        }

        @DisplayName("When we have a VIP passenger")
        @Nested
        class VipPassenger {

            @Test
            @DisplayName("Then you can add or remove him from flight")
            public void testPremiumFlightVipPassenger() {
                assertAll("Verify All condition for VIP passenger and premium flight",
                        () -> assertTrue(premiumFlight.addPassenger(john)),
                        () -> assertEquals(1, premiumFlight.getPassengersList().size()),
                        () -> assertTrue(premiumFlight.removePassenger(john)),
                        () -> assertEquals(0, premiumFlight.getPassengersList().size())
                );
            }
        }

    }
}
