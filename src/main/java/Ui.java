import exceptions.NumeratorException;
import exceptions.storage.LoadingException;

public class Ui {


    public static void printLine() {
        String line = "____________________________________________________________";
        System.out.println(line);
    }


    public static void printLogo() {
        String logo = "Hello! I'm Numerator\n" + "What can I do for you?";
        System.out.println(logo);
    }

    public static void printLoadingError(LoadingException e) {
        System.out.println(e.getMessage());
    }

    public static void printError(NumeratorException e) {
        System.out.println(e.getMessage());
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
