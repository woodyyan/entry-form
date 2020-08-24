package com.huawei.refactoring;

import com.huawei.refactoring.form.BelongingDeclaration;
import com.huawei.refactoring.form.Itinerary;
import com.huawei.refactoring.form.Passport;
import com.huawei.refactoring.form.ValuableArticle;
import com.huawei.refactoring.validation.ApprovalRule;

public class EntryApplication {

    private final Passport passport;
    private final Itinerary itinerary;
    private final ValuableArticle valuableArticle;
    private final BelongingDeclaration belongingDeclaration;

    private final boolean isClosedLivingStock;

    public EntryApplication(Passport passport, Itinerary itinerary,
                            BelongingDeclaration belongingDeclaration,
                            ValuableArticle valuableArticle,
                            boolean isClosedLivingStock) {
        this.passport = passport;
        this.itinerary = itinerary;
        this.belongingDeclaration = belongingDeclaration;
        this.isClosedLivingStock = isClosedLivingStock;
        this.valuableArticle = valuableArticle;
    }

    public Passport getPassport() {
        return passport;
    }

    public Itinerary getItinerary() {
        return itinerary;
    }

    public BelongingDeclaration getBelongingDeclaration() {
        return belongingDeclaration;
    }

    public ValuableArticle getValuableArticle() {
        return valuableArticle;
    }

    public boolean isClosedLivingStock() {
        return isClosedLivingStock;
    }

    public boolean isApproved(SupplementalInformation supplementalInformation) {
        for (ApprovalRule approvalRule : ApprovalRules.CURRENT) {
            approvalRule.validate(this, supplementalInformation);
        }
        return true;
    }
}
