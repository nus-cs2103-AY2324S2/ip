import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm AcademicWeapon");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        String input = br.readLine();
        ArrayList<String> lst = new ArrayList<>();
        while (!input.equals("bye")) {
            switch (input) {
                case "list":
                    System.out.println("____________________________________________________________");
                    for (int i = 0; i < lst.size(); i++) {
                        System.out.println(i + 1 + ". " + lst.get(i));
                    }
                    System.out.println("____________________________________________________________");
                    break;
                default:
                    System.out.println("____________________________________________________________");
                    System.out.println("added: " + input);
                    lst.add(input);
                    System.out.println("____________________________________________________________");
            }
            input = br.readLine();
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
        br.close();
    }
}
