import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class Parser {
    private String input;
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    public Parser(String input, Ui ui, TaskList tasks, Storage storage) {
        this.input = input;
        this.ui = ui;
        this.tasks = tasks;
        this.storage = storage;
    }

    public void parse() {
        if ("bye".equalsIgnoreCase(input)) {
            ui.printBye();
            ui.printLine();
            return;
        }

        if ("list".equalsIgnoreCase(input)) {
            ui.printList(tasks);
            ui.printLine();
            return;
        }

        if (input.startsWith("mark") || input.startsWith("unmark")) {
            handleMarking(input);
            ui.printLine();
            return;
        }

        if (input.startsWith("remove")) {
            handleRemove(input);
            ui.printLine();
            return;
        }


        if(input.startsWith("deadline")) {
            if (!validateDeadlineInput(input)) {
                ui.invalidTask();
                ui.printLine();
                return;
            }

            handleDeadlines(input);
            ui.printLine();
            return;
        }

        if(input.startsWith("todo")) {
            if (!validateTodoInput(input)) {
                System.out.println("Sorry, please complete your request by specifying the details of the task!");
                ui.printLine();
            }

            handleTodos(input);
            ui.printLine();
        }

        if(input.startsWith("event")) {
            if (!validateEventInput(input)) {
                ui.invalidTask();
                ui.printLine();
            }

            handleEvents(input);
            ui.printLine();
        }

        if (input.startsWith("delete")) {
            handleRemove(input);
            ui.printLine();
        }

        ui.invalidRequest();
    }


    private void handleRemove(String input) {
        String[] splitParts = input.split(" ");
        if (splitParts.length < 2) {
            ui.printWrongTaskNumber();
            return;
        }

        try {
            int index = Integer.parseInt(splitParts[1]) - 1;
            Task removedTask = tasks.remove(index);
            storage.saveTasks();
            System.out.println("Ok! I have removed this task from your list:\n  " + removedTask);
            System.out.println("Now you have " + tasks.size() + " tasks in your list.");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            ui.printWrongTaskNumber();
        }
    }

    /**
     * Handles marking or unmarking tasks as done based on user input.
     * @param input User input specifying the action and task index.
     */

    private void handleMarking(String input) {
        String[] split = input.split(" ");
        if (split.length < 2) {
            ui.printWrongTaskNumber();
            return;
        }

        try {
            int index = Integer.parseInt(split[1]) - 1;
            Task task = tasks.get(index);

            if ("mark".equalsIgnoreCase(split[0])) {
                task.markAsDone();
                storage.saveTasks();
                ui.printMarked(task);
            }

            else if ("unmark".equalsIgnoreCase(split[0])) {
                task.unmarkTask();
                storage.saveTasks();
                ui.printNotMarked(task);
            }

        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            ui.printWrongTaskNumber();
        }
    }


    /**
     * Handles the creation and addition of Deadline tasks to the task list.
     * @param input User input specifying the Deadline description and due date.
     */
    private void handleDeadlines(String input) {
        String[] splitParts = input.substring(9).split("/by", 2);

        if (splitParts.length > 1) {
            String description = splitParts[0].trim();
            String date = splitParts[1].trim();
            if (isValidDate(date)) {
                try {
                    LocalDate d1 = LocalDate.parse(date, DateTimeFormatter.ofPattern("M/d/yyyy HHmm"));
                    Deadlines deadline = new Deadlines(description, d1);
                    tasks.add(deadline);
                    storage.saveTasks();
                    ui.addDeadline(deadline);
                    System.out.println("Now you have " + tasks.size() + " tasks in your list.");
                } catch (DateTimeParseException e) {
                    ui.invalidDate();
                }
            } else {
                ui.invalidDate();
                Deadlines deadline = new Deadlines(description, date);
                tasks.add(deadline);
                storage.saveTasks();
                ui.addDeadline(deadline);
            }
        } else {
            ui.invalidDate();
        }
    }

    /**
     * Handles the creation and addition of Todo tasks to the task list.
     *
     * @param input User input specifying the Todo description.
     */
    private void handleTodos(String input) {
        String description = input.substring(5).trim();
        Todo todo = new Todo(description);
        tasks.add(todo);
        storage.saveTasks();
        System.out.println("Ok! I've added this todo: " + todo);
        System.out.println("Now you have " + tasks.size() + " tasks in your list.");
    }

    private void handleEvents(String input) {
        String[] splitParts = input.substring(6).split("/from", 2);
        String[] splitTo = splitParts[1].split("/to", 2);

        if (splitParts.length > 1) {
            String description = splitParts[0].trim();
            String fromDate = splitTo[0].trim();
            String toDate = splitTo[1].trim();
            if (isValidDate(fromDate)) {
                LocalDate d1 = LocalDate.parse(fromDate);
                if (isValidDate(toDate)) {
                    LocalDate d2 = LocalDate.parse(toDate);
                    Event event = new Event(description, d1, d2);
                    tasks.add(event);
                    storage.saveTasks();
                    System.out.println("Ok! I've added this event: " + event);
                }
            } else {
                Event event = new Event(description, fromDate, toDate);
                tasks.add(event);
                storage.saveTasks();
                System.out.println("Ok! I've added this event: " + event);
            }
            System.out.println("Now you have " + tasks.size() + " tasks in your list.");

        } else {
            ui.invalidDate();
        }
    }

    private static boolean isValidDate(String input) {
        try {
            LocalDate.parse(input, DateTimeFormatter.ofPattern("M/d/yyyy HHmm"));
            return true;
        } catch (DateTimeParseException e1) {
            try {
                LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return true;
            } catch (DateTimeParseException e2) {
                return false;
            }

        }
    }



    /**
     * Validates the input format for creating Todo tasks.
     *
     * @param input User input specifying the Todo description.
     * @return True if the input format is valid, false otherwise.
     */
    private static boolean validateTodoInput(String input) {
        return input.length() > 5;
    }

    /**
     * Validates the input format for creating Deadlines.
     *
     * @param input User input specifying the Deadline description and due date.
     * @return True if the input format is valid, false otherwise.
     */
    private static boolean validateDeadlineInput(String input) {
        String[] splitParts = input.substring(9).split("/by", 2);
        return splitParts.length > 1;
    }

    /**
     * Validates the input format for creating Events.
     *
     * @param input User input specifying the Event description and date range.
     * @return True if the input format is valid, false otherwise.
     */
    private static boolean validateEventInput(String input) {
        String[] splitParts = input.substring(6).split("/from", 2);
        return splitParts.length > 1;
    }

}
