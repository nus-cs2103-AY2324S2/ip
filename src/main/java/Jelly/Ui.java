package Jelly;
public class Ui {

    private static String line = "\n-------------------------------------------";

    private static String welcome = "(ᵔ_ᵔ) Hello! I'm Jelly\nWhat can I do for you?";
    private static String farewell = "(•︿•) Bye. Hope to see you again soon!";

    public Ui(){

    }

    public void printLoadingError(JellyException e){

        System.out.println(e.getMessage());
    }

    public void printLine(){

        System.out.println(line);
    }

    public void printWelcomeMessage(){

        System.out.println(welcome);
    }

    public void printFarewellMessage(){

        System.out.println(farewell);
    }

    public void printMessage(String message){

        System.out.println(message);
    }
}
