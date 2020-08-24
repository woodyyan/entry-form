package com.huawei.refactoring.validation;

import com.huawei.refactoring.EntryApplication;
import com.huawei.refactoring.RejectedException;
import com.huawei.refactoring.form.Address;
import com.huawei.refactoring.form.Itinerary;
import com.huawei.refactoring.form.ValuableArticle;
import org.junit.Test;

import java.util.Collections;

import static com.huawei.refactoring.TestData.ALL_OK_SUPPLEMENTAL;
import static com.huawei.refactoring.TestData.NOTHING_TO_DECLARATION;
import static com.huawei.refactoring.TestData.ZHANG_SAN_PASSPORT;

public class TotalValueOfAllArticlesShouldNotExceedLimitsTest {
    @Test(expected = RejectedException.class)
    public void should_not_take_more_than_2000_money_when_business_trip() {
        EntryApplication businessEntry = new EntryApplication(ZHANG_SAN_PASSPORT,
            new Itinerary(new Address("200 Main Street", "Chicago", "IL"), 1, Collections.singletonList("China"), "CA850", true),
            NOTHING_TO_DECLARATION,
            new ValuableArticle(true, false, 2001.0), false);

        new TotalValueOfAllArticlesShouldNotExceedLimits().validate(businessEntry, ALL_OK_SUPPLEMENTAL, 2000, 500);
    }

    @Test(expected = RejectedException.class)
    public void should_not_take_more_than_500_money_when_is_not_business_trip() {
        EntryApplication businessEntry = new EntryApplication(ZHANG_SAN_PASSPORT,
            new Itinerary(new Address("200 Main Street", "Chicago", "IL"), 1, Collections.singletonList("China"), "CA850", false),
            NOTHING_TO_DECLARATION,
            new ValuableArticle(false, false, 501), false);

        new TotalValueOfAllArticlesShouldNotExceedLimits().validate(businessEntry, ALL_OK_SUPPLEMENTAL, 2000, 500);
    }
}