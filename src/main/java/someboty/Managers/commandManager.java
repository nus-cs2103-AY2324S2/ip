package someboty.Managers;

import java.util.ArrayList;

import someboty.Exceptions.InputException;
import someboty.Exceptions.TerminateException;

import someboty.Tasks.Task;

public class commandManager {

    private static final String[] commands = {
        "help",
        "list",
        "dateFormats",
        "find",
        "mark", 
        "unmark", 
        "delete",
        "clear",
        "deadline", 
        "event", 
        "todo"
    };

    private taskManager manager;

    // CONSTRUCTOR
    public commandManager(taskManager taskList) {
        this.manager = taskList;
    }

    protected String parse(String input) {

        String command = input.split(" ")[0];

        if (command.equals("bye")) {
            // bypass chain of command straight to Duke.java
            manager.close();
            throw new TerminateException("LMAO XD");
        }
        
        try {
            switch (command) {
            
            case "list":
                return manager.printListTasks();

            case "help":
                return listCommands();

            case "dateFormats":
                return dateManager.validDateFormats();

            case "find":
                return findTasks(input);

            case "mark":
                return setTaskStatus(input, true);

            case "unmark":
                return setTaskStatus(input, false);

            case "delete":
                return deleteTask(input);

            case "clear":
                return clearTaskList();

            case "todo":
                return addTask('T', input);

            case "deadline":
                return addTask('D', input);

            case "event":
                return addTask('E', input);

            default:
                return "Command not recognized.\n"
                    + "Type 'help' to get the list of valid commands.";
            }

        } catch (InputException e) {
            return e.getMessage();
        }
    }

    private static String listCommands() {
        String response = "Here are the list of commands:\n";

        for (String command : commands) {
            response += String.format(" - %s\n", command);
        }

        return response;
    }

    private static String getDescription(String string) {
        String[] listOfStrings = string.trim().split(" ");
        String description = "";

        if (listOfStrings.length <= 1) {
            throw new InputException("Task description is not recognized.");
        }
        
        // join the split words together
        for (int i = 1; i < listOfStrings.length; i++) {
            description += listOfStrings[i] + " ";
        }

        return description.trim();
    }

    // mark/unmark a given task at the specified index as completed.
    private String setTaskStatus(String input, boolean status) {
        String description = getDescription(input);
        int index;

        try {
            index = Integer.parseInt(description) - 1;

        } catch(NumberFormatException e) {
            throw new InputException(
                "Unable to determine which task to mark or unmark.\n"
                + "Please use the format: 'mark TASK_NUMBER' or 'unmark TASK_NUMBER'"
                );
        }

        Task task = this.manager.setTaskStatus(index, status);
        return status
                ? "Uppzz lah so hardworking!\n " + task
                : "O...k... as you wish I guess...!\n " + task;
    }

    // delete a task at the specified index.
    private String deleteTask(String input) {
        String description = getDescription(input);
        int index;

        try {
            index = Integer.parseInt(description) - 1;

        } catch(NumberFormatException e) {
            throw new InputException(
                "Unable to determine which task to delete.\n"
                + "Please use the format: 'mark TASK_NUMBER' or 'unmark TASK_NUMBER'"
                );
        }

        Task deletedTask = this.manager.deleteTask(index);
        return "Noted. I've removed this task:\n"
            + String.format("  %s\n", deletedTask)
            + String.format("Now you have %d tasks in the list.", this.manager.getListSize());
    }

    private String clearTaskList() {
        this.manager.clear();
        return "Huh. Your list is magically gone!";
    }

    private String addTask(char type, String input) {
        String description = getDescription(input);
        Task newTask = this.manager.addTask(type, description);

        return "Got it. I've added this task:\n"
            + String.format("  %s\n", newTask)
            + String.format("Now you have %d tasks in the list.\n", this.manager.getListSize())
            +"(Type 'list' to see the full list of tasks)";
    }

    private String findTasks(String input) {
        String description = getDescription(input);
        ArrayList<Task> taskList = manager.findTasks(description);

        if (taskList.size() == 0) { // special case for when no matches found
            return "Huh. I couldn't find anything in this deep lonely abyss...";
        }

        int index = 1;
        String response = "Here are the matching tasks I could find I guess...";

        for (Task task : taskList) {
            response += String.format("\n%d %s", index, task);
            index++;
        }

        return response;
    }
}
