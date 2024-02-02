import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Parser {
    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private Parser() {
    }

    private static Parser instance = null;
    private Ui ui = null;
    private Liv liv = null;
    private TaskList taskList = null;
    private Storage storage = null;
    public static Parser getInstance() {
        if (instance == null) {
            instance = new Parser();
        }
        return instance;
    }

    public void initParser() {
        ui = Ui.getInstance();
        liv = Liv.getInstance();
        taskList = TaskList.getInstance();
        storage = Storage.getInstance();
    }

    public void ProcessInput(String input) throws InputException {

        String[] words = input.split(" ");

        // for multi-word commands
        if (words[0].equals("mark") || words[0].equals("unmark")) {
            if (isInteger(words[1])) {
                boolean isDone = words[0].equals("mark");
                int taskIndex = Integer.parseInt(words[1]);
                ui.speak(taskList.setTaskDoneWithIndex(taskIndex, words[0], isDone));
            } else {
                ui.speak("Action failed: task index input is not an integer");
            }
            return;
        }

        if (words[0].equals("delete")) {
            if (isInteger(words[1])) {
                int taskIndex = Integer.parseInt(words[1]);
                Task deletedTask = taskList.deleteTask(taskIndex);
                ui.speak("Noted. I've removed this task:"
                        + "\n"
                        + "    "
                        + deletedTask
                        + "\n"
                        + "Now you have " + taskList.getNumOfTasks() + " tasks in the list.");//input);
                return;
            } else {
                ui.speak("Action failed: task index input is not an integer");
            }
            return;
        }

        if (words[0].equals("todo")
                || words[0].equals("deadline")
                || words[0].equals("event")) {

            Task newTask = null;
            newTask = Task.createTask(words[0], input);
            taskList.addTask(newTask);
            ui.speak("Got it. I've added this task:"
                    + "\n"
                    + "    "
                    + newTask
                    + "\n"
                    + "Now you have " + taskList.getNumOfTasks() + " tasks in the list.");//input);
            return;
        }


        if (input.equals("bye")) {
            ui.EndSession();
            storage.saveToMemory();
            return;
        }

        if (input.equals("list") || input.equals("print tasks") ) {
            ui.listTasks();
            return;
        }

        throw new CommandNotFoundException(input);
    }

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("ddMMyy'T'HHmm");
    public static LocalDateTime parseDateAndTime(String input) {
        return LocalDateTime.parse(input, DATE_TIME_FORMATTER);
    }

    public static String convertDateTimeToCommandFormat(LocalDateTime localDateTime) {
        return localDateTime.format(DATE_TIME_FORMATTER);
    }
}
