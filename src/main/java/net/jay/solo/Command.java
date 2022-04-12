package net.jay.solo;

public class Command {
    /** The name of this command. This is used for identification so make sure it is unique. */
    private final String name;
    /** The (optional) description of this command. */
    private final String description;
    /** The possible arguments for this command, if any. */
    private final Argument<?>[] arguments;

    /**
     * Creates a new command with the provided name and no arguments.
     * @param name The name of this command.
     */
    public Command(String name) {
        this(name, (String)null);
    }

    /**
     * Creates a new command with no arguments and the provided name and description
     * @param name The name of this command.
     * @param description The description of this command.
     */
    public Command(String name, String description) {
        this.name = name.toLowerCase();
        this.description = description;
        this.arguments = null;
    }

    /**
     * Creates a new command with the provided name and arguments.
     * @param name The name of this command.
     * @param arguments The arguments in this command.
     */
    public Command(String name, Argument<?>... arguments) {
        this(name, null, arguments);
    }

    /**
     * Creates a new command with the provided name, description, and arguments.
     * @param name The name of this command.
     * @param description The description of this command.
     * @param arguments The arguments in this command.
     */
    public Command(String name, String description, Argument<?>... arguments) {
        this.name = name.toLowerCase();
        this.description = description;
        this.arguments = arguments;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Argument<?>[] getArguments() {
        return arguments;
    }
}
