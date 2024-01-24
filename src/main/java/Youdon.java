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
        while(!(data.isEmpty())) {
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
                for(int i = 0; taskList[i] != null; i++) {
                    System.out.println((i + 1) + ".[" + taskList[i].getStatusIcon() + "] " + taskList[i].description);
                }
                System.out.println(line);
                data = input.nextLine();
                continue;
            }

            if (data.contains(" ")) {
                // if input data has 2 parts, split into action & task number
                String[] parts = data.split(" ");
                String action = parts[0];
                String task = parts[1];
                int taskNumber = Integer.parseInt(task);

                // if input == "mark", mark the specified task as done
                if (action.equals("mark")) {
                    // mark task as done in array
                    taskList[taskNumber - 1].isDone = true;

                    // print out changes
                    System.out.println(line);
                    System.out.println("Nicely done! The task has been marked as done: ");
                    System.out.println("  [" + taskList[taskNumber - 1].getStatusIcon() + "] " + taskList[taskNumber - 1].description);
                    System.out.println(line);
                    data = input.nextLine();
                    continue;
                }

                // if input == "unmark", mark the specified task as undone
                if (action.equals("unmark")) {
                    // mark task as undone in array
                    taskList[taskNumber - 1].isDone = false;

                    // print out changes
                    System.out.println(line);
                    System.out.println("Okies! The task has been marked as undone: ");
                    System.out.println("  [" + taskList[taskNumber - 1].getStatusIcon() + "] " + taskList[taskNumber - 1].description);
                    System.out.println(line);
                    data = input.nextLine();
                    continue;
                }
            }

            // add task to tasklist
            taskList[index] = new Task(data);
            index++;

            // print that task has been added
            System.out.println(line);
            System.out.println("Task added: " + data);
            System.out.println(line);
            data = input.nextLine();
        }
    }
}
