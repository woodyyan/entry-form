package com.huawei.refactoring.form;

import static com.huawei.refactoring.form.Validators.notNegative;

public class ValuableArticle {
    private final boolean isCarrying10KCash;
    private final boolean isCarryingCommercialsMerchandise;
    private final double totalValueOfAllArticle;

    public ValuableArticle(boolean isCarrying10KCash, boolean isCarryingCommercialsMerchandise, double totalValueOfAllArticle) {
        this.isCarrying10KCash = isCarrying10KCash;
        this.isCarryingCommercialsMerchandise = isCarryingCommercialsMerchandise;
        this.totalValueOfAllArticle = notNegative(totalValueOfAllArticle, "totalValueOfAllArticle can't be negative");
    }

    public boolean isCarrying10KCash() {
        return isCarrying10KCash;
    }

    public boolean isCarryingCommercialsMerchandise() {
        return isCarryingCommercialsMerchandise;
    }

    public double getTotalValueOfAllArticle() {
        return totalValueOfAllArticle;
    }
}
