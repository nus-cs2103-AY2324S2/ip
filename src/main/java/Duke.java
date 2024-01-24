import java.util.*;
public class Duke {
    static String line = "    ____________________________________________________________";
    static String name = "Alfred";

    public static void separate(){
        System.out.println(line);
    }
    public static void spacing() {
        System.out.println("");
    }
    public static void intro() {
        separate();
        System.out.println("    Hello! I'm " + name + "\n    What can I do for you?");
        separate();
        spacing();
    }
    public static void leave() {
        System.out.println("    Bye. Hope to see you again soon!");
        separate();
        spacing();
    }
    public static void echo() {
        intro();
        Scanner input = new Scanner(System.in);
        while(true) {
            String echoed = input.nextLine();
            if(echoed.equals("bye")) {
                leave();
                break;
            } else {
                separate();
                System.out.println("    " + echoed);
                separate();
                spacing();
            }
        }
    }

    public static void main(String[] args) {
        echo();
    }
}
