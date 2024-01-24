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
            
            try{
                if ("bye".equals(splitInput[0])){
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if ("list".equals(splitInput[0])) {
                    for(int i=0; i< taskcount;i++){
                        System.out.println((i+1)+". " + tasks[i]);
                    }

                } else if("mark".equals(splitInput[0])){
                    int index = Integer.parseInt(splitInput[1])-1;
                    tasks[index].mark();
                    System.out.println("Nice! I've marked this task as done:\n  " + tasks[index]);

                } else if ("unmark".equals(splitInput[0])) {
                    int index = Integer.parseInt(splitInput[1])-1;
                    tasks[index].unMark();
                    System.out.println("OK, I've marked this task as not done yet:\n  " + tasks[index]);

                } else if ("todo".equals(splitInput[0])){
                    tasks[taskcount] = new Todo(splitInput[1]);
                    taskcount++;
                    System.out.println("Got it. I've added this task:\n  " + tasks[taskcount-1]+"\nNow you have "+ taskcount+" tasks in the list");

                } else if ("deadline".equals(splitInput[0])) {
                    String[] deadlineSplit = splitInput[1].split(" /by ");
                    tasks[taskcount] = new Deadline(deadlineSplit[0],deadlineSplit[1]);
                    taskcount++;
                    System.out.println("Got it. I've added this task:\n  " + tasks[taskcount-1]+"\nNow you have "+ taskcount+" tasks in the list");
                } else if("event".equals(splitInput[0])){
                    String[] eventSplit = splitInput[1].split(" /from | /to ");
                    tasks[taskcount] = new Event(eventSplit[0],eventSplit[1],eventSplit[2]);
                    taskcount++;
                    System.out.println("Got it. I've added this task:\n  " + tasks[taskcount-1]+"\nNow you have "+ taskcount+" tasks in the list");

                } else{
                    System.out.println("Error! Command not found");
                }


            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Error! Description to command not found");
            }


        }

    }

}
