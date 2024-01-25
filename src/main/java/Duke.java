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
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                break;
            }
            if (input.equalsIgnoreCase("list")) {
                String taskList = "";
                for (int i = 0; i < tasks.size(); i++) {
                    Task curr = tasks.get(i);
                    taskList += ((i+1) + ". " + curr.toString() + "\n");
                }
                System.out.println(gap + "Here are your tasks:\n" + taskList + gap);
            } else if (input.toLowerCase().contains("mark")) {
                StringTokenizer st = new StringTokenizer(input);
                String command = st.nextToken().toLowerCase();
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
            } else {
                StringTokenizer st = new StringTokenizer(input);
                String taskType = st.nextToken().toLowerCase();
                switch (taskType) {
                case "todo":
                    String todoDesc = "";
                    while(st.hasMoreTokens()) {
                        todoDesc += st.nextToken() + " ";
                    }
                    ToDo newToDo = new ToDo(todoDesc.trim());
                    tasks.add(newToDo);
                    System.out.println(gap + "Confirmed. New task added:\n"
                                      + newToDo.toString() + "\n");
                    break;
                case "deadline":
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
                    break;
                case "event":
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
                    break;
                default:
                    System.out.println("なに？！");
                    break;
                }
                System.out.println("Total task count: " + tasks.size() + ".\n" + gap);
            }
        }
        System.out.println(gap + "Goodbye! Have a pleasant day!\n" + gap);
    }
}
