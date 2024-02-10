import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Parser {
    private Ui ui = new Ui();
    private TaskList taskList;
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    public void parse() {
        ui.showWelcomeMessage();

        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();
        String firstWord = message.split(" ")[0].toLowerCase();

        while (!message.equalsIgnoreCase("bye")) {
            switch (firstWord) {
                case "list":
                    ui.showTaskList(taskList);
                    break;
                case "mark":
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
                    break;
                case "unmark":
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
                    break;
                case "delete":
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
                    break;
                case "todo":
                    try {
                        String task = message.split(" ", 2)[1];
                        taskList.createToDo(task);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        ui.showToDoFormat();
                    }
                    break;
                case "deadline":
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
                    break;
                case "event":
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
                    break;
                default:
                    ui.showWrongCommand();
                    break;
            }
            message = sc.nextLine();
            firstWord = message.split(" ")[0].toLowerCase();
        }
        ui.showExitMessage();
    }
}
