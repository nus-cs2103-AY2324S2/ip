package UI;

import Tasks.Task;

public class Ui {

    public String showInstructions() {
        return "Please type in:\n" +
                "------------------------\n" +
                "[to record your task(s)/ events]\n" +
                "- todo <task>\n" +
                "- deadline <task> /by <date>\n" +
                "- event <event> /from <date and time> /to <date and time>\n\n" +
                "[to view your task(s)]\n" +
                "- list\n\n" +
                "[to mark or unmark your task as done]\n" +
                "- mark <task number in list>\n" +
                "- unmark <task number in list>\n" +
                "-------------------------------------\n";
    }

    public void greet() {
        String greet = "    Hi! I am your favourite friend, Lelu :)\n    What can I do for you?\n";
        System.out.println(greet);
    }

    public void exit() {
        String exit = "    Ok bye, you shall be missed :(";
        System.out.println(exit);
    }

    public void markMessage(Task t) {
        System.out.printf("    Great job completing your task!\n      %s\n\n", t.toString());

    }
    public void unmarkMessage(Task t) {
        System.out.printf("    Don't forget to complete your task soon...\n      %s\n\n", t.toString());
    }

    public void deleteMessage(Task t, int size) {
        System.out.printf("    Ok, I have removed your task:\n    %s\n    You have %d task(s) in the " +
                "list now.\n\n", t.toString(), size);;
    }

    public void addMessage(Task t, int size) {
        System.out.printf("    Ok! I have added your task:\n      %s\n    You have %d task(s) in the " +
                "list now.\n\n", t.toString(), size);
    }

    public void dateFormatInstructions() {
        System.out.printf("    Your date should be in this format:\n    <YYYY-MM-DD HH:mm> e.g. 2024-02-03 15:25\n");
    }

}
