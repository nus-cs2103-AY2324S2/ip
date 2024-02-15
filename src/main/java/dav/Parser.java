package dav;

/**
 * Parses user input and performs corresponding actions on the task list.
 */
class Parser {

    /**
     * Parses the user input and performs the corresponding actions.
     * @param input User input to be parsed.
     * @param tasks TaskList object to perform actions on.
     * @param ui Ui object to handle user interface interactions.
     * @param storage Storage object for data storage operations.
     */
    public static void parseUserInput(String input, TaskList tasks, Ui ui, Storage storage) {
        System.out.println("____________________________________________________________");

        try {
            if (input.equalsIgnoreCase("list")) {
                tasks.listTasks();
            } else if (input.startsWith("mark ")) {
                int taskIndex = Integer.parseInt(input.substring(5).trim());
                tasks.markTaskDone(taskIndex);
            } else if (input.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(input.substring(7).trim());
                tasks.unmarkTaskDone(taskIndex);
            } else if (input.startsWith("todo")) {
                tasks.addTodoTask(input.substring(4).trim());
            } else if (input.startsWith("deadline")) {
                tasks.addDeadlineTask(input.substring(8).trim());
            } else if (input.startsWith("event")) {
                tasks.addEventTask(input.substring(5).trim());
            } else if (input.startsWith("check ")) {
                String dateString = input.substring(6).trim();
                tasks.checkTasksOnDate(dateString);
            } else if (input.startsWith("delete")) {
                int taskIndex = Integer.parseInt(input.substring(6).trim());
                tasks.deleteTask(taskIndex);
            } else if (!input.equalsIgnoreCase("bye")) {
                throw new IllegalArgumentException("Huh? what's that?");
            }
        } catch (NumberFormatException e) {
            System.out.println("This is not a valid task index.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("____________________________________________________________");
    }
}
