package com.huawei.refactoring;

import com.huawei.refactoring.form.Address;
import com.huawei.refactoring.form.BelongingDeclaration;
import com.huawei.refactoring.form.Itinerary;
import com.huawei.refactoring.form.Name;
import com.huawei.refactoring.form.Passport;
import com.huawei.refactoring.form.ValuableArticle;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;

public class TestData {
    public static final Passport ZHANG_SAN_PASSPORT = new Passport(new Name("San", "Zhang", ""), LocalDate.of(1975, 1, 1), "Beijing", "E47652345", "China");
    public static final Itinerary CHICAGO_NON_BUSINESS_ITINERARY = new Itinerary(new Address("200 Main Street", "Chicago", "IL"), 1, Collections.singletonList("China"), "CA850", false);
    public static final BelongingDeclaration NOTHING_TO_DECLARATION = new BelongingDeclaration(false, false, false, false);
    public static final ValuableArticle NO_VALUABLE_ARTICLE = new ValuableArticle(false, false, 0.0);
    public static final Passport ZHANG_SHISAN_PASSPORT = new Passport(new Name("Shisan", "Zhang", ""), LocalDate.of(2009, 6, 4), "Beijing", "E43241345", "China");
    public static final SupplementalInformation ALL_OK_SUPPLEMENTAL = new SupplementalInformation(
        new HashMap<>(), false, false, false, false, false, false
    );
}
