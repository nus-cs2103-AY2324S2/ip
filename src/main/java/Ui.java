public class Ui {

    public void greeting() {
        System.out.println("Hello, I am TalkingJohn\nWhat can I do for you?\n");
    }

    public void goodbye() {
        System.out.println("Bye, hope to see you soon");
    }

    public void emptyInput(String input) {
        System.out.println("OOPS!!! " + input + " cannot be empty.");
    }

    public void printAddTask(Task task, int taskArrSize) {
        System.out.println("Got it. I've added this task:\n" +
                task + "\nNow you have " + taskArrSize + " tasks in the list.");
    }

    public void invalidInput() {
        System.out.println("HMM? This is an activity planner. Please repeat \uD83E\uDD72");
    }
}
