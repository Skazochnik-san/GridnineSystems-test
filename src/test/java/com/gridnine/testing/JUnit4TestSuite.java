package com.gridnine.testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@Suite.SuiteClasses( { FlightBuilderTest.class, SegmentFilterImplTest.class })
@RunWith(Suite.class)
public class JUnit4TestSuite {
}