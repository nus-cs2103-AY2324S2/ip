import java.util.*;

public class Ypxmm {

    public static ArrayList<Task> tasks = new ArrayList<Task>(); //tasks
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sayHello();

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                sayGoodbye();
                break;
            } else if (input.equals("list")) {
                list();
            } else if (input.startsWith("mark")) {
                String[] vals = input.split(" ");
                int index = Integer.parseInt(vals[1]);
                mark(index);
            } else if (input.startsWith("unmark")) {
                String[] vals = input.split(" ");
                int index = Integer.parseInt(vals[1]);
                unmark(index);
            } else if (input.startsWith("todo")) {
                addTask(input, "todo");
            } else if (input.startsWith("deadline")) {
                addTask(input, "deadline");
            } else if (input.startsWith("event")) {
                addTask(input, "event");
            } else if (input.equals("get commands")) {
                getCommands();;
            } else {
                System.out.println("Sorry bro, idk what that means. You try type in \"get commands\" then see if got what u want.");
            }
        }
        sc.close();
    }
    public static void getCommands() {
        System.out.println("todo [task] - adds todo\ndeadline [task]/[by when] - adds deadline\n" +
                "event [task]/[from]/[to] - adds event\nlist - lists out all tasks\n" +
                "mark x - marks task x as done\nunmark x - unmarks task x as undone" +
                "bye - exit");
    }

    public static void sayHello() {
        System.out.println("Hello, I'm Ypxmm.");
        System.out.println("Need me do what for you?");
    }

    public static void sayGoodbye() {
        System.out.println("Oh you need zao alr? Okok see you next time!");
    }

    public static void list() {
        System.out.println("Ok wait ah, here are your tasks:");
        if (tasks.size() == 0) {
            System.out.println("No tasks yet la bro");
        } else {
            int count = 1;
            for (Task t : tasks) {
                System.out.println(count + ". " + t.toString());
                count++;
            }
        }
    }

    public static void mark(int index) {
        tasks.get(index - 1).markTask();
    }

    public static void unmark(int index) {
        tasks.get(index - 1).unmarkTask();
    }

    public static void addTask(String input, String type) {
        String[] info = input.split("/");
        if (type.equals("todo")) {
            Task t = new ToDo(info[0].substring(5));
            tasks.add(t);
            System.out.println("Ok I help you add this one liao:\n" + t.toString() +
                    "\nNow your list got " + tasks.size() + ((tasks.size() == 1) ? " task." : " tasks."));
        } else if (type.equals("deadline")) {
            Task t = new Deadline(info[0].substring(9), info[1]);
            tasks.add(t);
            System.out.println("Ok I help you add this one liao:\n" + t.toString() +
                    "\nNow your list got " + tasks.size() + ((tasks.size() == 1) ? " task." : " tasks."));
        } else {
            Task t = new Event(info[0].substring(6), info[1], info[2]);
            tasks.add(t);
            System.out.println("Ok I help you add this one liao:\n" + t.toString() +
                    "\nNow your list got " + tasks.size() + ((tasks.size() == 1) ? " task." : " tasks."));
        }
    }
}
