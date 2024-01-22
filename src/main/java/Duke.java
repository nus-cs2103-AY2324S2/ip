import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.exit;

public class Duke {
    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String name = "Bingus";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm \n" + name);
        System.out.println("What can I do for you?\n");


        BufferedReader bfn = new BufferedReader(
                new InputStreamReader(System.in));
        String str = bfn.readLine();
        while (true) {
            System.out.println(str);
            str = bfn.readLine();
            if (str.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n\n");
                System.exit(1);

            }
        }
    }
}
