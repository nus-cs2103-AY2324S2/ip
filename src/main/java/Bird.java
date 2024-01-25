

public class Bird {
    static Task[] taskList = new Task[100];
    static int index = 0;
    public static void main(String[] args) { 
        greet();
        Command.scan();
    }

    public static void greet() {
        System.out.println(" /\\_/\\");
        System.out.println("((@v@))");
        System.out.println("():::()");
        System.out.println(" VV-VV");
        System.out.println("What can I do for you?");
    }

    public static void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < index; i++) {
            int number = i + 1;
            System.out.println(number + ". " + taskList[i].toString());
        }
    }

    public static void addTask(Task task) {
        taskList[index] = task;
        index += 1;
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println(String.format("Now you have %d tasks in the list.", index));
    }


    public static void markTask(int idx) {
        Task task = taskList[idx-1];
        task.done();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    public static void unmarkTask(int idx) {
        Task task = taskList[idx-1];
        task.undone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
    }
}
