package com.huawei.refactoring;

import com.huawei.refactoring.form.Address;
import com.huawei.refactoring.form.BelongingDeclaration;
import com.huawei.refactoring.form.Itinerary;
import com.huawei.refactoring.form.Name;
import com.huawei.refactoring.form.Passport;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;

//00.Day2-Problem-start
public class App {
    public static void main(String... args) {
        approved();
        rejected();
    }

    private static void rejected() {
        EntryForm parent = new EntryForm(new Passport(new Name("San", "Zhang", ""), LocalDate.of(1975, 1, 1), "Beijing", "E47652345", "China"),
            new Itinerary(new Address("200 Main Street", "Chicago", "IL"), 1,
                Collections.singletonList("China"), "CA850", false),
            new BelongingDeclaration(false,
                false, false, false), false, true,
            false, 0.0);

        EntryForm child = new EntryForm(new Passport(new Name("Shisan", "Zhang", ""), LocalDate.of(2009, 6, 4), "Beijing", "E43241345", "China"),
            new Itinerary(new Address("200 Main Street", "Chicago", "IL"), 1,
                Collections.singletonList("China"), "CA850", false),
            new BelongingDeclaration(false,
                false, false, false), false, false,
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
        EntryForm parent = new EntryForm(new Passport(new Name("San", "Zhang", ""), LocalDate.of(1975, 1, 1), "Beijing", "E47652345", "China"),
            new Itinerary(new Address("200 Main Street", "Chicago", "IL"), 1,
                Collections.singletonList("China"), "CA850", false),
            new BelongingDeclaration(false, false, false, false), false, false,
            false, 0.0);

        EntryForm child = new EntryForm(new Passport(new Name("Shisan", "Zhang", ""), LocalDate.of(1989, 6, 4), "Beijing", "E43241345", "China"),
            new Itinerary(new Address("200 Main Street", "Chicago", "IL"), 1,
                Collections.singletonList("China"), "CA850", false),
            new BelongingDeclaration(false, false, false, false), false, false,
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
        System.out.println("Application: " + form.getPassport().getName().getFirstName() + " " + form.getPassport().getName().getMiddleName() + " " + form.getPassport().getName().getLastName());
        System.out.println("Birthday: " + form.getPassport().getBirthday().toString());
        System.out.println("US Address: " + form.getItinerary().getAddress().getStreet() + " " + form.getItinerary().getAddress().getCity() + " " + form.getItinerary().getAddress().getState());
        System.out.println("# of Family member: " + form.getItinerary().getNumberOfFamilyMember());
        System.out.println("Flight: " + form.getItinerary().getFlightNumber());
        System.out.println("Visited: " + String.join(", ", form.getItinerary().getCountriesVisited()));
        System.out.println("Passport: " + form.getPassport().getPassportNumber() + " Issued at: " + form.getPassport().getPassportIssuePlace());
        System.out.println("Resident at: " + form.getPassport().getCountryOfResident());
        System.out.println("Business Trip: " + form.getItinerary().isBusinessTrip());
        System.out.println("Bringing Fruits         : " + form.getBelongingDeclaration().hasFruits());
        System.out.println("         Meats          : " + form.getBelongingDeclaration().hasMeats());
        System.out.println("         Disease Agents : " + form.getBelongingDeclaration().hasDiseaseAgents());
        System.out.println("         Soil           : " + form.getBelongingDeclaration().hasSoil());
        System.out.println("Carrying 10K Cash: " + form.isCarrying10KCash());
        System.out.println("Is Closed Living Stockings: " + form.isClosedLivingStock());
        System.out.println("Carrying Commercials Merchandise : " + form.isCarryingCommercialsMerchandise());
        System.out.println("Total Value of all Article : " + form.getTotalValueOfAllArticle());
    }
}
