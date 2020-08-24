package com.huawei.refactoring.validation;

import com.huawei.refactoring.EntryApplication;
import com.huawei.refactoring.RejectedException;

public class ValueOfCommercialsMerchandiseShouldNotExceedLimit {
    public void valueOfCommercialsMerchandiseShouldNotExceedLimit(EntryApplication entryApplication, int limit) {
        //商务旅行携带商业产品不总价不能超过400
        if (entryApplication.getItinerary().isBusinessTrip()
            && entryApplication.getValuableArticle().isCarryingCommercialsMerchandise()
            && entryApplication.getValuableArticle().getTotalValueOfAllArticle() > limit) {
            throw new RejectedException("too much money");
        }
    }
}
