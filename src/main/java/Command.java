import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
                    int taskIndex = getTaskIndex(detail);
                    if (taskIndex >= listOfTasks.size() || taskIndex < 0){
                        throw new ToothlessException("Trying to mark nothing :(");
                    }
                    Task t = listOfTasks.get(taskIndex);
                    t.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    printTaskState(t, taskIndex);
                    break;
                }
                case UnMark:{
                    int taskIndex = getTaskIndex(detail);
                    if (taskIndex >= listOfTasks.size() || taskIndex < 0){
                        throw new ToothlessException("Trying to unmark nothing :(");
                    }
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
                    throw new ToothlessException("Me dragon, no understand this action :P");
            }
        } catch (ToothlessException e){
            System.out.println(e.getMessage());
        } finally {
            System.out.println(splitLine);
            return isDone;
        }
    }

    public static final Task createTask(Command c, String detail) throws ToothlessException{
        if(detail.equals("")){
            throw new ToothlessException("Human task no name :(");
        }
        Task newTask;
        switch (c){
            case Todo:{
                newTask = new Todo(detail);
                break;
            }
            case Deadline:{
                int dateIndex = detail.indexOf("/by");
                if (dateIndex == -1){
                    throw new ToothlessException("Human deadline no deadline @_@");
                }
                String description = detail.substring(0, dateIndex - 1);
                String date = detail.substring(dateIndex + 4);
                newTask = new Deadline(description, date);
                break;
            }
            default: {
                int date1Index = detail.indexOf("/from");
                if (date1Index == -1){
                    throw new ToothlessException("Human event no start date D:");
                }
                String description = detail.substring(0, date1Index - 1);
                detail = detail.substring(date1Index + 6);
                int date2Index = detail.indexOf("/to");
                if (date2Index == -1){
                    throw new ToothlessException("Human event no end date D:");
                }
                String startDate = detail.substring(0, date2Index - 1);
                String endDate = detail.substring(date2Index + 4);
                newTask = new Event(description, startDate, endDate);
            }
        }
        return newTask;
    }

    public static int getTaskIndex(String detail){
        Scanner s = new Scanner(detail).useDelimiter("\\s*");
        int taskIndex = s.nextInt();
        return taskIndex - 1;
    }

    public static void printTaskState(Task task, int index){
        System.out.format("%d. ["+ task.getTaskIcon()+"]["+ task.getStatusIcon() + "] " + task + "\n", index + 1);
    }
}
