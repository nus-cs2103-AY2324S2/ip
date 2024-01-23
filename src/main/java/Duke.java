import java.util.*;
public class Duke {
    static String line = "____________________________________________________________";
    static String name = "Alfred";

    public static void separate(){
        System.out.println(line);
    }
    public static void intro() {
        separate();
        System.out.println("Hello! I'm " + name + "\nWhat can I do for you?");
        separate();
    }
    public static void leave() {
        System.out.println("Bye. Hope to see you again soon!");
        separate();
    }
    
    public static void main(String[] args) {
        intro();
        leave();
    }
}
