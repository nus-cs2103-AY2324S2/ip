import java.util.*;
public class Duke {
    static String line = "    ____________________________________________________________";
    static String name = "Alfred";

    public static void separate(){
        System.out.println(line);
    }
    public static void spacing() {
        separate();
        System.out.println("");
    }

    public static void intro() {
        separate();
        System.out.println("     Hello! I'm " + name + "\n     What can I do for you?");
        spacing();
    }
    public static void leave() {
        System.out.println("     Bye. Hope to see you again soon!");
        spacing();
    }
    public static void echo() {
        intro();
        Scanner input = new Scanner(System.in);

        while(true) {
            String echoed = input.nextLine();
            if(echoed.equals("bye")) {
                separate();
                leave();
                break;
            } else {
                separate();
                System.out.println("     " + echoed);
                spacing();
            }
        }
    }

    public static void main(String[] args) {
        echo();
    }
}
