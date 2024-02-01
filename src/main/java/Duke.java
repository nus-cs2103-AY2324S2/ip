import java.nio.file.FileSystems;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;



public class Duke {

    private static final String SPACE = "    ";
    private static String[] taskTypes = new String[] {"todo", "deadline", "event"};

    private static TaskList tasks = new TaskList();

    private static Storage s = new Storage();

    private static final File f = new File(Duke.class.getProtectionDomain().getCodeSource().getLocation().getPath());
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello! I'm Dune, your task manager.");
        System.out.println("What can I do for you?");
        // method to load data from txt file
        String filePath = "";
        s.loadTasks(tasks);
        System.out.println("");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String text = scanner.nextLine();  // Read user input
            if (text.equals("list")) {
                tasks.print();
                continue;
            } else if (text.equals("bye")) {
                // exit program
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            if (text.startsWith("delete")) {
                remove(text.substring(6), tasks);
                s.saveTasks(tasks);
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
                    tasks.getTask(index - 1).complete();
                    s.saveTasks(tasks);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.getTask(index - 1));

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
            String check;
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

    public static void createNewTask(int i, String text, TaskList tasks) {
        Task x = new ToDo(text.trim());
        if (i == 1) {
            try {
                String[] parts = text.split("/by");
                if (parts.length < 2) {
                    throw new DukeException("Deadlines need a deadline /by ... ");
                } else if (parts.length > 2) {
                    throw new DukeException("There can only be 1 instance of /by. String cannot be parsed...");
                }
                try {
                    x = new Deadline(parts[0].trim(), parts[1].trim());
                } catch (DateTimeParseException d) {
                    if (d.getMessage().equals("Start date cannot be after end date")) {
                        System.out.println(d.getMessage());
                    } else {
                        System.out.println("Enter date in the format yyyy-mm-ddTHH:MM");
                    }
                    return;
                }
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
                try {
                    x = new Event(parts[0].trim(), dates[0].trim(), dates[1].trim());
                } catch (DateTimeParseException d) {
                    System.out.println(d.getMessage());
                    return;
                }
            } catch (DukeException d) {
                System.out.println(d);
                return;
            }

        }
        tasks.addTask(x);
        System.out.println("Got it. I've added this task:");
        System.out.println(x);
        System.out.println("Now you have " + tasks.getSize() + " tasks in your list.");
        s.saveTasks(tasks);
    }

    public static void remove(String indexStr, TaskList tasks)  {
        try {
            if (indexStr.trim().equals("")) {
                throw new DukeException("Give an index to remove");
            }
            int index = Integer.parseInt(indexStr.trim());
            Task t = tasks.getTask(index - 1);
            tasks.deleteTask(index - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(t);
            System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");

        } catch (NumberFormatException n) {
            System.out.println("Index to be removed needs to be an integer");
        } catch (IndexOutOfBoundsException i) {
            System.out.println("Give a valid index to remove");
        } catch (DukeException d) {
            System.out.println(d);
        }

    }

}
