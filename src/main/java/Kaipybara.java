import java.util.*;
import java.io.*;

/**
 * Encapsulate a chatbot names kaipybara that takes in input from the user and perform tasks such as creating a todo list.
 *
 * @author: CHEN WENLONG
 * @version: CS2103T AY23/24 Semester 2
 */
public class Kaipybara {
    /** A String to separate user input and system output. */
    private static final String LINE = "____________________________________________________________\n";

    /** A String to print when program starts running. */
    private static final String START = "Hey, nice to meet you! I am your personal chatbot:\n\n"
            + "          /'  _/                                /'                           \n"
            + "        /' _/~                                /'                             \n"
            + "     ,/'_/~  ____     O   ____              /'__     ____      ____     ____ \n"
            + "    /\\/~   /'    )  /'  /'    )--/'    /  /'    )  /'    )   )'    )--/'    )\n"
            + "  /'  \\  /'    /' /'  /'    /' /'    /' /'    /' /'    /'  /'       /'    /' \n"
            + "/'     \\(___,/(__(__/(___,/'  (___,/(__(___,/(__(___,/(__/'        (___,/(__ \n"
            + "                  /'             /'                                          \n"
            + "                /'       /     /'                                            \n"
            + "              /'        (___,/'                                              \n\n"
            + "What can I do for you today? :)\n";

    /** A String to print when program stops running. */
    private static final String END = "See you later alligator!\n";

    /** ArrayList<Task> to store all the Tasks the user has created. */
    private static final ArrayList<Task> TASKS=new ArrayList<>();

    /**
     * Stores the information in String into a Task and adds into the ArrayList
     * @param info The information from userInput
     */
    private static void store(String info){
        int id = TASKS.size()+1;
        TASKS.add(new Task(info));
    }

    /**
     * Prints the Task List, labels them with numbers
     */
    private static void printTasks(){
        System.out.println("Here are the tasks in your list:");
        for (int i=0;i<TASKS.size();i++){
            System.out.print((i+1)+".");
            System.out.println(TASKS.get(i));
        }
    }

    /**
     * Marks a Task as completed.
     * @param index Index of Task to be marked in the TASKS ArrayList.
     */
    private static void mark(int index){
        Task t = TASKS.get(index);
        t.setCompleted();
        System.out.println("Nice! I've marked this task as done:\n"+t);
        return;
    }

    /**
     * Marks Task as not completed.
     * @param index Index of Task to be marked in the TASKS ArrayList.
     */
    private static void unmark(int index){
        Task t = TASKS.get(index);
        t.setNotCompleted();
        System.out.println("OK, I've marked this task as not done yet:\n"+t);
        return;
    }
    /**
     * Simulate what goes on in the chatbot.
     */
    private static void simulate() {
        Scanner scanner = new Scanner(System.in); // read inputs from user
        String userInput="";
        while(true) {
            userInput = scanner.nextLine();
            String[] input = userInput.split(" ");
            String ins = input[0];
            System.out.print(LINE);
            // stops the program
            if(ins.equals("bye")) {
                break;
            }
            if (ins.equals("list")) { // List the tasks
                printTasks();
            } else if (ins.equals("mark")) { // Mark task as completed
                int index = Integer.parseInt(input[1]);
                mark(index-1);
            } else if (ins.equals("unmark")) { // Mark task as not completed
                int index = Integer.parseInt(input[1]);
                unmark(index-1);
            } else { // Adds Task to Task List
                store(userInput);
                System.out.println("added: " + userInput);
            }
            System.out.print(LINE);
        };
    }
    public static void main(String[] args){
        System.out.println(START); // opening statement
        simulate(); // simulate kaipybara chatbot
        System.out.println(END + LINE); // closing statement
    }
}
