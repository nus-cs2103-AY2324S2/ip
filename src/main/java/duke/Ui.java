package duke;

import java.util.Scanner;

public class Ui {
    private static String name = "GanAnWo";

    private Scanner sc;
    public Ui(){
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm " + name + "\n"
                + "What can I do for you?");
    }

    public void showMessage(String m){
        System.out.println(m);
    }

    public void showBye(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public String readCommand(){
        return sc.nextLine();
    }
}
