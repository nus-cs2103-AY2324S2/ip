import java.util.Scanner;
import java.util.ArrayList;

public class TalkingBox {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String NAME = "Talking Box";
        ArrayList<Task> taskList = new ArrayList<>();

        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_s|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */

        System.out.println("Hello, I am the " + NAME);
        System.out.println("What tasks do you have?");

        String taskType = in.next();
        String input = in.nextLine().trim();
        while (!(taskType.equals("bye"))) {
            switch(taskType) {
                case "list":
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println((i + 1) + ": " + taskList.get(i).toString());
                    }
                    break;
                case "mark":
                    int entry = Integer.parseInt(input) - 1;
                    System.out.println("Task marked as done. Good job!");
                    taskList.get(entry).mark();
                    System.out.println(taskList.get(entry).toString());
                    break;
                case "unmark":
                    int e = Integer.parseInt(input) - 1;
                    System.out.println("Alright! Task marked as not done");
                    taskList.get(e).unmark();
                    System.out.println(taskList.get(e).toString());
                    break;
                case "todo":
                    taskList.add(new toDo(input));
                    System.out.println("added task: ");
                    System.out.println(taskList.getLast().toString());
                    System.out.println("current number of tasks: " + taskList.size());
                    break;
                case "deadline":
                    String[] d = input.split("/");
                    taskList.add(new deadline(d[0], d[1]));
                    System.out.println("added task: ");
                    System.out.println(taskList.getLast().toString());
                    System.out.println("current number of tasks: " + taskList.size());
                    break;
                case "event":
                    String[] v = input.split("/");
                    taskList.add(new event(v[0], v[1], v[2]));
                    System.out.println("added task: ");
                    System.out.println(taskList.getLast().toString());
                    System.out.println("current number of tasks: " + taskList.size());
            }
            taskType = in.next();
            input = in.nextLine().trim();
        }

        System.out.println("Goodbye!");

    }
}

class Task {
    String name;
    String type;
    boolean complete;
    public Task(String name, String type) {
        this.name = name;
        this.type = type;
        this.complete = false;
    }
    String getType() {
        return this.type;
    }
    String getName() {
        return this.name;
    }
    boolean status() {
        return this.complete;
    }
    void mark() {
        this.complete = true;
    }
    void unmark() {
        this.complete = false;
    }
}

class toDo extends Task {
    public toDo(String name) {
        super(name, "T");
    }
    public String toString() {
        String status = this.complete ? "[x] " : "[ ]";
        return "[T] " + status + " " + this.name;
    }
}

class deadline extends Task {
    String dueDate;
    public deadline(String name, String dueDate) {
        super(name, "D");
        this.dueDate = dueDate;
    }
    public String toString() {
        String status = this.complete ? "[x] " : "[ ]";
        return "[D] " + status + " " + this.name + " (" + this.dueDate + ")";
    }
}

class event extends Task {
    String startTime;
    String endTime;
    public event(String name, String startTime, String endTime) {
        super(name, "E");
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public String toString() {
        String status = this.complete ? "[x] " : "[ ]";
        return "[E] " + status + " " + this.name + " (" + this.startTime + this.endTime + ")";
    }
}

