import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //        String logo = " ____        _        \n"
        //                + "|  _ \\ _   _| | _____ \n"
        //                + "| | | | | | | |/ / _ \\\n"
        //                + "| |_| | |_| |   <  __/\n"
        //                + "|____/ \\__,_|_|\\_\\___|\n";
        //        System.out.println("Hello from\n" + logo);
        String botName = "Wind";
        System.out.println("Hello I'm " + botName + "\n"
                + "What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>(100);
        while (true) {
            try {
                String input = scanner.nextLine();
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                }
                if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        int number = i + 1;
                        System.out.println(number + "." + tasks.get(i));
                    }
                    continue;
                }
                String[] splitedInput = input.split(" ");
                if (splitedInput[0].equals("delete")) {
                    validateTaskOperation(input, 6, tasks);
                    int taskNumber = Integer.parseInt(splitedInput[1]);
                    Task removedTask = tasks.get(taskNumber - 1);
                    tasks.remove(taskNumber - 1);
                    System.out.println("Noted. I've removed this task:\n" +
                            " " + removedTask + "\n" +
                            "Now you have " + tasks.size() + " tasks in the list");
                    continue;
                }
                if (splitedInput[0].equals("mark")) {
                    validateTaskOperation(input, 4, tasks);
                    int taskNumber = Integer.parseInt(splitedInput[1]);
                    tasks.get(taskNumber - 1).setIsDone(true);
                    System.out.println("Nice! I've marked this task as done:\n"
                            + " " + tasks.get(taskNumber - 1));
                    continue;
                }
                if (splitedInput[0].equals("unmark")) {
                    validateTaskOperation(input, 6, tasks);
                    int taskNumber = Integer.parseInt(splitedInput[1]);
                    tasks.get(taskNumber - 1).setIsDone(false);
                    System.out.println(
                            "OK, I've marked this task as not done yet:\n"
                                    + " " + tasks.get(taskNumber - 1));
                    continue;
                }

                if (splitedInput[0].equals("todo")) {
                    validateToDoInput(input);
                    tasks.add(new Todo(input.substring(4).trim()));
                    printAddTaskMessage(tasks.get(tasks.size() - 1),
                            tasks);
                    continue;
                }
                if (splitedInput[0].equals("deadline")) {
                    validateDeadlineInput(input);
                    tasks.add(Deadline.buildDeadLine(input));
                    printAddTaskMessage(tasks.get(tasks.size() - 1), tasks);
                    continue;
                }
                if (splitedInput[0].equals("event")) {
                    validateEventInput(input);
                    tasks.add(Event.buildEvent(input));
                    printAddTaskMessage(tasks.get(tasks.size() - 1), tasks);
                    continue;
                }
                throw new InvalidCommandException();
            } catch (DukeException e) {
                System.out.println(e.getDisplayMessage());
            }
        }

    }

    public static void printAddTaskMessage(Task task, List<Task> tasks) {
        System.out.println("Got it. I've added this task:\n"
                + " " + task + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void validateTaskOperation(String input, int operationLength,
                                             List<Task> tasks) throws InvalidInputException, MissingInputException, UnexpectedInputException {
        if (input.substring(operationLength).trim().isEmpty()) {

            throw new MissingInputException("Bro, please select a task");
        }
        if (input.split(" ").length > 2) {
            throw new UnexpectedInputException(
                    "Bro, please do the operation one by one");
        }
        int taskNumber = Integer.parseInt(input.split(" ")[1]);
        if (taskNumber <= 0 || taskNumber > tasks.size()) {
            throw new InvalidInputException(
                    "Bro, the task number you input does not exist");
        }
    }

    public static void validateToDoInput(
            String input) throws InvalidInputException, MissingInputException {
        if (input.substring(4).trim().isEmpty()) {
            throw new MissingInputException(
                    "Bro, we need a task description for your todo.\n"
                            + "E.g. todo SU All Courses");
        }
    }

    public static void validateDeadlineInput(
            String input) throws InvalidInputException, MissingInputException, InvalidInputFormatException, InvalidtKeyWordException {
        String[] splitInput = input.split("/");
        String example = "E.g. deadline SU All Courses /by 9pm";
        if (splitInput[0].substring(8).trim().isEmpty()) {
            throw new MissingInputException(
                    "Bro, we need a task description for your deadline.\n"
                            + example);
        }
        if (splitInput.length < 2) {
            throw new InvalidInputFormatException(
                    "Bro, it seems that you have entered an incorrect format for deadline.\n"
                            + example);
        }
        if (!splitInput[1].split(" ")[0].equals("by")) {
            throw new InvalidtKeyWordException(
                    "Bro, please use a \"by\" keyword to specify your deadline date.\n"
                            + example);
        }
        if (splitInput[1].split(" ").length < 2) {
            throw new MissingInputException(
                    "Bro, please specify your deadline date.\n"
                            + example);
        }
    }

    public static void validateEventInput(
            String input) throws InvalidInputException, MissingInputException, InvalidtKeyWordException {
        String[] splitInput = input.split("/");
        String example = "E.g. event Exam /from 9am /to 9pm";
        if (splitInput[0].substring(5).trim().isEmpty()) {
            throw new MissingInputException(
                    "Bro, please specify your task description for event.\n"
                            + example);
        }
        if (splitInput.length < 3) {
            throw new InvalidtKeyWordException(
                    "Bro, it seems that you have entered the command in wrong format.\n" +
                            example);
        }
        if (!splitInput[1].split(" ")[0].equals("from") || !splitInput[2].split(
                " ")[0].equals("to")) {
            throw new InvalidInputException(
                    "Bro, please use \"from\" and \"to\" keyword to specify your event start date and end date.\n"
                            + example);
        }
        if (splitInput[1].substring(
                4).trim().isEmpty() || splitInput[2].substring(
                2).trim().isEmpty()) {
            throw new InvalidInputException(
                    "Bro, it seems that you have missing a start date or an end date for your event.\n"
                            + example);
        }
    }
}

