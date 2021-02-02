package com.pluralsight.tddjunit5.airport;

import org.junit.jupiter.api.*;

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

        @DisplayName("Then you can add or remove him from flight")
        @Test
        public void testEconomyFlightUsualPassenger() {
            Passenger mike = new Passenger("Mike", false);

            assertEquals("1", economyFlight.getId());
            assertTrue(economyFlight.addPassenger(mike));
            assertEquals(1, economyFlight.getPassengersSet().size());
            assertTrue(economyFlight.getPassengersSet().contains(mike));

            assertTrue(economyFlight.removePassenger(mike));
            assertEquals(0, economyFlight.getPassengersSet().size());
        }

        @DisplayName("Then you can't add him more than once to flight")
        @RepeatedTest(5)
        public void testEconomyFlightUsualPassengerAddOnlyOnce(RepetitionInfo repetitionInfo) {
            Passenger mike = new Passenger("Mike", false);

            for (int i = 0; i < repetitionInfo.getCurrentRepetition(); i++) {
                economyFlight.addPassenger(mike);
            }

            assertEquals(1, economyFlight.getPassengersSet().size());
            assertTrue(economyFlight.getPassengersSet().contains(mike));
        }

        @Test
        public void testEconomyFlightVipPassenger() {
            Passenger john = new Passenger("John", true);

            assertEquals("1", economyFlight.getId());
            assertTrue(economyFlight.addPassenger(john));
            assertEquals(1, economyFlight.getPassengersSet().size());
            assertTrue(economyFlight.getPassengersSet().contains(john));

            assertFalse(economyFlight.removePassenger(john));
            assertEquals(1, economyFlight.getPassengersSet().size());
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
            assertEquals(0, businessFlight.getPassengersSet().size());
            assertFalse(businessFlight.removePassenger(mike));
            assertEquals(0, businessFlight.getPassengersSet().size());
        }

        @Test
        public void testBusinessFlightVipPassenger() {
            Passenger john = new Passenger("John", true);

            assertTrue(businessFlight.addPassenger(john));
            assertEquals(1, businessFlight.getPassengersSet().size());
            assertFalse(businessFlight.removePassenger(john));
            assertEquals(1, businessFlight.getPassengersSet().size());
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
                        () -> assertEquals(0, premiumFlight.getPassengersSet().size()),
                        () -> assertFalse(premiumFlight.removePassenger(mike)),
                        () -> assertEquals(0, premiumFlight.getPassengersSet().size())
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
                        () -> assertEquals(1, premiumFlight.getPassengersSet().size()),
                        () -> assertTrue(premiumFlight.removePassenger(john)),
                        () -> assertEquals(0, premiumFlight.getPassengersSet().size())
                );
            }
        }

    }
}
