import java.util.ArrayList;
import java.util.Scanner;


enum TodoState {
    UNDONE,
    DONE
}
class Todo extends Task {
    public Todo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}

class Deadline extends Task {
    String deadline;

    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + "(by: " + deadline + ")";
    }
}

class Event extends Task {
    String start;
    String end;

    public Event(String task, String start, String end) {
        super(task);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + "(from: " + start + " to: " + end + ")";
    }
}

abstract class Task {
    TodoState todoState;
    String task;

    public Task(String task) {
        this.todoState = TodoState.UNDONE;
        this.task = task;
    }


    @Override
    public String toString() {
        return "[" + (todoState == TodoState.DONE ? "X" : " ") + "] " + task;
    }
}

class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}

public class Duke {
    static String line = "____________________________________________________________";

    public static void main(String[] args) {

        System.out.println(line);
        System.out.println("Hello! I'm Brian\nWhat can I do for you?");
        System.out.println(line);
        ArrayList<Task> data = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String[] input = sc.nextLine().split(" ", 2);
            String method = input[0];
            String params = input.length == 1 ? "" : input[1];
            System.out.println(line);
            try {

                switch (method) {
                    case "list": {
                        for (int i = 0; i < data.size(); i++) {
                            System.out.printf("%d. %s\n", i + 1, data.get(i));
                        }
                        break;
                    }
                    case "mark": {
                        if (params.equals("")) {
                            throw new DukeException("The id of a mark cannot be empty.");
                        }
                        int index = Integer.parseInt(params) - 1;
                        data.get(index).todoState = TodoState.DONE;
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(data.get(index));
                        break;
                    }
                    case "unmark": {
                        if (params.equals("")) {
                            throw new DukeException("The id of a unmark cannot be empty.");
                        }
                        int index = Integer.parseInt(params) - 1;
                        data.get(index).todoState = TodoState.UNDONE;
                        System.out.println("Okay! I've marked this task as not done yet");
                        System.out.println(data.get(index));
                        break;
                    }
                    case "todo": {
                        if (params.equals("")) {
                            throw new DukeException("The description of a todo cannot be empty.");
                        }

                        Task curr = new Todo(params);
                        data.add(curr);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(curr);
                        System.out.printf("Now you have %d tasks in the list.\n", data.size());
                        break;
                    }
                    case "deadline": {
                        if (params.equals("")) {
                            throw new DukeException("The description of a deadline cannot be empty.");
                        }
                        String[] split = params.split(" /by ", 2);
                        if (split.length == 1) {
                            throw new DukeException("The deadline of a deadline cannot be empty.");
                        }
                        Task curr = new Deadline(split[0], split[1]);
                        data.add(curr);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(curr);
                        System.out.printf("Now you have %d tasks in the list.\n", data.size());
                        break;
                    }
                    case "event": {
                        if (params.equals("")) {
                            throw new DukeException("The description of a event cannot be empty.");
                        }
                        String[] split1 = params.split(" /from ", 2);
                        if (split1.length == 1) {
                            throw new DukeException("The from of a event cannot be empty.");
                        }
                        String[] split2 = split1[1].split(" /to ", 2);
                        if (split2.length == 1) {
                            throw new DukeException("The to of a event cannot be empty.");
                        }
                        Task curr = new Event(split1[0], split2[0], split2[1]);
                        data.add(curr);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(curr);
                        System.out.printf("Now you have %d tasks in the list.\n", data.size());
                        break;
                    }
                    case "delete": {
                        if (params.equals("")) {
                            throw new DukeException("The id of a delete cannot be empty.");
                        }
                        int index = Integer.parseInt(params) - 1;
                        Task curr = data.get(index - 1);
                        data.remove(index);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(curr);
                        System.out.printf("Now you have %d tasks in the list.\n", data.size());
                        break;
                    }
                    case "bye": {
                        System.out.println("Bye. Hope to see you again soon!");
                        System.exit(0);
                        break;
                    }
                    default: {
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }
                }
            } catch (DukeException e) {
                System.out.println("OOPS!!! " + e.getMessage());
            } finally {
                System.out.println(line);
            }
        }
    }
}
