import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    private static Task[] taskList = new Task[100];
    private static int listSize = 0;
    public static void main(String[] args) {

        Duke.greet();

        Scanner sc = new Scanner(System.in);
        boolean input = true;
        while (input) {
            try {
                String message = sc.nextLine();
                if (message.equals("bye")) {
                    input = false;
                } else if (message.equals("yap")) {
                    Duke.listYaps(taskList, listSize);
                } else if (message.startsWith("mark ")) {
                    String[] inputs = message.split(" ");
                    Integer index = Integer.parseInt(inputs[1]);
                    Task task = taskList[index - 1];
                    task.markDone();
                } else if (message.startsWith("unmark ")) {
                    String[] inputs = message.split(" ");
                    Integer index = Integer.parseInt(inputs[1]);
                    Task task = taskList[index - 1];
                    task.unmarkDone();
                } else if (message.startsWith("todo")) {
                    Task task = initTask(message, "todo");
                    addTasktoTaskList(task);
                } else if (message.startsWith("deadline")) {
                    Task task = initTask(message, "deadline");
                    addTasktoTaskList(task);
                } else if (message.startsWith("event")) {
                    Task task = initTask(message, "event");
                    addTasktoTaskList(task);
                } else {
                    throw new DukeException("Specify the task type, YAPPER!!");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }

        Duke.exit();

    }

    public static void greet() {
        String logo = "▀█▀ ▄▀█ █▀ █▄▀ █▄█ ▄▀█ █▀█ █▀█ █▀▀ █▀█\n"
                + "░█░ █▀█ ▄█ █░█ ░█░ █▀█ █▀▀ █▀▀ ██▄ █▀▄\n";

        System.out.println("*YAP* Good morning YAPPER! *YAP*\nGreetings from\n" + logo);
    }

    public static void exit() {
        String bye = "█▀▀ █▀█ █▀█ █▀▄ █▄▄ █▄█ █▀▀ █\n"
                + "█▄█ █▄█ █▄█ █▄▀ █▄█ ░█░ ██▄ ▄\n";

        System.out.println("Stoppin' the YAP...\n" + bye);
    }

    public static void echo(String message) {
        System.out.println("Added task:\n" + message);
    }

    public static void listYaps(Task[] taskList, int listSize) {
        if (listSize == 0) {
            System.out.println("Nothin' to yap...");
        }
        for (int i = 0; i < listSize; i++) {
            System.out.println((i+1)+". "+taskList[i]);
        }
    }

    public static Task initTask(String message, String taskType) {
        Task task;
        try {
            if (taskType.equals("todo")) {
                try {
                    String[] inputs = message.split("todo ");
                    task = new ToDo(inputs[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("Whats the task, yapper???");
                }
            } else if (taskType.equals("deadline")) {
                try {
                    message = message.substring("deadline ".length());
                    String[] inputs = message.split("/by");
                    task = new Deadline(inputs[0].trim(), inputs[1].trim());
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("Deadlines need a deadline, yapper!");
                } catch (StringIndexOutOfBoundsException e ) {
                    throw new DukeException("Whats the task, yapper???");
                }
            } else if (taskType.equals("event")) {
                try {
                    message = message.substring("event ".length());
                    String[] inputs = message.split("/from");
                    String[] innerInputs = inputs[1].split("/to");
                    task = new Event(inputs[0].trim(), innerInputs[0].trim(), innerInputs[1].trim());
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("YAPYAP, What time is your from and to?");
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("Whats the task, yapper???");
                }
            } else { //should not reach here because of filter in main logic
                task = new Task(message);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return task;
    }

    public static void addTasktoTaskList(Task task) {
        if (task == null) {
            return;
        }
        taskList[listSize] = task;
        Duke.echo(task.toString());
        listSize++;
    }

}
