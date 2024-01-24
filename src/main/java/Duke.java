import java.io.*;
import java.util.*;
public class Duke {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("================================ \n" +
                "Hello I'm Axolotl! \n" +
                "What can I do for you? \n" +
                "================================ \n");

        String input = sc.nextLine();
        ArrayList<Task> list = new ArrayList<>();

        while (!input.equals("bye")) {
            int cmdSplit = input.indexOf(" ");
            String command = input;
            String task = null;
            if (cmdSplit != -1) {
                command = input.substring(0, cmdSplit);
                task = input.substring(input.indexOf(" ") + 1);
            }
            else {
                if (command.equals("mark") | command.equals("unmark")) {
                    System.out.println("-------------------------------- \n" +
                            "Oops, I'm not sure which task you are referring to! Please indicate a task number (e.g. " + command + " 1) \n" +
                            "-------------------------------- \n");
                }
                else if (command.equals("todo")) {
                    System.out.println("-------------------------------- \n" +
                            "Oops, wrong format! Please indicate task details (e.g. todo CS2103 Lab 1) \n" +
                            "-------------------------------- \n");
                }
//                else if (command.equals("deadline")) {
//                    System.out.println("-------------------------------- \n" +
//                            "Oops, wrong format! Please follow this format for deadline task entries (e.g. deadline submit report /by 11/10/2019 5pm ) \n" +
//                            "-------------------------------- \n");
//                }
//                else if (command.equals("event")) {
//                    System.out.println("-------------------------------- \n" +
//                            "Oops, wrong format! Please follow this format for event task entries (e.g. team project meeting /from June 9th 2pm /to 4pm ) \n" +
//                            "-------------------------------- \n");
//                }
            }

            if (command.equals("list")) {
                System.out.println( "-------------------------------- \n" +
                                    "Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    Task iTask = list.get(i);
                    System.out.println((i+1) + ". " + iTask.toString());
                }
                System.out.println( "-------------------------------- \n");
            }
            else if (command.equals("mark")) {
                int taskNo = Integer.parseInt(task) - 1;
                Task dTask = list.get(taskNo);
                dTask.done();

                System.out.println("-------------------------------- \n" +
                        "Nice! I've marked task " + taskNo + " as done: \n" +
                        dTask.toString() + "\n" +
                        "-------------------------------- \n");
            }
            else if (command.equals("unmark")) {
                int taskNo = Integer.parseInt(task) - 1;
                Task uTask = list.get(taskNo);
                uTask.undone();

                System.out.println("-------------------------------- \n" +
                        "Sure, I've marked task " + taskNo + " as not done yet: \n" +
                        uTask.toString() + "\n" +
                        "-------------------------------- \n");
            }
            else if (command.equals("todo") | command.equals("deadline") | command.equals("event")) {
                Task t = null;
                boolean success = true;
                switch(command) {
                    case "todo":
                        t = new ToDo(task);
                        break;
                    case "deadline":
                        if (task == null || !task.contains(" /by ")) {
                            success = false;
                            System.out.println("-------------------------------- \n" +
                                    "Oops, wrong format! Please follow this format for deadline task entries (e.g. deadline submit report /by 11/10/2019 5pm ) \n" +
                                    "-------------------------------- \n");
                        }
                        else {
                            String deadline[] = task.split(" /by ");
                            t = new Deadline(deadline[0], deadline[1]);
                        }
                        break;
                    case "event":
                        if (task == null || !(task.contains(" /from ") && task.contains(" /to "))) {
                            success = false;
                            System.out.println("-------------------------------- \n" +
                                    "Oops, wrong format! Please follow this format for event task entries (e.g. event team project meeting /from June 9th 2pm /to 4pm ) \n" +
                                    "-------------------------------- \n");
                        }
                        else {
                            String event = task.substring(0, task.indexOf(" /from "));
                            String from = task.substring(task.indexOf("/from ") + 6, task.indexOf(" /to "));
                            String to = task.substring((task.indexOf("/to ") + 4));
                            t = new Event(event, from, to);
                        }
                        break;

                }
                if (success) {
                    list.add(t);
                    System.out.println("-------------------------------- \n" +
                            "Sure, I've added this task: \n" +
                            t.toString() + "\n" +
                            "Now you have " + list.size() + " task(s) in the list. \n" +
                            "-------------------------------- \n");
                }
            }

            else {
                System.out.println("-------------------------------- \n" +
                        "Oops, I'm not sure what you meant by that! Commands you can use: \n" +
                        "todo <task_name> \n" +
                        "deadline <task_name> /by <due_date> \n" +
                        "event <task_name> /from <start_date> /to <end_date \n" +
                        "list \n" +
                        "mark <task_number> \n" +
                        "unmark <task_number> \n" +
                        "-------------------------------- \n");
            }
            input = sc.nextLine();
        }

        System.out.println("================================ \n" +
                "Bye. Hope to see you again soon! \n" +
                "================================ \n");
    }
}
