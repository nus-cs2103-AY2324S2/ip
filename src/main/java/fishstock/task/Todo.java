package fishstock.task;

import fishstock.Command;
import fishstock.UserInput;

/**
 * Encapsulates Todo Task.
 * This Task only has a description.
 */
class Todo extends Task {
    /**
     * Initializes a Todo object.
     *
     * @param description The task description.
     */
    protected Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    protected Todo(String description) {
        this(description, false);
    }

    private static void checkIsValid(String[] splitInput) throws TaskException {
        if (splitInput[0].isEmpty()) {
            throw new TaskException("OH NOSE! The description of todo cannot be empty..");
        }
    }

    /**
     * Initializes a Todo object from UserInput.
     * Has format "todo [description]".
     *
     * @param input The input from user.
     * @return The generated Todo object.
     * @throws TaskException The exceptions while creating the Todo object.
     */
    protected static Todo of(UserInput input) throws TaskException {
        assert input.getCommandType() == Command.TODO : "The input type is not Todo";

        String[] splitInput = input.splitByKeywords();
        checkIsValid(splitInput);

        String description = splitInput[0];
        return new Todo(description);
    }

    @Override
    public Task clone() {
        return new Todo(this.getDescription(), this.getIsDone());
    }

    @Override
    public String toSaveFormat() {
        return Command.TODO.getShortened() + "|" + getDescription() + "|" + markStatusToInt() + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[" + Command.TODO.getShortened() + "]" + super.toString();
    }
}
