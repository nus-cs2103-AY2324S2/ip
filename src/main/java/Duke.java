import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    private static String LINE_BREAK = "\n---------------------------------------------\n";
    private static ArrayList<Task> tasks;

    private static class Task {
        private String task;
        private boolean completed;
        private enum Types {
            T,
            D,
            E
        }

        public Types type;

        public void setCompleted(boolean completed) {
            this.completed = completed;
        }

        public Task(String task) {
            this.task = task;
            this.completed = false;
        }

        public String completedIcon() {
            return this.completed ? "X" : " ";
        }

        public Task(String task, Types type) {
            this.task = task;
            this.completed = false;
            this.type = type;
        }
    }

    private static void Squid() {
        tasks = new ArrayList<>();
    }

    private static void add(String task) {
        tasks.add(new Task(task));
        echo(String.format("Added %s. When are you going to add 'feed Squid'?", task));
    }

    private static void list() {
        echo("Here are your tasks. Sucks to be you, my only 2 tasks are eating and sleeping.");
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            System.out.printf("%d: [%s][%s] %s%n", i + 1, currTask.type, currTask.completedIcon(), currTask.task);
        }
    }

    private static void hello() {
        System.out.println(LINE_BREAK);
        echo("HMm human. What do you want again?");
        System.out.println(LINE_BREAK);
    }

    private static void bye() {
        String message = "You're done. Time for my food.";
        echo(message);
    }

    private static void echo(String message) {
        System.out.println("Squid: " + message);
    }

    private static void todo(String message) {
        Task t = new Task(message, Task.Types.T);
        tasks.add(t);
        // TODO message
    }
    // TODO abstraction for printing task!


    private static void mark(String task, boolean completed) {
        // Find the task entry.
        Task found = null;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).task.equals(task)) {
                found = tasks.get(i);
            }
        }
        if (found != null) {
            found.setCompleted(completed);
            echo(String.format(completed
                    ? "That was slow, but at least you completed: \n [%s] %s%n"
                    : "Can't make up your mind? \n [%s] %s%n", found.completedIcon(), found.task));


        } else {
            echo("I can't find the task, dummy human!");
        }
    }

    private static boolean parseInput(boolean loop, String input) {
        String[] inputs = input.split(" ", 2);
        String command = inputs[0];
        String arguments = inputs.length > 1 ? inputs[1] : "";
        System.out.println(LINE_BREAK);

        switch (command) {
            case ("bye"):
                loop = false;
                bye();
                break;
            case ("list"):
                list();
                break;
            case ("mark"):
                mark(arguments, true);
                break;
            case ("unmark"):
                mark(arguments, false);
                break;
            case ("todo"):
                todo(arguments);
                break;
            default:
                add(input);
                break;
        }
        return loop;
    }

    public static void main(String[] args) {
        Squid();
        hello();

        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        while (loop) {
            String input = scanner.nextLine().strip();
            loop = parseInput(loop, input);
            System.out.println(LINE_BREAK);
        }

    }


}
