import java.util.Scanner;

public class Duke {
    private static Task[] instrList = new Task[100]; 
    private static int ptr = 0; 

    public static void main(String[] args) {
        System.out.println(greet());

        Scanner sc = new Scanner(System.in);

        String instr = sc.nextLine();
        while (!instr.equals("bye")) {
            if (instr.equals("list")) {
                listOut();
            } else if (instr.contains("unmark")) {
                unmark(instr);
            } else if (instr.contains("mark")) {
                mark(instr);
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
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < ptr; i++) {
            System.out.println(i + 1 + "."+ instrList[i].toString());
        }
    }

    public static void addText(String instr) {
        instrList[ptr++] = new Task(instr);
    }

    public static void mark(String instr) {
        int instrNum = Integer.valueOf(instr.split(" ")[1]) - 1;
        System.out.println("Nice! I've marked this task as done:"); 
        instrList[instrNum].markAsDone(); 
    }

    public static void unmark(String instr) {
        int instrNum = Integer.valueOf(instr.split(" ")[1]) - 1;
        System.out.println("OK, I've marked this task as not done yet:"); 
        instrList[instrNum].markAsUndone(); 
    }
}
