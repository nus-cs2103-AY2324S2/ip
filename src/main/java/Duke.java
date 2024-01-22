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
     */
    private void greet(){
        System.out.println("Hello! I'm Balom.\n" +
                "What can I do for you today?\n" +
                "Please start typing something.\n");
    }

    /**
     * Activates once Chatbot is called to shut down
     */
    private void bye(){
        System.out.println("Bye. Hope to see you again soon!");
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
            tasks[i].callTask();
        }
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
                showTasks();
            } else if (echo.contains("unmark")){
                int value = Integer.parseInt(echo.replaceAll("[^-0-9]", ""));
                if(value <= Task.currentTaskNo && value > 0){
                    tasks[value-1].unMarkTask();
                } else {
                    System.out.println("Please unmark a valid task!\n");
                }
            } else if (echo.contains("mark")){
                int value = Integer.parseInt(echo.replaceAll("[^-0-9]", ""));
                if(value <= Task.currentTaskNo && value > 0){
                    tasks[value-1].markAsDone();
                } else {
                    System.out.println("Please mark a valid task!\n");
                }
            } else {
                // add to tasks
                tasks[Task.currentTaskNo] = new Task(echo);
                System.out.println("added " + echo);
                System.out.println();
            }
        }

        bye();
    }
    public static void main(String[] args) {
        Duke Balom = new Duke();
        Balom.greet();
        Balom.chatting();
    }
}
