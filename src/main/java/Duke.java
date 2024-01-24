import java.io.*;

 class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return (this.getStatusIcon() + this.description);
    }
}


public class Duke {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out, true);

        Task[] myList = new Task[100];
        int pointer = 0;

        String prompt = "Hello! I'm TFamilyBot\n"
                + "What can I do for you? \n"
                + "____________________________________________________________\n";   
        pw.println(prompt);

        while (true) {
            String io = br.readLine();
            String[] words = io.split("\\s+"); 

            pw.println("____________________________________________________________\n");

            if (words[0].equals("bye")) {
                pw.println("Bye. Hope to see you again soon!");
                pw.println("____________________________________________________________\n");
                break;
            }

            else if (words[0].equals("list")) {
                for (int i = 0; i < pointer; i++) {
                    int show = i + 1;
                    pw.println(show + "." + myList[i]);
                }
                pw.println("____________________________________________________________\n");
            }
            else if (words[0].equals("mark")) {
                int c = Integer.parseInt(words[1]);
                myList[c-1].markAsDone();
                pw.println("Nice! I've marked this task as done:");
                pw.println(myList[c-1]);
                pw.println("____________________________________________________________\n");

            }
            else if (words[0].equals("unmark")) {
                int c = Integer.parseInt(words[1]);
                myList[c-1].markAsUndone();
                pw.println("OK, I've marked this task as not done yet:");
                pw.println(myList[c-1]);
                pw.println("____________________________________________________________\n");
            } else {
                Task t = new Task(io);
                myList[pointer] = t;
                pointer++;
                pw.println("added: " + io);
                pw.println("____________________________________________________________\n");
            }
        }
    }
}
