import java.util.Scanner;

public class Duke {
    private static String[] instrList = new String[100]; 
    private static int ptr = 0; 

    public static void main(String[] args) {
        System.out.println(greet());

        Scanner sc = new Scanner(System.in);

        String instr = sc.nextLine();
        while (!instr.equals("bye")) {
            if (instr.equals("list")) {
                listOut();
            } else {
                addText(instr);
                System.out.println(echo(instr)); 
            }
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
        return "added: " + instr;
    }

    public static void listOut() {
        for (int i = 0; i < ptr; i++) {
            System.out.println(i + 1 + ". " + instrList[i]);
        }
    }

    public static void addText(String instr) {
        instrList[ptr++] = instr;
    }
}
