import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    private static String space = "    ";

    private static String[] taskTypes = new String[] {"todo", "deadline", "event"};
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Dune, your task manager.");
        System.out.println("What can I do for you?");
        List<Task> tasks = new ArrayList<>();
        System.out.println("");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String text = scanner.nextLine();  // Read user input
            if (text.equals("list")) {
                // print out tasks line by line
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + "." + tasks.get(i));
                }
                continue;
            } else if (text.equals("bye")) {
                // exit program
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            if (text.startsWith("delete")) {
                remove(text.substring(6), tasks);
                continue;
            }

            if (text.startsWith("mark")) {
                try {
                    if (text.equals("mark")) {
                        throw new DukeException("Give an index to mark");
                    }
                    String remaining = "";
                    // remove all leading and trailing spaces
                    remaining = text.substring(4).trim();
                    // parseInt might throw NumberFormatException
                    int index = Integer.parseInt(remaining);
                    // Index... exception
                    tasks.get(index - 1).complete();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(index - 1));

                } catch (IndexOutOfBoundsException i) {
                    System.out.println("Give a valid index to mark");
                } catch (NumberFormatException n) {
                    System.out.println("Remaining characters do not match an integer");
                } catch (DukeException d) {
                    System.out.println(d);
                } finally {
                    continue;
                }
            }


            boolean addedTask = false;
            String check = "";
            for (int i = 0; i < taskTypes.length; i++) {
                check = taskTypes[i];
                if (text.startsWith(check)) {
                    addedTask = true;
                    try {
                        if (text.equals(check)) {
                            throw new DukeException("The description of a to-do cannot be empty.");
                        }
                    } catch (DukeException d) {
                        System.out.println(d);
                        continue;
                    }
                    createNewTask(i, text.substring(check.length()).trim(), tasks);
                    break;
                }
            }
            if (!addedTask) {
                {
                    try {
                        throw new DukeException("Tasks can only be of types todo, deadline, or event.");
                    } catch (DukeException d) {
                        System.out.println(d);
                        continue;
                    }
                }
            }
        }
    }

    public static void createNewTask(int i, String text, List<Task> tasks) {
        Task x = new ToDo(text.trim());
        if (i == 1) {
            try {
                String[] parts = text.split("/by");
                if (parts.length < 2) {
                    throw new DukeException("Deadlines need a deadline /by ... ");
                } else if (parts.length > 2) {
                    throw new DukeException("There can only be 1 instance of /by. String cannot be parsed...");
                }
                x = new Deadline(parts[0].trim(), parts[1].trim());
            } catch (DukeException d) {
                System.out.println(d);
                return;
            }

        } else if (i == 2) {
            String[] parts = text.split("/from");

            try {
                if (parts.length < 2) {
                    throw new DukeException("Events need a /from and a /to in this order");
                }
                String[] dates = parts[1].split("/to");
                if (parts.length > 2 || dates.length > 2) {
                    throw new DukeException("There can only be 1 instance of /from and /to\n" +
                            "String cannot be parsed...");
                } else if (dates.length < 2) {
                    throw new DukeException("Events need a /from and a /to in this order");
                }
                x = new Event(parts[0].trim(), dates[0].trim(), dates[1].trim());
            } catch (DukeException d) {
                System.out.println(d);
                return;
            }

        }
        tasks.add(x);
        System.out.println("Got it. I've added this task:");
        System.out.println(x);
        System.out.println("Now you have " + tasks.size() + " tasks in your list.");
    }

    public static void remove(String indexStr, List<Task> tasks)  {
        try {
            if (indexStr.trim().equals("")) {
                throw new DukeException("Give an index to remove");
            }
            int index = Integer.parseInt(indexStr.trim());
            Task t = tasks.get(index - 1);
            tasks.remove(index - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(t);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");

        } catch (NumberFormatException n) {
            System.out.println("Index to be removed needs to be an integer");
        } catch (IndexOutOfBoundsException i) {
            System.out.println("Give a valid index to remove");
        } catch (DukeException d) {
            System.out.println(d);
        }

    }
}
