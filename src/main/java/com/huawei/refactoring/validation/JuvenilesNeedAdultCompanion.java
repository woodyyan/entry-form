package com.huawei.refactoring.validation;

import com.huawei.refactoring.EntryApplication;
import com.huawei.refactoring.RejectedException;
import com.huawei.refactoring.SupplementalInformation;

import java.time.LocalDate;
import java.time.Period;

public class JuvenilesNeedAdultCompanion {

    public void validate(EntryApplication entryApplication, SupplementalInformation supplementalInformation) {
        if (Period.between(entryApplication.getPassport().getBirthday(), LocalDate.now()).getYears() < 18) {
            if (entryApplication.getItinerary().getNumberOfFamilyMember() == 0)
                throw new RejectedException("under 18 needs an adult");
            boolean staySame = true;
            boolean found = false;
            for (EntryApplication form : supplementalInformation.getFamilyMembers()) {
                if (Period.between(form.getPassport().getBirthday(), LocalDate.now()).getYears() > 18) {
                    found = true;

                    //陪同人需要跟18岁以下住在一起
                    if (stayAtSameAddress(entryApplication, form)) {
                        staySame = false;
                    }
                }

            }
            if (!found || !staySame) throw new RejectedException("under 18 needs an adult");
        }
    }

    private boolean stayAtSameAddress(EntryApplication entryApplication, EntryApplication form) {
        return !form.getItinerary().getAddress().getStreet().equals(entryApplication.getItinerary().getAddress().getStreet())
            || !form.getItinerary().getAddress().getCity().equals(entryApplication.getItinerary().getAddress().getCity())
            || !form.getItinerary().getAddress().getState().equals(entryApplication.getItinerary().getAddress().getState());
    }
}
