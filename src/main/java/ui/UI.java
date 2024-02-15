package ui;

import exceptions.DukeException;

public class UI {

    public UI() {
    }

    public String formalities(String context) {
        if (context.equals("greet")) {
            this.showLine();
            greet();
            this.showLine();
            String greeting = " Wassup dawg, I'm Snoopy";
            return greeting;
        } else if (context.equals("farewell")) {
            this.showLine();
            System.out.println(" Bye. Don't come back. jk!");
            this.showLine();
            String farewell = " Bye. Don't come back. jk!";
            return farewell;
        } else {
            throw new DukeException("Unknown formality context!");
        }
    }

    private static void greet() {
        System.out.println(" Wassup dawg, I'm Snoopy");
        System.out.println(" What can I do for you?");
    }


    public void showLine() {
        System.out.println("____________________________________________________________");
    }

}
