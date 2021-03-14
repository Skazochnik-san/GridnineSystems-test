package com.gridnine.testing;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


public class SegmentFilterImplTest{

    private FlightBuilder flightBuilder = new FlightBuilder();
    private List<Flight> flightList = flightBuilder.createFlights();
    private SegmentFilterImpl segmentFilter = new SegmentFilterImpl();
    private LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);


    @Test
    public void testShowAllSegments() {
        Assert.assertNotNull(flightList);
    }

    @Test
    public void testGetSetArrivalBeforeDepartureDate() {
        List<Flight> arrivalBeforeDepartureDateFligths = Arrays.asList(
                //A flight that departs before it arrives
                flightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.minusHours(6)),

                //A flight that departs before it arrives
                flightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.minusHours(7)),

                //A flight that departs before it arrives
                flightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.minusHours(8)));

        Assert.assertEquals(segmentFilter.getSetArrivalBeforeDepartureDate(arrivalBeforeDepartureDateFligths).size()
                ,3 );

        Assert.assertEquals(segmentFilter.getSetArrivalBeforeDepartureDate(flightList).size(),1 );
    }

    @Test
    public void testGetSetDepartureUntilTheCurrentTime(){
        List<Flight> departureUntilTheCurrentTimeFligths = Arrays.asList(
                //A normal flight with two hour duration
                flightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)),

                //A normal multi segment flight
                flightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5)),

                //A flight that departs before it arrives
                flightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.minusHours(6)),

                //A normal flight with two hour duration
                flightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(3)),

                //A normal multi segment flight
                flightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(3),
                        threeDaysFromNow.plusHours(4), threeDaysFromNow.plusHours(5)),

                //A flight that departs before it arrives
                flightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.minusHours(8)),

                //A flight with more than two hours ground time
                flightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6)),

                //Another flight with more than two hours ground time
                flightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4),
                        threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(7))
        );

        Assert.assertEquals(segmentFilter.getSetDepartureUntilTheCurrentTime(departureUntilTheCurrentTimeFligths).size()
                , 8);

        Assert.assertEquals(segmentFilter.getSetDepartureUntilTheCurrentTime(flightList).size(), 5 );
    }

    @Test
    public void testGetSetTimeOnEarthExceededByTwoHours(){
        List<Flight> timeOnEarthExceededByTwoHoursFligths = Arrays.asList(
                flightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6)),
                //Another flight with more than two hours ground time
                flightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4),
                        threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(7))
                ,
                //A flight with more than two hours ground time
                flightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6)),
                //Another flight with more than two hours ground time
                flightBuilder.createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4),
                        threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(7)));

        Assert.assertEquals(segmentFilter.getSetTimeOnEarthExceededByTwoHours(timeOnEarthExceededByTwoHoursFligths).size()
                , 4 );

        Assert.assertEquals(segmentFilter.getSetTimeOnEarthExceededByTwoHours(flightList).size(), 2 );
    }

}