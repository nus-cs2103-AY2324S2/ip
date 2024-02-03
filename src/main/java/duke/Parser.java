package duke;

/**
 * This class is responsible for making sense of the user input
 */
public class Parser {
    private Ui ui;
    private int taskIndex;
    private TaskList taskList;

    public Parser(TaskList taskList) {
        this.ui = new Ui();
        this.taskIndex = 1;
        this.taskList = taskList;
    }

    public boolean parseInput(String input) {
        if (input.equals("bye")) {
            return false;
        }

        if (input.equals("list")) { // show list
            this.taskIndex = 1;
            taskList.printList();
        } else if (input.startsWith("mark done")) { // mark as done
            try {
                int index = Integer.parseInt(input.substring(9).trim()) - 1;
                taskList.markDone(index);
            } catch (NumberFormatException e) {
                ui.printInvalidTaskIndex();
            }
        } else if (input.startsWith("mark undone")) { // mark as undone
            try {
                int index = Integer.parseInt(input.substring(11).trim()) - 1;
                taskList.markUndone(index);
            } catch (NumberFormatException e) {
                ui.printInvalidTaskIndex();
            }
        } else {
            taskList.addTask(input);
        }
        return true;
    }
}
