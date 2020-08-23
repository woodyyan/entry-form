package com.huawei.refactoring.form;

import java.util.List;

import static com.huawei.refactoring.form.Validators.couldBeNull;
import static com.huawei.refactoring.form.Validators.notNegative;
import static com.huawei.refactoring.form.Validators.notNull;

public class Itinerary {
    private Address address;
    private int numberOfFamilyMember;
    private List<String> countriesVisited;
    private String flightNumber;
    private boolean isBusinessTrip;

    public Itinerary(Address address, int numberOfFamilyMember, List<String> countriesVisited, String flightNumber, boolean isBusinessTrip) {
        this.address = address;
        this.numberOfFamilyMember = notNegative(numberOfFamilyMember, "number of family can't be negative");
        this.countriesVisited = couldBeNull(countriesVisited);
        this.flightNumber = notNull(flightNumber, "flightNumber can't be blank");
        this.isBusinessTrip = isBusinessTrip;
    }

    public Address getAddress() {
        return address;
    }

    public int getNumberOfFamilyMember() {
        return numberOfFamilyMember;
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
}
