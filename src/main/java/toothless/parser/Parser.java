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
     * Parses user input.
     *
     * @param tasks TaskList that is operated on when user command is executed.
     * @param ui Ui class used to print messages.
     * @param userInput User input string to be parsed.
     * @return String of response.
     * @throws ToothlessException if user input is invalid.
     */
    public String parseInput(TaskList tasks, Ui ui, String userInput) throws ToothlessException {
        String response = "";
        if (userInput.startsWith("list")) {
            response = ui.getListMessage(tasks.getTasks(),
                    "Oops! Looks like you haven't added any tasks yet!",
                    "Here are the tasks in your list:\n");
        } else if (userInput.startsWith("mark")) {
            response = parseIndexInput(tasks, ui, userInput, "mark");
        } else if (userInput.startsWith("unmark")) {
            response = parseIndexInput(tasks, ui, userInput, "unmark");
        } else if (userInput.startsWith("delete")) {
            response = parseIndexInput(tasks, ui, userInput, "delete");
        } else if (userInput.startsWith("find")) {
            response = parseFindInput(tasks, ui, userInput);
        } else if (userInput.startsWith("tag")) {
            response = parseTagInput(tasks, ui, userInput, "tag");
        } else if (userInput.startsWith("untag")) {
            response = parseTagInput(tasks, ui, userInput, "untag");
        } else if (userInput.startsWith("todo")) {
            response = parseToDoInput(tasks, ui, userInput);
        } else if (userInput.startsWith("deadline")) {
            response = parseDeadlineInput(tasks, ui, userInput);
        } else if (userInput.startsWith("event")) {
            response = parseEventInput(tasks, ui, userInput);
        } else {
            throw new ToothlessException("Sorry, I don't understand what that means D:");
        }
        return response;
    }

    /**
     * Parses the user input for commands dealing with list indexes.
     *
     * @param tasks TaskList that is operated on when user command is executed.
     * @param ui Ui class used to print messages.
     * @param indexInput User input string for list item to be validated.
     * @param command The user command that uses the list item.
     * @return String of response to list item commands.
     * @throws ToothlessException if user input is invalid or in the wrong format.
     */
    public String parseIndexInput(TaskList tasks, Ui ui, String indexInput, String command) throws ToothlessException {
        // check format
        if (!indexInput.matches("(mark|unmark|delete)\\s\\d+")) {
            throw new ToothlessException(
                    String.format("Sorry, purr-lease use the format: %s <listIndex>", command));
        }
        // split input and get integer
        String[] listInputSplit = indexInput.strip().split("\\s+");
        int listIndex = Integer.parseInt(listInputSplit[1]);
        // check if within list index
        if (listIndex < 1 || listIndex > tasks.getSize()) {
            throw new ToothlessException("Apurrlogies, there's no task at that index.");
        }
        // operate on tasklist according to command
        switch (command) {
        case "mark":
            Task markedTask = tasks.markTask(listIndex);
            return ui.getMarkedTaskMessage(markedTask);
        case "unmark":
            Task unmarkedTask = tasks.unmarkTask(listIndex);
            return ui.getUnmarkedTaskMessage(unmarkedTask);
        case "delete":
            Task deletedTask = tasks.deleteTask(listIndex);
            return ui.getDeletedTaskMessage(deletedTask, tasks.getSize());
        default:
            throw new ToothlessException("Sorry, I don't understand what that means D:");
        }
    }

    /**
     * Parses the user input for find command.
     *
     * @param tasks TaskList where keyword is to be searched.
     * @param ui Ui class used to print messages.
     * @param findInput The user input for find command.
     * @return String response of find command.
     * @throws ToothlessException if input is invalid or wrong format.
     */
    public String parseFindInput(TaskList tasks, Ui ui, String findInput) throws ToothlessException {
        if (!findInput.matches("find\\s[^/]+")) {
            throw new ToothlessException("Apurrlogies, please use the format: find <keyword>");
        }
        String keyword = findInput.replace("find", "").strip();
        if (keyword.isBlank()) {
            throw new ToothlessException("Sorry, keyword cannot be empty.");
        }
        ArrayList<Task> keywordTasks = tasks.findKeyword(keyword);
        return ui.getListMessage(keywordTasks,
                "Oops! Looks like there are no tasks matching the keyword!",
                "Here are the meow-tching tasks in your list:\n");
    }

    /**
     * Parses the user input for tag and untag command.
     *
     * @param tasks TaskList where task tags operations are performed.
     * @param ui Ui class used to print messages.
     * @param tagInput The user input for tag commands.
     * @param command The specific tag command.
     * @return String response of tag or untag command.
     * @throws ToothlessException if input is invalid or wrong format.
     */
    public String parseTagInput(TaskList tasks, Ui ui, String tagInput, String command) throws ToothlessException {
        if (!tagInput.matches("(tag|untag)\\s\\d+\\s[^/]+")) {
            throw new ToothlessException(
                    String.format("Sorry, purr-lease use the format: %s <taskListIndex> <tagLabel>", command));
        }
        String[] tagInputSplit = tagInput.strip().split("\\s+");
        int listIndex = Integer.parseInt(tagInputSplit[1]);
        // check if within list index
        if (listIndex < 1 || listIndex > tasks.getSize()) {
            throw new ToothlessException("Apurrlogies, there's no task at that index.");
        }
        // operate on tasklist according to command
        switch (command) {
        case "tag":
            Task taggedTask = tasks.addTaskTag(listIndex, tagInputSplit[2]);
            return ui.getTaggedTaskMessage(taggedTask);
        case "untag":
            Task untaggedTask = tasks.untagTask(listIndex, tagInputSplit[2]);
            return ui.getUntaggedTaskMessage(untaggedTask);
        default:
            throw new ToothlessException("Sorry, I don't understand what that means D:");
        }
    }

    /**
     * Validates the user input for todo command.
     *
     * @param tasks TaskList that new ToDo Task is added to.
     * @param ui Ui class used to print messages.
     * @param toDoInput User input string for todo command.
     * @return String response of todo command.
     * @throws ToothlessException if user input is invalid or wrong format.
     */
    public String parseToDoInput(TaskList tasks, Ui ui, String toDoInput) throws ToothlessException {
        if (!toDoInput.matches("todo\\s[^/]+")) {
            throw new ToothlessException("Apurrlogies, please use the format: todo <description>");
        }
        String taskDescription = toDoInput.replace("todo", "").strip();
        if (taskDescription.isBlank()) {
            throw new ToothlessException("Apurr-logies, task description cannot be empty.");
        }
        Task newTask = tasks.addToDoToList(taskDescription);
        return ui.getNewTaskMessage(newTask, tasks.getSize());
    }

    /**
     * Validates the user input for deadline command.
     *
     * @param tasks TaskList that new Deadline Task is added to.
     * @param ui Ui class used to print messages.
     * @param deadlineInput User input string for deadline command.
     * @return String response of deadline command.
     * @throws ToothlessException if user input is invalid or in the wrong format.
     */
    public String parseDeadlineInput(TaskList tasks, Ui ui, String deadlineInput) throws ToothlessException {
        if (!deadlineInput.matches("deadline\\s[^/]+\\s/by\\s\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}")) {
            throw new ToothlessException("Sorry, purr-lease use the format: "
                    + "deadline <description> /by <yyyy-mm-dd hh:mm>.");
        }
        String[] deadlineAttributes = deadlineInput.replace("deadline", "")
                .strip().split("\\s*/by\\s*");
        if (deadlineAttributes[0].isBlank()) {
            throw new ToothlessException("Sorry, task description cannot be empty.");
        }
        Task newTask = tasks.addDeadlineToList(deadlineAttributes[0], deadlineAttributes[1]);
        return ui.getNewTaskMessage(newTask, tasks.getSize());
    }

    /**
     * Validates the user input for event command.
     *
     * @param tasks TaskList that new Event Task is added to.
     * @param ui Ui class used to print messages.
     * @param eventInput User input string for event command.
     * @return String response of event command.
     * @throws ToothlessException if user input is invalid or in the wrong format.
     */
    public String parseEventInput(TaskList tasks, Ui ui, String eventInput) throws ToothlessException {
        if (!eventInput.matches("event\\s[^/]+\\s/from\\s\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}\\s"
                + "/to\\s\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}")) {
            throw new ToothlessException("Sorry, purr-lease use the format: "
                    + "event <description> /from <yyyy-mm-dd hh:mm> /to <yyyy-mm-dd hh:mm>");
        }
        String[] eventAttributes = eventInput.replace("event", "")
                .strip().split("\\s*/from\\s*|\\s*/to\\s*");
        if (eventAttributes[0].isBlank()) {
            throw new ToothlessException("Apurrlogies, the task description cannot be empty.");
        }
        Task newTask = tasks.addEventToList(eventAttributes[0], eventAttributes[1], eventAttributes[2]);
        return ui.getNewTaskMessage(newTask, tasks.getSize());
    }
}
