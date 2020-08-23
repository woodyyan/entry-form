package com.huawei.refactoring;


import com.huawei.refactoring.form.Address;
import com.huawei.refactoring.form.Itinerary;
import com.huawei.refactoring.form.Name;
import com.huawei.refactoring.form.Passport;
import com.huawei.refactoring.form.ValuableArticle;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;

import static com.huawei.refactoring.TestData.ALL_OK_SUPPLEMENTAL;
import static com.huawei.refactoring.TestData.CHICAGO_NON_BUSINESS_ITINERARY;
import static com.huawei.refactoring.TestData.NOTHING_TO_DECLARATION;
import static com.huawei.refactoring.TestData.NO_VALUABLE_ARTICLE;
import static com.huawei.refactoring.TestData.ZHANG_SAN_PASSPORT;
import static com.huawei.refactoring.TestData.ZHANG_SHISAN_PASSPORT;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class EntryFormTest {

    @Test
    public void smoke_test_happy() {
        EntryForm parent = new EntryForm(ZHANG_SAN_PASSPORT,
            CHICAGO_NON_BUSINESS_ITINERARY,
            NOTHING_TO_DECLARATION,
            NO_VALUABLE_ARTICLE, false);

        EntryForm child = new EntryForm(ZHANG_SHISAN_PASSPORT,
            CHICAGO_NON_BUSINESS_ITINERARY,
            NOTHING_TO_DECLARATION,
            NO_VALUABLE_ARTICLE, false);

        SupplementalInformation childSupplemental = new SupplementalInformation(new HashMap<EntryForm, SupplementalInformation>() {{
            put(parent, ALL_OK_SUPPLEMENTAL);
        }}, false, false, false, false, false, false);

        assertThat(parent.isApproved(ALL_OK_SUPPLEMENTAL), is(true));
        assertThat(child.isApproved(childSupplemental), is(true));
    }

    @Test
    public void smoke_test_sad() {
        EntryForm parent = new EntryForm(ZHANG_SAN_PASSPORT,
            CHICAGO_NON_BUSINESS_ITINERARY,
            NOTHING_TO_DECLARATION,
            new ValuableArticle(true, false, 0.0), false);

        EntryForm child = new EntryForm(ZHANG_SHISAN_PASSPORT,
            CHICAGO_NON_BUSINESS_ITINERARY,
            NOTHING_TO_DECLARATION,
            NO_VALUABLE_ARTICLE, false);

        SupplementalInformation childSupplemental = new SupplementalInformation(new HashMap<EntryForm, SupplementalInformation>() {{
            put(parent, ALL_OK_SUPPLEMENTAL);
        }}, false, false, false, false, false, false);

        try {
            parent.isApproved(ALL_OK_SUPPLEMENTAL);
        } catch (RejectedException e) {
            assertThat(e.getMessage(), is("too much money"));
        }

        try {
            child.isApproved(childSupplemental);
        } catch (RejectedException e) {
            assertThat(e.getMessage(), is("family not allowed"));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_accept_empty_as_address() {
        new EntryForm(ZHANG_SAN_PASSPORT,
            new Itinerary(new Address("", "Chicago", "IL"), 1,
                Collections.singletonList("China"), "CA850", false),
            NOTHING_TO_DECLARATION,
            NO_VALUABLE_ARTICLE, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_accept_empty_as_city() {
        new EntryForm(ZHANG_SAN_PASSPORT,
            new Itinerary(new Address("200 Main Street", "", "IL"), 1,
                Collections.singletonList("China"), "CA850", false),
            NOTHING_TO_DECLARATION,
            NO_VALUABLE_ARTICLE, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_accept_empty_as_state() {
        new EntryForm(ZHANG_SAN_PASSPORT,
            new Itinerary(new Address("200 Main Street", "Chicago", ""), 1,
                Collections.singletonList("China"), "CA850", false),
            NOTHING_TO_DECLARATION,
            NO_VALUABLE_ARTICLE, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_accept_empty_as_flight_number() {
        new EntryForm(ZHANG_SAN_PASSPORT,
            new Itinerary(new Address("200 Main Street", "Chicago", "IL"), 1,
                Collections.singletonList("China"), "", false),
            NOTHING_TO_DECLARATION,
            NO_VALUABLE_ARTICLE, false);
    }

    @Test
    public void should_accept_empty_as_countries_visited() {
        EntryForm form = new EntryForm(ZHANG_SAN_PASSPORT,
            new Itinerary(new Address("200 Main Street", "Chicago", "IL"), 1,
                null, "CA850", false),
            NOTHING_TO_DECLARATION,
            NO_VALUABLE_ARTICLE, false);

        assertThat(form.getItinerary().getCountriesVisited().size(), is(0));
    }
}