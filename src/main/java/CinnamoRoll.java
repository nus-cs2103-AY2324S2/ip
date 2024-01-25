import java.util.*;

class CinnamoRoll {

    private ArrayList<String> tasks;

    CinnamoRoll() {
        this.tasks = new ArrayList<>();
    }

    private final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    void greet() {
        System.out.println(logo);
        System.out.println("Hello! I'm CinnamoRoll!" + "\n" + "What can I do for you? \n");
    }
    void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private String add(String str) {
        this.tasks.add(str);
        return "   added: " + str;
    }

    String respond(String str) {
        if (str.equals("list")) {
            String output = "";
            for(int i = 0; i < this.tasks.size(); i++) {
                output += "   " + String.valueOf(i + 1) + ". " + this.tasks.get(i) + "\n";
            }
            return output;
        } else {
            return this.add(str) + "\n";
        }
    }
}