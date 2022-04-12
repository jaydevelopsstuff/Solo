package net.jay.solotesting;

public class Person {
    private final String name;
    private final Gender gender;
    private int age;

    public Person(String name, Gender gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public enum Gender {
        Male,
        Female;

        public static Gender fromString(String string) {
            return switch(string.toLowerCase()) {
                case "m", "male" -> Gender.Male;
                case "f", "female" -> Gender.Female;
                default -> null;
            };
        }
    }
}
