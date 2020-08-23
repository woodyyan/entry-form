package com.huawei.refactoring.form;

public class BelongingDeclaration {
    private final boolean fruits;
    private final boolean meats;
    private final boolean diseaseAgents;
    private final boolean soil;

    public BelongingDeclaration(boolean fruits, boolean meats, boolean diseaseAgents, boolean soil) {
        this.fruits = fruits;
        this.meats = meats;
        this.diseaseAgents = diseaseAgents;
        this.soil = soil;
    }

    public boolean hasFruits() {
        return fruits;
    }

    public boolean hasMeats() {
        return meats;
    }

    public boolean hasDiseaseAgents() {
        return diseaseAgents;
    }

    public boolean hasSoil() {
        return soil;
    }
}
