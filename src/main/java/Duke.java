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
        String[] details = name.split("/");
        Task t = null;
        if (details.length == 1) {
            t = new ToDo(details[0].substring(5));
        } else if (details.length == 2) {
            t = new Deadline(details[0].substring(9), this.processDeadline(details[1]));
        } else {
            t = new Event(details[0].substring(6), this.processEvent(details[1] +
                    "/" + details[2]));
        }
        this.tasks[counter] = t;
        this.counter += 1;

//        System.out.println("added: " + name);
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
//                System.out.println("correct");
                this.listTask();
            } else if (cmd.contains("unmark")) {
                    String[] lst = cmd.split(" ");
                    int pos = Integer.parseInt(lst[1]);
                    this.tasks[pos].undoTask();
            } else if (cmd.contains("mark")) {
                String[] lst = cmd.split(" ");
                int pos = Integer.parseInt(lst[1]);
                this.tasks[pos].doTask();

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




