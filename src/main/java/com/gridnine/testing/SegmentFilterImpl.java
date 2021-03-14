package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.time.temporal.ChronoUnit;

public class SegmentFilterImpl implements SegmentFilter {

    @Override
    public void showAllSegments(List<Flight> flights) {
        for (Flight flight : flights ) {
            System.out.println("Flight number - " + flight.getId());
            for(int i = 0; i < flight.getSegments().size(); i++) {
                System.out.println(flight.getSegments().get(i) + " Segment number is " + flight.getSegments().get(i).getId());
            }
        }
    }

    @Override
    public Set<Flight> getSetArrivalBeforeDepartureDate(List<Flight> flights) {

        Set<Flight> resultSet = new HashSet<>();
        List<Segment> split = new ArrayList<>();
        for (Flight flight : flights ) {
            split.addAll(flight.getSegments());

            while (split.size() > 0) {
                LocalDateTime departureTime = split.get(0).getDepartureDate();
                LocalDateTime arrivalTime = split.remove(0).getArrivalDate();
                if(arrivalTime.isBefore(departureTime)) {
                    showRightFLight(flight, departureTime, arrivalTime);
                    resultSet.add(flight);
                }
            }

        }
        return resultSet;
    }

    @Override
    public Set<Flight> getSetDepartureUntilTheCurrentTime(List<Flight> flights) {
        LocalDateTime timeNow = LocalDateTime.now();
        Set<Flight> resultSet = new HashSet<>();
        List<Segment> split = new ArrayList<>();
        for (Flight flight : flights ) {
            split.addAll(flight.getSegments());

            while (split.size() > 0) {
                LocalDateTime departureTime = (split.get(0).getDepartureDate());
                LocalDateTime arrivalTime = (split.remove(0).getArrivalDate());
                if(departureTime.isAfter(timeNow)) {
                    showRightFLight(flight, departureTime, arrivalTime);
                    resultSet.add(flight);
                }
            }

        }
        return resultSet;
    }

    @Override
    public Set<Flight> getSetTimeOnEarthExceededByTwoHours(List<Flight> flights) {
        Set<Flight> resultSet = new HashSet<>();

        for (Flight flight : flights) {
            long groundedTime = 0;
            List<Segment> split = new ArrayList<>();

            split.addAll(flight.getSegments());

            if (split.size() >= 2) {
                for (int i = 0; i <= split.size() - 1; i++) {
                    if (split.size() - 1 == i) {
                        break;
                    }

                    LocalDateTime arrivalTime = split.get(i).getArrivalDate();
                    LocalDateTime departureTime = split.get(i + 1).getDepartureDate();

                    groundedTime += ChronoUnit.HOURS.between(arrivalTime, departureTime);

                    if (groundedTime >= 3) {
                        showTransfer(flight, arrivalTime, departureTime);
                        resultSet.add(flight);
                    }
                }
            }
        }

        return resultSet;
    }

    private void showRightFLight(Flight flight, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        DateTimeFormatter dateTimeFormatter = getDateFormatter();
        System.out.println( "Flight number - " + flight.getId()  + "\n" + "Departure time: " + dateTimeFormatter.format(departureTime) + "\n" + "Arrival time: " + dateTimeFormatter.format(arrivalTime) );
        System.out.println("---------------------------------------------------------------------------");

    }

    private void showTransfer(Flight flight, LocalDateTime arrivalTime, LocalDateTime departureTime) {
        DateTimeFormatter dateTimeFormatter = getDateFormatter();
        System.out.println( "Flight number - " + flight.getId()  + "\n" + "Arrival time: " + dateTimeFormatter.format(arrivalTime) + "\n" + "Departure time: " + dateTimeFormatter.format(departureTime)  );
        System.out.println("---------------------------------------------------------------------------");

    }

    private DateTimeFormatter getDateFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }
}
