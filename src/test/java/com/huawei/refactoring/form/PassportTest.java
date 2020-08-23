package com.huawei.refactoring.form;

import org.junit.Test;

import java.time.LocalDate;

import static com.huawei.refactoring.TestData.ZHANG_SAN_PASSPORT;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PassportTest {
    @Test(expected = IllegalArgumentException.class)
    public void should_not_accept_empty_as_first_name() {
        new Passport(new Name("", "Zhang", ""),
            LocalDate.of(1975, 1, 1),
            "Beijing", "E47652345", "China");
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_accept_empty_as_last_name() {
        new Passport(new Name("San", "", ""),
            LocalDate.of(1975, 1, 1),
            "Beijing", "E47652345", "China");
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_accept_empty_as_issue_place() {
        new Passport(new Name("San", "Zhang", ""), LocalDate.of(1975, 1, 1), "", "E47652345", "China");
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_accept_empty_as_passport_number() {
        new Passport(new Name("San", "Zhang", ""), LocalDate.of(1975, 1, 1), "Beijing", "", "China");
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_accept_empty_as_country_of_resident() {
        new Passport(new Name("San", "Zhang", ""), LocalDate.of(1975, 1, 1), "Beijing", "E47652345", "");
    }

    @Test
    public void should_accept_empty_as_middle_name() {
        assertThat(ZHANG_SAN_PASSPORT.getName().getMiddleName(), is(""));
    }
}
