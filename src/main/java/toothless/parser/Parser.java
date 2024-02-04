package toothless.parser;

import toothless.exception.ToothlessException;
import toothless.task.Task;
import toothless.task.TaskList;
import toothless.ui.Ui;

/**
 * A class that parses, resolves and validates user input.
 */
public class Parser {
    /**
     * The main method that parses user input.
     * 
     * @param userInput User input string to be parsed.
     * @param taskList TaskList class that is operated on when user command is executed.
     * @param ui Ui class used to print messages.
     * @return Boolean of isRunning in Toothless. True if program continues, False if program stops.
     * @throws ToothlessException if user input is invalid.
     */
    public boolean parseInput(String userInput, TaskList taskList, Ui ui) throws ToothlessException {
        if (userInput.equals("bye")) {
            ui.printMessage("Bye. Purr-lease chat again soon!");
            return false;
        } else if (userInput.equals("list")) {
            ui.printList(taskList.getTaskList());
        } else if (userInput.startsWith("mark ") || userInput.equals("mark")) {
            int listIndex = validateListInput(userInput, "mark", taskList.size());
            Task markedTask = taskList.markTask(listIndex);
            ui.printMarkedTask(markedTask);
        } else if (userInput.startsWith("unmark ") || userInput.equals("unmark")) {
            int listIndex = validateListInput(userInput, "unmark", taskList.size());
            Task unmarkedTask = taskList.unmarkTask(listIndex);
            ui.printUnmarkedTask(unmarkedTask);
        } else if (userInput.startsWith("todo ") || userInput.equals("todo")) {
            String taskDescription = validateToDoInput(userInput);
            Task newTask = taskList.addToDoToList(taskDescription);
            ui.printNewTask(newTask, taskList.size());
        } else if (userInput.startsWith("deadline ") || userInput.equals("deadline")) {
            String[] deadlineAttributes = validateDeadlineInput(userInput);
            Task newTask = taskList.addDeadlineToList(deadlineAttributes[0], deadlineAttributes[1]);
            ui.printNewTask(newTask, taskList.size());
        } else if (userInput.startsWith("event ") || userInput.equals("event")) {
            String[] eventAttributes = validateEventInput(userInput);
            Task newTask = taskList.addEventToList(eventAttributes[0], eventAttributes[1], eventAttributes[2]);
            ui.printNewTask(newTask, taskList.size());
        } else if (userInput.startsWith("delete ") || userInput.equals("delete")) {
            int listIndex = validateListInput(userInput, "delete", taskList.size());
            Task deletedTask = taskList.deleteTask(listIndex);
            ui.printDeletedTask(deletedTask, taskList.size());
        } else {
            throw new ToothlessException("Sorry, I don't understand what that means D:");
        }
        return true;
    }

    /**
     * Validates the user input for commands dealing with list item.
     * 
     * @param listInput User input string for list item to be validated.
     * @param command The user command that uses the list item.
     * @param taskListSize The size of the taskList.
     * @return Integer of the list index to be retrieved.
     * @throws ToothlessException if user input is invalid or in the wrong format.
     */
    public int validateListInput(String listInput, String command, int taskListSize) throws ToothlessException {
        // split string by spaces
        String[] markInputSplit = listInput.strip().split("\\s+");
        try {
            if (markInputSplit.length > 2) {
                throw new ToothlessException(
                        String.format("Sorry, purr-lease only include one numeric argument after %s.", command));
            } else if (markInputSplit.length < 2 || markInputSplit[1].isBlank()) {
                throw new ToothlessException(String.format("Sorry, purr-lease state a list index to %s.", command));
            }
            // try parsing integer
            int listIndex = Integer.parseInt(markInputSplit[1]);
            // check index bounds
            if (listIndex < 1 || listIndex > taskListSize) {
                throw new ToothlessException("Apurrlogies, there's no task at that index.");
            }
            return listIndex;
        } catch (NumberFormatException e) {
            throw new ToothlessException(String.format("Sorry, purr-lease use a numeric list index to %s.", command));
        }
    }

    /**
     * Validates the user input for todo command.
     * 
     * @param toDoInput User input string for todo command.
     * @return The todo task description.
     * @throws ToothlessException if task description is empty.
     */
    public String validateToDoInput(String toDoInput) throws ToothlessException {
        String taskDescription = toDoInput.replace("todo ", "").strip();
        if (taskDescription.isBlank()) {
            throw new ToothlessException("Apurrlogies, the task description cannot be empty.");
        }
        return taskDescription;
    }

    /**
     * Validates the user input for deadline command.
     * 
     * @param deadlineInput User input string for deadline command.
     * @return String array containing task description and /by field.
     * @throws ToothlessException if user input is invalid or in the wrong format.
     */
    public String[] validateDeadlineInput(String deadlineInput) throws ToothlessException {
        String[] deadlineAttributes = deadlineInput.replace("deadline ", "")
                .strip().split("\\s+/by\\s+");

        if (deadlineAttributes.length != 2) {
            throw new ToothlessException("Sorry, purr-lease use the format: " +
                    "deadline [description] /by [yyyy-mm-dd hh:mm].");
        } else if (deadlineAttributes[0].isBlank()) {
            throw new ToothlessException("Apurrlogies, the task description cannot be empty.");
        } else if (deadlineAttributes[1].isBlank()) {
            throw new ToothlessException("Apurrlogies, the /by field cannot be empty.");
        }

        return deadlineAttributes;
    }

    /**
     * Validates the user input for event command.
     * 
     * @param eventInput User input string for event command.
     * @return String array containing task description, /from field and /to field.
     * @throws ToothlessException if user input is invalid or in the wrong format.
     */
    public String[] validateEventInput(String eventInput) throws ToothlessException {
        String[] eventAttributes = new String[3];
        String[] tempAttributes = eventInput.replace("event ", "")
                .strip().split("\\s+/from\\s+|\\s+/to\\s+");

        int fromIndex = eventInput.indexOf("/from");
        int toIndex = eventInput.indexOf("/to");

        if (tempAttributes.length != 3) {
            throw new ToothlessException("Sorry, purr-lease use the format: " +
                    "event [description] /from [yyyy-mm-dd hh:mm] /to [yyyy-mm-dd hh:mm]");
        } else if (fromIndex == -1 || toIndex == -1) {
            throw new ToothlessException("Sorry, purr-lease remember to include the /from and /to fields.");
        } else if (tempAttributes[0].isBlank()) {
            throw new ToothlessException("Apurrlogies, the task description cannot be empty.");
        } else if (tempAttributes[1].isBlank() || tempAttributes[2].isBlank()) {
            throw new ToothlessException(("Apurrlogies, the /from and /to fields cannot be empty."));
        }

        eventAttributes[0] = tempAttributes[0];

        if (fromIndex < toIndex) {
            eventAttributes[1] = tempAttributes[1];
            eventAttributes[2] = tempAttributes[2];
        } else {
            eventAttributes[1] = tempAttributes[2];
            eventAttributes[2] = tempAttributes[1];
        }

        return eventAttributes;
    }
}
