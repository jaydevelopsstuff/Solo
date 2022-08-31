package net.jay.solo;

import java.util.function.Function;

/**
 * Represents an argument of a command. Allows parsing of inputted strings from either predefined functions or a custom one.
 * @param <T> The type to be outputted by {@link Argument#parse(String)}.
 */
public class Argument<T> {
    /** The value outputted by {@link Argument#parse(String)}, null if the parse function errored or hasn't been called yet. */
    private T value = null;
    /** Whether this argument must be inputted */
    private boolean required;
    /** The function used in {@link Argument#parse(String)} */
    protected Function<String, T> parseFunction = null;

    /**
     * Instantiates a new Argument with a premade parse function based on the given class.
     * @param standardInputClass One of the following classes: Boolean, Character, String, Byte, Short, Integer, Long, Float, or Double.
     */
    @SuppressWarnings("unchecked")
    public Argument(Class<T> standardInputClass) {
        this.required = true;

        switch(standardInputClass.getName().substring(standardInputClass.getPackageName().length() + 1)) {
            case "Boolean" -> parseFunction = (String input) -> switch(input.trim().toLowerCase()) {
                case "f", "false" -> (T)Boolean.FALSE;
                case "t", "true" -> (T)Boolean.TRUE;
                default -> null;
            };
            case "Character" -> parseFunction = (String input) -> (T)Character.valueOf(input.charAt(0));
            case "String" -> parseFunction = (String input) -> (T)input;
            case "Byte" -> parseFunction = (String input) -> {
                Byte output = null;
                try {
                    output = Byte.valueOf(input);
                } catch(NumberFormatException ignored) {}
                return (T)output;
            };
            case "Short" -> parseFunction = (String input) -> {
                Short output = null;
                try {
                    output = Short.valueOf(input);
                } catch(NumberFormatException ignored) {}
                return (T)output;
            };
            case "Integer" -> parseFunction = (String input) -> {
                Integer output = null;
                try {
                    output = Integer.valueOf(input);
                } catch(NumberFormatException ignored) {}
                return (T)output;
            };
            case "Long" -> parseFunction = (String input) -> {
                Long output = null;
                try {
                    output = Long.valueOf(input);
                } catch(NumberFormatException ignored) {}
                return (T)output;
            };
            case "Float" -> parseFunction = (String input) -> {
                Float output = null;
                try {
                    output = Float.valueOf(input);
                } catch(NumberFormatException ignored) {}
                return (T)output;
            };
            case "Double" -> parseFunction = (String input) -> {
                Double output = null;
                try {
                    output = Double.valueOf(input);
                } catch(NumberFormatException ignored) {}
                return (T)output;
            };
            default -> {
                throw new IllegalArgumentException("Input class must be one of the following: Boolean, Character, String, Byte, Short, Integer, Long, Float, or Double.");
            }
        }
    }

    /**
     * Instantiates a new Argument with a premade parse function based on the given class.
     * @param required Whether this argument is required.
     * @param standardInputClass One of the following classes: Boolean, Character, String, Byte, Short, Integer, Long, Float, or Double.
     */
    public Argument(Class<T> standardInputClass, boolean required) {
        this(standardInputClass);
        this.required = required;
    }

    /**
     * Instantiates a new Argument with a premade parse function based on the given class and parses the provided input.
     * @param initialInput The input to first parse.
     * @param standardInputClass One of the following classes: Boolean, Character, String, Byte, Short, Integer, Long, Float, or Double.
     */
    public Argument(Class<T> standardInputClass, String initialInput) {
        this(standardInputClass);
        this.parse(initialInput);
    }

    /**
     * Instantiates a new Argument with a premade parse function based on the given class and parses the provided input.
     * @param required Whether this argument is required.
     * @param standardInputClass One of the following classes: Boolean, Character, String, Byte, Short, Integer, Long, Float, or Double.
     * @param initialInput The input to first parse.
     */
    public Argument(Class<T> standardInputClass, boolean required, String initialInput) {
        this(standardInputClass);
        this.required = required;
        this.parse(initialInput);
    }

    /**
     * Instantiates a new Argument with the provided parse function.
     * @param customParseFunction The function that will be used for parsing.
     */
    public Argument(Function<String, T> customParseFunction) {
        this.required = true;
        this.parseFunction = customParseFunction;
    }

    /**
     * Instantiates a new Argument with the provided parse function.
     * @param required Whether this argument is required.
     * @param customParseFunction The function that will be used for parsing.
     */
    public Argument(Function<String, T> customParseFunction, boolean required) {
        this.required = required;
        this.parseFunction = customParseFunction;
    }

    /**
     * Instantiates a new Argument with the provided parse function, then parse the initial input.
     * @param customParseFunction The function that will be used for parsing.
     * @param initialInput The input to first parse.
     */
    public Argument(Function<String, T> customParseFunction, String initialInput) {
        this.required = true;
        this.parseFunction = customParseFunction;
        this.parse(initialInput);
    }

    /**
     * Instantiates a new Argument with the provided parse function, then parse the initial input.
     * @param required Whether this argument is required.
     * @param customParseFunction The function that will be used for parsing.
     * @param initialInput The input to first parse.
     */
    public Argument(Function<String, T> customParseFunction, boolean required, String initialInput) {
        this.required = required;
        this.parseFunction = customParseFunction;
        this.parse(initialInput);
    }

    /**
     * Parses the given input based off the current {@link Argument#parseFunction}.
     * If the parse function fails, or does not exist null will be the result.
     * @param input An input string that will be parsed.
     * @return The parsed input, based off the current parse function.
     */
    public T parse(String input) {
        value = parseFunction.apply(input);
        return value;
    }

    public Function<String, T> getParseFunction() {
        return parseFunction;
    }

    public void setParseFunction(Function<String, T> customParseFunction) {
        this.parseFunction = customParseFunction;
    }
}
