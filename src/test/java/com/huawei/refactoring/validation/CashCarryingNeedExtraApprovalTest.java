package com.huawei.refactoring.validation;

import com.huawei.refactoring.EntryApplication;
import com.huawei.refactoring.RejectedException;
import com.huawei.refactoring.SupplementalInformation;
import com.huawei.refactoring.form.ValuableArticle;
import org.junit.Test;

import java.util.HashMap;

import static com.huawei.refactoring.TestData.CHICAGO_NON_BUSINESS_ITINERARY;
import static com.huawei.refactoring.TestData.NOTHING_TO_DECLARATION;
import static com.huawei.refactoring.TestData.ZHANG_SAN_PASSPORT;

public class CashCarryingNeedExtraApprovalTest {
    @Test(expected = RejectedException.class)
    public void should_cash_carrying_need_extra_approval() {
        EntryApplication entryApplication = new EntryApplication(ZHANG_SAN_PASSPORT,
            CHICAGO_NON_BUSINESS_ITINERARY,
            NOTHING_TO_DECLARATION,
            new ValuableArticle(true, false, 0), false);
        SupplementalInformation supplementalInformation = new SupplementalInformation(new HashMap<>(), false, false, false, false, false, false);
        new CashCarryingNeedExtraApproval().validate(entryApplication, supplementalInformation);
    }
}