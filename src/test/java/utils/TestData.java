package utils;
import net.datafaker.Faker;

public class TestData {
    private static final Faker faker = new Faker();

    public static String generateRandomName() {
        return faker.name().fullName();
    }

    public static String generateRandomEmail() {
        return faker.internet().emailAddress();
    }

    public static String generateRandomPassword() {
        return faker.internet().password();
    }   

    public static String generateFirstName() {
        return faker.name().firstName();
    }

    public static String generateLastName() {
        return faker.name().lastName();
    }

    public static String generateCompany() {
        return faker.company().name();
    }

    public static String generateStreetAddress() {
        return faker.address().streetAddress();
    }

    public static String generateSecondaryAddress() {
        return faker.address().secondaryAddress();
    }

    public static String generateState() {
        return faker.address().state();
    }

    public static String generateCity() {
        return faker.address().city();
    }

    public static String generateZipCode() {
        return faker.address().zipCode();
    }

    public static String generateMobileNumber() {
        return faker.phoneNumber().phoneNumber();
    }

}
