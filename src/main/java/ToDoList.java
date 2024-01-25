import java.util.ArrayList;
import java.util.Arrays;

public class ToDoList {
    private final ArrayList<Task>  tasks = new ArrayList<>();

    public void addToList(String s){
        if (s.trim().isEmpty()) {
            System.out.println("Task cannot be empty. Please enter a valid task.");
        }
        //regex thing
        String[] parts = s.trim().split("\\s+", 2);
        String taskType = parts[0].toLowerCase();
        String taskDescription = parts.length > 1 ? parts[1].trim() : "";

        switch (taskType) {
            case "todo":
                tasks.add(new ToDoTask(taskDescription));
                System.out.println("____________________________________________________________\n"+"Got it. I've added this task:\n  " + new ToDoTask(taskDescription));
                break;
            case "deadline":
                String[] deadlineParts = taskDescription.split("\\s+by\\s+", 2);
                if (deadlineParts.length != 2) {
                    System.out.println("Invalid deadline format. Please specify the deadline using 'by'.");
                    return;
                }
                tasks.add(new DeadLineTask(deadlineParts[1], deadlineParts[0]));
                System.out.println("____________________________________________________________\n" + "Got it. I've added this task:\n  " + new DeadLineTask(deadlineParts[1], deadlineParts[0]));
                break;
            case "event":
                String[] eventParts = taskDescription.split("\\s+(?=\\S+(-|\\sto\\s))", 2);
                System.out.println("event description GROUPing " +  Arrays.toString(eventParts));
                if (eventParts.length != 2) {
                    System.out.println("Invalid event format. Please specify the event using follow by <Date (optional)> <time1> to <time2>");
                    return;
                }
                String[] eventTimingParts = eventParts[1].split("(-|\\s+to\\s+)", 2);
                if (eventTimingParts.length != 2) {
                    System.out.println("Invalid event format. Please specify the event ending using from to");
                    return;
                }
                tasks.add(new EventTask(eventTimingParts[0], eventTimingParts[1], eventParts[0]));
                System.out.println("____________________________________________________________\n"+"Got it. I've added this task:\n  " + new EventTask(eventTimingParts[0], eventTimingParts[1], eventParts[0]) +"\n" );
                break;
            default:
                System.out.println("Invalid input. Please type 'todo', 'deadline', or 'event' follow by your task requirement.");
        }
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public void showLists(){
        if (tasks.size() == 0){
            System.out.println( "____________________________________________________________\n"
                    + "NOTHING IN TO DO LIST 🎉\n"
                    +"____________________________________________________________");

        } else{
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append('.').append("\t").append(tasks.get(i)).append("\n");
        }
            System.out.println("____________________________________________________________\n" + "Here are the tasks in your list:\n"+ sb + "____________________________________________________________");

        }
    }
    public void showMark(int taskNumber) {
        if (!isValidIndex(taskNumber)) {
            System.out.println("Please input valid number  \n to see the available number(s) of your task type list");
        } else {
            Task t = tasks.get(taskNumber - 1);
            t.markAsDone();
            System.out.println("____________________________________________________________\n" +
                    "Nice! I've marked this task as done:\n" +
                    t + "\n" +
                    "____________________________________________________________");
        }
    }
    public void showUnmark(int taskNumber){
        if (!isValidIndex(taskNumber)) {
            System.out.println("Please input valid number  \nto see the available number(s) of your task type list");
        } else {
            Task t = tasks.get(taskNumber - 1);
            t.markAsNotDone();
            System.out.println("____________________________________________________________\n" +
                    "OK, I've marked this task as not done yet:\n" +
                    t + "\n" +
                    "____________________________________________________________");
        }
    }
    private boolean isValidIndex(int index) {
        return index >= 1 && index <= tasks.size();
    }




}
