package net.jay.solotesting;

import net.jay.solo.Argument;
import net.jay.solo.Command;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Standard {
    @Test
    public void basicTest() {
        String name = "hElp";
        String description = "Provides info regarding the program and its commands.";
        Command command = new Command(name, description);
        assertEquals(name.toLowerCase(), command.getName());
        assertEquals(description, command.getDescription());
    }

    @Test
    public void basicArgumentsTest() {
        Command command = new Command("args-test", new Argument<>(String.class), new Argument<>(Integer.class));
        String firstInput = "a-string-input";
        String secondInput = "56942";
        String firstResult = (String)command.getArguments()[0].parse(firstInput);
        Integer secondResult = (Integer)command.getArguments()[1].parse(secondInput);
        assertEquals(firstInput, firstResult);
        assertEquals(Integer.parseInt(secondInput), secondResult.intValue());
    }

    @Test
    public void argumentsCustomParseFunctionTest() {
        Function<String, Person> parsePersonFunction = (input) -> {
            String[] rawInfo = input.split(",");
            String name = rawInfo[0].trim();
            Person.Gender gender = Person.Gender.fromString(rawInfo[1].trim());
            int age = Integer.parseInt(rawInfo[2].trim());
            if(gender == null) throw new IllegalArgumentException("gender");
            if(age < 0) throw new IllegalArgumentException("age");
            return new Person(name, gender, age);
        };

        Command command = new Command("complex-args-test", new Argument<>(parsePersonFunction), new Argument<>(parsePersonFunction));
        String firstName = "Bob";
        Person.Gender firstGender = Person.Gender.Male;
        int firstAge = 28;
        String secondName = "Mary";
        Person.Gender secondGender = Person.Gender.Female;
        int secondAge = 35;
        Person firstPerson = (Person)command.getArguments()[0].parse(firstName + "," + firstGender.name() + "," + firstAge);
        Person secondPerson = (Person)command.getArguments()[1].parse(secondName + "," + secondGender.name() + "," + secondAge);
        assertEquals(firstName, firstPerson.getName());
        assertEquals(firstGender, firstPerson.getGender());
        assertEquals(firstAge, firstPerson.getAge());
        assertEquals(secondName, secondPerson.getName());
        assertEquals(secondGender, secondPerson.getGender());
        assertEquals(secondAge, secondPerson.getAge());
    }
}
