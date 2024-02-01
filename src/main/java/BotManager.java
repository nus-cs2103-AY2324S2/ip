import java.util.ArrayList;

public class BotManager {
    private final String name;
    private ArrayList<Task> tasks;

    BotManager(String name) {
        this.name = name;
        this.tasks = new ArrayList<Task>();
    }

    void greeting() {
        System.out.println("    Hello! I'm " + name);
        System.out.println("    What can I do for you?\n");
    }

    void exit() {
        System.out.println("    Bye. Hope to see you again soon!\n");
    }

    private Task createTask(String prompt) throws DukeException {
        String[] order = prompt.split(" ", 2);
        String taskType = order[0];
        switch (taskType) {
        case "todo":
            if (order.length == 1) {
                throw new DukeException("    Oops! Not sure about the description of the todo!\n");
            } else if (order[1].trim().isEmpty()) {
                throw new DukeException("    Oops! Not sure about the description of the todo!\n");
            }
            return new Todo(order[1]);
        case "deadline":
            String[] deadline = order[1].split(" /by ");
            if (deadline.length == 1) {
                throw new DukeException("    Oops! Incorrect format!\n");
            } else if (deadline[0].trim().isEmpty()) {
                throw new DukeException("    Oops! Not sure about the description of the deadline!\n");
            } else if (deadline[1].trim().isEmpty()) {
                throw new DukeException("    Oops! Not sure about the deadline!\n");
            }
            return new Deadline(deadline[0], deadline[1]);
        case "event":
            String[] startTime = order[1].split(" /from ");
            if (startTime.length == 1) {
                throw new DukeException("    Oops! Incorrect format!\n");
            } else if (startTime[0].trim().isEmpty()) {
                throw new DukeException("    Oops! Not sure about the description of the event!\n");
            } else if (startTime[1].split(" /to ")[0].trim().isEmpty()) {
                throw new DukeException("    Oops! Not sure about the starting time!\n");
            }
            String[] endTime = order[1].split(" /to ");
            if (endTime.length == 1) {
                throw new DukeException("    Oops! Incorrect format!\n");
            } else if (endTime[1].trim().isEmpty()) {
                throw new DukeException("    Oops! Not sure about the ending time!\n");
            }
            return new Event(startTime[0], startTime[1].split(" /to ")[0], endTime[1]);
        default:
            throw new DukeException("    Sorry! I don't see what you mean...\n");
        }
    }

    private void addTask(String prompt) throws DukeException {
        Task task = createTask(prompt);
        tasks.add(task);
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        if (tasks.size() == 1) {
            System.out.println("    Now you have 1 task in the list\n");
        } else {
            System.out.printf("    Now you have %d tasks in the list\n%n", tasks.size());
        }
    }

    private void list() {
        if (tasks.isEmpty()) {
            System.out.println("    No tasks yet...\n");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("    " + (i + 1) + "." + tasks.get(i));
            }
        }
        System.out.println();
    }

    private void mark(int num) throws DukeException {
        if (num <= 0 || num > tasks.size()) {
            throw new DukeException("    OOPS! Invalid Index!\n");
        }
        Task task = tasks.get(num - 1);
        if (task.isDone()) {
            System.out.println("    Already done. No need to mark again.\n");
        } else {
            task.mark();
            System.out.println("    Nice! I've marked this task as done:");
            System.out.println("      " + task + '\n');
        }
    }

    private void unmark(int num) throws DukeException {
        if (num <= 0 || num > tasks.size()) {
            throw new DukeException("OOPS! Invalid Index!\n");
        }
        Task task = tasks.get(num - 1);
        if (!task.isDone()) {
            System.out.println("    Not done in the first place. No need to unmark.\n");
        } else {
            task.unmark();
            System.out.println("    OK, I've marked this task as not done yet:");
            System.out.println("      " + task + '\n');
        }
    }

    void answer(String prompt) {
        String[] order = prompt.split(" ");
        try {
            switch (order[0]) {
            case "list":
                list();
                break;
            case "mark":
                mark(Integer.parseInt(order[1]));
                break;
            case "unmark":
                unmark(Integer.parseInt(order[1]));
                break;
            default:
                addTask(prompt);
            }
        } catch (DukeException e) {
            System.out.println(e);
        }
    }
}
