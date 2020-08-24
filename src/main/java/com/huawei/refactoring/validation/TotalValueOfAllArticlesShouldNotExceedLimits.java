package com.huawei.refactoring.validation;

import com.huawei.refactoring.EntryApplication;
import com.huawei.refactoring.RejectedException;
import com.huawei.refactoring.SupplementalInformation;

public class TotalValueOfAllArticlesShouldNotExceedLimits implements ApprovalRule {

    private final int businessTrip;
    private final int nonBusinessTrip;

    public TotalValueOfAllArticlesShouldNotExceedLimits(int businessTrip, int nonBusinessTrip) {
        this.businessTrip = businessTrip;
        this.nonBusinessTrip = nonBusinessTrip;
    }

    public void validate(EntryApplication entryApplication, SupplementalInformation supplementalInformation) {
        //全家出行总财产
        double total = entryApplication.getValuableArticle().getTotalValueOfAllArticle();
        if (entryApplication.getItinerary().getNumberOfFamilyMember() > 0) {
            for (EntryApplication form : supplementalInformation.getFamilyMembers()) {
                total += form.getValuableArticle().getTotalValueOfAllArticle();
            }
        }
        //商务出行，全家可携超过某数额的财产
        if (entryApplication.getItinerary().isBusinessTrip()) {
            if (total > this.businessTrip) throw new RejectedException("too much money");
        } else {
            if (total > this.nonBusinessTrip) throw new RejectedException("too much money");
        }
    }
}
