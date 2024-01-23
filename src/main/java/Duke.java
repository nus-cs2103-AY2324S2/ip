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
                try {
                    unmark(instr);
                } catch (DukeException e) {
                    System.out.println(e.toString()); 
                }
            } else if (instr.contains("mark")) {
                try {
                    mark(instr);
                } catch (DukeException e) {
                    System.out.println(e.toString()); 
                }
            } else {
                try {
                    addTask(instr);
                } catch (DukeException e) {
                    System.out.println(e.toString()); 
                }
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

    public static void addTask(String instr) throws DukeException {
        if (instr.contains("todo")) {
            try {
                Todo taskTodo = new Todo(instr.split("todo ")[1]);
                instrList[ptr++]  = taskTodo;
                System.out.println("Got it. I've added this task:");
                System.out.println(taskTodo.toString());
            } catch (ArrayIndexOutOfBoundsException e ){
                throw new DukeException("OOPS!!! The description of a todo cannot be empty. \nTry again!"); 
            }
        } else if (instr.contains("deadline")) {
            try {
                String deadlineDescription = instr.substring(9);
                String[] tskNames = deadlineDescription.split(" /by ");
                Deadline taskDeadline = new Deadline(tskNames[0], tskNames[1]); 
                instrList[ptr++]  = taskDeadline;
                System.out.println("Got it. I've added this task:");
                System.out.println(taskDeadline.toString());
            } catch(StringIndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! You cannot leave the description of a deadline to be empty. \nTry again!");
            } catch(ArrayIndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! You missed out the deadline time of this task. \nTry again!");
            }
        } else if (instr.contains("event")) {
            try {
                String eventDescription = instr.substring(6);
                String[] instrsubString = eventDescription.split(" /from ");
                String name = instrsubString[0]; 
                String[] startAndEnd = instrsubString[1].split(" /to "); 
                Events taskEvent = new Events(name, startAndEnd[0], startAndEnd[1]);
                instrList[ptr++]  = taskEvent;
                System.out.println("Got it. I've added this task:");
                System.out.println(taskEvent.toString()); 
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! You cannot leave the description of an event to be empty. \nTry again!");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! You missed out the date of this task. \nTry again!");
            }
        } else {
            throw new DukeException("OOPS!!! What is that? I'm sorry, but I don't recognise this command :-( \nTry another command!"); 
        }
        System.out.println("Now you have " + ptr + " tasks in the list."); 
    }

    public static void mark(String instr) throws DukeException {
        try {
            int instrNum = Integer.valueOf(instr.split(" ")[1]) - 1;
            String res = instrList[instrNum].markAsDone(); 
            System.out.println("Nice! I've marked this task as done:" + "\n" + res); 
        } catch (NullPointerException e) {
            throw new DukeException("OOPS!! You have inputted an invalid task number. \nTry again with a different task number!"); 
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOOPS!!! You missed out the task number."); 
        }
    }

    public static void unmark(String instr) throws DukeException {
        try {
            int instrNum = Integer.valueOf(instr.split(" ")[1]) - 1;
            String res = instrList[instrNum].markAsUndone(); 
            System.out.println("OK, I've marked this task as not done yet:" + "\n" + res); 
        } catch (NullPointerException e) {
            throw new DukeException("OOPS!! You have inputted an invalid task number. \nTry again with a different task number!"); 
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOOPS!!! You missed out the task number."); 
        }
    }
}
