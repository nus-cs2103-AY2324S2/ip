import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public enum Command {
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        BYE,
        UNKNOWN;

        public static Command valueOfOrElse(String command) {
            try {
                return Command.valueOf(command);
            } catch (IllegalArgumentException e) {
                return UNKNOWN;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> AL = new ArrayList<>();

        printGreeting();

        while (true) {
            boolean userExit = false;
            String input = sc.nextLine();
            String inputs[] = input.split(" ", 2);
            String command = inputs[0];

            if (command.equalsIgnoreCase("bye")) {
                printMsg("Bye. Hope to see you again soon!");
                break;
            }

            try {
                if (command.length() <= 0) {
                    throw new DukeException("Your keyboard seems to be frozen! "
                                            + "I can't see your message!");
                }

                Command cmd = Command.valueOfOrElse(command.toUpperCase());

                switch (cmd) {
                    case LIST:
                        printDiv();
                        System.out.println("\tHere are the tasks in your list:");
                        for (int i = 0; i < AL.size(); i++) {
                            int num = i + 1;
                            System.out.println("\t" + num + ". " + AL.get(i));
                        }
                        printDiv();
                        break;
                    case MARK:
                        if (inputs.length != 2) {
                            throw new DukeException("You forgot to specify the task!!");
                        }

                        int taskIndex = Integer.parseInt(inputs[1]) - 1;

                        if (taskIndex < 0 || taskIndex >= AL.size()) {
                            throw new DukeException("Oh no!!! Invalid task index!");
                        }

                        printDiv();
                        Task taskToMark = AL.get(taskIndex);
                        taskToMark.mark();
                        System.out.println("\tNice! I've marked this task as done: ");
                        System.out.println("\t" + taskToMark);
                        printDiv();
                        break;
                    case UNMARK:
                        if (inputs.length != 2) {
                            throw new DukeException("You forgot to specify the task!!");
                        }

                        int taskIndexUn = Integer.parseInt(inputs[1]) - 1;

                        if (taskIndexUn < 0 || taskIndexUn >= AL.size()) {
                            throw new DukeException("Oh no!!! Invalid task index!");
                        }

                        printDiv();
                        Task taskToUnmark = AL.get(taskIndexUn);
                        taskToUnmark.unmark();
                        System.out.println("\tOK, I've marked this task as not done yet: ");
                        System.out.println("\t" + taskToUnmark);
                        printDiv();
                        break;
                    case TODO:
                        if (inputs.length != 2) {
                            throw new DukeException("Oh no! Your todo command is as "
                                                    + "silent as a snowball " 
                                                    + "rolling down a mountain.");
                        }

                        printDiv();
                        System.out.println("\tGot it. I've added this task:");
                        Todo todo = new Todo(inputs[1]);
                        AL.add(todo);
                        System.out.println("\t" + todo);
                        printListCounter(AL);
                        printDiv();
                        break;
                    case DEADLINE:
                        if (inputs.length != 2) {
                            throw new DukeException("OOPS!!! The deadline task "
                                                    + "cannot be empty.");
                        }

                        // separate to description: deadlineInfo[0]: return book 
                        // and deadline: deadlineInfo[1]: by Sunday
                        String[] deadlineInfo = inputs[1].split("/", 2);

                        if (deadlineInfo.length != 2) {
                            throw new DukeException("OOPS!!! Please add "
                                                    + "a valid deadline.");
                        }

                        printDiv();
                        System.out.println("\tGot it. I've added this task:");
                        Deadline deadline = new Deadline(
                                                deadlineInfo[0].strip(), 
                                                deadlineInfo[1].replaceFirst(
                                                    "by", 
                                                    "").strip());
                        AL.add(deadline);
                        System.out.println("\t" + deadline);
                        printListCounter(AL);
                        printDiv();
                        break;
                    case EVENT:
                        if (inputs.length != 2) {
                            throw new DukeException("OOPS!!! The event task "
                                                    + "cannot be empty.");
                        }

                        String[] eventInfo = inputs[1].split("/", 3);
                        if (eventInfo.length != 3) {
                            throw new DukeException("OOPS!!! Please check "
                                                    + "the event format again.");
                        }

                        printDiv();
                        System.out.println("\tGot it. I've added this task:");
                        Event event = new Event(
                                            eventInfo[0].strip(), 
                                            eventInfo[1].replaceFirst("from", "").strip(),
                                            eventInfo[2].replaceFirst("to", "").strip());
                        AL.add(event);
                        System.out.println("\t" + event);
                        printListCounter(AL);
                        printDiv();
                        break;
                    case DELETE:
                        if (inputs.length != 2) {
                            throw new DukeException("OOPS!!! The delete command "
                                                    + "cannot be empty.");
                        }

                        int delIndex = Integer.parseInt(inputs[1]) - 1;

                        if (delIndex < 0 || delIndex >= AL.size()) {
                            throw new DukeException("Oh no!!! Invalid task index!");
                        }
                        printDiv();
                        System.out.println("\tNoted. I've removed this task:");
                        System.out.println("\t" + AL.get(delIndex));
                        AL.remove(delIndex);
                        printListCounter(AL);
                        printDiv();
                        break;
                    case BYE:
                        printMsg("Bye. Hope to see you again soon!");
                        userExit = true;
                        break;
                    default:
                        throw new DukeException("OOPS!!! Your command " 
                                                + "triggered a virtual avalanche of confusion.");
                }
            } catch (NumberFormatException e) {
                printMsg("Enter a numeric value!!");
            } catch (DukeException e) {
                printMsg(e.toString());
            } catch (Exception e) {
                printMsg("Invalid input.");
            }

            if (userExit) break;
        }

        sc.close();
    }

    public static void printGreeting() {
        printDiv();
        System.out.println("\tHello! I'm Puffin.");
        System.out.println("\tWhat can I do for you?");
        printDiv();
    }

    public static void printDiv() {
        String DIV = "____________________________________________________________";
        System.out.println("\t" + DIV);
    }

    public static void printMsg(String msg) {
        printDiv();
        System.out.println("\t" + msg);
        printDiv();
    }

    public static void printListCounter(ArrayList<Task> AL) {
        System.out.println("\tNow you have " + AL.size() + " tasks in the list.");
    }
}
