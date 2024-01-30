import java.util.ArrayList;
import java.util.*;

public class ToDoList implements Iterable<Task>{
    private final ArrayList<Task>  tasks = new ArrayList<>();

    public void addToList(Task t){
        tasks.add(t);
    }

    public void addToList(String s) throws IllegalArgumentException{
        //handling empty task
        if (s.trim().isEmpty()) {
            throw new IllegalArgumentException("Task cannot be empty. Please enter a valid task.");
        }
        //regex to split type of task and possible dates for non-todo task
        String[] parts = s.trim().split("\\s+", 2);
        String taskType = parts[0].toLowerCase();
        String taskDescription = parts.length > 1 ? parts[1].trim() : "";

        switch (taskType) {
            case "todo":
                tasks.add(new ToDoTask(taskDescription));
                System.out.println("HASSNT:\n"+"Got it. I've added this task:  " + new ToDoTask(taskDescription));
                break;
            case "deadline":
                //splitting event details from date details (using by)
                String[] deadlineParts = taskDescription.split("\\s+by\\s+", 2);
                //handling case that does not include by
                if (deadlineParts.length != 2) {
                    throw new IllegalArgumentException("Invalid deadline format. Please specify the task following by its deadline using 'by' ");
                }
                tasks.add(new DeadLineTask(deadlineParts[1], deadlineParts[0]));
                System.out.println("HASSNT:\n" + "Got it. I've added this task:  " + new DeadLineTask(deadlineParts[1], deadlineParts[0]));
                break;
            case "event":
                //splitting event details from date details using whitespace between words and time
                String[] eventParts = taskDescription.split("\\s+(?=\\S+(-|\\sto\\s))", 2);

                //handling case that does not include x to y or x-y
                if (eventParts.length != 2) {
                    throw new IllegalArgumentException("Invalid event format. Please specify the task following by its event time using \"time1 to time2 or time1-time2\"");
                }
                String[] eventTimingParts = eventParts[1].split("(-|\\s+to\\s+)", 2);
//                if (eventTimingParts.length != 2) {
//                    System.out.println("Invalid event format. Please specify the event using to");
//                    return;
//                }
                tasks.add(new EventTask(eventTimingParts[0], eventTimingParts[1], eventParts[0]));
                System.out.println("HASSNT:\n"+"Got it. I've added this task:  " + new EventTask(eventTimingParts[0], eventTimingParts[1], eventParts[0]));
                break;
            default:
                throw new IllegalArgumentException("Invalid input. To add a task to the list, please type 'todo', 'deadline', or 'event' follow by your task requirement to add task to your list. ");
        }
        System.out.println("\nNow you have " + tasks.size() + " tasks in the list.");
    }

    public void showLists(){
        if (tasks.size() == 0){
            System.out.println( "HASSNT:\n" + "NO TASKS IN TO DO LIST 🎉");

        } else{
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append('.').append("\t").append(tasks.get(i)).append("\n");
        }
            System.out.println("HASSNT:\n" + "Here are the tasks in your list:\n"+ sb);

        }
    }
    public void removeTask(int index) throws IllegalArgumentException{
        if (!isValidIndex(index)) {
            throw new IllegalArgumentException("Please input valid number. to see the available number(s) of your task type list");
        } else {
            String t = tasks.get(index - 1).toString();
            tasks.remove(index - 1);
            System.out.println("HASSNT:\n" + "Noted. I've removed this task: " + t);
        }
    }
    public void showMark(int taskNumber) throws IllegalArgumentException{
        //handling invalid index of taskNumber
        if (!isValidIndex(taskNumber)) {
            throw new IllegalArgumentException("Please input valid number. to see the available number(s) of your task type list");
        } else {
            Task t = tasks.get(taskNumber - 1);
            t.markAsDone();
            System.out.println("HASSNT:\n" + "Nice! I've marked this task as done: " + t);
        }
    }
    public void showUnmark(int taskNumber) throws IllegalArgumentException{
        //handling invalid index of taskNumber
        if (!isValidIndex(taskNumber)) {
            throw new IllegalArgumentException("Please input valid number. to see the available number(s) for your task type list");
        } else {
            Task t = tasks.get(taskNumber - 1);
            t.markAsNotDone();
            System.out.println("HASSNT:\n" +  "OK, I've marked this task as not done yet:\n" + t);
        }
    }
    private boolean isValidIndex(int index) {
        return index >= 1 && index <= tasks.size();
    }
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }


}
