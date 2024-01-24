import java.util.*;

public class Ypxmm {

    public static ArrayList<Task> tasks = new ArrayList<Task>(); //tasks
    public static void main(String[] args) throws YpxmmException {
        Scanner sc = new Scanner(System.in);
        sayHello();

        while (true) {
            try {
                String input = sc.nextLine();
                if (input.equals("bye")) {
                    sayGoodbye();
                    break;
                } else if (input.equals("list")) {
                    list();
                } else if (input.startsWith("mark")) {
                    String[] vals = input.split(" ");
                    try {
                        int index = Integer.parseInt(vals[1]);
                        mark(index);
                    } catch (IndexOutOfBoundsException e) {
                        throw new YpxmmException("Brother, key in mark <space> then a valid number");
                    } catch (NumberFormatException n) {
                        throw new YpxmmException("You tell me now what task am I supposed to mark if you don't provide me with a number?");
                    }
                } else if (input.startsWith("unmark")) {
                    String[] vals = input.split(" ");
                    try {
                        int index = Integer.parseInt(vals[1]);
                        unmark(index);
                    } catch (IndexOutOfBoundsException e) {
                        throw new YpxmmException("Brother, key in unmark <space> then a valid number");
                    } catch (NumberFormatException n) {
                        throw new YpxmmException("You tell me now what task am I supposed to unmark if you don't provide me with a number?");
                    }
                } else if (input.startsWith("todo")) {
                    addTask(input, "todo");
                } else if (input.startsWith("deadline")) {
                    addTask(input, "deadline");
                } else if (input.startsWith("event")) {
                    addTask(input, "event");
                } else if (input.equals("get commands")) {
                    getCommands();
                } else if (input.startsWith("delete")) {
                    String[] vals = input.split(" ");
                    try {
                        int index = Integer.parseInt(vals[1]);
                        delete(index);
                    } catch (IndexOutOfBoundsException e) {
                        throw new YpxmmException("Brother, key in delete <space> then a valid number");
                    } catch (NumberFormatException n) {
                        throw new YpxmmException("You tell me now what task am I supposed to delete if you don't provide me with a number?");
                    }
                } else {
                    throw new YpxmmException("Sorry bro, idk what that means. You try type in \"get commands\" then see if got what u want.");
                }
            } catch (YpxmmException y) {
                System.out.println(y.getMessage());
            }
        }
        sc.close();
    }
    public static void getCommands() {
        System.out.println("todo <task> - adds todo\ndeadline <task>/<by when> - adds deadline\n" +
                "event <task>/<from when>/<to when> - adds event\nlist - lists out all tasks\n" +
                "mark <x> - marks task x as done\nunmark <x> - unmarks task x as undone\n" +
                "bye - exit");
    }

    public static void sayHello() {
        System.out.println("Hello, I'm Ypxmm.\nNeed me do what for you?");
    }

    public static void sayGoodbye() {
        System.out.println("Oh you need zao alr? Okok see you next time!");
    }

    public static void list() {
        if (tasks.size() == 0) {
            System.out.println("No tasks yet la bro");
        } else {
            System.out.println("Ok wait ah, here are your tasks:");
            int count = 1;
            for (Task t : tasks) {
                System.out.println(count + ". " + t.toString());
                count++;
            }
        }
    }

    public static void mark(int index) {
        try {
            try {
                tasks.get(index - 1).markTask();
            } catch (IndexOutOfBoundsException e) {
                throw new YpxmmException("Eh u seh isit? Now your list got " +
                        (tasks.size() == 0 ? "no tasks to mark." : tasks.size() +
                                " tasks, enter any number from 1 to " + tasks.size()));
            }
        } catch (YpxmmException y) {
            System.out.println(y.getMessage());
        }
    }

    public static void unmark(int index) {
        try {
            try {
                tasks.get(index - 1).unmarkTask();
            } catch (IndexOutOfBoundsException e) {
                throw new YpxmmException("Eh u seh isit? Now your list got " +
                        (tasks.size() == 0 ? "no tasks to unmark." : tasks.size() +
                                " tasks, enter any number from 1 to " + tasks.size()));
            }
        } catch (YpxmmException y) {
            System.out.println(y.getMessage());
        }
    }

    public static void delete(int index) {
        try {
            try {
                String t = tasks.get(index - 1).toString();
                tasks.remove(index - 1);
                System.out.println("Ok deleted liao:\n" + t + "\nNow your list got " +
                        (tasks.size() == 0 ? "no tasks." : tasks.size() + " tasks left."));
            } catch (IndexOutOfBoundsException e) {
                throw new YpxmmException("Eh u seh isit? Now your list got " +
                        (tasks.size() == 0 ? "no tasks to delete." : tasks.size() +
                                " tasks, enter any number from 1 to " + tasks.size()));
            }
        } catch (YpxmmException y) {
            System.out.println(y.getMessage());
        }
    }

    public static void addTask(String input, String type) {
        try {
            if (type.equals("todo")) {
                try {
                    String[] info = input.split("todo ");
                    if (info[1].isBlank()) {
                        throw new YpxmmException("Help la, can just tell me what is the name of your task anot?");
                    }
                    Task t = new ToDo(info[1]);
                    tasks.add(t);
                    System.out.println("Ok I help you add this one liao:\n" + t.toString() +
                            "\nNow your list got " + tasks.size() + ((tasks.size() == 1) ? " task." : " tasks."));
                } catch (IndexOutOfBoundsException e) {
                    throw new YpxmmException("You trying to test my patience ah? Type \"get commands\" if u blur and dunno how to use me properly.");
                }
            } else if (type.equals("deadline")) {
                try {
                    String[] info = input.split("/");
                    if (info[0].split("deadline ")[1].isBlank() || info[1].isBlank()) {
                        throw new YpxmmException("Help la, can just tell me what is the name of your task anot?");
                    }
                    Task t = new Deadline(info[0].substring(9), info[1]);
                    tasks.add(t);
                    System.out.println("Ok I help you add this one liao:\n" + t.toString() +
                            "\nNow your list got " + tasks.size() + ((tasks.size() == 1) ? " task." : " tasks."));
                } catch (IndexOutOfBoundsException e) {
                    throw new YpxmmException("You trying to test my patience ah? Check that u got key in the deadline lehhh\n" +
                            "Type \"get commands\" if u blur and dunno how to use me properly.");
                }
            } else {
                try {
                    String[] info = input.split("/");
                    if (info[0].split("event ")[1].isBlank() || info[1].isBlank() || info[2].isBlank()) {
                        throw new YpxmmException("Help la, can just tell me what is the name of your task anot?");
                    }
                    Task t = new Event(info[0].substring(6), info[1], info[2]);
                    tasks.add(t);
                    System.out.println("Ok I help you add this one liao:\n" + t.toString() +
                            "\nNow your list got " + tasks.size() + ((tasks.size() == 1) ? " task." : " tasks."));
                } catch (IndexOutOfBoundsException e) {
                    throw new YpxmmException("Eh brother last warning ah. Check that u got key in the start and end time\n" +
                            "Type \"get commands\" if u blur and dunno how to use me properly.");
                }
            }
        } catch (YpxmmException y) {
            System.out.println(y.getMessage());
        }
    }
}
