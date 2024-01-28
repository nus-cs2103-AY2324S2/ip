import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> instrList = new ArrayList<>();

    private enum TaskCommand {
        TODO, DEADLINE, EVENT
    }

    public static void main(String[] args) {
        System.out.println(greet());

        Scanner sc = new Scanner(System.in);

        String instr = sc.nextLine();
        while (!instr.equals("bye")) {
            if (instr.equals("list")) {
                listOut();
            } else {
                String cmdWord = instr.split(" ")[0];
                try {
                    if (cmdWord.equals("unmark")) {
                        unmark(instr);
                    } else if (cmdWord.equals("mark")) {
                        mark(instr);
                    } else if (cmdWord.equals("delete")) {
                        delete(instr);
                    } else {
                        if (cmdWord.equals("todo")) {
                            addTask(TaskCommand.TODO, instr);
                        } else if (cmdWord.equals("deadline")) {
                            addTask(TaskCommand.DEADLINE, instr);
                        } else if (cmdWord.equals("event")) {
                            addTask(TaskCommand.EVENT, instr);
                        } else {
                            throw new DukeException("OOPS!!! What is that? I'm sorry, but I don't recognise this command :-( \nTry another command!"); 
                        } 
                    }
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
        for (int i = 0; i < instrList.size() ; i++) {
            System.out.println(i + 1 + "."+ instrList.get(i).toString());
        }
    }

    public static void addTask(TaskCommand cmd, String instr) throws DukeException {
        switch (cmd) {
            case TODO: 
                try {
                    Todo taskTodo = new Todo(instr.split("todo ")[1]);
                    instrList.add(taskTodo); 
                    System.out.println("Got it. I've added this task:");
                    System.out.println(taskTodo.toString());
                } catch (ArrayIndexOutOfBoundsException e ){
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty. \nTry again!"); 
                }
                break;
            case DEADLINE: 
                try {
                    String deadlineDescription = instr.substring(9);
                    String[] tskNames = deadlineDescription.split(" /by ");
                    Deadline taskDeadline = new Deadline(tskNames[0], tskNames[1]); 
                    instrList.add(taskDeadline); 
                    System.out.println("Got it. I've added this task:");
                    System.out.println(taskDeadline.toString());
                } catch(StringIndexOutOfBoundsException e) {
                    throw new DukeException("OOPS!!! You cannot leave the description of a deadline to be empty. \nTry again!");
                } catch(ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("OOPS!!! You missed out the deadline time of this task. \nTry again!");
                }
                break;
            case EVENT: 
                try {
                    String eventDescription = instr.substring(6);
                    String[] instrsubString = eventDescription.split(" /from ");
                    String name = instrsubString[0]; 
                    String[] startAndEnd = instrsubString[1].split(" /to "); 
                    Events taskEvent = new Events(name, startAndEnd[0], startAndEnd[1]);
                    instrList.add(taskEvent); 
                    System.out.println("Got it. I've added this task:");
                    System.out.println(taskEvent.toString()); 
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("OOPS!!! You cannot leave the description of an event to be empty. \nTry again!");
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("OOPS!!! You missed out the date of this task. \nTry again!");
                }
                break;
            
            default:
            throw new DukeException("OOPS!!! What is that? I'm sorry, but I don't recognise this command :-( \nTry another command!");  
        }              
        System.out.println("Now you have " + instrList.size() + " tasks in the list."); 
    }

    public static void mark(String instr) throws DukeException {
        try {
            int instrNum = Integer.valueOf(instr.split(" ")[1]) - 1;
            String res = instrList.get(instrNum).markAsDone(); 
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
            String res = instrList.get(instrNum).markAsUndone(); 
            System.out.println("OK, I've marked this task as not done yet:" + "\n" + res); 
        } catch (NullPointerException e) {
            throw new DukeException("OOPS!! You have inputted an invalid task number. \nTry again with a different task number!"); 
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOOPS!!! You missed out the task number."); 
        }
    } 

    public static void delete(String instr) throws DukeException { 
        try {
            int ptr = Integer.valueOf(instr.split(" ")[1]) - 1; 
            Task str = instrList.get(ptr); 
            instrList.remove(ptr); 
            System.out.println("Noted. I've removed this task: \n" + str.toString() + "\nNow you have " + instrList.size() + " tasks in the list.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOOPS!!! You missed out the task number."); 
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!! You have inputted an invalid task number. \nTry again with a different task number!"); 
        } 
    }
}
