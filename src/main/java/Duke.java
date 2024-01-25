import java.util.*;
import Task.Task;

public class Duke {
    public static void main(String[] args) {
        String divider = "        ------------------------------------------------------------";
        String possibleCommands = "        TODO     --- todo [task name]\n" +
                                  "        DEADLINE --- deadline [task name] /by [due date]\n" +
                                  "        EVENT    --- event [task name] /from [date] /to [date]" ;
        System.out.println(divider);
        System.out.println("        Hello! I'm Chronos.");
        System.out.println("        What can I do for you?");
        System.out.println(divider);

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();
        int noOfTasks = 0;

        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            String[] token = command.split(" ", 2);

            if (token[0].equals("bye")) {
                System.out.println(divider);
                System.out.println("        Bye. Hope to see you again soon!");
                System.out.println(divider);
                break;
            } else {
                switch(token[0]) {
                    case "list":
                        if (token.length != 1) {
                            System.out.println(divider);
                            System.out.println("        Invalid command. To print the list of tasks, please use list only.");
                            System.out.println(divider);
                        } else if (tasks.isEmpty()) {
                            System.out.println(divider);
                            System.out.println("        There are no outstanding tasks in your list.\n");
                            System.out.println("        You may add various tasks with the commands below:\n" + possibleCommands);
                            System.out.println(divider);
                        } else {
                            System.out.println(divider);
                            System.out.println("        Here are the tasks in your list:");
                            for (int i = 1; i < noOfTasks + 1; i++) {
                                Task currentTask = tasks.get(i - 1);
                                System.out.println("        " + i + ". " + currentTask.toString());
                            }
                            System.out.println(divider);
                        }
                        break;
                    case "mark":
                        if (token.length != 2 || token[1].trim().isEmpty()) {
                            System.out.println(divider);
                            System.out.println("        Missing task number. Please include the task number to be marked.");
                            System.out.println(divider);
                        } else {
                            try {
                                int i = Integer.parseInt(token[1]);
                                if (i >= 0 && i <= tasks.size() - 1) {
                                    int selectedTaskNumberToBeMarked = Integer.parseInt(token[1]);
                                    Task selectedTaskToBeMarked = tasks.get(selectedTaskNumberToBeMarked - 1);
                                    selectedTaskToBeMarked.setMarked();
                                    tasks.set(selectedTaskNumberToBeMarked - 1, selectedTaskToBeMarked);
                                    System.out.println(divider);
                                    System.out.println("        Nice! I've marked this task as done:");
                                    System.out.println("          " + selectedTaskToBeMarked);
                                    System.out.println(divider);
                                } else {
                                    System.out.println(divider);
                                    System.out.println("        Task number out of range. Please include a valid task number.");
                                    System.out.println(divider);
                                }
                            } catch (NumberFormatException nfe) {
                                System.out.println(divider);
                                System.out.println("        Task number is not an integer. Please include a valid task number.");
                                System.out.println(divider);
                            }
                        }
                        break;
                    case "unmark":
                        if (token.length != 2 || token[1].trim().isEmpty()) {
                            System.out.println(divider);
                            System.out.println("        Missing task number. Please include the task number to be unmarked.");
                            System.out.println(divider);
                        } else {
                            try {
                                int i = Integer.parseInt(token[1]);
                                if (i >= 0 && i <= tasks.size() - 1) {
                                    int selectedTaskNumberToBeUnmarked = Integer.parseInt(token[1]);
                                    Task selectedTaskToBeUnmarked = tasks.get(selectedTaskNumberToBeUnmarked - 1);
                                    selectedTaskToBeUnmarked.setUnmarked();
                                    tasks.set(selectedTaskNumberToBeUnmarked - 1, selectedTaskToBeUnmarked);
                                    System.out.println(divider);
                                    System.out.println("        OK, I've marked this task as not done yet:");
                                    System.out.println("          " + selectedTaskToBeUnmarked);
                                    System.out.println(divider);
                                } else {
                                    System.out.println(divider);
                                    System.out.println("        Task number out of range. Please include a valid task number.");
                                    System.out.println(divider);
                                }
                            } catch (NumberFormatException nfe) {
                                System.out.println(divider);
                                System.out.println("        Task number is not an integer. Please include a valid task number.");
                                System.out.println(divider);
                            }
                        }
                        break;
                    case "todo":
                        if (token.length != 2 || token[1].trim().isEmpty()) {
                            System.out.println(divider);
                            System.out.println("        Missing description. Please include a description of your todo.");
                            System.out.println(divider);
                        } else {
                            Todo todo = new Todo(token[1]);
                            tasks.add(todo);
                            noOfTasks++;

                            System.out.println(divider);
                            System.out.println("        Got it. I've added this task:");
                            System.out.println("          " + todo);
                            System.out.println("        Now you have " + tasks.size() + " tasks in the list.");
                            System.out.println(divider);
                        }
                        break;
                    case "deadline":
                        try {
                            String[] descriptionAndBy = token[1].split("/");
                            String[] dueDate = descriptionAndBy[1].split(" ", 2);

                            Deadline deadline = new Deadline(descriptionAndBy[0].trim(), dueDate[1]);
                            tasks.add(deadline);
                            noOfTasks++;

                            System.out.println(divider);
                            System.out.println("        Got it. I've added this task:");
                            System.out.println("          " + deadline);
                            System.out.println("        Now you have " + tasks.size() + " tasks in the list.");
                            System.out.println(divider);
                        } catch (Exception e) {
                            System.out.println(divider);
                            System.out.println("        Invalid command. Please include a task name and a valid due date following the syntax of the example below:");
                            System.out.println("        e.g. deadline return library book /by 6th March");
                        }
                        break;
                    case "event":
                        try {
                            String[] descriptionAndFromAndTo = token[1].split("/");
                            String task = descriptionAndFromAndTo[0];
                            String from = descriptionAndFromAndTo[1].split(" ", 2)[1];
                            String to = descriptionAndFromAndTo[2].split(" ", 2)[1];
                            Event event = new Event(task.trim(), from.trim(), to.trim());
                            tasks.add(event);
                            noOfTasks++;

                            System.out.println(divider);
                            System.out.println("        Got it. I've added this task:");
                            System.out.println("          " + event);
                            System.out.println("        Now you have " + tasks.size() + " tasks in the list.");
                            System.out.println(divider);
                        } catch (Exception e) {
                            System.out.println(divider);
                            System.out.println("        Invalid command. Please include a task name and a valid due date following the syntax of the example below:");
                            System.out.println("        e.g. event project meeting /from 2 Feb 2pm /to 2 Feb 4pm");
                            System.out.println(divider);
                        }
                        break;
                    default:
                        System.out.println(divider);
                        System.out.println("        Invalid command. Please try again.");
                        System.out.println(divider);
                        break;
                }
            }
        }

        sc.close();
    }
}