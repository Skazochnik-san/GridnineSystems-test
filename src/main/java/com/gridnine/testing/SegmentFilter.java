package com.gridnine.testing;

import java.util.List;
import java.util.Set;

public interface SegmentFilter {

    void showAllSegments(List<Flight> flights);
    Set<Flight> getSetDepartureUntilTheCurrentTime(List<Flight> flights);
    Set<Flight> getSetArrivalBeforeDepartureDate(List<Flight> flights);
    Set<Flight> getSetTimeOnEarthExceededByTwoHours(List<Flight> flights);
}
