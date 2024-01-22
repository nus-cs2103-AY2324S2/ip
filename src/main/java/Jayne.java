import java.util.Scanner;
public class Jayne {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String dash = "___________________________________";
        System.out.println(dash);
        System.out.println("Hello, I'm Jayne");
        System.out.println("What can I do for you?\n" + dash);
        TaskList taskList = new TaskList();

        while (true) {
            String input = scanner.nextLine();

            //enterd bye
            if (input.equalsIgnoreCase("bye")) {
                System.out.println(dash + "\nBye. Hope to see you again soon!\n" + dash);
                break;
            }
            if (input.equalsIgnoreCase("list")) {
                System.out.println(dash);
                taskList.display();
                System.out.println(dash);
            } else {
                // Add other inputs to the task list
                taskList.add(input);
                System.out.println(dash + "\nadded: " + input + "\n" + dash);
            }
        }

        scanner.close();
    }
}
