import java.util.*;

class Task {
    protected String description;
    protected String type;
    protected boolean isDone;

    public Task(String type, String description) {
        this.type = type;
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    public void numberOfTasks(ArrayList<Task> arr) {
        System.out.println(
                arr.size() == 1 ? "Now you have 1 task in the list." :
                        "Now you have " + arr.size() + " tasks in the list.");
    }

    public String markStatus() {
        isDone = true;
        return "------------------------------- \n" +
                "Nice! I've marked this as done: \n " + "[" + this.type + "][" + getStatusIcon() + "] " + description +
                "\n-------------------------------";
    }

    public String unmarkStatus() {
        isDone = false;
        return "------------------------------- \n" +
                "OK, I've marked this task as not done yet: \n " + "[" + this.type + "][" + getStatusIcon() + "] " + description +
                "\n-------------------------------";
    }

    public static void getList(ArrayList<Task> arr) {
        for (int i = 0; i < arr.size(); i++) {
            Task task = arr.get(i);
            String taskDetails = (i + 1) + ".[" + task.type + "][" + (task != null ? task.getStatusIcon() : "") + "] " + task.description;
            if (task instanceof Deadline) {
                Deadline deadlineTask = (Deadline) task;
                taskDetails += " (by: " + deadlineTask.by + ")";
            } else if (task instanceof Event) {
                Event eventTask = (Event) task;
                taskDetails += " (from: " + eventTask.from + " to: " + eventTask.to + ")";
            }
            System.out.println(taskDetails);
        }
    }

}

class ToDo extends Task {
    private ArrayList<Task> arr = new ArrayList<>();
    public ToDo(String type, String description) {
        super(type, description);
    }

    @Override
    public String toString() {
        arr.add(this);
        return "Got it. I've added this task: \n [T][" + getStatusIcon() + "] " + getDescription();
    }
}

class Deadline extends Task {
    protected String by;

    public Deadline(String type,String description, String by) {
        super(type, description);
        int index = description.indexOf("/");
        if (index != -1) {
            this.by = description.substring(index + 3).trim();
            this.description = description.substring(0, index).trim();
        } else {
            this.by = "";
        }
    }
    @Override
    public String toString() {
        return "Got it. I've added this task: \n [D][" + getStatusIcon() + "] " + getDescription() +  " (by: " + by + ")";
    }
}

class Event extends Task {
    protected String from;
    protected String to;

    public Event(String type, String description, String from, String to) {
        super(type, description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "Got it. I've added this task:\n [E][" + getStatusIcon() + "] " + getDescription()
                + (from.isEmpty() ? "" : " (from: " + from ) + (to.isEmpty() ? "" : " to: " + to + ")");
    }
}


public class Duke {

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scan = new Scanner(System.in);

        System.out.println("-------------------------------");
        System.out.println("Hello! I'm Tango. \nWhat can I do for you today?");
        System.out.println("-------------------------------");

        while (true) {
            String input = scan.nextLine().trim();
            String[] words = input.split("\\s+");

            if (words[0].equalsIgnoreCase("bye")) {
                System.out.println("-------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("-------------------------------");
                break;
            } else if (words[0].equalsIgnoreCase("list")) {
                System.out.println("-------------------------------");
                System.out.println(tasks.size() == 0 ? "You have no tasks in your list!" :
                        tasks.size() == 1 ? "Here is the task in your list:" :
                        "Here are the tasks in your list:");
                Task.getList(tasks);
                System.out.println("-------------------------------");
            } else if (words[0].equalsIgnoreCase("mark") && words.length > 1) {
                int index = Integer.parseInt(words[1]) - 1;
                if (index >= 0 && index < tasks.size()) {
                    Task task = tasks.get(index);
                    System.out.println(task.markStatus());
                } else {
                    System.out.println("-------------------------------");
                    System.out.println("You have not created a task " + index + " yet");
                    System.out.println("-------------------------------");
                }
            } else if (words[0].equalsIgnoreCase("unmark") && words.length > 1) {
                int index = Integer.parseInt(words[1]) - 1;
                if (index >= 0 && index < tasks.size()) {
                    Task task = tasks.get(index);
                    System.out.println(task.unmarkStatus());
                } else {
                    System.out.println("-------------------------------");
                    System.out.println("You have not created a task " + index + " yet");
                    System.out.println("-------------------------------");
                }
            } else if (words[0].equalsIgnoreCase("todo") && words.length > 1) {
                System.out.println("-------------------------------");
                Task task = new ToDo("T", input.substring(5).trim());
                tasks.add(task);
                System.out.println(task.toString());
                task.numberOfTasks(tasks);
                System.out.println("-------------------------------");

            } else if (words[0].equalsIgnoreCase("deadline") && words.length > 1) {
                System.out.println("-------------------------------");
                Task task = new Deadline("D", input.substring(9).trim(), words[2]);
                tasks.add(task);
                System.out.println(task.toString());
                task.numberOfTasks(tasks);
                System.out.println("-------------------------------");

            }
            else if (words[0].equalsIgnoreCase("event") && words.length > 1) {
                System.out.println("-------------------------------");
                int fromIndex = input.indexOf("/from");
                int toIndex = input.indexOf("/to");

                String from = "";
                String to = "";

                if (fromIndex != -1 && toIndex != -1 && fromIndex < toIndex) {
                    from = input.substring(fromIndex + 5, toIndex).trim();
                    to = input.substring(toIndex + 3).trim();
                    input = input.substring(0, fromIndex).trim();
                }

                Task task = new Event("E", input.substring(6).trim(), from, to);
                tasks.add(task);
                System.out.println(task.toString());
                task.numberOfTasks(tasks);
                System.out.println("-------------------------------");
            }
            else {
                System.out.println("-------------------------------");
                System.out.println("added: " + input);
                tasks.add(new Task("null", input));
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("-------------------------------");
            }
        }
    }
}
