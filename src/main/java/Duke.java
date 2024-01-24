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

    public void greet() {
        System.out.println("Hello, I'm Baymax " + "\n" + "What can I do for you?");
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void echo(String task) {
        System.out.println("added: " + task);
    }

    public void addTask(String name) {
        this.tasks[counter] = new Task(name);
        this.counter += 1;
        System.out.println("added: " + name);
    }

    public void listTask() {
        for (int i = 0; i < counter; i++) {
            System.out.println(i + 1 + ". " + this.tasks[i]);
        }
        return;
    }

    public void mark(int i) {
        this.tasks[i].doTask();
    }

    public void unmark(int i) {
        this.tasks[i].undoTask();
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


class Task {

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

