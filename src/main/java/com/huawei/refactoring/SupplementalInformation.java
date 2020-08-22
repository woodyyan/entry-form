package com.huawei.refactoring;

import java.util.Map;
import java.util.Set;

public class SupplementalInformation {
    private Map<EntryForm, SupplementalInformation> familyMembers;
    private boolean fruitsOK;
    private boolean soilOK;
    private boolean meatsOK;
    private boolean diseaseAgentsOK;
    private boolean carrying10KOK;

    public SupplementalInformation(Map<EntryForm, SupplementalInformation> familyMembers, boolean fruitsOK, boolean soilOK, boolean meatsOK, boolean diseaseAgentsOK, boolean carrying10KOK, boolean isClosedStockingOK) {
        this.familyMembers = familyMembers;
        this.fruitsOK = fruitsOK;
        this.soilOK = soilOK;
        this.meatsOK = meatsOK;
        this.diseaseAgentsOK = diseaseAgentsOK;
        this.carrying10KOK = carrying10KOK;
        this.isClosedStockingOK = isClosedStockingOK;
    }

    private boolean isClosedStockingOK;

    public Set<EntryForm> getFamilyMembers() {
        return familyMembers.keySet();
    }

    public SupplementalInformation getFamilyMemberSupplementalInformation(EntryForm member) {
        return familyMembers.get(member);
    }

    public boolean isFruitsOK() {
        return fruitsOK;
    }

    public boolean isSoilOK() {
        return soilOK;
    }

    public boolean isMeatsOK() {
        return meatsOK;
    }

    public boolean isDiseaseAgentsOK() {
        return diseaseAgentsOK;
    }

    public boolean isCarrying10KOK() {
        return carrying10KOK;
    }

    public boolean isClosedStockingOK() {
        return isClosedStockingOK;
    }
}
