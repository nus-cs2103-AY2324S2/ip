import java.io.IOException;
import java.time.format.DateTimeParseException;
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

    private Storage storage;
    private static TaskList tasks;

    private Duke() {
        storage = new Storage("./data/duke.txt");
        tasks = new TaskList(this.storage.loadTasks());
    }

    public void run() {
        printGreeting();
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Duke duke = new Duke();
        duke.run();

        while (true) {
            boolean isExit = false;
            String input = sc.nextLine();
            String inputs[] = input.split(" ", 2);
            String command = inputs[0];

            try {
                if (command.length() <= 0) {
                    throw new DukeException("Your keyboard seems to be frozen! "
                                            + "I can't see your message!");
                }

                Command cmd = Command.valueOfOrElse(command.toUpperCase());

                switch (cmd) {
                    case LIST:
                        printDiv();
                        tasks.printTasks();
                        printDiv();
                        break;
                    case MARK:
                        if (inputs.length != 2) {
                            throw new DukeException("You forgot to specify the task!!");
                        }

                        int taskIndex = Integer.parseInt(inputs[1]) - 1;

                        if (taskIndex < 0 || taskIndex >= tasks.getSize()) {
                            throw new DukeException("Oh no!!! Invalid task index!");
                        }

                        printDiv();
                        tasks.markTask(taskIndex);
                        System.out.println("\tNice! I've marked this task as done: ");
                        System.out.println("\t" + tasks.getTask(taskIndex));
                        printDiv();
                        break;
                    case UNMARK:
                        if (inputs.length != 2) {
                            throw new DukeException("You forgot to specify the task!!");
                        }

                        int taskIndexUn = Integer.parseInt(inputs[1]) - 1;

                        if (taskIndexUn < 0 || taskIndexUn >= tasks.getSize()) {
                            throw new DukeException("Oh no!!! Invalid task index!");
                        }

                        printDiv();
                        tasks.unmarkTask(taskIndexUn);
                        System.out.println("\tOK, I've marked this task as not done yet: ");
                        System.out.println("\t" + tasks.getTask(taskIndexUn));
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
                        tasks.addTask(todo);
                        System.out.println("\t" + todo);
                        tasks.printListCounter();
                        printDiv();
                        break;
                    case DEADLINE:
                        if (inputs.length != 2) {
                            throw new DukeException("OOPS!!! The deadline task "
                                                    + "cannot be empty.");
                        }

                        // separate to description: deadlineInfo[0]: return book 
                        // and deadline: deadlineInfo[1]: by Sunday
                        String[] deadlineInfo = inputs[1].split("/by", 2);

                        if (deadlineInfo.length != 2) {
                            throw new DukeException("OOPS!!! Please add "
                                                    + "a valid deadline.");
                        }

                        printDiv();
                        System.out.println("\tGot it. I've added this task:");
                        Deadline deadline = new Deadline(
                                                deadlineInfo[0].strip(), 
                                                deadlineInfo[1].strip());
                        tasks.addTask(deadline);
                        System.out.println("\t" + deadline);
                        tasks.printListCounter();
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
                        tasks.addTask(event);
                        System.out.println("\t" + event);
                        tasks.printListCounter();
                        printDiv();
                        break;
                    case DELETE:
                        if (inputs.length != 2) {
                            throw new DukeException("OOPS!!! The delete command "
                                                    + "cannot be empty.");
                        }

                        int delIndex = Integer.parseInt(inputs[1]) - 1;

                        if (delIndex < 0 || delIndex >= tasks.getSize()) {
                            throw new DukeException("Oh no!!! Invalid task index!");
                        }
                        printDiv();
                        System.out.println("\tNoted. I've removed this task:");
                        System.out.println("\t" + tasks.getTask(delIndex));
                        tasks.deleteTask(delIndex);
                        tasks.printListCounter();
                        printDiv();
                        break;
                    case BYE:
                        printMsg("Bye. Hope to see you again soon!");
                        isExit = true;
                        break;
                    default:
                        throw new DukeException("OOPS!!! Your command " 
                                                + "triggered a virtual avalanche of confusion.");
                }
            } catch (NumberFormatException e) {
                printMsg("Enter a numeric value!!");
            } catch (DukeException e) {
                printMsg(e.toString());
            } catch (DateTimeParseException e) {
                printMsg("ERROR!! Please format date as YYYY-MM-DD and time as HHMM");
            } catch (Exception e) {
                printMsg("Invalid input.");
                e.printStackTrace();
            }

            if (isExit) break;
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
}
