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
    public static String parseUserInput(String input, TaskList tasks, Ui ui, Storage storage) {
        try {
            if (input.equalsIgnoreCase("list")) {
                return tasks.listTasks();
            } else if (input.startsWith("mark ")) {
                int taskIndex = Integer.parseInt(input.substring(5).trim());
                return tasks.markTaskDone(taskIndex);
            } else if (input.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(input.substring(7).trim());
                return tasks.unmarkTaskDone(taskIndex);
            } else if (input.startsWith("todo")) {
                return tasks.addTodoTask(input.substring(4).trim());
            } else if (input.startsWith("deadline")) {
                return tasks.addDeadlineTask(input.substring(8).trim());
            } else if (input.startsWith("event")) {
                return tasks.addEventTask(input.substring(5).trim());
            } else if (input.startsWith("check ")) {
                String dateString = input.substring(6).trim();
                return tasks.checkTasksOnDate(dateString);
            } else if (input.startsWith("delete")) {
                int taskIndex = Integer.parseInt(input.substring(6).trim());
                return tasks.deleteTask(taskIndex);
            } else if (input.startsWith("find ")) {
                String keyword = input.substring(5).trim();
                return tasks.findTasks(keyword);
            } else if (input.equalsIgnoreCase("bye")) {
                return tasks.exit();
            } else {
                throw new IllegalArgumentException("Huh? what's that?");
            }
        } catch (NumberFormatException e) {
            return "This is not a valid task index.";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
}