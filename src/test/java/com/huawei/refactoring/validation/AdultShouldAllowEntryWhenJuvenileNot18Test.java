package com.huawei.refactoring.validation;

import com.huawei.refactoring.EntryApplication;
import com.huawei.refactoring.RejectedException;
import org.junit.Test;

import static com.huawei.refactoring.TestData.ALL_OK_SUPPLEMENTAL;
import static com.huawei.refactoring.TestData.CHICAGO_NON_BUSINESS_ITINERARY;
import static com.huawei.refactoring.TestData.NOTHING_TO_DECLARATION;
import static com.huawei.refactoring.TestData.NO_VALUABLE_ARTICLE;
import static com.huawei.refactoring.TestData.ZHANG_SHISAN_PASSPORT;

public class AdultShouldAllowEntryWhenJuvenileNot18Test {
    @Test(expected = RejectedException.class)
    public void juvenile_should_travel_with_adult() {
        EntryApplication juvenile = new EntryApplication(ZHANG_SHISAN_PASSPORT,
            CHICAGO_NON_BUSINESS_ITINERARY,
            NOTHING_TO_DECLARATION,
            NO_VALUABLE_ARTICLE, false);
        new AdultShouldAllowEntryWhenJuvenileNot18().validate(juvenile, ALL_OK_SUPPLEMENTAL);
    }
}