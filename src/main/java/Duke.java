import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Duke { // put LINE at start and end of switch then can remove all inbetween, make DukeException Class,
    private static final String LINE = "    ____________________________________________________________\n";
    private static final String INDENT = "    ";
    private static final ArrayList<Task> tasks = new ArrayList<Task>();

    private static boolean running = true;
    public enum Command {
        BYE("bye"),
        LIST("list"),
        MARK("mark"),
        UNMARK("unmark"),
        DELETE("delete"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event");
        private final String name;
        private static final Map<String, Command> COMMAND_MAP = new HashMap<>();

        private Command(String name) {
            this.name = name;
        }

        static {
            for (Command c: values()) {
                COMMAND_MAP.put(c.name, c);
            }
        }
        public static Command valueOfCommandName(String name) {
            return COMMAND_MAP.get(name);
        }
    }

    public static void main(String[] args) {
        Duke.hello();
        Scanner in = new Scanner(System.in);

        while (running) {


            // get next command and arguments
            String input = in.nextLine().trim();
            String[] cmdArg = input.split(" ", 2); // [command, arguments]

            System.out.print(LINE);
            try {
                parseCommand(cmdArg);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

            System.out.print(LINE);
        }
    }

    public static void parseCommand(String[] cmdArg) throws DukeException {
        Command cmd = Command.valueOfCommandName(cmdArg[0]);

        // invalid command
        if (cmd == null) throw (new DukeException(INDENT + "What is blud yappin'? Here's the legit commands:\n"
                    + INDENT + "list, todo, deadline, event, mark, unmark, delete, bye"));

        // Commands
        switch (cmd) {
            case BYE:
                Duke.bye();
                break;
            case LIST:
                listTasks();
                break;
            case MARK:
            case UNMARK:
            case DELETE:
                if (cmdArg.length == 2) {
                    try {
                        int i = Integer.parseInt(cmdArg[1]);

                        // incorrect index
                        if (i > tasks.size()) {
                            throw(new DukeException(INDENT + "You ain't got that many tasks bruh!"));
                        } else if (i < 1) { // incorrect index
                            throw(new DukeException(INDENT + "Start from task 1 lil bro!"));
                        }

                        // Execute MARK/UNMARK/DELETE
                        if (cmdArg[0].equals("mark")) {
                            markTask(i);
                        } else if (cmdArg[0].equals("unmark")) {
                            unmarkTask(i);
                        } else {
                            deleteTask(i);
                        }
                    } catch (java.lang.NumberFormatException e) { // non number typed
                        throw(new DukeException(INDENT + "Ain't no way! We lackin' just numbers after mark/unmark/delete.\n"
                                + INDENT + "e.g. unmark 2"));
                    } catch (DukeException e) {
                        throw(e);
                    }
                } else { // no arguments
                    throw(new DukeException(INDENT + "Ain't no way! Which task in the list we vibin' with?\n"
                            + INDENT + "e.g. mark/unmark/delete 1"));
                }
                break;
            case TODO:
                if (cmdArg.length != 2) { // no arguments
                    throw(new DukeException(INDENT + "Ain't no way! You got caught lackin' the format!\n"
                            + INDENT + "e.g. todo <task>"));
                }
                addTask(cmdArg[1], Task.ID.TODO);
                break;
            case DEADLINE:
                if (cmdArg.length != 2) { // no arguments
                    throw(new DukeException(INDENT + "Ain't no way! You got caught lackin' the format!\n"
                            + INDENT + "e.g. deadline <task> /by <date/time>"));
                }
                addTask(cmdArg[1], Task.ID.DEADLINE);
                break;
            case EVENT:
                if (cmdArg.length != 2) { // no arguments
                    throw(new DukeException(INDENT + "Ain't no way! You got caught lackin' the format!\n"
                            + INDENT + "e.g. event <task> /from <start date/time> /to <start date/time>"));
                }
                addTask(cmdArg[1], Task.ID.EVENT);
                break;
            default: // Shouldn't reach here, invalid commands should be null
                throw(new DukeException(INDENT + "What is blud yappin'? Here's the legit commands:"
                        + INDENT + "list, todo, deadline, event, mark, unmark, delete, bye"));
        }
    }

    public static void hello() {
        String logo =
                "       :::   :::           :::        :::::::::       :::::::::       ::::::::::       :::::::::\n" +
                "      :+:   :+:         :+: :+:      :+:    :+:      :+:    :+:      :+:              :+:    :+:\n" +
                "      +:+ +:+         +:+   +:+     +:+    +:+      +:+    +:+      +:+              +:+    +:+\n" +
                "      +#++:         +#++:++#++:    +#++:++#+       +#++:++#+       +#++:++#         +#++:++#:\n" +
                "      +#+          +#+     +#+    +#+             +#+             +#+              +#+    +#+\n" +
                "     #+#          #+#     #+#    #+#             #+#             #+#              #+#    #+#\n" +
                "    ###          ###     ###    ###             ###             ##########       ###    ###\n\n";
        System.out.print(LINE + "    What's poppin' fam, it's ya boi\n\n" + logo +
                "    Hit me up with those deets and let's vibe together!\n" + LINE);
    }

    public static void bye() {
        System.out.print(INDENT + "Peace out, fam! Stay lit and keep those good vibes rollin'!\n");
        running = false;
    }

    public static void listTasks() {
        System.out.println(INDENT + "I gotchu bruv. Here's your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(INDENT + (i + 1) + "." + tasks.get(i));
        }
    }

    // add task according to what type they are
    // ID = { 0 : Todo, 1 : Deadline, 2 : Event }
    public static void addTask(String arg, Task.ID id) throws DukeException {
        switch (id) {
            case TODO:
                Todo todo = new Todo(arg);
                tasks.add(todo);
                System.out.println(INDENT + "Ayo new task just dropped:\n  " + INDENT + todo);
                System.out.println(INDENT + "Yo, we're " + tasks.size() + " task(s) deep! Let's keep this SIGMA GRINDSET!");
                break;
            case DEADLINE: {
                String[] descTime = arg.split(" /by "); // [description, by]
                if (descTime.length == 2) {
                    Deadline deadline = new Deadline(descTime[0], descTime[1]);
                    tasks.add(deadline);
                    System.out.println(INDENT + "Ayo new task just dropped:\n  " + INDENT + deadline);
                    System.out.println(INDENT + "Yo, we're " + tasks.size() + " task(s) deep! Let's keep this SIGMA GRINDSET!");
                } else { // incorrect formatting for /by
                    System.out.println(INDENT + "When you wanna do this task by lil bro?\n"
                            + INDENT + "e.g. deadline <task> /by <date/time>");
                }
                break;
            }
            case EVENT: {
                String[] descTime = arg.split(" /from "); // [description, fromTo]
                if (descTime.length == 2) {
                    String[] fromTo = descTime[1].split(" /to "); // [from , to]
                    if (fromTo.length == 2) {
                        Event event = new Event(descTime[0], fromTo[0], fromTo[1]);
                        tasks.add(event);
                        System.out.println(INDENT + "Ayo new task just dropped:\n  " + INDENT + event);
                        System.out.print(INDENT + "Yo, we're " + tasks.size() + " task(s) deep! Let's keep this SIGMA GRINDSET!\n");
                    } else { // incorrect formatting for /to
                        throw(new DukeException(INDENT + "When does this event end lil bro?\n"
                                + INDENT + "e.g. event <task> /from <start date/time> /to <start date/time>"));
                    }
                } else { // incorrect formatting for /from
                    throw(new DukeException(INDENT + "When does this event start lil bro?\n"
                            + INDENT + "e.g. event <task> /from <start date/time> /to <start date/time>"));
                }
                break;
            }
            default: // Invalid Task ID
                throw(new DukeException("Invalid Task ID, user shouldn't reach here"));
        }
    }

    public static void markTask(int i) {
        Task task = tasks.get(i - 1);
        task.markAsDone();
        System.out.println(INDENT + "Yasss King/Queen! This task is officially slayed:\n  " + INDENT + task);
    }

    public static void unmarkTask(int i) {
        Task task = tasks.get(i - 1);
        task.unmark();
        System.out.println(INDENT + "You forgor:\n  " + INDENT + task);
    }

    public static void deleteTask(int i) {
        Task task = tasks.get(i - 1);
        tasks.remove(i - 1);
        System.out.println(INDENT + "Zamn! This task never happened:\n  " + INDENT + task);
    }
}