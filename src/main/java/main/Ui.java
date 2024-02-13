package main;

import Objects.Task;

public class Ui {
    public void showLoadingError() {
    }

    public static void showLine(){
        System.out.println("=================================================");
    }

    public static void greetings(){
        showLine();
        System.out.println("\nYo! I'm Poe\nWhat can I do for you bro\n");
        showLine();
    }

    public static void bye(){
        showLine();
        System.out.println("\nBye come again\n");
        showLine();
    }

    public static void success(Task t){
        showLine();
        System.out.println("\nyippie added new task\n"+t.toString()+"\n");
        showLine();

    }

    public static void error(){
        System.out.println("no input :(\n");
        showLine();
    }

    public static void deadlineError(){
        System.out.println("input deadline with this format (eg: deadline assignment /by 2024-05-19\n");
        showLine();

    }

    public static void eventError(){
        System.out.println("input event with this format (eg: event party /from 2024-05-19 /to 2024-05-20\n");
        showLine();

    }


}
