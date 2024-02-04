package Duke.activityAndUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The {@code Todo} class represents a to-do task, implementing the {@link Activity} interface. It encapsulates
 * a simple task with a status indicating whether the task is completed or not, and a name describing the task.
 */
public class Todo implements Activity {
    List<String> act;

    /**
     * Constructs a new {@code Todo} instance with the specified status and name.
     *
     * @param status The initial status of the to-do task, typically indicating completion (e.g., "√" for done, "X" for not done).
     * @param name The name or description of the to-do task.
     */
    public Todo(String status, String name) {
        act = new ArrayList<>();
        act.add(status); // Status
        act.add(name); // Task name
    }

    /**
     * Prints the to-do task's details, including its status and name.
     * The task is displayed in a formatted string that highlights its completion status and description.
     */
    @Override
    public void printActivity() {
        System.out.format("\t\t [T][%s]%s%n", act.get(0), act.get(1));
    }

    /**
     * Returns the name or description of the to-do task.
     *
     * @return A {@code String} representing the name of the to-do task.
     */
    @Override
    public String getName() {
        return act.get(1);
    }

    /**
     * Marks the to-do task as complete or incomplete based on the input. If the input is "mark", the status is set to a
     * checkmark (indicating completion). If the input is "unmark", the status is set to an "X" (indicating not completed).
     * After marking, the updated task details are printed.
     *
     * @param input A {@code String} indicating whether to mark the to-do task as completed ("mark") or not completed ("unmark").
     */
    @Override
    public void mark(String input) {
        if (Objects.equals(input, "mark")) {
            act.set(0, "√");
        } else if (Objects.equals(input, "unmark")) {
            act.set(0, "X");
        }
        System.out.format("\t%sed:%n", input);
        printActivity();
    }
}
