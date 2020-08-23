package com.huawei.refactoring;

import com.huawei.refactoring.form.Address;
import com.huawei.refactoring.form.Name;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import static com.huawei.refactoring.form.Validators.afterToday;
import static com.huawei.refactoring.form.Validators.couldBeNull;
import static com.huawei.refactoring.form.Validators.notNegative;
import static com.huawei.refactoring.form.Validators.notNull;

public class EntryForm {
    //表格问题 1
    private Name name;
    //表格问题 2
    private LocalDate birthday;

    private Address address;
    //表格问题 3
    private int numberOfFamilyMember;
    //表格问题 5
    private String passportIssuePlace;
    //表格问题 6
    private String passportNumber;
    //表格问题 7
    private String countryOfResident;
    //表格问题 8
    private List<String> countriesVisited;
    //表格问题 9
    private String flightNumber;
    //表格问题 10
    private boolean isBusinessTrip;
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

    public EntryForm(Name name, LocalDate birthday, int numberOfFamilyMember,
                     Address address, String passportIssuePlace, String passportNumber,
                     String countryOfResident, List<String> countriesVisited, String flightNumber,
                     boolean isBusinessTrip, boolean isBringingFruits, boolean isBringingMeats,
                     boolean isBringingDiseaseAgents, boolean isBringSoil, boolean isClosedLivingStock,
                     boolean isCarrying10KCash, boolean isCarryingCommercialsMerchandise, double totalValueOfAllArticle) {
        this.name = name;
        this.birthday = afterToday(notNull(birthday, "birthday can't be null"));
        this.numberOfFamilyMember = notNegative(numberOfFamilyMember, "number of family can't be negative");
        this.address = address;
        this.passportIssuePlace = notNull(passportIssuePlace, "passportIssuePlace can't be blank");
        this.passportNumber = notNull(passportNumber, "passportNumber can't be blank");
        this.countryOfResident = notNull(countryOfResident, "countryOfResident can't be blank");
        this.countriesVisited = couldBeNull(countriesVisited);
        this.flightNumber = notNull(flightNumber, "flightNumber can't be blank");
        this.isBusinessTrip = isBusinessTrip;
        this.isBringingFruits = isBringingFruits;
        this.isBringingMeats = isBringingMeats;
        this.isBringingDiseaseAgents = isBringingDiseaseAgents;
        this.isBringSoil = isBringSoil;
        this.isClosedLivingStock = isClosedLivingStock;
        this.isCarrying10KCash = isCarrying10KCash;
        this.isCarryingCommercialsMerchandise = isCarryingCommercialsMerchandise;
        this.totalValueOfAllArticle = notNegative(totalValueOfAllArticle, "totalValueOfAllArticle can't be negative");
    }

    public Name getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public int getNumberOfFamilyMember() {
        return numberOfFamilyMember;
    }

    public Address getAddress() {
        return address;
    }

    public String getPassportIssuePlace() {
        return passportIssuePlace;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getCountryOfResident() {
        return countryOfResident;
    }

    public List<String> getCountriesVisited() {
        return countriesVisited;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public boolean isBusinessTrip() {
        return isBusinessTrip;
    }

    public double getTotalValueOfAllArticle() {
        return totalValueOfAllArticle;
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
        int years = Period.between(birthday, LocalDate.now()).getYears();

        if (years < 18) {
            if (numberOfFamilyMember == 0) throw new RejectedException("under 18 needs an adult");
            boolean staySame = true;
            boolean found = false;
            for (EntryForm form : supplementalInformation.getFamilyMembers()) {
                if (Period.between(form.birthday, LocalDate.now()).getYears() > 18) {
                    found = true;

                    //陪同人需要跟18岁以下住在一起
                    if (!form.getAddress().getStreet().equals(this.address.getStreet())
                        || !form.getAddress().getCity().equals(this.address.getCity())
                        || !form.getAddress().getState().equals(this.address.getState())) {
                        staySame = false;
                    }
                }

            }
            if (!found || !staySame) throw new RejectedException("under 18 needs an adult");
        }

        //全家出行总财产
        double total = totalValueOfAllArticle;
        if (numberOfFamilyMember > 0) {
            for (EntryForm form : supplementalInformation.getFamilyMembers()) {
                total += form.getTotalValueOfAllArticle();
            }
        }
        //商务出行，全家可携超过某数额的财产
        if (isBusinessTrip) {
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
                if (Period.between(form.birthday, LocalDate.now()).getYears() > 18) {
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
        if (isBusinessTrip && isCarryingCommercialsMerchandise && totalValueOfAllArticle > 400) {
            throw new RejectedException("too much money");
        }
        //非商务旅行严禁携带商业产品
        if (!isBusinessTrip && isCarryingCommercialsMerchandise)
            throw new RejectedException("commercial merchandise not allowed");

        //其他校验

        return true;
    }
}
