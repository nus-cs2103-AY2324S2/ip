import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Duke d = new Duke();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        d.echo();
    }

    public String greet() {
        return "Hello, I'm Baymax " + "\n" + "What can I do for you?";
    }

    public String bye() {
        return "Bye. Hope to see you again soon!";
    }

    public void echo() {
        Scanner s = new Scanner(System.in);
        this.greet();
        while (true) {
            String cmd = s.nextLine();
            if (cmdb.equals("bye")) {
                System.out.println(this.bye());
                break;
            } else {
                System.out.println(cmd);
            }
        }
    }

}

