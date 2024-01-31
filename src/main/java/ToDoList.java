import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToDoList implements Iterable<Task> {
    private final ArrayList<Task> tasks = new ArrayList<>();

    public void addToList(Task t) {
        tasks.add(t);
    }

    public void addToList(String s) throws IllegalArgumentException {
        //handling empty task
        if (s.trim().isEmpty()) {
            throw new IllegalArgumentException("Task cannot be empty. Please enter a valid task.");
        }
        //regex to split type of task and possible dates for non-todo task
        String[] parts = s.trim().split("\\s+", 2);
        System.out.println(Arrays.toString(parts));
        String taskType = parts[0].toLowerCase();
        String taskDescription = parts.length > 1 ? parts[1].trim() : "";
        if (taskDescription.equals("")) {
            throw new IllegalArgumentException("Please type in event or deadline or todo followed by your task description to add a task to the list");
        }

        switch (taskType) {
            case "todo":
                tasks.add(new ToDoTask(taskDescription));
                System.out.println("HASSNT:\n" + "Got it. I've added this task:  " + new ToDoTask(taskDescription));
                break;
            case "deadline":
                //splitting event details from date details (using by)
                String[] deadlineParts = taskDescription.split("\\s+by\\s+", 2);
                String pattern1 = "\\d{2}-\\d{2}";
                String pattern2 = "\\d{2}-\\d{2} \\d{2}:\\d{2} [ap]m";
                System.out.println(Arrays.toString(deadlineParts));
                //handling case that does not include by
                if (deadlineParts.length < 2) {
                    throw new IllegalArgumentException("Invalid deadline format. Please specify the task following by its deadline using 'by mm-dd or mm-dd hh:mm am/pm' eg. task1 by 01-14 01:00 pm  or just task1 by 01-14");
                }
                if (!deadlineParts[1].trim().matches(pattern1) && !deadlineParts[1].trim().matches(pattern2)) {
                    System.out.println(deadlineParts[1].trim().matches(pattern1));
                    System.out.println(deadlineParts[1].trim().matches(pattern2));
                    throw new IllegalArgumentException("Invalid deadline format. Please specify the task following by its deadline using 'by mm-dd or mm-dd hh:mm am/pm' eg. task1 by 01-14 01:00 pm  or task1 by 01-14");
                }
                String time = deadlineParts[1];
                String taskName = deadlineParts[0];
                tasks.add(new DeadLineTask(time, taskName));
                System.out.println("HASSNT:\n" + "Got it. I've added this task:  " + new DeadLineTask(deadlineParts[1], deadlineParts[0]));
                break;
            case "event":
                //splitting event details from date details using whitespace between words and time
                String eventPattern1 = "\\d{2}:\\d{2} [ap]m to \\d{2}:\\d{2} [ap]m";
                String eventPattern2 = "\\d{2}-\\d{2} \\d{2}:\\d{2} [ap]m to \\d{2}:\\d{2} [ap]m";
                int posPattern1 = setEventTimingPos(taskDescription, eventPattern1);
                int posPattern2 = setEventTimingPos(taskDescription, eventPattern2);
                String taskN;
                String eventDetail;
                if (posPattern2 != -1) {
                    taskN = taskDescription.substring(0, posPattern2);
                    eventDetail = taskDescription.substring(posPattern2);
                }
                else if (posPattern1 != -1) {
                    taskN = taskDescription.substring(0, posPattern1);
                    eventDetail = taskDescription.substring(posPattern1);
                } else {
                    throw new IllegalArgumentException("Invalid event format. Please specify the event using event followed by task name and its time in the following format <hh:mm am/pm to hh:mm am/pm or MM-dd hh:mm am/pm to hh:mm am/pm> eg1. event task1 10:00 am to 12:00 am  eg.2 event task1 01-01 10:00 am to 12:00 pm");
                }
                String[] timingDetails = eventDetail.split("to");
                EventTask t = new EventTask(timingDetails[0], timingDetails[1], taskN);
                tasks.add(t);
                System.out.println("HASSNT:\n" + "Got it. I've added this task:  " + t);
                break;
            default:
                throw new IllegalArgumentException("Invalid input. To add a task to the list, please type 'todo', 'deadline', or 'event' follow by your task requirement to add task to your list. ");
        }
        System.out.println("\nNow you have " + tasks.size() + " tasks in the list.");
    }

    public void showLists() {
        if (tasks.size() == 0) {
            System.out.println("HASSNT:\n" + "NO TASKS IN TO DO LIST 🎉");

        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                sb.append(i + 1).append('.').append("\t").append(tasks.get(i)).append("\n");
            }
            System.out.println("HASSNT:\n" + "Here are the tasks in your list:\n" + sb);

        }
    }

    public void removeTask(int index) throws IllegalArgumentException {
        if (!isValidIndex(index)) {
            throw new IllegalArgumentException("Please input valid number. to see the available number(s) of your task type list");
        } else {
            String t = tasks.get(index - 1).toString();
            tasks.remove(index - 1);
            System.out.println("HASSNT:\n" + "Noted. I've removed this task: " + t);
        }
    }

    public void showMark(int taskNumber) throws IllegalArgumentException {
        //handling invalid index of taskNumber
        if (!isValidIndex(taskNumber)) {
            throw new IllegalArgumentException("Please input valid number. to see the available number(s) of your task type list");
        } else {
            Task t = tasks.get(taskNumber - 1);
            t.markAsDone();
            System.out.println("HASSNT:\n" + "Nice! I've marked this task as done: " + t);
        }
    }

    public void showUnmark(int taskNumber) throws IllegalArgumentException {
        //handling invalid index of taskNumber
        if (!isValidIndex(taskNumber)) {
            throw new IllegalArgumentException("Please input valid number. to see the available number(s) for your task type list");
        } else {
            Task t = tasks.get(taskNumber - 1);
            t.markAsNotDone();
            System.out.println("HASSNT:\n" + "OK, I've marked this task as not done yet:\n" + t);
        }
    }

    private boolean isValidIndex(int index) {
        return index >= 1 && index <= tasks.size();
    }

    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

    private int setEventTimingPos(String input, String regex) {
        int match;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            match = matcher.start();
        } else {
            match = -1;
        }
        return match;
    }

}









