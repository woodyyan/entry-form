package com.huawei.refactoring.validation;

import com.huawei.refactoring.EntryApplication;
import com.huawei.refactoring.RejectedException;
import com.huawei.refactoring.form.ValuableArticle;
import org.junit.Test;

import static com.huawei.refactoring.TestData.CHICAGO_NON_BUSINESS_ITINERARY;
import static com.huawei.refactoring.TestData.NOTHING_TO_DECLARATION;
import static com.huawei.refactoring.TestData.ZHANG_SAN_PASSPORT;

public class CannotCarryCommercialsMerchandiseWhenNotBusinessTripTest {

    @Test(expected = RejectedException.class)
    public void non_business_trip_should_not_allowed_to_take_any_commercials_merchandise() {
        EntryApplication entryApplication = new EntryApplication(ZHANG_SAN_PASSPORT,
            CHICAGO_NON_BUSINESS_ITINERARY,
            NOTHING_TO_DECLARATION,
            new ValuableArticle(false, true, 100.0), false);
        new CannotCarryCommercialsMerchandiseWhenNotBusinessTrip().validate(entryApplication);
    }
}