package cowboy;

/**
 * Parser class deals with making sense of the user command
 */
public class Parser {
    private Ui ui;
    private TaskList taskList;

    /**
     * Constructor for Parser
     *
     * @param taskList
     */
    public Parser(TaskList taskList) {
        this.ui = new Ui();
        this.taskList = taskList;
    }

    /**
     * Executes user input
     *
     * @param input user input
     * @return boolean
     */
    public boolean executeUserInput(String input) {
        if (input.equals("bye")) {
            return false;
        }

        if (input.equals("list")) { // show list
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
        } else if (input.startsWith("find")) { // tasks that contain keyword
            try {
                String keyword = input.substring(5).trim();
                taskList.findTasks(keyword);
            } catch (StringIndexOutOfBoundsException e) {
                ui.printInvalidKeyword();
            }
        } else { // else adds task
            taskList.addTask(input);
        }
        return true;
    }
}
