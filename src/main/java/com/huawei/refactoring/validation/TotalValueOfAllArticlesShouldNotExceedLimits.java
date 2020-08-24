package com.huawei.refactoring.validation;

import com.huawei.refactoring.EntryApplication;
import com.huawei.refactoring.RejectedException;
import com.huawei.refactoring.SupplementalInformation;

public class TotalValueOfAllArticlesShouldNotExceedLimits {
    public void totalValueOfAllArticlesShouldNotExceedLimits(EntryApplication entryApplication, SupplementalInformation supplementalInformation, int businessTrip, int nonBusinessTrip) {
        //全家出行总财产
        double total = entryApplication.getValuableArticle().getTotalValueOfAllArticle();
        if (entryApplication.getItinerary().getNumberOfFamilyMember() > 0) {
            for (EntryApplication form : supplementalInformation.getFamilyMembers()) {
                total += form.getValuableArticle().getTotalValueOfAllArticle();
            }
        }
        //商务出行，全家可携超过某数额的财产
        if (entryApplication.getItinerary().isBusinessTrip()) {
            if (total > businessTrip) throw new RejectedException("too much money");
        } else {
            if (total > nonBusinessTrip) throw new RejectedException("too much money");
        }
    }
}
