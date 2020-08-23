package com.huawei.refactoring.form;

import static com.huawei.refactoring.form.Validators.notNull;

public class Address {
    private String street;
    private String city;
    private String state;

    public Address(String street, String city, String state) {
        this.street = notNull(street, "street can't be blank");
        this.city = notNull(city, "city can't be blank");
        this.state = notNull(state, "state can't be blank");
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }
}
