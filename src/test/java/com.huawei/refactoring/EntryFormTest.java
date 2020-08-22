package com.huawei.refactoring;


import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class EntryFormTest {
    @Test
    public void smoke_test_happy() {
        EntryForm parent = new EntryForm("San", "Zhang", "", LocalDate.of(1975, 1, 1),
                1, "200 Main Street", "Chicago", "IL",
                "Beijing", "E47652345", "China",
                Arrays.asList("China"), "CA850", false, false,
                false, false, false, false, false,
                false, 0.0);

        EntryForm child = new EntryForm("Shisan", "Zhang", "", LocalDate.of(2009, 6, 4),
                1, "200 Main Street", "Chicago", "IL",
                "Beijing", "E43241345", "China",
                Arrays.asList("China"), "CA850", false, false,
                false, false, false, false, false,
                false, 0.0);

        SupplementalInformation parentSupplemental = new SupplementalInformation(
                new HashMap<>(), false, false, false, false, false, false
        );

        SupplementalInformation childSupplemental = new SupplementalInformation(new HashMap<EntryForm, SupplementalInformation>(){{            put(parent, parentSupplemental);        }}, false, false, false, false, false, false);

        assertThat(parent.isApproved(parentSupplemental), is(true));
        assertThat(child.isApproved(childSupplemental), is(true));
    }

    @Test
    public void smoke_test_sad() {
        EntryForm parent = new EntryForm("San", "Zhang", "", LocalDate.of(1975, 1, 1),
                1, "200 Main Street", "Chicago", "IL",
                "Beijing", "E47652345", "China",
                Arrays.asList("China"), "CA850", false, false,
                false, false, false, false, true,
                false, 0.0);

        EntryForm child = new EntryForm("Shisan", "Zhang", "", LocalDate.of(2009, 6, 4),
                1, "200 Main Street", "Chicago", "IL",
                "Beijing", "E43241345", "China",
                Arrays.asList("China"), "CA850", false, false,
                false, false, false, false, false,
                false, 0.0);

        SupplementalInformation parentSupplemental = new SupplementalInformation(
                new HashMap<>(), false, false, false, false, false, false
        );

        SupplementalInformation childSupplemental = new SupplementalInformation(new HashMap<EntryForm, SupplementalInformation>(){{            put(parent, parentSupplemental);        }}, false, false, false, false, false, false);

        try {
            parent.isApproved(parentSupplemental);
        } catch (RejectedException e) {
            assertThat(e.getMessage(), is("too much money"));
        }

        try {
            child.isApproved(childSupplemental);
        } catch (RejectedException e) {
            assertThat(e.getMessage(), is("family not allowed"));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_accept_empty_as_first_name() {
        new EntryForm("", "Zhang", "", LocalDate.of(1975, 1, 1),
                1, "200 Main Street", "Chicago", "IL",
                "Beijing", "E47652345", "China",
                Arrays.asList("China"), "CA850", false, false,
                false, false, false, false, false,
                false, 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_accept_empty_as_last_name() {
        new EntryForm("San", "", "", LocalDate.of(1975, 1, 1),
                1, "200 Main Street", "Chicago", "IL",
                "Beijing", "E47652345", "China",
                Arrays.asList("China"), "CA850", false, false,
                false, false, false, false, false,
                false, 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_accept_empty_as_address() {
        new EntryForm("San", "Zhang", "", LocalDate.of(1975, 1, 1),
                1, "", "Chicago", "IL",
                "Beijing", "E47652345", "China",
                Arrays.asList("China"), "CA850", false, false,
                false, false, false, false, false,
                false, 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_accept_empty_as_city() {
        new EntryForm("San", "Zhang", "", LocalDate.of(1975, 1, 1),
                1, "200 Main Street", "", "IL",
                "Beijing", "E47652345", "China",
                Arrays.asList("China"), "CA850", false, false,
                false, false, false, false, false,
                false, 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_accept_empty_as_state() {
        new EntryForm("San", "Zhang", "", LocalDate.of(1975, 1, 1),
                1, "200 Main Street", "Chicago", "",
                "Beijing", "E47652345", "China",
                Arrays.asList("China"), "CA850", false, false,
                false, false, false, false, false,
                false, 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_accept_empty_as_issue_place() {
        new EntryForm("San", "Zhang", "", LocalDate.of(1975, 1, 1),
                1, "200 Main Street", "Chicago", "IL",
                "", "E47652345", "China",
                Arrays.asList("China"), "CA850", false, false,
                false, false, false, false, false,
                false, 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_accept_empty_as_passport_number() {
        new EntryForm("San", "Zhang", "", LocalDate.of(1975, 1, 1),
                1, "200 Main Street", "Chicago", "IL",
                "Beijing", "", "China",
                Arrays.asList("China"), "CA850", false, false,
                false, false, false, false, false,
                false, 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_accept_empty_as_country_of_resident() {
        new EntryForm("San", "Zhang", "", LocalDate.of(1975, 1, 1),
                1, "200 Main Street", "Chicago", "IL",
                "Beijing", "E47652345", "",
                Arrays.asList("China"), "CA850", false, false,
                false, false, false, false, false,
                false, 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_accept_empty_as_flight_number() {
        new EntryForm("San", "Zhang", "", LocalDate.of(1975, 1, 1),
                1, "200 Main Street", "Chicago", "IL",
                "Beijing", "E47652345", "China",
                Arrays.asList("China"), "", false, false,
                false, false, false, false, false,
                false, 0.0);
    }

    @Test
    public void should_not_accept_empty_as_middle_name() {
        EntryForm form = new EntryForm("San", "Zhang", "", LocalDate.of(1975, 1, 1),
                1, "200 Main Street", "Chicago", "IL",
                "Beijing", "E47652345", "China",
                Arrays.asList("China"), "CA850", false, false,
                false, false, false, false, false,
                false, 0.0);

        assertThat(form.getMiddleName(), is(""));
    }

    @Test
    public void should_not_accept_empty_as_countries_visited() {
        EntryForm form = new EntryForm("San", "Zhang", "", LocalDate.of(1975, 1, 1),
                1, "200 Main Street", "Chicago", "IL",
                "Beijing", "E47652345", "China",
                null, "CA850", false, false,
                false, false, false, false, false,
                false, 0.0);

        assertThat(form.getCountriesVisited().size(), is(0));
    }
}