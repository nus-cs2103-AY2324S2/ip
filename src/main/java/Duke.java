import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class Duke {
    private static String storedTasksPath = "./data/storedTasks.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();
        Storage storage = new Storage(storedTasksPath);
        Ui ui = new Ui();

        storage.load(taskList);

        ui.showWelcomeMessage();
        String message = sc.nextLine();

        while (!message.equalsIgnoreCase("bye")) {
            if (message.equalsIgnoreCase("list")) {
                ui.showTaskList(taskList);
            } else if (message.split(" ")[0].equalsIgnoreCase("mark")) {
                try {
                    String number = message.split(" ")[1];
                    int n = Integer.parseInt(number);
                    taskList.mark(n);
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.showForgetTaskNumber();
                    ui.showMarkFormat();
                } catch (NumberFormatException e) {
                    ui.showMarkFormat();
                }
            } else if (message.split(" ")[0].equalsIgnoreCase("unmark")) {
                try {
                    String number = message.split(" ")[1];
                    int n = Integer.parseInt(number);
                    taskList.unmark(n);
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.showForgetTaskNumber();
                    ui.showUnmarkFormat();
                } catch (NumberFormatException e) {
                    ui.showUnmarkFormat();
                }
            } else if (message.split(" ")[0].equalsIgnoreCase("delete")) {
                try {
                    String number = message.split(" ")[1];
                    int n = Integer.parseInt(number);
                    taskList.delete(n);
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.showForgetTaskNumber();
                    ui.showDeleteFormat();
                } catch (NumberFormatException e) {
                    ui.showDeleteFormat();
                }
            } else if (message.split(" ")[0].equalsIgnoreCase("todo")) {
                try {
                    String task = message.split(" ", 2)[1];
                    taskList.createToDo(task);

                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.showToDoFormat();
                }
            } else if (message.split(" ")[0].equalsIgnoreCase("deadline")) {
                try {
                    String task = message.split(" ", 2)[1];
                    String description = task.split(" /by ", 2)[0];
                    String by = task.split(" /by ", 2)[1];
                    LocalDate byDate = null;
                    try {
                        byDate = LocalDate.parse(by);
                    } catch (DateTimeParseException e) {
                        ui.showDateFormat();
                    }
                    if (byDate != null) {
                        taskList.createDeadline(description, byDate);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.showDeadlineFormat();
                }
            } else if (message.split(" ")[0].equalsIgnoreCase("event")) {
                try {
                    String task = message.split(" ", 2)[1];
                    String description = task.split(" /from ", 2)[0];
                    String fromBy = task.split(" /from ", 2)[1];
                    String from = fromBy.split(" /to ", 2)[0];
                    String to = fromBy.split(" /to ", 2)[1];
                    LocalDate fromDate = null;
                    LocalDate toDate = null;
                    try {
                        fromDate = LocalDate.parse(from);
                        toDate = LocalDate.parse(to);
                    } catch (DateTimeParseException e) {
                        ui.showDateFormat();
                    }
                    if (fromDate != null && toDate != null) {
                        taskList.createEvent(description, fromDate, toDate);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.showEventFormat();
                }
            } else {
                ui.showWrongCommand();
            }
            message = sc.nextLine();
        }
        storage.store(taskList);

        ui.showExitMessage();
    }
}
