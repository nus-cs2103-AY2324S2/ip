import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        String gap = "____________________________________________________________\n";
        System.out.println(logo);
        System.out.println(gap + "Greetings! I am Aegis.\n"
                         + "How can I assist you?\n" + gap);
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        while (true) {
            try {
                String input = sc.nextLine();
                StringTokenizer st = new StringTokenizer(input);
                String identifier = st.nextToken().toLowerCase();
                if (identifier.equals("bye")) {
                    sc.close();
                    System.out.println(gap + "Goodbye! Have a pleasant day!\n" + gap);
                    break;
                }
                if (identifier.equals("list")) {
                    if (st.hasMoreTokens()) {
                        throw new DukeException("list command does not accept arguments.\n"
                                               + "Enter 'list' to view the current list of tasks");
                    }
                    String taskList = "";
                    for (int i = 0; i < tasks.size(); i++) {
                        Task curr = tasks.get(i);
                        taskList += ((i+1) + ". " + curr.toString() + "\n");
                    }
                    System.out.println(gap + "Here are your tasks:\n" + taskList + gap);
                } else if (identifier.contains("mark")) {
                    String command = identifier.toLowerCase();
                    int taskNum = Integer.parseInt(st.nextToken()) - 1;
                    Task selectedTask = tasks.get(taskNum);
                    if (command.equals("mark")) {
                        selectedTask.markDone();
                        System.out.println(gap + "Well done, task marked as completed.\n"
                                + selectedTask.toString() + "\n" + gap);
                    } else {
                        selectedTask.markNotDone();
                        System.out.println(gap + "Understood, task marked as uncompleted.\n"
                                + selectedTask.toString() + "\n" + gap);
                    }
                } else if (identifier.equals("todo") || identifier.equals("deadline")
                        || identifier.equals("event")) {
                    switch (identifier) {
                    case "todo":
                        if (st.hasMoreTokens()) {
                            String todoDesc = "";
                            while(st.hasMoreTokens()) {
                                todoDesc += st.nextToken() + " ";
                            }
                            ToDo newToDo = new ToDo(todoDesc.trim());
                            tasks.add(newToDo);
                            System.out.println(gap + "Confirmed. New task added:\n"
                                    + newToDo.toString() + "\n");
                        } else {
                            throw new DukeException("todo command requires a description for the task."
                                                   + "\n\nPlease leave a space after 'todo' and enter"
                                                   + " the task description.");
                        }
                        break;
                    case "deadline":
                        if (st.hasMoreTokens()) {
                            String[] deadlineInfo = input.split("/");
                            st = new StringTokenizer(deadlineInfo[0].trim());
                            st.nextToken();
                            String deadlineDesc = "";
                            while(st.hasMoreTokens()) {
                                deadlineDesc += st.nextToken() + " ";
                            }
                            st = new StringTokenizer(deadlineInfo[1].trim());
                            st.nextToken();
                            String by = "";
                            while(st.hasMoreTokens()) {
                                by += st.nextToken() + " ";
                            }
                            Deadline newDeadline = new Deadline(deadlineDesc.trim(), by.trim());
                            tasks.add(newDeadline);
                            System.out.println(gap + "Confirmed. New task added:\n"
                                    + newDeadline.toString() + "\n");
                        } else {
                            throw new DukeException("deadline command requires a description for the task"
                                                   + " and a deadline. \n\nPlease leave a space after 'deadline'"
                                                   + " and enter the task description, \nfollowed by a space and a"
                                                   + " forward slash then the deadline of the task.");
                        }
                        break;
                    case "event":
                        if (st.hasMoreTokens()) {
                            String[] eventInfo = input.split("/");
                            st = new StringTokenizer(eventInfo[0].trim());
                            st.nextToken();
                            String eventDesc = "";
                            while(st.hasMoreTokens()) {
                                eventDesc += st.nextToken() + " ";
                            }
                            st = new StringTokenizer(eventInfo[1].trim());
                            st.nextToken();
                            String from = "";
                            while (st.hasMoreTokens()) {
                                from += st.nextToken() + " ";
                            }
                            st = new StringTokenizer(eventInfo[2].trim());
                            st.nextToken();
                            String end = "";
                            while (st.hasMoreTokens()) {
                                end += st.nextToken() + " ";
                            }
                            Event newEvent = new Event(eventDesc.trim(), from.trim(), end.trim());
                            tasks.add(newEvent);
                            System.out.println(gap + "Confirmed. New task added:\n"
                                    + newEvent.toString() + "\n");
                        } else {
                            throw new DukeException("event command requires a description for the task,"
                                    + " start time and end time. \n\nPlease leave a space after 'event'"
                                    + " and enter the task description, \nfollowed by a space and forward slash"
                                    + " before the start time, \nfollowed by another space and forward slash"
                                    + " before the end time.");
                        }
                        break;
                    default:
                        System.out.println("なに？！");
                        break;
                    }
                    System.out.println("Total task count: " + tasks.size() + ".\n" + gap);
                } else if (identifier.equals("delete")) {
                    int delIndex = Integer.parseInt(st.nextToken()) - 1;
                    Task toDelete = tasks.remove(delIndex);
                    System.out.println(gap + "Acknowledged. The following task has been removed:\n"
                                      + toDelete.toString());
                    System.out.println("\nTasks remaining: " + tasks.size() + ".\n" + gap);
                } else {
                    throw new DukeException("I do not recognize that command.\n"
                                           + "Please enter a valid command.");
                }
            } catch (DukeException e) {
                System.out.println(gap + e.getMessage() + "\n" + gap);
            }
        }
    }
}