package com.huawei.refactoring.validation;

import com.huawei.refactoring.EntryApplication;
import com.huawei.refactoring.RejectedException;
import com.huawei.refactoring.SupplementalInformation;

public class ValueOfCommercialsMerchandiseShouldNotExceedLimit implements ApprovalRule {

    private final int limit;

    public ValueOfCommercialsMerchandiseShouldNotExceedLimit(int limit) {
        this.limit = limit;
    }

    public void validate(EntryApplication entryApplication, SupplementalInformation supplementalInformation) {
        //商务旅行携带商业产品不总价不能超过400
        if (entryApplication.getItinerary().isBusinessTrip()
            && entryApplication.getValuableArticle().isCarryingCommercialsMerchandise()
            && entryApplication.getValuableArticle().getTotalValueOfAllArticle() > this.limit) {
            throw new RejectedException("too much money");
        }
    }
}
