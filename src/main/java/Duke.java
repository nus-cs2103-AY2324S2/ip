import java.util.*;
public class Duke {
    static String line = "    ____________________________________________________________";
    static String indent = "     ";
    static String name = "Alfred";

    public static void separate(){
        System.out.println(line);
    }
    public static void spacing() {
        separate();
        System.out.println("");
    }

    public static void intro() {
        separate();
        System.out.println(indent + "Hello! I'm " + name + "\n     What can I do for you?");
        spacing();
    }
    public static void leave() {
        System.out.println(indent + "Bye. Hope to see you again soon!");
        spacing();
    }
    public task identify(String request){
        if (request.startsWith("todo")) {
            String[] reqList = request.split("todo ");
            todo current = new todo(reqList[1]);
            return current;
        } else if (request.startsWith("deadline")) {
            String[] reqList = request.split("deadline ");
            String[] timeList = reqList[1].split("/by ");
            String name = timeList[0];
            String time = timeList[1];
            deadline current = new deadline(name, time);
            return current;

        } else if (request.startsWith("event")) {
            String[] reqList = request.split("event ");
            String[] startList = reqList[1].split(" /from ");
            String name = startList[0];
            String[] endList = startList[1].split(" /to ");
            String start = endList[0];
            String end = endList[1];

            event current = new event(name, start, end);
            return current;

        } else{
            task current = new task(request);
            return current;
        }
    }

    public void start() {
        intro();
        Scanner input = new Scanner(System.in);
        ArrayList<task> taskList = new ArrayList<task>();
        while(true) {
            String current = input.nextLine();
            if(current.equals("bye")) {
                separate();
                leave();
                break;
            } else if(current.equals("list")) {
                separate();
                System.out.println(indent + "Here are the tasks in your list:");
                int count = 1;
                for (task i : taskList) {
                    System.out.println(indent + Integer.toString(count) + "." + i.getStatus());
                    count++;
                }
                spacing();
            } else if (current.startsWith("mark")) {
                String[] marking = current.split(" ");
                int position = Integer.parseInt(marking[1]) - 1;
                task curr = taskList.get(position);
                curr.makeDone();

                separate();
                System.out.println(indent + "Nice! I've marked this task as done:");
                System.out.println(indent + taskList.get(position).getStatus());
                spacing();

            } else if (current.startsWith("unmark")) {
                /* removing from done */
                String[] marking = current.split(" ");
                int position = Integer.parseInt(marking[1]) - 1;
                task curr = taskList.get(position);
                curr.makeUndone();

                separate();
                System.out.println(indent + "OK, I've marked this task as not done yet:");
                System.out.println(indent + taskList.get(position).getStatus());
                spacing();
            } else {
                task newTask = identify(current);
                taskList.add(newTask);
                separate();
                System.out.println(indent + "Got it. I've added this task:");
                System.out.println(indent + "  " + newTask.getStatus());
                System.out.println(indent + "Now you have " + Integer.toString(taskList.size()) +
                        " tasks in the list.");
                spacing();
            }
        }
    }
    public class task {
        private boolean done = false;
        private String name;
        private String type; /* T, D or E*/

        public task(String name){
            this.name = name;
        }
        public boolean isDone(){
            return this.done;
        }
        public String getName(){
            return this.name;
        }

        public void makeDone(){
            this.done = true;
        }
        public void makeUndone(){
            this.done = false;
        }
        /* add getType for each task type*/
        public String getType(){
            return "";
        }

        public String getTime(){
            return "";
        }
        public String getStatus(){
            if (this.isDone()) {
                return this.getType() + "[X] " + this.getName() + this.getTime();
            } else {
                return this.getType() + "[ ] " + this.getName() + this.getTime();
            }
        }
    }
    public class todo extends task{

        public todo(String name) {
            super(name);
        }
        @Override
        public String getType(){
            return "[T]";
        }

    }
    public class deadline extends task{
        private String end;
        public deadline(String name, String end) {
            super(name);
            this.end = end;
        }
        @Override
        public String getType(){
            return "[D]";
        }
    @Override
        public String getTime(){
            return "(by: " + end + ")";
        }
    }

    public class event extends task{
        private String start;
        private String end;

        public event(String name, String start, String end) {
            super(name);
            this.start = start;
            this.end = end;
        }
        @Override
        public String getType(){
            return "[E]";
        }
        @Override
        public String getTime(){
            return " (from: " + start + " to: " + end + ")";
        }

    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}
