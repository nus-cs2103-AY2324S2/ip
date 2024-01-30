import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Capone {
    private enum Command {
        LIST("list"), MARK("mark"), UNMARK("unmark"), TODO("todo"), DEADLINE("deadline"),
        EVENT("event"), DELETE("delete"), BYE("bye"), HELP("help");

        private final String command;

        Command(String command) {
            this.command = command;
        }

        @Override
        public String toString() {
            return this.command;
        }
    }
    // We assume there is no more than 100 tasks added.
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void processInputs() {
        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Read the user input
            String input = scanner.nextLine();

            // Split inputs by space and store them in an arraylist for processing.
            ArrayList<String> inputList = new ArrayList<>(Arrays.asList(input.split("\\s+")));

            String firstWord = inputList.get(0);
            try {
                if (firstWord.equalsIgnoreCase(Command.LIST.toString())) {
                    listTasks();
                } else if (firstWord.equalsIgnoreCase(Command.MARK.toString())) {
                    markTask(inputList);
                } else if (firstWord.equalsIgnoreCase(Command.UNMARK.toString())) {
                    unmarkTask(inputList);
                } else if (firstWord.equalsIgnoreCase(Command.TODO.toString())) {
                    processTodo(inputList);
                } else if (firstWord.equalsIgnoreCase(Command.DEADLINE.toString())) {
                    processDeadline(inputList);
                } else if (firstWord.equalsIgnoreCase(Command.EVENT.toString())) {
                    processEvent(inputList);
                } else if (firstWord.equalsIgnoreCase(Command.DELETE.toString())) {
                    deleteTask(inputList);
                } else if (firstWord.equalsIgnoreCase(Command.BYE.toString())) {
                    break;
                } else if (firstWord.equalsIgnoreCase(Command.HELP.toString())) {
                    displayHelp();
                } else {
                    invalidCommand();
                }

                TaskStorage.writeTasksToJsonFile(tasks);
            } catch (CaponeException e) {
                System.out.println(e.getMessage());
            }
        }

        // If user entered "bye", exit program. Clean up.
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }

    public static void printWelcomeMsg() {
        String logo = "░█▀▀░█▀█░█▀█░█▀█░█▀█░█▀▀░\n"
                + "░█░░░█▀█░█▀▀░█░█░█░█░█▀▀░\n"
                + "░▀▀▀░▀░▀░▀░░░▀▀▀░▀░▀░▀▀▀░";
        System.out.printf("Hello! I'm\n%s\nWhat can I do for you?\n%n", logo);
    }

    public static void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s\n", i+1, tasks.get(i).toString());
        }
    }

    public static void markTask(ArrayList<String> inputList) throws CaponeException {
        if (inputList.size() == 1) {
            throw new CaponeException("Please enter an index of a task you'd like to mark.\n" +
                    "You can view all tasks using the 'list' command.\n" +
                    "Usage: mark [index]");
        } else if (inputList.size() > 2) {
            throw new CaponeException("Please enter only one index you would like to mark.\n" +
                    "You can view all tasks using the 'list' command.\n" +
                    "Usage: mark [index]");
        }

        // Mark task as done.
        try {
            Task markedTask = tasks.get(Integer.parseInt(inputList.get(1))-1);
            markedTask.markTask();

            // Inform user that task has been marked.
            System.out.println("Nice! I've marked this task as done:\n" + markedTask);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new CaponeException("Sorry, you have entered an invalid index.\n" +
                    "You can check the list of valid indices using the 'list' command.");
        }
    }

    public static void unmarkTask(ArrayList<String> inputList) throws CaponeException{
        if (inputList.size() == 1) {
            throw new CaponeException("Please enter an index of a task you'd like to unmark.\n" +
                    "You can view all tasks using the 'list' command.\n" +
                    "Usage: unmark [index]");
        } else if (inputList.size() > 2) {
            throw new CaponeException("Please enter only one index you would like to unmark.\n" +
                    "You can view all tasks using the 'list' command.\n" +
                    "Usage: unmark [index]");
        }

        try {
            Task unmarkedTask = tasks.get(Integer.parseInt(inputList.get(1))-1);
            unmarkedTask.unmarkTask();

            // Inform user that task has been marked.
            System.out.println("OK, I've marked this task as not done yet:\n" + unmarkedTask);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new CaponeException("Sorry, you have entered an invalid index.\n" +
                    "You can check the list of valid indices using the 'list' command.");
        }
    }

    public static void processTodo(ArrayList<String> inputList) throws CaponeException {
        // If the inputList has only one string, throw error (insufficient args).
        if (inputList.size() == 1) {
            throw new CaponeException("Please enter a description for this ToDo task!\n" +
                    "Usage: todo [description]");
        }

        // Combine the remaining words into a single string
        StringBuilder description = new StringBuilder();
        for (int i = 1; i < inputList.size(); i++) {
            if (i == inputList.size() - 1) {
                description.append(inputList.get(i));
                break;
            }
            description.append(inputList.get(i)).append(" ");
        }

        ToDo newTodo = new ToDo(description.toString(), false);

        tasks.add(newTodo);

        System.out.printf("Got it. I've added this task:\n%s\n" +
                "Now you have %d task(s) in the list.\n", newTodo.toString(), tasks.size());

    }

    public static void processDeadline(ArrayList<String> inputList) throws CaponeException {
        // If the inputList has only one string, throw error (insufficient args).
        if (inputList.size() == 1) {
            throw new CaponeException("Insufficient arguments!\n" +
                    "Usage: deadline [description] /by [date]");
        }

        // Find the index of the /by command.
        int byNdx = inputList.indexOf("/by");

        // Catch potential errors from date entry.
        if (byNdx == inputList.size() - 1 || byNdx == -1) {
            throw new CaponeException("Please enter a date for this deadline task!\n" +
                    "Usage: deadline [description] /by [date]");
        }

        // Combine description of task into one string.
        StringBuilder description = new StringBuilder();
        for (int i = 1; i < byNdx; i++) {
            if (i == byNdx - 1) {
                description.append(inputList.get(i));
                break;
            }
            description.append(inputList.get(i)).append(" ");
        }

        if (description.toString().equalsIgnoreCase("")) {
            throw new CaponeException("Insufficient arguments!\n" +
                    "Usage: deadline [description] /by [date]");
        }

        LocalDate date = null;
        LocalTime time = null;
        // Process input for the deadline (i.e. after the /by command).
        StringBuilder byDate = new StringBuilder();
        for (int i = byNdx + 1; i < inputList.size(); i++) {
            if (isDateFormat(inputList.get(i))) {
                date = Capone.parseDate(inputList.get(i));
                continue;
            }

            if (isTimeFormat(inputList.get(i))) {
                time = Capone.parseTime(inputList.get(i));
                continue;
            }

            // If this is the last word to be added.
            if (i == inputList.size() - 1) {
                byDate.append(inputList.get(i));
            } else {
                byDate.append(inputList.get(i)).append(" ");
            }
        }


        if (date != null) {
            if (time != null) {
                tasks.add(new Deadline(description.toString(), false, date.atTime(time)));
            } else {
                tasks.add(new Deadline(description.toString(), false, date.atStartOfDay()));
            }
        } else {
            // If only the time is specified, the deadline will be the time at the next day.
            if (time != null) {
                tasks.add(new Deadline(description.toString(), false,
                        LocalDate.now().plusDays(1).atTime(time)));
            } else {
                tasks.add(new Deadline(description.toString(), false, byDate.toString()));
            }
        }

        System.out.printf("Got it. I've added this task:\n%s\n" +
                "Now you have %d task(s) in the list.\n", tasks.get(tasks.size()-1).toString(), tasks.size());
    }

    /**
     * Checks if date was an input. The recognized format is:
     * YYYY-MM-DD
     *
     * @param input the input string to be checked against.
     * @return true if a valid date is recognised, false otherwise.
     */
    private static boolean isDateFormat(String input) {
        String dateFormatRegex = "\\d{4}-\\d{2}-\\d{2}";

        // Check if the input string matches the format
        return input.matches(dateFormatRegex);
    }

    /**
     * Checks if time was an input. The recognized formats is:
     * 1800 (24-hour format).
     *
     * @param input the input string to be checked against.
     * @return true if a valid time is recognised, false otherwise.
     */
    public static boolean isTimeFormat(String input) {
        String timeFormatRegex = "(\\d{4})";

        // Check if the input string matches the format
        return input.matches(timeFormatRegex);
    }

    public static void processEvent(ArrayList<String> inputList) throws CaponeException {
        // If the inputList has only one string, throw error (insufficient args).
        if (inputList.size() == 1) {
            throw new CaponeException("Insufficient arguments!\n" +
                    "Usage: event [description] /from [date] /to [date]");
        }

        int fromNdx = inputList.indexOf("/from");
        int toNdx = inputList.indexOf("/to");

        // If /to is specified before /from, throw error.
        if (toNdx < fromNdx) {
            throw new CaponeException("Please input from date followed by to date!\n" +
                    "Usage: event [description] /from [date] /to [date]");
        }

        // Catch potential errors from date entry.
        if (fromNdx == -1 || toNdx == -1 || toNdx - fromNdx == 1 || fromNdx - toNdx == 1 ||
                fromNdx == inputList.size() - 1 || toNdx == inputList.size() - 1) {
            throw new CaponeException("Please enter a start and end date!\n" +
                    "Usage: event [description] /from [date] /to [date]");
        }

        // Combine the task description into a single string.
        StringBuilder description = new StringBuilder();
        for (int i = 1; i < fromNdx; i++) {
            if (i == fromNdx - 1) {
                description.append(inputList.get(i));
                break;
            }
            description.append(inputList.get(i)).append(" ");
        }

        if (description.toString().equalsIgnoreCase("")) {
            throw new CaponeException("Insufficient arguments!\n" +
                    "Usage: event [description] /from [date] /to [date]");
        }

        LocalDate fromDate = null;
        LocalTime fromTime = null;
        // Process input for the deadline (i.e. after the /by command).
        StringBuilder fromDateString = new StringBuilder();
        for (int i = fromNdx + 1; i < toNdx; i++) {
            if (isDateFormat(inputList.get(i))) {
                fromDate = Capone.parseDate(inputList.get(i));
                continue;
            }

            if (isTimeFormat(inputList.get(i))) {
                fromTime = Capone.parseTime(inputList.get(i));
                continue;
            }

            // If this is the last word to be added.
            if (i == inputList.size() - 1) {
                fromDateString.append(inputList.get(i));
            } else {
                fromDateString.append(inputList.get(i)).append(" ");
            }
        }

        LocalDate toDate = null;
        LocalTime toTime = null;
        // Process input for the deadline (i.e. after the /by command).
        StringBuilder toDateString = new StringBuilder();
        for (int i = toNdx + 1; i < inputList.size(); i++) {
            if (isDateFormat(inputList.get(i))) {
                toDate = Capone.parseDate(inputList.get(i));
                continue;
            }

            if (isTimeFormat(inputList.get(i))) {
                toTime = Capone.parseTime(inputList.get(i));
                continue;
            }

            // If this is the last word to be added.
            if (i == inputList.size() - 1) {
                toDateString.append(inputList.get(i));
            } else {
                toDateString.append(inputList.get(i)).append(" ");
            }
        }

        LocalDateTime fromDateTime = processDateTime(fromDate, fromTime);
        LocalDateTime toDateTime = processDateTime(toDate, toTime);

        if (fromDateTime != null && toDateTime != null) {
            tasks.add(new Event(description.toString(), false, fromDateTime, toDateTime));
        } else if (fromDateTime != null || toDateTime != null) {
            // If either fromDateTime or toDateTime is null but the other is not.
            throw new CaponeException("Oops! It seems like there is a format mismatch between" +
                    "your start and dates and end dates.\nMake sure you enter both of them in the accepted " +
                    "date format!\nAlternatively, you can specify a string for both your start and end dates.\n" +
                    "Use the 'help' command for more information.");
        } else {
            tasks.add(new Event(description.toString(), false,
                    fromDateString.toString(), toDateString.toString()));
        }

        System.out.printf("Got it. I've added this task:\n%s\n" +
                "Now you have %d task(s) in the list.\n", tasks.get(tasks.size()-1).toString(), tasks.size());

    }

    public static LocalDateTime processDateTime(LocalDate date, LocalTime time) {
        if (date != null) {
            if (time != null) {
                return date.atTime(time);
            } else {
                return date.atStartOfDay();
            }
        } else {
            // If only the time is specified, the deadline will be the time at the next day.
            if (time != null) {
                return LocalDate.now().plusDays(1).atTime(time);
            } else {
                // Else, if both date and time are null, return null input to use
                // the string input of date/time by user.
                return null;
            }
        }
    }

    public static LocalDate parseDate(String date) throws CaponeException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(date, dateFormatter);
        } catch (DateTimeException e) {
            throw new CaponeException("Oops! You have entered an invalid date. Please try again.");
        }
    }

    public static LocalTime parseTime(String time) throws CaponeException {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        try {
            return LocalTime.parse(time, timeFormatter);
        } catch (DateTimeException e) {
            throw new CaponeException("Oops! You have entered an invalid time. Please try again.");
        }
    }

    public static void deleteTask(ArrayList<String> inputList) throws CaponeException{
        // If the inputList has more than two arguments, throw exception.
        if (inputList.size() == 1) {
            throw new CaponeException("Please enter an index of a task you'd like to delete.\n" +
                    "You can view all tasks using the 'list' command.\n" +
                    "Usage: delete [index]");
        } else if (inputList.size() > 2) {
            throw new CaponeException("Please enter only one index you would like to delete.\n" +
                    "You can view all tasks using the 'list' command.\n" +
                    "Usage: delete [index]");
        }

        try {
            // Remove the task from the tasks Arraylist.
            Task removedTask = tasks.remove(Integer.parseInt(inputList.get(1))-1);

            System.out.printf("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.\n",
                    removedTask.toString(), tasks.size());
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new CaponeException("Sorry, you have entered an invalid index.\n" +
                    "You can check the list of valid indices using the 'list' command.");
        }
    }

    public static void invalidCommand() throws CaponeException{
        throw new CaponeException("I'm sorry, I don't understand what you just said.\n" +
                "Use 'help' to display the list of valid commands");

    }

    // TODO: UPDATE HELP MENU
    public static void displayHelp() {
        System.out.println("Commands I understand:\n" +
                "1. list - Lists the tasks entered.\n" +
                "2. todo [description] - Creates a new ToDo task. Remember to enter the description!\n" +
                "3. deadline [description] /by [date] - Creates a new Deadline task.\n" +
                "   Remember to enter the description and date!\n" +
                "   Dates are recognised in the following format - 'yyyy-mm-dd HHmm' (24-hour).\n" +
                "4. event [description] /from [date] /to [date] - Creates a new Event task.\n" +
                "   Remember to enter the description, as well as the start and end date!\n" +
                "   Dates are recognised in the following format - 'yyyy-mm-dd HHmm' (24-hour).\n" +
                "5. mark [index] - Marks a task as completed. Use this in conjunction with the 'list' command!\n" +
                "6. unmark [index] - Unmarks a task. Use this in conjunction with the 'list' command!\n" +
                "7. delete [index] - Deletes a task. Use this in conjunction with the 'list' command!");
    }

    public static void main(String[] args) {

        Capone.printWelcomeMsg();

        try {
            TaskStorage.readTasksFromJsonFile(tasks);
        } catch (TaskListCorruptedException e) {
            System.out.println(e.getMessage());
        }

        Capone.processInputs();
    }
}