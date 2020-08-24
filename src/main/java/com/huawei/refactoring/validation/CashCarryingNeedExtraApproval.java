package com.huawei.refactoring.validation;

import com.huawei.refactoring.EntryApplication;
import com.huawei.refactoring.RejectedException;
import com.huawei.refactoring.SupplementalInformation;

public class CashCarryingNeedExtraApproval {
    public void validate(EntryApplication entryApplication, SupplementalInformation supplementalInformation) {
        //是否允许带10K现金
        if (entryApplication.getValuableArticle().isCarrying10KCash() && !supplementalInformation.isCarrying10KOK())
            throw new RejectedException("too much money");
    }
}
