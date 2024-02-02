package duke;

public class UI {
    private final String LINE = "______________________________________________________\n";


    public UI() {}
    public void onStart() {
        System.out.print(LINE);
        System.out.println("Hello! I'm ChatterPal!");
        System.out.println("What can I do for you?");
        System.out.print(LINE);
    }

    public void onTaskAddition(String s) {
        System.out.println(LINE + s + "\n" + LINE);
    }
    public void onTaskDeletion(String s, int interg) {
        String message = String.format("%sNoted. I've removed this task:\n" +
                "%s\nNow you have %d tasks left.\n%s", LINE, s, interg, LINE);

        System.out.println(message);
    }
    public void onPrintList(String s) {
        System.out.println(LINE + s + LINE);
    }
    public void onMark(String s) {
        String output = LINE + "Great job on completing the task!\n" + s + "\n" + LINE;
        System.out.println(output);
    }
    public void onUnmark(String s) {
        String output = LINE + "OK, I've marked this task as not done yet: \n" + s + "\n" + LINE;
        System.out.println(output);
    }
    public void onEnd() {
        System.out.println(LINE);
        System.out.println("Farewell! Can't wait to catch up with you again. Until next time, " +
                "take care and stay awesome! ");
        System.out.println(LINE);
    }

}
