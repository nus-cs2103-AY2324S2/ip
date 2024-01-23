import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println(greet());

        Scanner sc = new Scanner(System.in);

        String instr = sc.nextLine();
        while (!instr.equals("bye")) {
            System.out.println(echo(instr)); 
            instr = sc.nextLine();
        }

        System.out.println(exit());

        sc.close();
    }

    public static String greet() {
        return "Hello! I'm YLEXI. \nWhat can I do for you?"; 
    }

    public static String exit() {
        return "Bye. Hope to see you again soon!";
    }

    public static String echo(String instr) {
        return instr;
    }
}
