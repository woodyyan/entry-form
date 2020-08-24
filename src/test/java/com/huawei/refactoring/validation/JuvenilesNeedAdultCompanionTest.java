package com.huawei.refactoring.validation;

import com.huawei.refactoring.EntryApplication;
import com.huawei.refactoring.RejectedException;
import com.huawei.refactoring.SupplementalInformation;
import com.huawei.refactoring.form.Address;
import com.huawei.refactoring.form.Itinerary;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;

import static com.huawei.refactoring.TestData.ALL_OK_SUPPLEMENTAL;
import static com.huawei.refactoring.TestData.CHICAGO_NON_BUSINESS_ITINERARY;
import static com.huawei.refactoring.TestData.NOTHING_TO_DECLARATION;
import static com.huawei.refactoring.TestData.NO_VALUABLE_ARTICLE;
import static com.huawei.refactoring.TestData.ZHANG_SHISAN_PASSPORT;

public class JuvenilesNeedAdultCompanionTest {
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

        new JuvenilesNeedAdultCompanion().validate(juvenile, new SupplementalInformation(new HashMap<EntryApplication, SupplementalInformation>() {{
            put(adultStayAtNewYork, ALL_OK_SUPPLEMENTAL);
        }}, false, false, false, false, false, false));
    }

}