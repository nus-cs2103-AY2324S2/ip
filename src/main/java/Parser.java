public class Parser {

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
            } else {
                System.out.println("Invalid command. Please input a valid command.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
