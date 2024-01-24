import java.io.*;

public class Tiny {
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(System.out);
        out.println("   ____________________________________________________________");
        out.println("   Hello! I'm Tiny!");
        out.println("   What can I do for you?");
        out.println("   ____________________________________________________________\n");
        out.flush();

        while (true) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = br.readLine();   
            if (input.equals("bye")) break;
            out.println("   ____________________________________________________________");
            out.println("   " + input);
            out.println("   ____________________________________________________________\n");
            out.flush();
        }
        out.println("   ____________________________________________________________");
        out.println("   Bye. Hope to see you again soon!");
        out.println("   ____________________________________________________________");



        out.flush();
    }
}
