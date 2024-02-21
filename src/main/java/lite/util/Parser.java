package lite.util;

import lite.Storage;
import lite.Ui;
import lite.task.TaskList;
import lite.util.LiteException;

public class Parser {

    /**
     * Executes a command based on the input given
     * @param input Input given by user
     * @param tasks List of tasks
     * @param ui List of instructions available
     * @return A corresponding task based on the input
     */
    public static String parse(String input, TaskList tasks, Ui ui) {
        if (input.equals("list")) {
            return tasks.outputTasks();
        }
        String output;
        String instruction[] = input.split(" ", 2); // It only splits the first " "
        if (instruction[0].equals("mark")) {
            output = ui.markTask(instruction, tasks);
        } else if (instruction[0].equals("unmark")){
            output = ui.unmarkTask(instruction, tasks);
        } else if (instruction[0].equals("delete")) {
            output = ui.deleteTask(instruction, tasks);
        } else if (instruction[0].equals("todo")) {
            output = ui.addToDoTask(instruction, tasks);
        } else if (instruction[0].equals("deadline")) {
            output = ui.addDeadlineTask(instruction, tasks);
        } else if (instruction[0].equals("event")) {
            output = ui.addEventTask(instruction, tasks);
        } else if (instruction[0].equals("find"))  {
            output = ui.findTask(instruction, tasks);
        } else {
            return LiteException.invalidInput();
        }
        Storage.save(tasks);
        return output;
    }
}
