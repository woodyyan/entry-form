package com.huawei.refactoring.validation;

import com.huawei.refactoring.EntryApplication;
import com.huawei.refactoring.RejectedException;
import com.huawei.refactoring.SupplementalInformation;

public class CannotCarryCommercialsMerchandiseWhenNotBusinessTrip implements ApprovalRule {
    public void validate(EntryApplication entryApplication, SupplementalInformation supplementalInformation) {
        //非商务旅行严禁携带商业产品
        if (!entryApplication.getItinerary().isBusinessTrip() && entryApplication.getValuableArticle().isCarryingCommercialsMerchandise())
            throw new RejectedException("commercial merchandise not allowed");
    }
}
