package com.huawei.refactoring;

import com.huawei.refactoring.form.BelongingDeclaration;
import com.huawei.refactoring.form.Itinerary;
import com.huawei.refactoring.form.Passport;
import com.huawei.refactoring.form.ValuableArticle;

import java.time.LocalDate;
import java.time.Period;

public class EntryApplication {
    private final Passport passport;
    private final Itinerary itinerary;
    private final ValuableArticle valuableArticle;
    private final BelongingDeclaration belongingDeclaration;

    //表格问题 12
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

    public boolean isClosedLivingStock() {
        return isClosedLivingStock;
    }

    public boolean isApproved(SupplementalInformation supplementalInformation) {
        //18岁以下需大人陪同
        int years = Period.between(this.getPassport().getBirthday(), LocalDate.now()).getYears();

        if (years < 18) {
            if (this.itinerary.getNumberOfFamilyMember() == 0) throw new RejectedException("under 18 needs an adult");
            boolean staySame = true;
            boolean found = false;
            for (EntryApplication form : supplementalInformation.getFamilyMembers()) {
                if (Period.between(form.getPassport().getBirthday(), LocalDate.now()).getYears() > 18) {
                    found = true;

                    //陪同人需要跟18岁以下住在一起
                    if (!form.getItinerary().getAddress().getStreet().equals(this.getItinerary().getAddress().getStreet())
                        || !form.getItinerary().getAddress().getCity().equals(this.getItinerary().getAddress().getCity())
                        || !form.getItinerary().getAddress().getState().equals(this.getItinerary().getAddress().getState())) {
                        staySame = false;
                    }
                }

            }
            if (!found || !staySame) throw new RejectedException("under 18 needs an adult");
        }

        //全家出行总财产
        double total = this.valuableArticle.getTotalValueOfAllArticle();
        if (this.itinerary.getNumberOfFamilyMember() > 0) {
            for (EntryApplication form : supplementalInformation.getFamilyMembers()) {
                total += form.getValuableArticle().getTotalValueOfAllArticle();
            }
        }
        //商务出行，全家可携超过某数额的财产
        if (this.itinerary.isBusinessTrip()) {
            if (total > 2000) throw new RejectedException("too much money");
        } else {
            if (total > 500) throw new RejectedException("too much money");
        }


        //是否允许带10K现金
        if (this.getValuableArticle().isCarrying10KCash() && !supplementalInformation.isCarrying10KOK())
            throw new RejectedException("too much money");


        //如果18岁以下，监护人需要被允许入境
        if (years < 18) {
            boolean found = false;
            for (EntryApplication form : supplementalInformation.getFamilyMembers()) {
                if (Period.between(form.getPassport().getBirthday(), LocalDate.now()).getYears() > 18) {
                    try {
                        if (form.isApproved(supplementalInformation.getFamilyMemberSupplementalInformation(form)))
                            found = true;
                    } catch (RejectedException e) {
                        found = false;
                    }
                }
            }
            if (!found) throw new RejectedException("family not allowed");
        }

        //商务旅行携带商业产品不总价不能超过400
        if (this.itinerary.isBusinessTrip()
            && this.valuableArticle.isCarryingCommercialsMerchandise()
            && this.valuableArticle.getTotalValueOfAllArticle() > 400) {
            throw new RejectedException("too much money");
        }
        //非商务旅行严禁携带商业产品
        if (!this.itinerary.isBusinessTrip() && this.valuableArticle.isCarryingCommercialsMerchandise())
            throw new RejectedException("commercial merchandise not allowed");

        //其他校验

        return true;
    }

    public ValuableArticle getValuableArticle() {
        return valuableArticle;
    }
}
