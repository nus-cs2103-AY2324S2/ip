import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private String name;
    private ArrayList<Task> tasks = new ArrayList<Task>();

    public Duke(String name) {
        this.name = name;
    }
    public void greetings() {
        String reply = "Hello! I'm " + this.name + ". \n"
                + "What can I do for you?";
        System.out.println(reply);
    }

    public void quitApplication() {
        String reply = "Bye. Hope to see you again soon!";
        System.out.println(reply);
    }

    public void addTask(String input) throws TaskException {
        String[] splitInput = input.split(" ", 2);
        String type = splitInput[0];

        if (splitInput.length == 0) {
            throw new TaskException("OOPS!!! Please enter some tasks!");
        } else if (!containsEnumValue(TaskEnum.class, type)) {
            throw new TaskException("OOPS!!! I'm sorry, but I don't know what that means.");
        } else if (splitInput.length == 1) {
            throw new TaskException("OOPS!!! Please provide some task description");
        }

        String[] info = splitInput[1].split("/");
        String description = info[0];
        switch(type) {
            case "todo":
                tasks.add(new Todo(description));
                break;
            case "deadline":
                if (info.length < 2) throw new TaskException("OOPS!!! Please provide a deadline.");

                String by = info[1].replaceAll("by", "").trim();
                tasks.add(new Deadline(description, by));
                break;
            case "event":
                if (info.length < 3) throw new TaskException("OOPS!!! Please provide an event begin and deadline.");

                String from = info[1].replaceAll("from", "").trim();
                by = info[2].replaceAll("to", "").trim();
                tasks.add(new Event(description, from, by));
                break;
        }

        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }

    public void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) == null) break;
            Task task = tasks.get(i);
            System.out.println(i + 1 + "." + task.toString());
        }
    }

    public void changeTaskStatus(int taskNum, boolean status) throws IndexOutOfBoundsException {
        if (taskNum > tasks.size()) throw new IndexOutOfBoundsException("OOPS!!! The task doesn't exist!");

        int index = taskNum - 1;
        Task task = tasks.get(index);
        task.setStatusIcon(status);

        if (status) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Duke duke = new Duke("Rikko");
        duke.greetings();

        while (true) {
            try {
                String input = scanner.nextLine();
                String command = input.split(" ", 0)[0];
                switch(command) {
                    case "bye":
                        duke.quitApplication();
                        return;
                    case "list":
                        duke.printTasks();
                        break;
                    case "mark":
                        int taskNum = Integer.parseInt(input.split(" ", 0)[1]);
                        duke.changeTaskStatus(taskNum, true);
                        break;
                    case "unmark":
                        taskNum = Integer.parseInt(input.split(" ", 0)[1]);
                        duke.changeTaskStatus(taskNum, false);
                        break;
                    default:
                        duke.addTask(input);
                        break;
                }
            } catch (TaskException e) {
                System.err.println(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                System.err.println(e.getMessage());
            }
        }
    }
    private static <E extends Enum<E>> boolean containsEnumValue(Class<E> enumClass, String value) {
        for (Enum<E> enumConstant : enumClass.getEnumConstants()) {
            if (enumConstant.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
