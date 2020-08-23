package com.huawei.refactoring;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;

//00.Day2-Problem-start
public class App {
    public static void main(String... args) {
        approved();
        rejected();
    }

    private static void rejected() {
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

        SupplementalInformation childSupplemental = new SupplementalInformation(new HashMap<EntryForm, SupplementalInformation>() {{
            put(parent, parentSupplemental);
        }}, false, false, false, false, false, false);

        validate(parent, parentSupplemental);
        validate(child, childSupplemental);
    }

    private static void approved() {
        EntryForm parent = new EntryForm("San", "Zhang", "", LocalDate.of(1975, 1, 1),
            1, "200 Main Street", "Chicago", "IL",
            "Beijing", "E47652345", "China",
            Arrays.asList("China"), "CA850", false, false,
            false, false, false, false, false,
            false, 0.0);

        EntryForm child = new EntryForm("Shisan", "Zhang", "", LocalDate.of(1989, 6, 4),
            1, "200 Main Street", "Chicago", "IL",
            "Beijing", "E43241345", "China",
            Arrays.asList("China"), "CA850", false, false,
            false, false, false, false, false,
            false, 0.0);

        SupplementalInformation parentSupplemental = new SupplementalInformation(
            new HashMap<>(), false, false, false, false, false, false
        );

        SupplementalInformation childSupplemental = new SupplementalInformation(new HashMap<EntryForm, SupplementalInformation>() {{
            put(parent, parentSupplemental);
        }}, false, false, false, false, false, false);

        validate(parent, parentSupplemental);
        validate(child, childSupplemental);
    }

    private static void validate(EntryForm child, SupplementalInformation childSupplemental) {
        printForm(child);
        try {
            if (child.isApproved(childSupplemental)) {
                System.out.println("APPROVED");
            }
        } catch (RejectedException e) {
            System.out.println("REJECTED : " + e.getMessage());
        }
    }

    private static void printForm(EntryForm form) {
        System.out.println("Application: " + form.getName().getFirstName() + " " + form.getName().getMiddleName() + " " + form.getName().getLastName());
        System.out.println("Birthday: " + form.getBirthday().toString());
        System.out.println("US Address: " + form.getAddress() + " " + form.getCity() + " " + form.getState());
        System.out.println("# of Family member: " + form.getNumberOfFamilyMember());
        System.out.println("Flight: " + form.getFlightNumber());
        System.out.println("Visited: " + String.join(", ", form.getCountriesVisited()));
        System.out.println("Passport: " + form.getPassportNumber() + " Issued at: " + form.getPassportIssuePlace());
        System.out.println("Resident at: " + form.getCountryOfResident());
        System.out.println("Business Trip: " + form.isBusinessTrip());
        System.out.println("Bringing Fruits         : " + form.isBringingFruits());
        System.out.println("         Meats          : " + form.isBringingMeats());
        System.out.println("         Disease Agents : " + form.isBringingDiseaseAgents());
        System.out.println("         Soil           : " + form.isBringSoil());
        System.out.println("Carrying 10K Cash: " + form.isCarrying10KCash());
        System.out.println("Is Closed Living Stockings: " + form.isClosedLivingStock());
        System.out.println("Carrying Commercials Merchandise : " + form.isCarryingCommercialsMerchandise());
        System.out.println("Total Value of all Article : " + form.getTotalValueOfAllArticle());
    }
}
