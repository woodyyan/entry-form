package com.huawei.refactoring;

import com.huawei.refactoring.form.Itinerary;
import com.huawei.refactoring.form.Passport;

import java.time.LocalDate;
import java.time.Period;

import static com.huawei.refactoring.form.Validators.notNegative;

public class EntryForm {
    private Passport passport;
    private Itinerary itinerary;

    //表格问题 11
    private boolean isBringingFruits;
    private boolean isBringingMeats;
    private boolean isBringingDiseaseAgents;
    private boolean isBringSoil;
    //表格问题 12
    private boolean isClosedLivingStock;
    //表格问题 13
    private boolean isCarrying10KCash;
    //表格问题 14
    private boolean isCarryingCommercialsMerchandise;
    //表格问题 15
    private double totalValueOfAllArticle;

    public EntryForm(Passport passport, Itinerary itinerary, boolean isBringingFruits, boolean isBringingMeats,
                     boolean isBringingDiseaseAgents, boolean isBringSoil, boolean isClosedLivingStock,
                     boolean isCarrying10KCash, boolean isCarryingCommercialsMerchandise, double totalValueOfAllArticle) {
        this.passport = passport;
        this.itinerary = itinerary;
        this.isBringingFruits = isBringingFruits;
        this.isBringingMeats = isBringingMeats;
        this.isBringingDiseaseAgents = isBringingDiseaseAgents;
        this.isBringSoil = isBringSoil;
        this.isClosedLivingStock = isClosedLivingStock;
        this.isCarrying10KCash = isCarrying10KCash;
        this.isCarryingCommercialsMerchandise = isCarryingCommercialsMerchandise;
        this.totalValueOfAllArticle = notNegative(totalValueOfAllArticle, "totalValueOfAllArticle can't be negative");
    }

    public Passport getPassport() {
        return passport;
    }

    public double getTotalValueOfAllArticle() {
        return totalValueOfAllArticle;
    }

    public Itinerary getItinerary() {
        return itinerary;
    }

    public boolean isBringingFruits() {
        return isBringingFruits;
    }

    public boolean isBringingMeats() {
        return isBringingMeats;
    }

    public boolean isBringingDiseaseAgents() {
        return isBringingDiseaseAgents;
    }

    public boolean isBringSoil() {
        return isBringSoil;
    }

    public boolean isClosedLivingStock() {
        return isClosedLivingStock;
    }

    public boolean isCarrying10KCash() {
        return isCarrying10KCash;
    }

    public boolean isCarryingCommercialsMerchandise() {
        return isCarryingCommercialsMerchandise;
    }

    public boolean isApproved(SupplementalInformation supplementalInformation) {
        //18岁以下需大人陪同
        int years = Period.between(this.getPassport().getBirthday(), LocalDate.now()).getYears();

        if (years < 18) {
            if (this.itinerary.getNumberOfFamilyMember() == 0) throw new RejectedException("under 18 needs an adult");
            boolean staySame = true;
            boolean found = false;
            for (EntryForm form : supplementalInformation.getFamilyMembers()) {
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
        double total = totalValueOfAllArticle;
        if (this.itinerary.getNumberOfFamilyMember() > 0) {
            for (EntryForm form : supplementalInformation.getFamilyMembers()) {
                total += form.getTotalValueOfAllArticle();
            }
        }
        //商务出行，全家可携超过某数额的财产
        if (this.itinerary.isBusinessTrip()) {
            if (total > 2000) throw new RejectedException("too much money");
        } else {
            if (total > 500) throw new RejectedException("too much money");
        }


        //是否允许带10K现金
        if (isCarrying10KCash && !supplementalInformation.isCarrying10KOK())
            throw new RejectedException("too much money");


        //如果18岁以下，监护人需要被允许入境
        if (years < 18) {
            boolean found = false;
            for (EntryForm form : supplementalInformation.getFamilyMembers()) {
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
        if (this.itinerary.isBusinessTrip() && isCarryingCommercialsMerchandise && totalValueOfAllArticle > 400) {
            throw new RejectedException("too much money");
        }
        //非商务旅行严禁携带商业产品
        if (!this.itinerary.isBusinessTrip() && isCarryingCommercialsMerchandise)
            throw new RejectedException("commercial merchandise not allowed");

        //其他校验

        return true;
    }
}
