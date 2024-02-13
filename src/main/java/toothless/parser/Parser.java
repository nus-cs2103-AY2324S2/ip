package toothless.parser;

import java.util.ArrayList;

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
     * @param userInput User input string to be parsed.
     * @param tasks TaskList class that is operated on when user command is executed.
     * @param ui Ui class used to print messages.
     * @return String of response.
     * @throws ToothlessException if user input is invalid.
     */
    public String parseInput(String userInput, TaskList tasks, Ui ui) throws ToothlessException {
        String response = "";
        if (userInput.equals("bye")) {
            response = "Bye. Purr-lease chat again soon!";
        } else if (userInput.equals("list")) {
            response = ui.getListMessage(tasks.getTasks(),
                    "Oops! Looks like you haven't added any tasks yet!",
                    "Here are the tasks in your list:\n");
        } else if (userInput.startsWith("mark ") || userInput.equals("mark")) {
            int listIndex = validateListInput(userInput, "mark", tasks.size());
            Task markedTask = tasks.markTask(listIndex);
            response = ui.getMarkedTaskMessage(markedTask);
        } else if (userInput.startsWith("unmark ") || userInput.equals("unmark")) {
            int listIndex = validateListInput(userInput, "unmark", tasks.size());
            Task unmarkedTask = tasks.unmarkTask(listIndex);
            response = ui.getUnmarkedTaskMessage(unmarkedTask);
        } else if (userInput.startsWith("todo ") || userInput.equals("todo")) {
            String taskDescription = validateToDoInput(userInput);
            Task newTask = tasks.addToDoToList(taskDescription);
            response = ui.getNewTaskMessage(newTask, tasks.size());
        } else if (userInput.startsWith("deadline ") || userInput.equals("deadline")) {
            String[] deadlineAttributes = validateDeadlineInput(userInput);
            Task newTask = tasks.addDeadlineToList(deadlineAttributes[0], deadlineAttributes[1]);
            response = ui.getNewTaskMessage(newTask, tasks.size());
        } else if (userInput.startsWith("event ") || userInput.equals("event")) {
            String[] eventAttributes = validateEventInput(userInput);
            Task newTask = tasks.addEventToList(eventAttributes[0], eventAttributes[1], eventAttributes[2]);
            response = ui.getNewTaskMessage(newTask, tasks.size());
        } else if (userInput.startsWith("delete ") || userInput.equals("delete")) {
            int listIndex = validateListInput(userInput, "delete", tasks.size());
            Task deletedTask = tasks.deleteTask(listIndex);
            response = ui.getDeletedTaskMessage(deletedTask, tasks.size());
        } else if (userInput.startsWith("find ") || userInput.equals("find")) {
            String keyword = validateFindInput(userInput);
            ArrayList<Task> keywordTasks = tasks.findKeyword(keyword);
            response = ui.getListMessage(keywordTasks,
                    "Oops! Looks like there are no tasks matching the keyword!",
                    "Here are the meow-tching tasks in your list:\n");
        } else {
            throw new ToothlessException("Sorry, I don't understand what that means D:");
        }
        return response;
    }

    /**
     * Validates the user input for commands dealing with list item.
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
     * @param deadlineInput User input string for deadline command.
     * @return String array containing task description and /by field.
     * @throws ToothlessException if user input is invalid or in the wrong format.
     */
    public String[] validateDeadlineInput(String deadlineInput) throws ToothlessException {
        String[] deadlineAttributes = deadlineInput.replace("deadline ", "")
                .strip().split("\\s+/by\\s+");

        if (deadlineAttributes.length != 2) {
            throw new ToothlessException("Sorry, purr-lease use the format: "
                    + "deadline [description] /by [yyyy-mm-dd hh:mm].");
        } else if (deadlineAttributes[0].isBlank()) {
            throw new ToothlessException("Apurrlogies, the task description cannot be empty.");
        } else if (deadlineAttributes[1].isBlank()) {
            throw new ToothlessException("Apurrlogies, the /by field cannot be empty.");
        }

        return deadlineAttributes;
    }

    /**
     * Validates the user input for event command.
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
            throw new ToothlessException("Sorry, purr-lease use the format: "
                    + "event [description] /from [yyyy-mm-dd hh:mm] /to [yyyy-mm-dd hh:mm]");
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

    /**
     * Validates the user input for find command.
     * @param findInput The user input for find command.
     * @return The keyword to be found.
     * @throws ToothlessException if keyword is blank.
     */
    public String validateFindInput(String findInput) throws ToothlessException {
        String keyword = findInput.replace("find ", "").strip();
        if (keyword.isBlank()) {
            throw new ToothlessException("Apurrlogies, the keyword cannot be empty.");
        }
        return keyword;
    }
}
