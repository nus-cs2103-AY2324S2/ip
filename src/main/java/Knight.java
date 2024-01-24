import java.util.ArrayList;
import java.util.Scanner;

public class Knight {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final String logo = "    ┓┏┓  •  ┓  \n"
            + "    ┃┫ ┏┓┓┏┓┣┓╋\n"
            + "    ┛┗┛┛┗┗┗┫┛┗┗\n"
            + "           ┛\n";

    private enum Command {
        BYE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        EVENT,
        DEADLINE;
    }

    private static void speak(String s) {
        System.out.println("    " + s);
    }

    private static Task locateTask(String s) {
        int index = Integer.parseInt(s);
        Task task;
        try {
            task = tasks.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            speak("I regret to inform thee, Your Excellency, that thou lackest a task bearing this index in thy list.");
            throw new TaskNotFoundException();
        }
        return task;
    }

    private static Command parseCommand(String s) {
        if (s.equals("bye")) return Command.BYE;
        else if (s.equals("list")) return Command.LIST;
        else if (s.matches("mark [1-9]\\d*")) return Command.MARK;
        else if (s.matches("unmark [1-9]\\d*")) return Command.UNMARK;
        else if (s.matches("todo \\S.*")) return Command.TODO;
        else if (s.matches("deadline \\S.* /by \\S.*")) return Command.DEADLINE;
        else if (s.matches("event \\S.* /from \\S.* /to \\S.*")) return Command.EVENT;
        else throw new NonstandardCommandException();
    }
    public static void main(String[] args) {
        speak("Greetings, Your Excellency! Thy humble\n"
                + logo
                + "    doth stand before thee. How may I serve thee on this day?");

        Scanner scan = new Scanner(System.in);
        String message;
        Command command;

        while (true) {
            message = scan.nextLine();

            try {
                command = parseCommand(message);
            } catch (NonstandardCommandException e) {
                // exception to be dealt with later
                speak("Take heed, for thou shouldst reference the task thou wishest to alter by its index.");
                continue;
            }

            if (command == Command.BYE) break;

            if (command == Command.LIST) {
                if (tasks.isEmpty()) {
                    speak("Your Excellency, thy list remaineth free of tasks at this present moment.");
                } else {
                    speak("Behold, the duties thou hast assigned:");
                    int i = 1;
                    for (Task t : tasks) {
                        speak(i + "." + t);
                        i++;
                    }
                }
                continue;
            }

            if (command == Command.MARK) {
                Task task;
                try {
                    task = locateTask(message.substring(5));
                } catch (TaskNotFoundException e) {
                    continue;
                }

                task.mark();
                speak("Well met! This task hath been marked as fulfilled:\n    " + task);
                continue;
            }

            if (command == Command.UNMARK) {
                Task task;
                try {
                    task = locateTask(message.substring(7));
                } catch (TaskNotFoundException e) {
                    continue;
                }

                task.unmark();
                speak("Verily, I have marked this task as not yet done:\n    " + task);
                continue;
            }

            if (command == Command.TODO) {
                Task task = new ToDo(message.substring(5));
                tasks.add(task);
                speak("Understood. This task hath been added to thy list:\n    " + task);
                continue;
            }

            if (command == Command.DEADLINE) {
                String[] params = message.split(" /");
                Task task = new Deadline(params[0].substring(9), params[1].substring(3));
                tasks.add(task);
                speak("Understood. This task hath been added to thy list:\n    " + task);
                continue;
            }

            if (command == Command.EVENT) {
                String[] params = message.split(" /");
                Task task = new Event(params[0].substring(6), params[1].substring(5), params[2].substring(3));
                tasks.add(task);
                speak("Understood. This task hath been added to thy list:\n    " + task);
                continue;
            }
        }

        speak("Farewell, Your Excellency! May we cross paths once more in the near future.");
    }
}
