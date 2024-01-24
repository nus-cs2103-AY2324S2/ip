import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Duke.greet();

        Scanner sc = new Scanner(System.in);
        Task[] taskList = new Task[100];
        int listSize = 0;
        boolean input = true;
        while (input) {
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
            } else if (message.startsWith("todo ")) {
                Task task = new ToDo(message);
                taskList[listSize] = task;
                Duke.echo(task.toString());
                listSize++;
            } else if (message.startsWith("deadline ")) {
                String[] inputs = message.split("/by");
                Task task = new Deadline(inputs[0].trim(), inputs[1].trim());
                taskList[listSize] = task;
                Duke.echo(task.toString());
                listSize++;
            } else if (message.startsWith("event ")) {
                String[] inputs = message.split("/from");
                String[] innerInputs = inputs[1].split("/to");
                Task task = new Event(inputs[0].trim(), innerInputs[0].trim(), innerInputs[1].trim());
                taskList[listSize] = task;
                Duke.echo(task.toString());
                listSize++;
            } else {
                Task task = new Task(message);
                taskList[listSize] = task;
                Duke.echo(task.toString());
                listSize++;
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
        System.out.println("Added task: \n" + message);
    }

    public static void listYaps(Task[] taskList, int listSize) {
        if (listSize == 0) {
            System.out.println("Nothin' to yap...");
        }
        for (int i = 0; i < listSize; i++) {
            System.out.println((i+1)+". "+taskList[i]);
        }
    }

}
