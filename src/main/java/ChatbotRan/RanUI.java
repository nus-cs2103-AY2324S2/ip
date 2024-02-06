package ChatbotRan;

public class RanUI {
    public void greet() {
        System.out.println("Hello. I am ");
        String art = "__________                \n" +
                "\\______   \\_____    ____  \n" +
                " |       _/\\__  \\  /    \\ \n" +
                " |    |   \\ / __ \\|   |  \\\n" +
                " |____|_  /(____  /___|  /\n" +
                "        \\/      \\/     \\/ ";
        System.out.println(art);
        System.out.println("What would you like to do today?");
    }

    public void line() {
        System.out.println("____________________________________________________________");
    }

    public void printTasks(TaskList taskList) {
        int size = taskList.size();
        if (size == 0) {
            System.out.println("You haven't got any tasks.");
        } else {
            for (int i = 0; i < size; i++) {
                System.out.println("Task " + (i + 1) + ":" + taskList.get(i));
            }
        }
    }

    public void unknown() {

        System.out.println("I didn't understand that.");
    }

    public void addTask(Task task) {
        System.out.println("I've added this task to the list: ");
        System.out.println(task);
    }

    void printNumber(int size) {
        if (size == 1) {
            System.out.println("There is now 1 task in the list");
        } else {
            System.out.println("There are now " + size + " tasks in the list");
        }
    }

    public void delete(Task task) {
        System.out.println("I've deleted this task: ");
        System.out.println(task);
    }

    public void bye() {
        System.out.println("Goodbye, please return soon.");
    }

    public void unmark(boolean complete) {
        if (complete) {
            System.out.println("If that's the case, I'll set that task as incomplete: ");
        } else {
            System.out.println("That task is already incomplete: ");

        }
    }

    public void mark(boolean completed) {

        if (!completed) {
            System.out.println("Alright. I have marked this task as complete: ");
        } else {
            System.out.println("That task is already complete: ");
        }
    }

    public void printTask(Task task) {
        System.out.println(task);
    }

    public void error(TaskException e) {
        System.out.println(e.getMessage());
    }
}
