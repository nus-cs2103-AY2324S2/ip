import java.util.ArrayList;
import java.util.List;

public enum Command {
    List, Mark, UnMark, Todo, Deadline, Event, Bye, Invalid;

    private static List<Task> listOfTasks = new ArrayList<>();
    private static String splitLine = "____________________________________________________________";
    private static String exitString = "Bye. Hope to see you again soon!";

    public static final boolean handleCommand(Command command, String detail){
        boolean isDone = false;
        try {
            switch (command){
                case List:
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < listOfTasks.size(); i++) {
                        Task t = listOfTasks.get(i);
                        printTaskState(t, i);
                    }
                    break;
                case Mark:{
                    int taskIndex = detail.charAt(0) - (int)'0' - 1;
                    Task t = listOfTasks.get(taskIndex);
                    t.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    printTaskState(t, taskIndex);
                    break;
                }
                case UnMark:{
                    int taskIndex = detail.charAt(0) - (int)'0' - 1;
                    Task t = listOfTasks.get(taskIndex);
                    t.markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    printTaskState(t, taskIndex);
                    break;
                }
                case Todo:
                case Deadline:
                case Event:{
                    Task t = createTask(command, detail);
                    listOfTasks.add(t);
                    System.out.println("Got it. I've added this task:");
                    printTaskState(t, listOfTasks.size() - 1);
                    System.out.format("Now you have %d tasks in the list.\n", listOfTasks.size());
                    break;
                }
                case Bye:
                    isDone = true;
                    System.out.println(exitString);
                    break;
                case Invalid:
                    break;
            }
        } finally {
            System.out.println(splitLine);
            return isDone;
        }
    }

    public static final Task createTask(Command c, String detail){
        Task newTask;
        switch (c){
            case Todo:{
                newTask = new Todo(detail);
                break;
            }
            case Deadline:{
                int dateIndex = detail.indexOf("/by");
                String description = detail.substring(0, dateIndex - 1);
                String date = detail.substring(dateIndex + 4);
                newTask = new Deadline(description, date);
                break;
            }
            default: {
                int date1Index = detail.indexOf("/from");
                String description = detail.substring(0, date1Index - 1);
                detail = detail.substring(date1Index + 6);
                int date2Index = detail.indexOf("/to");
                String startDate = detail.substring(0, date2Index - 1);
                String endDate = detail.substring(date2Index + 4);
                newTask = new Event(description, startDate, endDate);
            }
        }
        return newTask;
    }

    public static void printTaskState(Task task, int index){
        System.out.format("%d. ["+ task.getTaskIcon()+"]["+ task.getStatusIcon() + "] " + task + "\n", index + 1);
    }
}
