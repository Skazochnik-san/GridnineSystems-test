package com.gridnine.testing;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

public class FlightBuilderTest {

    private FlightBuilder flightBuilder = new FlightBuilder();
    private List<Flight> flightList = flightBuilder.createFlights();


    @Test
    public void testFlightNotNull(){
        Assert.assertNotNull(flightList);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateFlight(){
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
        Flight flight = flightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2));
        Flight flightTwo = flightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2));
        Assert.assertNotNull(flight);
        Assert.assertNotNull(flight.getSegments());
        Assert.assertNotNull(flight.getId());

        Assert.assertEquals((long) flight.getId(), 7);
        Assert.assertEquals((long) flightTwo.getId(), 8);

        Assert.assertNotEquals(flight.getId(), flightTwo.getId());

        flightBuilder.createFlight(threeDaysFromNow);
    }
}