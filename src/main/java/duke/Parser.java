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

    /**
     * Constructs a Parser with commands to UI, Storage, and TaskList to handle user commands.
     *
     * @param ui       The user interface component of Duke.
     * @param storage  The storage component responsible for file operations.
     * @param taskList The task list component managing tasks.
     */
    public Parser(Ui ui, Storage storage, TaskList taskList) {
        this.ui = ui;
        this.storage = storage;
        this.taskList = taskList;
    }

    public Parser() {
    }

    /**
     * Processes the user command, and implements tasks based on the command type.
     *
     * @param user_word The command line input by the user.
     * @throws IOException   If there is an error during input/output operations.
     * @throws DukeException If the command is invalid or incomplete.
     */
    public void processCommand(String user_word) throws IOException, DukeException {
        if (user_word.equals("bye")) {
            ui.showGoodbye();
            System.exit(0);
        } if (user_word.equals("list")) {
            taskList.listTasks();
        } else if (user_word.contains("unmark")) {
            int element_index = Integer.parseInt(user_word.split(" ")[1]) - 1;
            taskList.unmarkTask(element_index);
        } else if (user_word.contains("mark")) {
            int element_index = Integer.parseInt(user_word.split(" ")[1]) - 1;
            taskList.markTask(element_index);
        } else if (user_word.equals("deadline")) {
            ui.divider();
            throw new DukeException("ERROR!! Please give the description of deadline.\n______________________________________________________");
        } else if (user_word.contains("deadline")) {
            String[] array_split = user_word.split("/by ");
            String description = array_split[0].substring("deadline".length()).trim();
            LocalDateTime dateTime = parseDateTime(array_split[1].trim());
            Deadline deadline = new Deadline(description, dateTime);
            taskList.addDeadlineTask(deadline);
        } else if (user_word.equals("todo")) {
            ui.divider();
            throw new DukeException("ERROR!! Please give the description of todo.\n______________________________________________________");
        } else if (user_word.contains("todo")) {
            ToDo todo = new ToDo(user_word.substring(5));
            taskList.addTodoTask(todo);
        } else if (user_word.equals("event")) {
            ui.divider();
            String[] parts = user_word.split(" /from | /to ");
            if (parts.length < 3) {
                throw new DukeException("ERROR!! Please provide both start and end time for the event.\n______________________________________________________");
            }
            String description = parts[0].substring("event".length()).trim();
            LocalDate startTime = parseDate(parts[1].trim());
            LocalDate endTime = parseDate(parts[2].trim());

            if (startTime != null && endTime != null) {
                Event temp_event = new Event(description, startTime, endTime);
                taskList.addEventTask(temp_event);
            } else {
                System.out.println("Invalid format for event times.");
            }
            throw new DukeException("ERROR!! Please give the description of event.\n______________________________________________________");
        } else if (user_word.contains("event")) {
            String[] event = user_word.split("/from | /to ");
            Event temp_event = new Event(event[0].substring(6),
                    LocalDate.parse(event[1]), LocalDate.parse(event[2]));
            taskList.addEventTask(temp_event);
        } else if (user_word.contains("delete")) {
            int deleted_index = Integer.parseInt(user_word.split(" ")[1]);
            taskList.deleteTask(deleted_index);
        } else {
            ui.divider();
            throw new DukeException("ERROR!! I can't understand what you mean by that\n______________________________________________________");
        }
    }

    private static LocalDate parseDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd, e.g., 2023-03-15");
            return null;
        }
    }

    private static LocalDateTime parseDateTime(String dateTimeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use d/M/yyyy HHmm, e.g., 2/12/2019 1800");
            return null;
        }
    }

    private void handleDeadline(String userWord) throws DukeException, IOException {
        if (!userWord.contains("/by")) {
            throw new DukeException("ERROR!! Please provide a deadline using '/by'.\n______________________________________________________");
        }
        String[] parts = userWord.split("/by", 2);
        String description = parts[0].substring(9).trim();
        LocalDateTime dateTime = LocalDateTime.parse(parts[1].trim(), DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        Deadline deadline = new Deadline(description, dateTime);
        taskList.addTask(deadline);
    }

    private void handleTodo(String userWord) throws DukeException, IOException {
        if (userWord.trim().equals("todo")) {
            throw new DukeException("ERROR!! Please give the description of todo.\n______________________________________________________");
        }
        String description = userWord.substring(5).trim();
        ToDo todo = new ToDo(description);
        taskList.addTask(todo);
    }

    private void handleEvent(String userWord) throws DukeException, IOException {
        if (!userWord.contains("/from") && !userWord.contains("/to")) {
            throw new DukeException("ERROR!! Please provide both start and end dates for the event using '/from' and '/to'.\n______________________________________________________");
        }
        try {
            String description = userWord.substring(5, userWord.indexOf("/from")).trim();
            String fromDateStr = userWord.substring(userWord.indexOf("/from") + 6, userWord.indexOf("/to")).trim();
            String toDateStr = userWord.substring(userWord.indexOf("/to") + 4).trim();

            LocalDate startTime = LocalDate.parse(fromDateStr, DateTimeFormatter.ISO_LOCAL_DATE);
            LocalDate endTime = LocalDate.parse(toDateStr, DateTimeFormatter.ISO_LOCAL_DATE);

            Event event = new Event(description, startTime, endTime);
            taskList.addTask(event);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format. Please use YYYY-MM-DD, e.g., 2023-09-15");
        }
    }


}

