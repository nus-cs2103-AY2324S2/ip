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
    public void echo() {
        intro();
        Scanner input = new Scanner(System.in);
        ArrayList<task> todo = new ArrayList<task>();
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
                for (task i : todo) {
                    if (i.isDone()) { /* if done*/
                        System.out.println(indent + Integer.toString(count) + ".[X] " + i.getName());
                    } else { /* not done*/
                        System.out.println(indent + Integer.toString(count) + ".[ ] " + i.getName());
                    }
                    count++;
                }
                spacing();
            } else if (current.startsWith("mark")) {
                /* adding to done*/
                String[] marking = current.split(" ");
                int position = Integer.parseInt(marking[1]) - 1;
                task curr = todo.get(position);
                curr.makeDone();

                separate();
                System.out.println(indent + "Nice! I've marked this task as done:");
                System.out.println(indent + "  [X] " + todo.get(position).getName());
                spacing();

            } else if (current.startsWith("unmark")) {
                /* removing from done */
                String[] marking = current.split(" ");
                int position = Integer.parseInt(marking[1]) - 1;
                task curr = todo.get(position);
                curr.makeUndone();

                separate();
                System.out.println(indent + "OK, I've marked this task as not done yet:");
                System.out.println(indent + "  [ ] " + todo.get(position).getName());
                spacing();
            } else {
                task newTask = new task(current);
                todo.add(newTask);
                separate();
                System.out.println(indent + "added: " + current);
                spacing();
            }
        }
    }
    public class task {
        private boolean done = false;
        private String name;

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
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.echo();
    }
}
