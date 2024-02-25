package ui;

import exceptions.DukeException;

public class UI {

    public UI() {
    }

    public String formalities(String context) {
        if (context.equals("greet")) {
            String greeting = " Wassup dawg, I'm Snoopy";
            return greeting;
        } else if (context.equals("farewell")) {
            String farewell = " Bye. Don't come back. jk!";
            return farewell;
        } else {
            throw new DukeException("Unknown formality context!");
        }
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

}
