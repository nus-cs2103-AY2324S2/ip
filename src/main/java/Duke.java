import java.util.*;
import java.io.*;

public class Duke {
    public static void main(String[] args) throws Exception {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Yappy\n    What can I do for you?");
        System.out.println("    ____________________________________________________________");

        ArrayList<String> AL = new ArrayList<>();

        while(true) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = br.readLine();

            if(input.equals("bye")) {
                break;
            } else if(input.equals("list")) {
                System.out.println("    ____________________________________________________________");
                for(int i = 1; i <= AL.size(); i++) {
                    System.out.println("    " + i + ". " + AL.get(i-1));
                }
                System.out.println("    ____________________________________________________________");
            } else {
                AL.add(input);
                System.out.println("    ____________________________________________________________");
                System.out.println("    added: " + input);
                System.out.println("    ____________________________________________________________");
            }
        }

        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
