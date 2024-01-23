import java.util.Scanner;
/**
 * Represent the Chatbot class to be used for interaction with the user
 * CS2103T
 * AY23/24 Semester 2
 * Author: Chua Zen Khoon
 */
public class Duke {

    /**
     * The Chatbot uses a Task array to keep track of ongoing tasks
     */
    private Task[] tasks = new Task[100];

    /**
     * Activates once Chatbot is booted up
     * @returns a greeting message
     */
    private String greet(){
        return "Hello! I'm Balom.\n" +
                "What can I do for you today?\n" +
                "Please start typing something.\n";
    }

    /**
     * Activates once Chatbot is called to shut down
     * @return a goodbye message
     */
    private String bye(){
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints the Task array to user, directs user to input Tasks if empty
     */
    private void showTasks(){

        if(Task.currentTaskNo == 0){
            System.out.println("Add tasks to list first! Type something other than List/list or Bye/bye.");
        }

        System.out.println("Here are the tasks in your list:\n");

        for(int i = 0; i< Task.currentTaskNo; i++){
            System.out.println( Integer.toString(i+1 ) +"." + tasks[i].toString());
        }
    }

    /**
     * Marks/Unmarks a Task in the Task array as requested by the user
     * @param echo string to be assessed and operated on
     */
    private void markMechanism(String echo){
        if (echo.contains("unmark")){
            int value = Integer.parseInt(echo.replaceAll("[^-0-9]", ""));
            if(value <= Task.currentTaskNo && value > 0){
                System.out.println(tasks[value-1].unMarkTask());
            } else {
                System.out.println("Please unmark a valid task!\n");
            }
        } else if (echo.contains("mark")){
            int value = Integer.parseInt(echo.replaceAll("[^-0-9]", ""));
            if(value <= Task.currentTaskNo && value > 0){
                System.out.println(tasks[value-1].markAsDone());
            } else {
                System.out.println("Please mark a valid task!\n");
            }
        }
    }

    /**
     * Creates a Task in the Task array as requested by the user
     * @param echo string to be assessed and operated on
     */
    private void taskMechanism(String echo){
        if(echo.contains("deadline")) {
            String echo1[] = echo.split("deadline", 2);
            String deadline[] = echo1[1].split("/by", 2);
            tasks[Task.currentTaskNo] = new Deadline(deadline[0], deadline[1]);
        } else if (echo.contains("event")){
            String echo1[] = echo.split("event", 2);
            String event[] = echo1[1].split("/", 3);
            tasks[Task.currentTaskNo] = new Event(event[0], event[1], event[2]);
        } else if (echo.contains("todo")) {
            String todo[] = echo.split("todo", 2);
            tasks[Task.currentTaskNo] = new Todo(todo[1]);
        } else {
            System.out.println("Please enter a todo, deadline or event with the relevant details!\n" +
                    "Todo: todo + task ; \n" +
                    "Event: event + task + /from... + /to... ; \n" +
                    "Deadline: deadline + task + /by...");
            return;
        }

        System.out.println("Understood. I've added this task:\n "
                + tasks[Task.currentTaskNo-1].toString()
                + "\nNow you have " + Integer.toString(Task.currentTaskNo)
                + " task(s) in the list.");
    }

    /**
     * Chatbot's main loop: keeps getting user input until called to shut down
     */
    private void chatting(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            //get input
            String echo = scanner.nextLine();

            //catches bye, or list first then unmark/mark, else is user input into task array
            if(echo.equals("bye") || echo.equals("Bye")){
                break;
            } else if (echo.equals("list")|| echo.equals("List")) {
                this.showTasks();
            } else if (echo.contains("unmark") || echo.contains("mark")){
                this.markMechanism(echo);
            } else {
                this.taskMechanism(echo);
            }
        }

        System.out.println(bye());
    }
    public static void main(String[] args) {
        Duke Balom = new Duke();
        System.out.println(Balom.greet());
        Balom.chatting();
    }
}
