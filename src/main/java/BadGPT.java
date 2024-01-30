import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BadGPT {
    private static final String NAME = "BadGPT";
    private static List<Task> tasks = new ArrayList<>(100);

    public static void main(String[] args) {
        line();
        System.out.println("Hello! I'm " + NAME + ".\n" + "What can I do for you?");
        line();

        Scanner sc = new Scanner(System.in);

        while (true) {
            String cmd = sc.next();

            try {
                // Cases
                // "bye": Exit the loop and the program.
                // "list": List out all currently stored tasks.
                // "mark": Mark the task corresponding to the number entered after as complete.
                // "unmark": Unmark the task corresponding to the number entered after.
                // Else, store the string entered as a new Task object.
                switch (cmd) {
                    case "bye": {
                        sc.close();
                        bye();
                        break;
                    }
                    case "list": {
                        list();
                        break;
                    }
                    case "mark": {
                        try {
                            int taskNum = sc.nextInt() - 1;
                            mark(taskNum);
                        } catch (InputMismatchException e) {
                            throw new TaskNotFoundException(e.getMessage(), tasks.size());
                        } finally {
                            sc.nextLine();
                        }
                        break;
                    }
                    case "unmark": {
                        try {
                            int taskNum = sc.nextInt() - 1;
                            unmark(taskNum);
                        } catch (InputMismatchException e) {
                            throw new TaskNotFoundException(e.getMessage(), tasks.size());
                        } finally {
                            sc.nextLine();
                        }
                        break;
                    }
                    case "todo": {
                        String description = sc.nextLine().stripLeading();
                        if (description.isEmpty()) {
                            line();
                            System.out.println("are you satisfied with that, todo aoi");
                            line();
                        } else {
                            store(new ToDo(description));
                        }
                        break;
                    }
                    case "deadline": {
                        String[] taskInfo = parser(sc.nextLine());
                        store(new Deadline(taskInfo[0], taskInfo[1]));
                        break;
                    }
                    case "event": {
                        String[] taskInfo = parser(sc.nextLine());
                        store(new Event(taskInfo[0], taskInfo[1]));
                        break;
                    }
                    case "delete": {
                        try {
                            int taskNum = sc.nextInt() - 1;
                            delete(taskNum);
                        } catch (InputMismatchException e) {
                            throw new TaskNotFoundException(e.getMessage(), tasks.size());
                        } finally {
                            sc.nextLine();
                        }
                        break;
                    }
                    default: {
                        line();
                        System.out.println("我不明白");
                        sc.nextLine(); // Remove the entire line after the command
                        line();
                    }
                }
            } catch (TaskNotFoundException e) {
                System.err.println(e);
            }
        }
    }

    // TODO: add following exceptions: command not found, missing description/date for tasks,
    // invalid format for task type,

    public static void line() {
        System.out.println("____________________________________________________________");
    }

    public static void store(Task task) {
        tasks.add(task);
        line();
        System.out.println("Added task: " + task);
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
        line();
    }

    public static void list() {
        line();
        // TODO: add case where nothing is in list
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        line();
    }

    public static void mark(int taskNum) throws TaskNotFoundException {
        try {
            String msg;
            if (!tasks.get(taskNum).isComplete()) {
                tasks.get(taskNum).complete();
                msg = "Nice! I've marked this task as done:\n" + tasks.get(taskNum);
            } else {
                msg = tasks.get(taskNum) + "\nThis task has already been completed.";
            }
            line();
            System.out.println(msg);
            line();
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException(e.getMessage(), tasks.size());
        }
    }

    public static void unmark(int taskNum) throws TaskNotFoundException {
        try {
            String msg;
            if (tasks.get(taskNum).isComplete()) {
                tasks.get(taskNum).uncomplete();
                msg = "wyd bro\n" + tasks.get(taskNum);
            } else {
                msg = tasks.get(taskNum) + "\nit's not even done yet lol";
            }
            line();
            System.out.println(msg);
            line();
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException(e.getMessage(), tasks.size());
        }
    }

    public static String[] parser(String in) {
        String taskInfo = "", description = "";
        String[] out = new String[2];
        for (String next : in.split(" ")) {
            if (next.contains("/")) {
                if (description.isEmpty()){
                    description = taskInfo.trim();
                    taskInfo = "";
                }
                next = next.substring(1);
                next += ":";
            }
            taskInfo += next + " ";
        }
        taskInfo = taskInfo.trim();
        out[0] = description;
        out[1] = taskInfo;
        return out;
    }

    public static void delete(int taskNum) throws TaskNotFoundException {
        try {
            Task task = tasks.remove(taskNum);
            line();
            System.out.println("This task has been removed: " + task);
            System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
            System.out.println("No, what are you waiting for? Do it! Just do it!");
            line();
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException(e.getMessage(), tasks.size());
        }
    }

    public static void bye() {
        line();
        System.out.println("Smell ya later");
        line();
        System.exit(0);
    }
}
