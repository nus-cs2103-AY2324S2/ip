import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private Task[] tasks;
    private int counter;



    public Duke() {
        this.tasks = new Task[100];
        this.counter = 0;
    }
    public static void main(String[] args) {
        Duke d = new Duke();

//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        d.runBot();
    }

    private void greet() {
        System.out.println("Hello, I'm Baymax " + "\n" + "What can I do for you?");
    }

    private void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private void echo(String task) {
        System.out.println("added: " + task);
    }

    private void addTask(String name) {
        String[] details = name.split(" ");
        String type = details[0];
        if (!check(type)) {
            System.out.println("please enter a valid task type");
            return;
        } else {
            switch(type) {
                case "todo" :
                    addToDo(name);
                    break;
                case "deadline":
                    addDeadline(name);
                    break;
                default:
                    addEvent(name);
                    break;
            };

        }
//        Task t = null;
//        if (details.length == 1) {
//            String task_name = details[0].substring(4);
//            if (task_name.length() == 0) {
//                System.out.println("todo task cannot be blank");
//                return;
//            }
//            t = new ToDo(task_name);
//        } else if (details.length == 2) {
//            t = new Deadline(details[0].substring(8), this.processDeadline(details[1]));
//        } else {
//            t = new Event(details[0].substring(5), this.processEvent(details[1] +
//                    "/" + details[2]));
//        }
//        this.tasks[counter] = t;
        //this.counter += 1;


    }

    private boolean checkBlankString(String s) {
        return s.trim().isEmpty();
    }

    private void addToDo(String name) {
        String[] lst = name.split("todo");
        if (lst.length == 0 || checkBlankString(lst[1])) {
            System.out.println("Don't leave the task description blank");
        } else {
            Task t = new ToDo(lst[1]);
            this.tasks[this.counter] = t;
            this.counter += 1;
        }

    }



    private void addDeadline(String name) {
        String[] lst = name.split("deadline");
        if (lst.length == 0 || checkBlankString(lst[1])) {
            System.out.println("Don't leave the task description blank");
        } else if (!name.contains("/")) {
            System.out.println("Please leave a \" / \" for the due date");
        } else {
            lst = lst[1].split("/");
            name = lst[0];
            String date = lst[1];
            if (checkBlankString(name) || checkBlankString(date)) {
                System.out.println("Please fill in both the name and due date");
                return;
            }
            Task t = new Deadline(name, date);
            this.tasks[this.counter] = t;
            this.counter += 1;
        }

    }

    public void addEvent(String name) {
        String[] lst = name.split("event");
        if (lst.length == 0 || checkBlankString(lst[1])) {
            System.out.println("Don't leave the task description blank");
        } else if (!name.contains("/")) {
            System.out.println("Please leave a \" / \" for the due date");
        } else {
            lst = lst[1].split("/");
            if (lst.length != 3) {
                System.out.println("Please enter the correct format for event");
                return;
            }
            name = lst[0];
            String start = lst[1];
            String end = lst[2];
            if (checkBlankString(name) || checkBlankString(start) || checkBlankString(end)) {
                System.out.println("Please fill in both the name and due date");
                return;
            }
            Task t = new Event(name, start + " " + end);
            this.tasks[this.counter] = t;
            this.counter += 1;
        }

    }

    private void listTask() {
        for (int i = 0; i < counter; i++) {
            System.out.println(i + 1 + "." + this.tasks[i]);
        }
        return;
    }

    public void mark(int i) {
        this.tasks[i].doTask();
    }

    private void unmark(int i) {
        this.tasks[i].undoTask();
    }

    private String processDeadline(String date) {
        return date.substring(3);
    }

    private String processEvent(String date) {
        String[] lst = date.split("/");
        //System.out.println(Arrays.toString(lst));
        String start = lst[0].split(" ")[1];
        //System.out.println(start);
        String end = lst[1].split(" ")[1];
        //System.out.println("from: " + start + " to: " + end);
        return "from: " + start + " to: " + end;

    }

    private boolean check(String cmd) {
        String[] cmds = {"todo", "event", "deadline"};
        return Arrays.stream(cmds).anyMatch(cmd::equals);
    }

    public void runBot() {
        Scanner s = new Scanner(System.in);
        this.greet();
        boolean active = true;

        while (active) {
            String cmd = s.nextLine();
            if (cmd.equals("bye")) {
                active = false;
                this.bye();
            } else if (cmd.equals("list")) {

                this.listTask();
            } else if (cmd.contains("unmark")) {
                    String[] lst = cmd.split(" ");
                    int pos = Integer.parseInt(lst[1]);
                    this.tasks[pos - 1].undoTask();
            } else if (cmd.contains("mark")) {
                String[] lst = cmd.split(" ");
                int pos = Integer.parseInt(lst[1]);
                this.tasks[pos - 1].doTask();
            } else {
//                System.out.println("wrong" + cmd.equals("list"));
//                System.out.println(cmd);
                this.addTask(cmd);
            }




        }


    }



}


abstract class Task {

    private String name;

    private boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    @Override
    public String toString() {
        if (done) {
            return "[X]" + this.name;
        } else {
            return "[ ]" + this.name;
        }

    }

    public void doTask() {
        if (!done) {
            this.done = true;
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(this.toString());
        } else {
            System.out.println("This task is already done");
        }

    }

    public void undoTask() {
        if (done) {
            this.done = false;
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(this.toString());
        } else {
            System.out.println("This task has not been done");

        }

    }
}

class ToDo extends Task{

    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}


class Deadline extends Task{

    private String deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.deadline + ")";
    }
}

class Event extends Task{

    private String time;

    public Event(String name, String time) {
        super(name);
        this.time = time;
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + this.time;
    }
}




