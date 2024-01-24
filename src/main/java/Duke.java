import java.util.Scanner;

public class Duke {
    private String[] tasks;
    private int counter;



    public Duke() {
        this.tasks = new String[100];
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

    public void addTask(String task) {
        this.tasks[counter] = task;
        this.counter += 1;
        System.out.println("added: " + task);
    }

    public void listTask() {
        for (int i = 0; i < counter; i++) {
            System.out.println(i + 1 + ". " + this.tasks[i]);
        }
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
            } else {
                this.addTask(cmd);
            }




        }


    }



}

