import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.sql.SQLOutput;

public class Duke {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm AcademicWeapon");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        String input = br.readLine();
        while (!input.equals("bye")) {
            System.out.println("____________________________________________________________");
            System.out.println(input);
            System.out.println("____________________________________________________________");
            input = br.readLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
        pw.flush();
        br.close();
    }
}
