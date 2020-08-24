package com.huawei.refactoring;

import com.huawei.refactoring.validation.AdultShouldAllowEntryWhenJuvenileNot18;
import com.huawei.refactoring.validation.ApprovalRule;
import com.huawei.refactoring.validation.CannotCarryCommercialsMerchandiseWhenNotBusinessTrip;
import com.huawei.refactoring.validation.CashCarryingNeedExtraApproval;
import com.huawei.refactoring.validation.JuvenilesNeedAdultCompanion;
import com.huawei.refactoring.validation.TotalValueOfAllArticlesShouldNotExceedLimits;
import com.huawei.refactoring.validation.ValueOfCommercialsMerchandiseShouldNotExceedLimit;

import java.util.Arrays;
import java.util.List;

public class ApprovalRules {

    public static final List<ApprovalRule> CURRENT = Arrays.asList(
        new JuvenilesNeedAdultCompanion(),
        new TotalValueOfAllArticlesShouldNotExceedLimits(2000, 500),
        new CashCarryingNeedExtraApproval(),
        new AdultShouldAllowEntryWhenJuvenileNot18(),
        new ValueOfCommercialsMerchandiseShouldNotExceedLimit(400),
        new CannotCarryCommercialsMerchandiseWhenNotBusinessTrip()
    );
}
