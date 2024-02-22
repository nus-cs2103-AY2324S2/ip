package Duke.Commands;

import Duke.Activities.Activity;
import Duke.Activities.ActivityList;
import Duke.Converstion.Dialog;
import Duke.Exception.CommandException;

/**
 * Represents a command to add a Todo activity to an {@link ActivityList}.
 * Extends the {@link Command} class and implements the {@link AddActivity} interface.
 */
public class AddTodo extends Command implements AddActivity {

    /**
     * The name of the Todo activity.
     */
    private final String NAME;

    /**
     * Constructor to initialize the AddTodo command by parsing the input.
     *
     * @param input The input string containing the name of the Todo activity.
     */
    public AddTodo(String input) {
        super(input);
        NAME = phrases(input);
    }

    /**
     * Returns the input string as the name of the Todo activity.
     *
     * @param input The input string.
     * @return The name of the Todo activity.
     */
    public String phrases(String input) {
        return input;
    }

    /**
     * Adds the Todo activity to the specified {@link ActivityList}.
     *
     * @param list The activity list to which the Todo activity should be added.
     * @return The added Todo activity.
     */
    @Override
    public Activity addToList(ActivityList list) {
        return list.add(NAME);
    }

    /**
     * Executes the command by adding the Todo activity to the specified {@link ActivityList}.
     *
     * @param list The activity list to which the Todo activity should be added.
     * @throws CommandException If an error occurs during execution, such as an empty activity name.
     */
    @Override
    public void execute(ActivityList list) throws CommandException {
        if (NAME.isEmpty()) {
            throw new CommandException("The activity name of a todo cannot be empty.");
        } else {
            if(addToList(list) == null) {
                throw new CommandException("The activity name of a todo cannot be a duplicate.");
            }
        }
    }

    /**
     * Returns a string representation of the command's execution output.
     *
     * @return A string indicating the successful addition of the Todo activity.
     */
    @Override
    public String toString() {
        String output = Dialog.printLine();
        output += "Got it. I've added this task: " + NAME + ".\n";
        output += Dialog.printLine();
        return output;
    }
}
