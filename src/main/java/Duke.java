import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static ArrayList<Task> taskList = new ArrayList<Task>();
    public static Integer taskCount = 0;

    public static void main(String[] args) {
        //Greets User
        String lineBreak = "-----------------";
        System.out.println(lineBreak + '\n' +
                "Hello! I'm Panda" + '\n' +
                "What can I do for you?" + '\n' +
                lineBreak);

        Scanner sc = new Scanner(System.in);
        String user_input = sc.nextLine();
        // #Edit code from here onwards#
        while (!user_input.equals("bye")) {
            try {
                String[] inputArray = user_input.split(" ");
                switch (inputArray[0]) {
                    case "list":
                        if (taskList.size() == 0) {
                            System.out.println(lineBreak + '\n' +
                                    "There is currently no task in your list, trying adding some!" + '\n' +
                                    lineBreak);
                        } else {
                            System.out.println(lineBreak + '\n' +
                                    "Here are the tasks in your list: ");
                            for (int i = 0; i < taskCount; i++) {
                                System.out.println("" + (i + 1) + ". " + taskList.get(i));
                            }
                            System.out.println(lineBreak);
                        }
                        break;
                    case "mark": {
                        int taskNum = Integer.parseInt(inputArray[1]);
                        Task currTask = taskList.get(taskNum - 1);
                        currTask.check();
                        taskList.set(taskNum - 1, currTask);
                        System.out.println(lineBreak + '\n' +
                                "Nice! I've marked this task as done:" + '\n' +
                                currTask + '\n' +
                                lineBreak);
                        break;
                    }
                    case "unmark": {
                        int taskNum = Integer.parseInt(inputArray[1]);
                        Task currTask = taskList.get(taskNum - 1);
                        currTask.uncheck();
                        taskList.set(taskNum - 1, currTask);
                        System.out.println(lineBreak + '\n' +
                                "OK, I've marked this task as not done yet:" + '\n' +
                                currTask + '\n' +
                                lineBreak);
                        break;
                    }
                    case "todo": {
                        String[] tempArray = user_input.split(" ", 2);
                        if (tempArray.length == 1) {
                            throw new Exception("OOPS!!! The description of a todo cannot be empty.");
                        }
                        Task currTask = new Todo(tempArray[1]);
                        taskList.add(currTask);
                        taskCount++;
                        System.out.println(lineBreak + '\n' +
                                "Got it. I've added this task:" + '\n' +
                                currTask + '\n' +
                                "Now you have " + taskCount + " tasks in the list." + '\n' +
                                lineBreak);
                        break;
                    }
                    case "deadline": {
                        String[] dateArray = user_input.split("/by");
                        String[] descriptionArray = dateArray[0].split(" ", 2);
                        Task currTask = new Deadline(descriptionArray[1].stripLeading(), dateArray[1]);
                        taskList.add(currTask);
                        taskCount++;
                        System.out.println(lineBreak + '\n' +
                                "Got it. I've added this task:" + '\n' +
                                currTask + '\n' +
                                "Now you have " + taskCount + " tasks in the list." + '\n' +
                                lineBreak);
                        break;
                    }
                    case "event": {
                        String[] dateArray = user_input.split("/");
                        String[] descriptionArray = dateArray[0].split(" ", 2);
                        String date = dateArray[1].substring(5);
                        String[] timeArray = user_input.split("/to");
                        String time = timeArray[1];
                        Task currTask = new Event(descriptionArray[1].stripTrailing(), date, time);
                        taskList.add(currTask);
                        taskCount++;
                        System.out.println(lineBreak + '\n' +
                                "Got it. I've added this task:" + '\n' +
                                currTask + '\n' +
                                "Now you have " + taskCount + " tasks in the list." + '\n' +
                                lineBreak);

                        break;
                    }

                    default: {
                        throw new Exception("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
            } catch (Exception e) {
                System.out.println(lineBreak + '\n' +
                        e.getMessage() + '\n' +
                        lineBreak);

            }
            user_input = sc.nextLine();
        }
        System.out.println(lineBreak + '\n' +
                "Bye. Hope to see you again soon!" + '\n' +
                lineBreak);
    }
}



//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

