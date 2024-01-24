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

class Todo extends Task {

    public Todo(String description) {
        super(description);
    }
    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }
}

 class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

 class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + ") " + "to: " + to + ")";
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
            String[] words = io.split("\\s+", 2); 
            String detail = words.length > 1 ? words[1] : ""; 

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
            } 
            else if (words[0].equals("todo")) {
                pw.println("Got it. I've added this task:");
                Todo t = new Todo(detail);
                pw.println(t);
                myList[pointer] = t;
                pointer++;
                pw.println("Now you have " + pointer + " tasks in the list.");
                pw.println("____________________________________________________________\n");
            }
            
            else if (words[0].equals("deadline")) {
                pw.println("Got it. I've added this task:");
                String[] parts = detail.split("\\s*/by\\s*", 2);
                Deadline t = new Deadline(parts[0], parts[1]);
                pw.println(t);
                myList[pointer] = t;
                pointer++;
                pw.println("Now you have " + pointer + " tasks in the list.");
                pw.println("____________________________________________________________\n");
            } else {
                pw.println("Got it. I've added this task:");
                String[] firstSplit = detail.split("\\s*/from\\s*", 2);
                String[] secondSplit = firstSplit[1].split("\\s*/to\\s*", 2);
                Event t = new Event(firstSplit[0], secondSplit[0], secondSplit[1]);
                pw.println(t);
                myList[pointer] = t;
                pointer++;
                pw.println("Now you have " + pointer + " tasks in the list.");
                pw.println("____________________________________________________________\n");
            }
        }
    }
}
