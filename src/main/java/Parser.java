
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

    public void parseInput(String input) {
        if (input.equals("bye")) {
            break;
        }

        if (input.equals("list")) { // show list
            this.taskIndex = 1;
            tasklist.printList();
        } else if (input.startsWith("mark done")) { // mark as done
            try {
                int index = Integer.parseInt(input.substring(9).trim()) - 1;
                markDone(index);
            } catch (NumberFormatException e) {
                ui.printInvalidTaskIndex();
            }
        } else if (input.startsWith("mark undone")) { // mark as undone
            try {
                int index = Integer.parseInt(input.substring(11).trim()) - 1;
                markUndone(index);
            } catch (NumberFormatException e) {
                ui.printInvalidTaskIndex();
            }
        } else {
            addTask(input);
        }
    }
}
