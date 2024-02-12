package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String DATETIME_FORMAT = "d/M/yyyy HHmm";

    public Parser(Ui ui, Storage storage, TaskList taskList) {
        this.ui = ui;
        this.storage = storage;
        this.taskList = taskList;
    }

    public Parser() {
    }

    /**
     * Processes the user command, modifies the task list accordingly, and returns a response message.
     *
     * @param userWord The command input by the user.
     * @return A response message indicating the result of the command.
     * @throws IOException   If an IO exception occurs during file operations.
     * @throws DukeException If the user input is invalid or causes an error.
     */
    public String processCommand(String userWord) throws IOException, DukeException {
        if (userWord.equals("bye")) {
            return "Bye. Hope to see you again soon!";
        } else if (userWord.equals("list")) {
            return handleList(userWord);
        } else if (userWord.startsWith("unmark")) {
            int elementIndex = Integer.parseInt(userWord.split(" ")[1]) - 1;
            return handleUnmark(userWord);
        } else if (userWord.startsWith("mark")) {
            int elementIndex = Integer.parseInt(userWord.split(" ")[1]) - 1;
            return handleMark(userWord);
        } else if (userWord.startsWith("deadline")) {
            return handleDeadline(userWord);
        } else if (userWord.startsWith("todo")) {
            return handleTodo(userWord);
        } else if (userWord.startsWith("event")) {
            return handleEvent(userWord);
        } else if (userWord.startsWith("delete")) {
            int deletedIndex = Integer.parseInt(userWord.split(" ")[1]) - 1;
            return handleDelete(userWord);
        } else if (userWord.startsWith("find")) {
            String keyword = userWord.substring(5).trim();
            return handleFind(keyword);
        } else {
            return "ERROR!! I can't understand what you mean by that.";
        }
    }

    private String handleTodo(String userWord) throws DukeException, IOException {
        assert userWord != null;
        if (userWord.trim().equals("todo")) {
            throw new DukeException("ERROR!! Please give the description of todo.");
        }
        String description = userWord.substring(5).trim();
        ToDo todo = new ToDo(description);
        taskList.addTodoTask(todo);
        return "Got it. I've added this todo: " + todo;
    }

    private String handleEvent(String userWord) throws DukeException, IOException {
        assert userWord != null;
        if (!userWord.contains("/from") || !userWord.contains("/to")) {
            throw new DukeException("ERROR!! Please provide both start and end dates for the event using '/from' and '/to'.");
        }
        try {
            String description = userWord.substring(6, userWord.indexOf("/from")).trim();
            String fromStr = userWord.substring(userWord.indexOf("/from") + 6, userWord.indexOf("/to")).trim();
            String toStr = userWord.substring(userWord.indexOf("/to") + 4).trim();

            LocalDate startTime = LocalDate.parse(fromStr, DateTimeFormatter.ofPattern(DATE_FORMAT));
            LocalDate endTime = LocalDate.parse(toStr, DateTimeFormatter.ofPattern(DATE_FORMAT));
            Event event = new Event(description, startTime, endTime);
            taskList.addEventTask(event);
            return "Got it. I've added this event: " + event;
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format. Please use YYYY-MM-DD, e.g., 2023-09-15");
        }
    }


    private String handleDeadline(String userWord) throws DukeException, IOException {
        assert userWord != null && userWord.contains("/by");
        if (!userWord.contains("/by")) {
            throw new DukeException("ERROR!! Please provide a deadline using '/by'.");
        }
        try {
            String[] parts = userWord.split("/by", 2);
            String description = parts[0].substring(9).trim();
            LocalDateTime dateTime = LocalDateTime.parse(parts[1].trim(), DateTimeFormatter.ofPattern(DATETIME_FORMAT));
            Deadline deadline = new Deadline(description, dateTime);
            taskList.addDeadlineTask(deadline);
            return "Got it. I've added this deadline: " + deadline;
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format. Please use d/M/yyyy HHmm, e.g., 2/12/2019 1800");
        }
    }

    private String handleFind(String userWord) {
        return taskList.findTask(userWord);
    }

    private String handleDelete(String userWord) throws DukeException {
        assert userWord != null && userWord.startsWith("delete");
        try {
            int deletedIndex = Integer.parseInt(userWord.split(" ")[1]) - 1;
            return taskList.deleteTask(deletedIndex);
        } catch (NumberFormatException | IndexOutOfBoundsException | IOException e) {
            throw new DukeException("Please enter a valid task number to delete.");
        }
    }

    private String handleMark(String userWord) throws DukeException {
        assert userWord != null && userWord.startsWith("mark");
        try {
            int elementIndex = Integer.parseInt(userWord.split(" ")[1]) - 1;
            return taskList.markTask(elementIndex);
        } catch (NumberFormatException | IndexOutOfBoundsException | IOException e) {
            throw new DukeException("Please enter a valid task number to mark.");
        }
    }

    private String handleUnmark(String userWord) throws DukeException {
        assert userWord != null && userWord.startsWith("unmark");
        try {
            int elementIndex = Integer.parseInt(userWord.split(" ")[1]) - 1;
            return taskList.unmarkTask(elementIndex);
        } catch (NumberFormatException | IndexOutOfBoundsException | IOException e) {
            throw new DukeException("Please enter a valid task number to unmark.");
        }
    }

    private String handleList(String userWord) throws IOException {
        assert userWord != null && userWord.equals("list");
        return taskList.listTasks();
    }


}
