package com.huawei.refactoring.validation;

import com.huawei.refactoring.EntryApplication;
import com.huawei.refactoring.RejectedException;
import com.huawei.refactoring.form.Address;
import com.huawei.refactoring.form.Itinerary;
import com.huawei.refactoring.form.ValuableArticle;
import org.junit.Test;

import java.util.Collections;

import static com.huawei.refactoring.TestData.NOTHING_TO_DECLARATION;
import static com.huawei.refactoring.TestData.ZHANG_SAN_PASSPORT;

public class ValueOfCommercialsMerchandiseShouldNotExceedLimitTest {
    @Test(expected = RejectedException.class)
    public void business_trip_should_be_not_able_to_carry_commercials_merchandise_with_value_more_than_400() {
        EntryApplication entryApplication = new EntryApplication(ZHANG_SAN_PASSPORT,
            new Itinerary(new Address("200 Main Street", "Chicago", "IL"), 1, Collections.singletonList("China"), "CA850", true),
            NOTHING_TO_DECLARATION,
            new ValuableArticle(false, true, 401), false);
        new ValueOfCommercialsMerchandiseShouldNotExceedLimit().validate(entryApplication, 400);
    }
}