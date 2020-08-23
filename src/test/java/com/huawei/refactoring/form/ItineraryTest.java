package com.huawei.refactoring.form;

import org.junit.Test;

import java.util.Collections;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ItineraryTest {
    @Test(expected = IllegalArgumentException.class)
    public void should_not_accept_empty_as_address() {
        new Itinerary(new Address("", "Chicago", "IL"), 1,
            Collections.singletonList("China"), "CA850", false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_accept_empty_as_city() {
        new Itinerary(new Address("200 Main Street", "", "IL"), 1,
            Collections.singletonList("China"), "CA850", false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_accept_empty_as_state() {
        new Itinerary(new Address("200 Main Street", "Chicago", ""), 1,
            Collections.singletonList("China"), "CA850", false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_accept_empty_as_flight_number() {
        new Itinerary(new Address("200 Main Street", "Chicago", "IL"), 1,
            Collections.singletonList("China"), "", false);
    }

    @Test
    public void should_accept_empty_as_countries_visited() {
        Itinerary itinerary = new Itinerary(new Address("200 Main Street", "Chicago", "IL"), 1,
            null, "CA850", false);

        assertThat(itinerary.getCountriesVisited().size(), is(0));
    }
}
