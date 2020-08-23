package com.huawei.refactoring.form;

import static com.huawei.refactoring.form.Validators.couldBeNull;
import static com.huawei.refactoring.form.Validators.notNull;

public class Name {
    private final String firstName;
    private final String lastName;
    private final String middleName;

    public Name(String firstName, String lastName, String middleName) {
        this.firstName = notNull(firstName, "first name can't be blank");
        this.lastName = notNull(lastName, "last name can't be blank");
        this.middleName = couldBeNull(middleName);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }
}
