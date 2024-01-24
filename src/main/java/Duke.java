import java.io.*;
import java.util.*;

public class Duke {
    public static void main(String[] args) throws IOException {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Yappy\n    What can I do for you?");
        System.out.println("    ____________________________________________________________");

        while(true) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String input = br.readLine();

            if(input.equals("bye")) {
                break;
            }

            System.out.println("    ____________________________________________________________");
            System.out.println("    " + input);
            System.out.println("    ____________________________________________________________");
        }

        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
