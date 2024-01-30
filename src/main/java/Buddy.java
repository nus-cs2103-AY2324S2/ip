import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Buddy {
    public enum Command {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, INVALID
    }
    public static final String LINE_BREAK = "____________________________________________________________\n";
    protected static final DateTimeFormatter DATE_TIME_PARSE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    ArrayList<Task> taskList;
    Storage storage = new Storage("buddy.txt");

    private void greet() {
        String logo =
            "               ____            _     _       \n" +
            "              |  _ \\          | |   | |      \n" +
            "              | |_) |_   _  __| | __| |_   _ \n" +
            "              |  _ <| | | |/ _` |/ _` | | | |\n" +
            "              | |_) | |_| | (_| | (_| | |_| |\n" +
            "              |____/ \\__,_|\\__,_|\\__,_|\\__, |\n" +
            "                                        __/ |\n" +
            "                                       |___/ \n";
        System.out.println(LINE_BREAK + logo + LINE_BREAK
                + " Hello friend!\n" + " How can I help you?\n" + LINE_BREAK);
    }

    private void exit() {
        System.out.println(LINE_BREAK + " Bye. Hope to see you again soon!\n" + LINE_BREAK);
    }

    private void run() {
        boolean isRunning = true;
        Scanner sc = new Scanner(System.in);
        taskList = storage.load();

        while (isRunning) {
            String input = sc.nextLine().trim();

            if (!input.isEmpty()) {
                String[] inputSplit = input.split(" ", 2);
                Command command = getCommand(inputSplit[0]);

                switch (command) {
                case BYE:
                    isRunning = false;
                    break;
                case LIST:
                    System.out.print(LINE_BREAK);
                    System.out.println("Here you go bud!:");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println((i + 1) + ". "+ taskList.get(i));
                    }
                    System.out.print(LINE_BREAK);
                    break;
                case MARK:
                    try {
                        if (inputSplit.length == 1) {
                            throw new IllegalArgumentException("Please provide a task number!");
                        }

                        int index = Integer.parseInt(inputSplit[1].trim()) - 1;
                        if (index >= taskList.size()) {
                            throw new ArrayIndexOutOfBoundsException("Not a valid task buddy!");
                        }

                        taskList.get(index).mark();
                        System.out.println(LINE_BREAK + "I've updated the following task!:\n"
                                + taskList.get(index) + "\n" + LINE_BREAK);
                        break;
                    } catch (NumberFormatException nfe) {
                        System.out.println(LINE_BREAK + "Not a valid task buddy!\n" + LINE_BREAK);
                        break;
                    } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
                        System.out.println(LINE_BREAK + e.getMessage() + "\n" + LINE_BREAK);
                        break;
                    }
                case UNMARK:
                    try {
                        if (inputSplit.length == 1) {
                            throw new IllegalArgumentException("Please provide a task number!");
                        }

                        int index = Integer.parseInt(inputSplit[1].trim()) - 1;
                        if (index >= taskList.size()) {
                            throw new ArrayIndexOutOfBoundsException("Not a valid task buddy!");
                        }

                        taskList.get(index).unmark();
                        System.out.println(LINE_BREAK + "I've updated the following task!:\n"
                                + taskList.get(index) + "\n" + LINE_BREAK);
                        break;
                    } catch (NumberFormatException nfe) {
                        System.out.println(LINE_BREAK + "Not a valid task buddy!\n" + LINE_BREAK);
                        break;
                    } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
                        System.out.println(LINE_BREAK + e.getMessage() + "\n" + LINE_BREAK);
                        break;
                    }
                case DELETE:
                    try {
                        if (inputSplit.length == 1) {
                            throw new IllegalArgumentException("Please provide a task number!");
                        }

                        int index = Integer.parseInt(inputSplit[1].trim()) - 1;
                        if (index >= taskList.size()) {
                            throw new ArrayIndexOutOfBoundsException("Not a valid task buddy!");
                        }

                        deleteTask(taskList.get(index));
                        break;
                    } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
                        System.out.println(LINE_BREAK + e.getMessage() + "\n" + LINE_BREAK);
                        break;
                    }
                case TODO:
                    try {
                        if (inputSplit.length == 1) {
                            throw new IllegalArgumentException("Please provide a valid task!");
                        }

                        Todo todo = new Todo(inputSplit[1].trim());
                        addTask(todo);
                        break;
                    } catch (IllegalArgumentException iae) {
                        System.out.println(LINE_BREAK + iae.getMessage() + "\n" + LINE_BREAK);
                        break;
                    }
                case DEADLINE:
                    try {
                        if (inputSplit.length == 1) {
                            throw new IllegalArgumentException("Please provide a task and deadline!");
                        }

                        String[] timeSplit = inputSplit[1].split("/by", 2);
                        if (timeSplit.length <= 1 || timeSplit[1].isEmpty()) {
                            throw new IllegalArgumentException("Please provide a deadline!");
                        }

                        LocalDateTime by = LocalDateTime.parse(
                                timeSplit[1].trim(), DATE_TIME_PARSE_FORMAT);

                        Deadline deadline = new Deadline(
                                timeSplit[0].trim(), by);
                        addTask(deadline);
                        break;
                    } catch (IllegalArgumentException iae) {
                        System.out.println(LINE_BREAK + iae.getMessage() + "\n" + LINE_BREAK);
                        break;
                    } catch (DateTimeParseException dtpe) {
                        System.out.println(LINE_BREAK
                                + "That's not a valid date!\nPlease use this format!: dd/mm/yyyy 2359\n"
                                        + LINE_BREAK);
                        break;
                    }
                case EVENT:
                    try {
                        if (inputSplit.length == 1) {
                            throw new IllegalArgumentException("Please provide a task and date/time range!");
                        }

                        String[] timeSplit = inputSplit[1].split("/from", 2);
                        if (timeSplit.length <= 1 || timeSplit[1].isEmpty()) {
                            throw new IllegalArgumentException("Please provide a start date/time!");
                        }

                        String[] timeSplit2 = timeSplit[1].split("/to", 2);
                        if (timeSplit2.length <= 1 || timeSplit2[1].isEmpty()) {
                            throw new IllegalArgumentException("Please provide an end date/time!");
                        }

                        LocalDateTime from = LocalDateTime.parse(
                                timeSplit2[0].trim(), DATE_TIME_PARSE_FORMAT);
                        LocalDateTime to = LocalDateTime.parse(
                                timeSplit2[1].trim(), DATE_TIME_PARSE_FORMAT);

                        Event event = new Event(
                                timeSplit[0].trim(), from, to);
                        addTask(event);
                        break;
                    } catch (IllegalArgumentException iae) {
                        System.out.println(LINE_BREAK + iae.getMessage() + "\n" + LINE_BREAK);
                        break;
                    } catch (DateTimeParseException dtpe) {
                        System.out.println(LINE_BREAK
                                + "That's not a valid date!\nPlease use this format!: dd/mm/yyyy 2359\n"
                                        + LINE_BREAK);
                        break;
                    }
                default:
                    System.out.println(LINE_BREAK + "That's not a valid command!\n" + LINE_BREAK);
                    break;
                }
            }
        }
        storage.save(taskList);
        sc.close();
    }

    public void addTask(Task task) {
        taskList.add(task);
        String msg = "Alrighty! I've added the task to your list!\n" + task + "\n"
                + "You have " + taskList.size() + " tasks!\n";
        System.out.println(LINE_BREAK + msg + LINE_BREAK);
    }

    public void deleteTask(Task task) {
        taskList.remove(task);
        String msg = "I've removed the task from your list!\n" + task + "\n"
                + "You have " + taskList.size() + " tasks!\n";
        System.out.println(LINE_BREAK + msg + LINE_BREAK);
    }

    public Command getCommand(String input) {
        try {
            return Command.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException iae) {
            return Command.INVALID;
        }
    }

    public static void main(String[] args) {
        Buddy buddy = new Buddy();
        buddy.greet();
        buddy.run();
        buddy.exit();
    }
}
