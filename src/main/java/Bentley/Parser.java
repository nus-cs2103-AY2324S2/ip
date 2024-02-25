package bentley;

public class Parser {

    /**
     * Parses the user command and performs the corresponding action.
     *
     * @param input    The user input command.
     * @param taskList The TaskList object to perform actions on.
     * @param ui       The Ui object for user interface interaction.
     * @param storage  The Storage object for reading and writing tasks.
     * @return A string representing the result or feedback of the command.
     * @throws IllegalArgumentException If the command is not recognized.
     */
    public static String parseCommand(String input, TaskList taskList, Ui ui, Storage storage) {
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();

        switch (command) {
            case "bye":
                ui.getByeMessage();
                storage.writeTasks(taskList.getTasks());
                System.exit(0);
            case "list":
                String listResult = taskList.listTasks();
                return listResult;
            case "mark":
                String markResult = taskList.markAsDone(input);
                storage.writeTasks(taskList.getTasks());
                return markResult;
            case "unmark":
                String unmarkResult = taskList.markAsUndone(input);
                storage.writeTasks(taskList.getTasks());
                return unmarkResult;
            case "delete":
                String deleteResult = taskList.deleteTask(input);
                storage.writeTasks(taskList.getTasks());
                return deleteResult;
            case "todo":
                String todoResult = taskList.addTodoTask(input);
                storage.writeTasks(taskList.getTasks());
                return todoResult;
            case "deadline":
                String deadlineResult = taskList.addDeadlineTask(input);
                storage.writeTasks(taskList.getTasks());
                return deadlineResult;
            case "event":
                String eventResult = taskList.addEventTask(input);
                storage.writeTasks(taskList.getTasks());
                return eventResult;
            case "find":
                String findResult = taskList.findTasks(input);
                return findResult;

            default:
                throw new IllegalArgumentException("I'm sorry, but I don't understand that command.");
        }
    }
}
