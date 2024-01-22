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
            String input = scanner.nextLine().trim();
            String[] parts = input.split(" ", 2);

            //entered bye
            if (input.equalsIgnoreCase("bye")) {
                System.out.println(dash + "\nBye. Hope to see you again soon!\n" + dash);
                break;
            }

            if (input.equalsIgnoreCase("list")) {
                System.out.println(dash);
                taskList.display();
                System.out.println(dash);
            } else if (parts[0].equalsIgnoreCase("mark") && parts.length > 1){
                int taskNumber = Integer.parseInt(parts[1]);
                taskList.markTaskAsDone(taskNumber);
                System.out.println(dash);
                System.out.println("Nice! I've marked this task as done:\n  " + taskList.getTask(taskNumber));
                System.out.println(dash);
            } else if (parts[0].equalsIgnoreCase("unmark") && parts.length > 1) {
                int taskNumber = Integer.parseInt(parts[1]);
                taskList.markTaskAsNotDone(taskNumber);
                System.out.println(dash);
                System.out.println("OK, I've marked this task as not done yet:\n  " + taskList.getTask(taskNumber));
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
