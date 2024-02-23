package bentley;

/**
 * A class responsible for parsing user commands and executing corresponding
 * actions.
 */
public class Parser {

    /**
     * Parses the user input and performs the corresponding action.
     *
     * @param userInput The user input command.
     * @param taskList  The TaskList object to manage tasks.
     * @param ui        The Ui object for handling user interface interactions.
     * @param storage   The Storage object for managing task data persistence.
     */
    public static void parseCommand(String userInput, TaskList taskList, Ui ui, Storage storage) {
        try {
            if (userInput.equals("Bye")) {
                ui.showByeMessage();
                storage.writeTasks(taskList.getTasks());
                System.exit(0);
            } else if (userInput.equals("List")) {
                taskList.listTasks();
            } else if (userInput.startsWith("todo")) {
                taskList.addTodoTask(userInput);
            } else if (userInput.startsWith("deadline")) {
                taskList.addDeadlineTask(userInput);
            } else if (userInput.startsWith("event")) {
                taskList.addEventTask(userInput);
            } else if (userInput.startsWith("mark")) {
                taskList.markAsDone(userInput);
            } else if (userInput.startsWith("unmark")) {
                taskList.markAsUndone(userInput);
            } else if (userInput.startsWith("delete")) {
                taskList.deleteTask(userInput);
            } else if (userInput.startsWith("find")) {
                taskList.findTasks(userInput.substring(5).trim());
            } else {
                System.out.println("Invalid command. Please input a valid command.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
