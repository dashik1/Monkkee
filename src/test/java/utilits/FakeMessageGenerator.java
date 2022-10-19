package utilits;

import com.github.javafaker.Faker;

public class FakeMessageGenerator {

    public static String generateNewEntry() {
        return new Faker().lorem().paragraph(2);
    }
    public static String generateWord() {
        return new Faker().lorem().word();
    }
    public static String generatePassword() {
        return new Faker().internet().password();
    }
}
