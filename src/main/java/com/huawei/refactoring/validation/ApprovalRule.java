package com.huawei.refactoring.validation;

import com.huawei.refactoring.EntryApplication;
import com.huawei.refactoring.SupplementalInformation;

public interface ApprovalRule {
    void validate(EntryApplication entryApplication, SupplementalInformation supplementalInformation);
}
