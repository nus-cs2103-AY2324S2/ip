import java.io.*;

public class Tiny {
    public static void main(String[] args) throws IOException {

        Task[] tasks = new Task[100];
        int totalTasks = 0;

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
            if (input.equals("list")) {
                for (int i = 0; i < totalTasks; i++) {
                    out.println("   "+ (i + 1) + ". " + tasks[i].getName());
                }
            } else {
                tasks[totalTasks] = new Task(input);
                totalTasks++; 
                out.println("   added: " + input);
            }
            out.println("   ____________________________________________________________\n");
            out.flush();
        }
        out.println("   ____________________________________________________________");
        out.println("   Bye. Hope to see you again soon!");
        out.println("   ____________________________________________________________");



        out.flush();
    }
}
