import java.util.Scanner;
public class Tam {
    static String dividerText = "____________________________________________________________\n";
    static Scanner scannerObj = new Scanner(System.in);
    static String[] taskList = new String[100];
    static int numTasks = 0;
    public static void main(String[] args) {
        Tam.greet();
        int status = readCommand();
        while (status == 1) {
            status = readCommand();
        }
        Tam.exit();
    }

    public static void greet() {
        String greetText = "Hello! I'm Tam the Task Manager!\nWhat can I do for you?\n";
        System.out.print(dividerText);
        System.out.print(greetText);
        System.out.print(dividerText);
    }

    public static void exit() {
        String exitText = "Bye. Hope to see you again soon!\n";
        System.out.print(exitText);
        System.out.print(dividerText);
    }

    public static int readCommand() {
        String input = scannerObj.nextLine();
        System.out.print(dividerText);
        // exit command
        if (input.toLowerCase().equals("bye") || input.toLowerCase().equals("exit")) {
            return 0;
        }
        // list tasks command
        else if (input.toLowerCase().equals("list")) {
            for (int i = 0; i < numTasks; i++) {
                System.out.println((i+1) + ". " + taskList[i]);
            }
            System.out.print(dividerText);
            return 1;
        }
        // add task command
        else {
            System.out.println("Added: " + input);
            taskList[numTasks] = input;
            numTasks++;
            System.out.print(dividerText);
            return 1;
        }
    }
}
