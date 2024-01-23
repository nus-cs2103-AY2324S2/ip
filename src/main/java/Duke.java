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
                addTask(instr);
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

    public static void listOut() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < ptr; i++) {
            System.out.println(i + 1 + "."+ instrList[i].toString());
        }
    }

    public static void addTask(String instr) {
        System.out.println("Got it. I've added this task:");
        if (instr.contains("todo")) {
            Todo taskTodo = new Todo(instr.split("todo ")[1]);
            instrList[ptr++]  = taskTodo;
            System.out.println(taskTodo.toString());
        } else if (instr.contains("deadline")) {
            String[] tskNames = instr.split("deadline ")[1].split(" /by ");
            Deadline taskDeadline = new Deadline(tskNames[0], tskNames[1]); 
            instrList[ptr++]  = taskDeadline;
            System.out.println(taskDeadline.toString());
        } else if (instr.contains("event")) {
            //EVENT
            String[] instrsubString = instr.split("event ")[1].split(" /from ");
            String name = instrsubString[0]; 
            String[] startAndEnd = instrsubString[1].split(" /to "); 
            Events taskEvent = new Events(name, startAndEnd[0], startAndEnd[1]);
            instrList[ptr++]  = taskEvent;
            System.out.println(taskEvent.toString()); 
        }
        System.out.println("Now you have " + ptr + " tasks in the list."); 
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
