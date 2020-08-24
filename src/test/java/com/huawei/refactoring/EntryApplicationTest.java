package com.huawei.refactoring;


import com.huawei.refactoring.form.Address;
import com.huawei.refactoring.form.Itinerary;
import com.huawei.refactoring.form.ValuableArticle;
import org.junit.Test;

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

public class EntryApplicationTest {

    @Test
    public void smoke_test_happy() {
        EntryApplication parent = new EntryApplication(ZHANG_SAN_PASSPORT,
            CHICAGO_NON_BUSINESS_ITINERARY,
            NOTHING_TO_DECLARATION,
            NO_VALUABLE_ARTICLE, false);

        EntryApplication child = new EntryApplication(ZHANG_SHISAN_PASSPORT,
            CHICAGO_NON_BUSINESS_ITINERARY,
            NOTHING_TO_DECLARATION,
            NO_VALUABLE_ARTICLE, false);

        SupplementalInformation childSupplemental = new SupplementalInformation(new HashMap<EntryApplication, SupplementalInformation>() {{
            put(parent, ALL_OK_SUPPLEMENTAL);
        }}, false, false, false, false, false, false);

        assertThat(parent.isApproved(ALL_OK_SUPPLEMENTAL), is(true));
        assertThat(child.isApproved(childSupplemental), is(true));
    }

    @Test
    public void smoke_test_sad() {
        EntryApplication parent = new EntryApplication(ZHANG_SAN_PASSPORT,
            CHICAGO_NON_BUSINESS_ITINERARY,
            NOTHING_TO_DECLARATION,
            new ValuableArticle(true, false, 0.0), false);

        EntryApplication child = new EntryApplication(ZHANG_SHISAN_PASSPORT,
            CHICAGO_NON_BUSINESS_ITINERARY,
            NOTHING_TO_DECLARATION,
            NO_VALUABLE_ARTICLE, false);

        SupplementalInformation childSupplemental = new SupplementalInformation(new HashMap<EntryApplication, SupplementalInformation>() {{
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

    @Test(expected = RejectedException.class)
    public void juvenile_should_travel_with_adult() {
        EntryApplication juvenile = new EntryApplication(ZHANG_SHISAN_PASSPORT,
            CHICAGO_NON_BUSINESS_ITINERARY,
            NOTHING_TO_DECLARATION,
            NO_VALUABLE_ARTICLE, false);
        juvenile.isApproved(ALL_OK_SUPPLEMENTAL);
    }

    @Test(expected = RejectedException.class)
    public void juvenile_should_stay_at_same_place_with_adult() {
        EntryApplication juvenile = new EntryApplication(ZHANG_SHISAN_PASSPORT,
            CHICAGO_NON_BUSINESS_ITINERARY,
            NOTHING_TO_DECLARATION,
            NO_VALUABLE_ARTICLE, false);
        EntryApplication adultStayAtNewYork = new EntryApplication(ZHANG_SHISAN_PASSPORT,
            new Itinerary(new Address("120 NewYork Street", "New York", "IL"), 1, Collections.singletonList("China"), "CA850", false),
            NOTHING_TO_DECLARATION,
            NO_VALUABLE_ARTICLE, false);

        juvenile.isApproved(new SupplementalInformation(new HashMap<EntryApplication, SupplementalInformation>() {{
            put(adultStayAtNewYork, ALL_OK_SUPPLEMENTAL);
        }}, false, false, false, false, false, false));
    }

    @Test(expected = RejectedException.class)
    public void should_not_take_more_than_2000_money_when_business_trip() {
        EntryApplication businessEntry = new EntryApplication(ZHANG_SAN_PASSPORT,
            new Itinerary(new Address("200 Main Street", "Chicago", "IL"), 1, Collections.singletonList("China"), "CA850", true),
            NOTHING_TO_DECLARATION,
            new ValuableArticle(true, false, 2001.0), false);

        businessEntry.isApproved(ALL_OK_SUPPLEMENTAL);
    }

    @Test(expected = RejectedException.class)
    public void should_not_take_more_than_500_money_when_is_not_business_trip() {
        EntryApplication businessEntry = new EntryApplication(ZHANG_SAN_PASSPORT,
            new Itinerary(new Address("200 Main Street", "Chicago", "IL"), 1, Collections.singletonList("China"), "CA850", false),
            NOTHING_TO_DECLARATION,
            new ValuableArticle(false, false, 501), false);

        businessEntry.isApproved(ALL_OK_SUPPLEMENTAL);
    }

    @Test(expected = RejectedException.class)
    public void non_business_trip_should_not_allowed_to_take_any_commercials_merchandise() {
        new EntryApplication(ZHANG_SAN_PASSPORT,
            CHICAGO_NON_BUSINESS_ITINERARY,
            NOTHING_TO_DECLARATION,
            new ValuableArticle(false, true, 100.0), false)
            .isApproved(ALL_OK_SUPPLEMENTAL);
    }

    @Test(expected = RejectedException.class)
    public void business_trip_should_be_not_able_to_carry_commercials_merchandise_with_value_more_than_400() {
        new EntryApplication(ZHANG_SAN_PASSPORT,
            CHICAGO_NON_BUSINESS_ITINERARY,
            NOTHING_TO_DECLARATION,
            new ValuableArticle(false, true, 401), false)
            .isApproved(ALL_OK_SUPPLEMENTAL);
    }
}