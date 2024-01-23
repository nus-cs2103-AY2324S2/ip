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
     * @return a greeting message
     */
    private String greet(){
        return "Hello! I'm Balom.\nWhat can I do for you today?\n\n" +
                "---Start by entering a todo, deadline or event with the relevant details!\n" +
                "Todo: todo + task ;\n" +
                "Event: event + task + /from... + /to... ;\n" +
                "Deadline: deadline + task + /by...;\n" +
                "View the task list with List/list, or close the chat with Bye/bye!---\n";
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
    private void showTasks() throws DukeException{

        if(Task.currentTaskNo == 0){
            throw new DukeException("Add tasks to list first! Type something other than List/list or Bye/bye.\n");
        } else {
            System.out.println("Here are the tasks in your list:\n");

            for (int i = 0; i < Task.currentTaskNo; i++) {
                System.out.println((i + 1) + "." + tasks[i].toString());
            }
            System.out.println();
        }
    }

    /**
     * Marks/Unmarks a Task in the Task array as requested by the user
     * @param echo string to be assessed and operated on
     */
    private void markMechanism(String echo) throws DukeException{
        if (echo.contains("unmark")){
            int value = Integer.parseInt(echo.replaceAll("[^-0-9]", ""));
            if(value <= Task.currentTaskNo && value > 0){
                System.out.println(tasks[value-1].unMarkTask());
            } else {
                throw new DukeException("Please unmark a valid task!\n");
            }
        } else if (echo.contains("mark")){
            int value = Integer.parseInt(echo.replaceAll("[^-0-9]", ""));
            if(value <= Task.currentTaskNo && value > 0){
                System.out.println(tasks[value-1].markAsDone());
            } else {
                throw new DukeException("Please mark a valid task!\n");
            }
        }
    }

    /**
     * Creates a Task in the Task array as requested by the user
     * @param echo string to be assessed and operated on
     */
    private void taskMechanism(String echo) throws DukeException, ArrayIndexOutOfBoundsException{
        try {
            String keyword = echo.split(" ")[0];
            if (keyword.equals("deadline")) {
                String echo1[] = echo.split("deadline", 2);
                String deadline[] = echo1[1].split("/by", 2);
                if((deadline[0]).matches("\\s+") || ((deadline[1]).matches("\\s+"))
                    ||(deadline[1].equals(""))){
                    throw new DukeException("Empty task fields where applicable are not allowed.\n");
                } else {
                    tasks[Task.currentTaskNo] = new Deadline(deadline[0], deadline[1]);
                }
            } else if (keyword.equals("event")) {
                String echo1[] = echo.split("event", 2);
                String event[] = echo1[1].split("/from", 2);
                String event1[] = event[1].split("/to", 2);

                if(((event[0]).matches("\\s+")) || (event1[0].matches("\\s+"))
                    || (event1[1].matches("\\s+")) || (event1[1].matches(""))){
                    throw new DukeException("Empty task fields where applicable are not allowed.\n");
                } else {
                    tasks[Task.currentTaskNo] = new Event(event[0], event1[0], event1[1]);
                }
            } else if (keyword.equals("todo")) {
                String todo[] = echo.split("todo", 2);
                //test if empty task
                if((todo[1]).matches("\\s+") ||(todo[1]).equals("")){
                    throw new DukeException("Empty task fields where applicable are not allowed.\n");
                } else {
                    tasks[Task.currentTaskNo] = new Todo(todo[1]);
                }
            } else {
                throw new DukeException("Error.Please enter a todo, deadline or event with the relevant details!\n" +
                        "Todo: todo + task ;\n" +
                        "Event: event + task + /from... + /to... ;\n" +
                        "Deadline: deadline + task + /by...;\n");
            }

            System.out.println("Understood. I've added this task:\n "
                    + tasks[Task.currentTaskNo-1].toString()
                    + "\nNow you have " + Task.currentTaskNo
                    + " task(s) in the list.\n");

        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Enter a todo, deadline or event with the relevant details!\n" +
                    "Todo: todo + task ; \n" +
                    "Event: event + task + /from... + /to... ; \n" +
                    "Deadline: deadline + task + /by...;\n");
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
            try {
                //catches bye, or list first then unmark/mark, else is user input into task array
                if (echo.equals("bye") || echo.equals("Bye")) {
                    break;
                } else if (echo.equals("list") || echo.equals("List")) {
                    this.showTasks();
                } else if (echo.contains("unmark") || echo.contains("mark")) {
                    this.markMechanism(echo);
                } else {
                    this.taskMechanism(echo);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
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
