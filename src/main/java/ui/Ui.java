package ui;

public class Ui {
    public static final String HI_MESSAGE = "Hello! I'm JerryBot.\n What can I do for you?";
    public static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";

    public static String hiMessage() {
        return HI_MESSAGE;
    }

    public static String byeMessage() {
        return BYE_MESSAGE;
    }

    public static String taskRemovalMessage(String deletedTaskString, int tasksLeft) {
        return String.format("Noted. I've removed this task: \n %s \n Now you have %d tasks in the list.",
                deletedTaskString, tasksLeft);
    }

    public static String addTaskMessage(String taskString, int tasksLeft) {
        return String.format("Got it. I've added this task:\n %s \n Now you have %d tasks in the list.",
                taskString, tasksLeft);
    }

    public static String markTaskAsDoneMessage(String taskString) {
        return String.format("Nice! I've marked this task as done: \n %s", taskString);
    }

    public static String markTaskAsUndoneMessage(String taskString) {
        return String.format("Nooo! I've marked this task as undone: \n %s", taskString);
    }

}
