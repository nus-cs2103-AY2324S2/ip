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
                System.out.println(dash + "\nNice! I've marked this task as done:\n  " + taskList.getTask(taskNumber) + "\n" + dash);

            } else if (parts[0].equalsIgnoreCase("unmark") && parts.length > 1) {
                int taskNumber = Integer.parseInt(parts[1]);
                taskList.markTaskAsNotDone(taskNumber);
                System.out.println(dash + "\nOK, I've marked this task as not done yet:\n  " + taskList.getTask(taskNumber) + "\n"  + dash);

            } else if (parts[0].equalsIgnoreCase("todo")) {
                Todo newTodo = new Todo(parts[1]);
                taskList.addTask(newTodo);
                System.out.println(dash + "\nGot it. I've added this task:");
                System.out.println("  " + newTodo);
                System.out.println("Now you have " + taskList.getTaskCount() + " tasks in the list." + "\n"  + dash);

            } else if (parts[0].equalsIgnoreCase("deadline")) {
                String[] deadlineParts = parts[1].split(" /by ", 2);
                Deadline newDeadline = new Deadline(deadlineParts[0], deadlineParts[1]);
                taskList.addTask(newDeadline);
                System.out.println(dash + "\nGot it. I've added this task:");
                System.out.println("  " + newDeadline);
                System.out.println("Now you have " + taskList.getTaskCount() + " tasks in the list." + "\n"  + dash);

            } else if (parts[0].equalsIgnoreCase("event")) {
                String[] eventParts = parts[1].split(" /from ", 2);
                String[] times = eventParts[1].split(" /to ", 2);
                Event newEvent = new Event(eventParts[0], times[0], times[1]);
                taskList.addTask(newEvent);
                System.out.println(dash + "\nGot it. I've added this task:");
                System.out.println("  " + newEvent);
                System.out.println("Now you have " + taskList.getTaskCount() + " tasks in the list." + "\n"  + dash);

            } else {
                // Add other inputs to the task list
                taskList.add(input);
                System.out.println(dash + "\nadded: " + input + "\n" + dash);
            }
        }

        scanner.close();
    }
}
