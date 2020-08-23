package com.huawei.refactoring.form;

import java.time.LocalDate;

import static com.huawei.refactoring.form.Validators.afterToday;
import static com.huawei.refactoring.form.Validators.notNull;

public class Passport {
    private final Name name;
    private final LocalDate birthday;
    private final String passportIssuePlace;
    private final String passportNumber;
    private final String countryOfResident;

    public Passport(Name name, LocalDate birthday, String passportIssuePlace, String passportNumber, String countryOfResident) {
        this.name = name;
        this.birthday = afterToday(notNull(birthday, "birthday can't be null"));
        this.passportIssuePlace = notNull(passportIssuePlace, "passportIssuePlace can't be blank");
        this.passportNumber = notNull(passportNumber, "passportNumber can't be blank");
        this.countryOfResident = notNull(countryOfResident, "countryOfResident can't be blank");
    }

    public Name getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getPassportIssuePlace() {
        return passportIssuePlace;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getCountryOfResident() {
        return countryOfResident;
    }
}
