import java.util.Scanner;

public class Youdon {
    public static void main(String[] args) {
        // initialise array and index
        Task[] taskList = new Task[100];
        int index = 0;

        // string constants
        String line = "----------------------------------------------------------------";
        String welcomeMsg = "Hello! I'm Youdon!\nWhat can I do for you?\n";
        String byeMsg = "Bye. Hope to see you again soon!";

        // chatbot welcome message
        System.out.println(line);
        System.out.println(welcomeMsg);
        System.out.println(line);

        // scan input
        Scanner input = new Scanner(System.in);
        String data = input.nextLine();

        // when there is input present
        while (!(data.isEmpty())) {
            // if input == "bye", print chatbot bye message
            if (data.equals("bye")) {
                System.out.println(line);
                System.out.println(byeMsg);
                System.out.println(line);
                break;
            }

            // if input == "list", return tasklist
            if (data.equals("list")) {
                System.out.println(line);
                System.out.println("Here are your tasks:");
                for (int i = 0; taskList[i] != null; i++) {
                    System.out.println((i + 1) + ". " + taskList[i].toString());
                }
                System.out.println(line);
                data = input.nextLine();
                continue;
            }

            if (data.contains(" ")) {
                // if input data has 2 parts, split into command & task number
                String[] parts = data.split(" ", 2);
                String command = parts[0];
                String task = parts[1];

                // if input == "mark", mark the specified task as done
                if (command.equals("mark")) {
                    // mark task as done in array
                    int taskNumber = Integer.parseInt(task);
                    taskList[taskNumber - 1].isDone = true;

                    // print out changes
                    System.out.println(line);
                    System.out.println("Nicely done! The task has been marked as done: ");
                    System.out.println("  " + taskList[taskNumber - 1].toString());
                    System.out.println(line);
                    data = input.nextLine();
                    continue;
                }

                // if input == "unmark", mark the specified task as undone
                if (command.equals("unmark")) {
                    // mark task as undone in array
                    int taskNumber = Integer.parseInt(task);
                    taskList[taskNumber - 1].isDone = false;

                    // print out changes
                    System.out.println(line);
                    System.out.println("Okies! The task has been marked as undone: ");
                    System.out.println("  " + taskList[taskNumber - 1].toString());
                    System.out.println(line);
                    data = input.nextLine();
                    continue;
                }

                // differentiate between type of tasks and add to tasklist
                if (command.equals("todo")) {
                    // add to tasklist
                    taskList[index] = new Todo(task);
                    // print out task added
                    System.out.println(line);
                    System.out.println("Alright! Task added:\n  " + taskList[index].toString());
                    System.out.println("You now have " + (index + 1) + " task(s) in the list.");
                    System.out.println(line);
                    index++;
                }

                // if task has "by", split into task and deadline
                if (task.contains("/by ")) {
                    String[] chunks = task.split("/by ");
                    if (command.equals("deadline")) {
                        // add to tasklist
                        String taskDesc = chunks[0];
                        String deadline = chunks[1];
                        taskList[index] = new Deadline(taskDesc, deadline);

                        // print out task added
                        System.out.println(line);
                        System.out.println("Alright! Task added:\n  " + taskList[index].toString());
                        System.out.println("You now have " + (index + 1) + " task(s) in the list.");
                        System.out.println(line);
                        index++;
                    }
                }

                if (task.contains("/from ")) {
                    String[] sections = task.split("/from |/to");
                    if (command.equals("event")) {
                        // add to tasklist
                        String taskDesc = sections[0];
                        String start = sections[1];
                        String end = sections[2];
                        taskList[index] = new Event(taskDesc, start, end);

                        // print out task added
                        System.out.println(line);
                        System.out.println("Alright! Task added:\n  " + taskList[index].toString());
                        System.out.println("You now have " + (index + 1) + " task(s) in the list.");
                        System.out.println(line);
                        index++;
                    }
                }
                data = input.nextLine();
            }
        }
    }
}