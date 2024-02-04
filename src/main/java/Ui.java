public class Ui {

    public Ui() {

    }
    public static void printGreetings() {
        System.out.println("Hello... I'm Riz...\n"
                + "What can I help you with today?\n");
    }

    public static void printGoodbye() {
        System.out.println("Bye... Hope to see you again...\n");
    }

    public static void printClearConfirmation() {
        System.out.println("Are you sure you want to delete all tasks? y/n");
    }

    public static void printAllCleared() {
        System.out.println("All tasks have been cleared...");
    }

    public static void markError() {
        RizException e = new RizException("Oh you sure you completed the task?...");
        System.out.println(e.getMessage());
    }

    public static void unmarkError() {
        RizException e = new RizException("Seems like there's more to be done...");
        System.out.println(e.getMessage());
    }

    public static void deleteError() {
        RizException e = new RizException("Oh you sure you wanna delete the task?...");
        System.out.println(e.getMessage());
    }

    public static void todoError() {
        RizException e = new RizException("Indecisive aren't we...");
        System.out.println(e.getMessage());
    }

    public static void deadlineError() {
        RizException e = new RizException("seems like the deadline isn't so soon after all...");
        System.out.println(e.getMessage());
    }

    public static void eventError() {
        RizException e = new RizException("What? Did the event get cancelled...");
        System.out.println(e.getMessage());
    }

    public static void yapError() {
        RizException e = new RizException("Are you speaking Yapanese?...");
        System.out.println(e.getMessage());
    }
}
