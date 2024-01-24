import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        class Task {
            protected String description;
            protected boolean isDone;

            public Task(String description) {
                this.description = description;
                this.isDone = false;
            }

            public void mark() {
                isDone = true;
            }

            public void unMark() {
                isDone = false;
            }

            public String getStatusIcon() {
                return (isDone ? "[X]" : "[ ]");
            }

            @Override
            public String toString() {
                return getStatusIcon() + " " + description;
            }
        }

        class Todo extends Task {
            public Todo(String description) {
                super(description);
            }

            @Override
            public String toString() {
                return "[T]" + super.toString();
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
            protected String start;
            protected String end;

            public Event(String description, String start, String end) {
                super(description);
                this.start = start;
                this.end = end;
            }

            @Override
            public String toString() {
                return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
            }
        }


        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm Tom \nWhat can I do for you?\n");
        System.out.println(line);
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskcount = 0;


        while (true){
            String input = scanner.nextLine();
            String[] splitInput = input.split(" ", 2);
            String command = splitInput[0];
            String details = splitInput.length > 1 ? splitInput[1] : "You didnt enter the details to the command!!!";


            if ("bye".equals(command)){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if ("list".equals(command)) {
                for(int i=0; i< taskcount;i++){
                    System.out.println((i+1)+". " + tasks[i]);
                }

            } else if("mark".equals(command)){
                int index = Integer.parseInt(details)-1;
                tasks[index].mark();
                System.out.println("Nice! I've marked this task as done:\n  " + tasks[index]);

            } else if ("unmark".equals(command)) {
                int index = Integer.parseInt(details)-1;
                tasks[index].unMark();
                System.out.println("OK, I've marked this task as not done yet:\n  " + tasks[index]);

            } else if ("todo".equals(command)){
                tasks[taskcount] = new Todo(details);
                taskcount++;
                System.out.println("Got it. I've added this task:\n  " + tasks[taskcount-1]+"\nNow you have "+ taskcount+" tasks in the list");

            } else if ("deadline".equals(command)) {
                String[] deadlineSplit = details.split(" /by ");
                tasks[taskcount] = new Deadline(deadlineSplit[0],deadlineSplit[1]);
                taskcount++;
                System.out.println("Got it. I've added this task:\n  " + tasks[taskcount-1]+"\nNow you have "+ taskcount+" tasks in the list");
            } else if("event".equals(command)){
                String[] eventSplit = details.split(" /from | /to ");
                tasks[taskcount] = new Event(eventSplit[0],eventSplit[1],eventSplit[2]);
                taskcount++;
                System.out.println("Got it. I've added this task:\n  " + tasks[taskcount-1]+"\nNow you have "+ taskcount+" tasks in the list");

            }


            /*
            tasks[taskcount] = new Task(input);
                System.out.println("added: "+input);
                taskcount++;
             */

        }

    }

}
