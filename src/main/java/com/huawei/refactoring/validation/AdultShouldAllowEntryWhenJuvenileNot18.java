package com.huawei.refactoring.validation;

import com.huawei.refactoring.EntryApplication;
import com.huawei.refactoring.RejectedException;
import com.huawei.refactoring.SupplementalInformation;

import java.time.LocalDate;
import java.time.Period;

public class AdultShouldAllowEntryWhenJuvenileNot18 implements ApprovalRule {
    public void validate(EntryApplication entryApplication, SupplementalInformation supplementalInformation) {
        //如果18岁以下，监护人需要被允许入境
        if (Period.between(entryApplication.getPassport().getBirthday(), LocalDate.now()).getYears() < 18) {
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
    }
}
