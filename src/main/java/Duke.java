import java.util.Scanner;
import java.io.PrintWriter;

class IO {
    private Scanner sc;
    private String hLine = "________________________________________________";
    private String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private String greetMsg = "Hello! I'm Hatsune Miku!\n"
            + " What can I do for you?";
    private String exitMsg = "Bye. Hope to see you again soon!";

    public IO() {
        sc = new Scanner(System.in);
    }

    public void greet() {
        System.out.println("Hello from\n" + logo);
        System.out.println(hLine);
        System.out.println(greetMsg);
        System.out.println(hLine);
    }

    public void read() {
        String input = sc.nextLine();
        if (input.equals("bye")) {
            echo(exitMsg);
            System.exit(0);
        } else {
            echo(input);
        }
        read();
    }

    private void echo(String msg) {
        System.out.println(msg);
        System.out.println(hLine);
    }
}
public class Duke {
    public static void main(String[] args) throws Exception {
        IO io = new IO();

        io.greet();
        io.read();
    }
}
