package com.huawei.refactoring;

import com.huawei.refactoring.form.BelongingDeclaration;
import com.huawei.refactoring.form.Itinerary;
import com.huawei.refactoring.form.Passport;
import com.huawei.refactoring.form.ValuableArticle;
import com.huawei.refactoring.validation.AdultShouldAllowEntryWhenJuvenileNot18;
import com.huawei.refactoring.validation.CannotCarryCommercialsMerchandiseWhenNotBusinessTrip;
import com.huawei.refactoring.validation.CashCarryingNeedExtraApproval;
import com.huawei.refactoring.validation.JuvenilesNeedAdultCompanion;
import com.huawei.refactoring.validation.TotalValueOfAllArticlesShouldNotExceedLimits;
import com.huawei.refactoring.validation.ValueOfCommercialsMerchandiseShouldNotExceedLimit;

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
        new JuvenilesNeedAdultCompanion().validate(this, supplementalInformation);
        new TotalValueOfAllArticlesShouldNotExceedLimits(2000, 500).validate(this, supplementalInformation);
        new CashCarryingNeedExtraApproval().validate(this, supplementalInformation);
        new AdultShouldAllowEntryWhenJuvenileNot18().validate(this, supplementalInformation);
        new ValueOfCommercialsMerchandiseShouldNotExceedLimit(400).validate(this, supplementalInformation);
        new CannotCarryCommercialsMerchandiseWhenNotBusinessTrip().validate(this, supplementalInformation);
        return true;
    }
}
