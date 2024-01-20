import java.util.Scanner;

public class Duke {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final TaskList TASKS = new TaskList();

    public static void main(String[] args) {
        printBanner();
        printGreetings();
        handleInput();
        printExit();
        Duke.SCANNER.close();
    }

    private static void handleInput() {
        String input = Duke.SCANNER.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                Duke.print(Duke.TASKS.toString().split("\n"));
            } else if (input.startsWith("mark ")) {
                String indexStr = input.substring(5);
                int index = Integer.parseInt(indexStr);
                Duke.TASKS.getTask(index).done();
                String[] messages = {
                    "Nice! I've marked this task as done:",
                    Duke.TASKS.getTask(index).toString()
                };
                Duke.print(messages);
            } else if (input.startsWith("unmark ")) {
                // TODO
            } else {
                String[] messages = { "added: " + input };
                Duke.TASKS.addTask(new Task(input));
                Duke.print(messages);
            }
            input = Duke.SCANNER.nextLine();
        }
    }

    private static void printBanner() {
        System.out.println(" ██████╗ █████╗ ██████╗ ██████╗ ██╗   ██╗");
        System.out.println("██╔════╝██╔══██╗██╔══██╗██╔══██╗╚██╗ ██╔╝");
        System.out.println("██║     ███████║██████╔╝██████╔╝ ╚████╔╝ ");
        System.out.println("██║     ██╔══██║██╔═══╝ ██╔═══╝   ╚██╔╝  ");
        System.out.println("╚██████╗██║  ██║██║     ██║        ██║   ");
        System.out.println(" ╚═════╝╚═╝  ╚═╝╚═╝     ╚═╝        ╚═╝   ");
    }

    private static void print(String[] messages) {
        System.out.println("____________________________________________________________");
        for (String msg : messages) {
            System.out.println(" " + msg);
        }
        System.out.println("____________________________________________________________\n");
    }

    private static void printGreetings() {
        String[] messages = {"Hello! I'm Cappy", "What can I do for you?"};
        Duke.print(messages);
    }

    private static void printExit() {
        String[] messages = {"Bye. Hope to see you again soon!"};
        Duke.print(messages);
    }
}
