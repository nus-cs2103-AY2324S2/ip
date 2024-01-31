import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.util.ArrayList;
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
    private ArrayList<Task> tasks;

    /**
     * Constructor for a Duke instance (Different chatbot instances for different users)
     */
    public Duke(){
        tasks = new ArrayList<>();
    }


    public void dirAndFileSetUp(String directoryPath){
        String fileToCheck = "duke.txt";

        File directory = new File(directoryPath);
        if (!directory.exists()){
            directory.mkdir();
        }

        File file = new File(directoryPath + "/" + fileToCheck);
        boolean hasRecords = true;
        try {
            hasRecords = file.createNewFile();
        } catch(IOException e){
            System.out.println("There seems to be previous records. Loading...");
        }
        if(hasRecords == false){
            System.out.println("Previous records loaded.\n");
            try {
                Scanner scanner = new Scanner(file);
                while(scanner.hasNextLine()){
                    transferFileContent(scanner.nextLine());
                }
            } catch (FileNotFoundException e){
                System.out.println("Error.");
            } catch (DukeException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void transferFileContent (String line) throws DukeException{
        String taskComponents[] = line.split("@");
        Task taskAdded = new Task("Error. Unable to retrieve Task.");
        if(taskComponents.length == 3){
            taskAdded = new Todo(taskComponents[2]);

        } else if (taskComponents.length == 4 ){
            taskAdded = new Deadline(taskComponents[2],taskComponents[3]);

        } else if (taskComponents.length == 5 ) {
            taskAdded = new Event(taskComponents[2], taskComponents[3], taskComponents[4]);

        }
        tasks.add(taskAdded);
        if(taskComponents[1].equals("1")){
            taskAdded.markAsDone();
        }
    }

    public void fileUpdate(Task taskToUpdate, int command, int lineNum) {
        String taskString = taskToUpdate.toString(true);
        try {
            if(command == 0) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(
                            "C:/repos/cs2103t stuff/data/duke.txt",true ));
                File file = new File("C:/repos/cs2103t stuff/data/duke.txt");
                if(file.length() != 0){
                    writer.newLine();
                }
                writer.write(taskString);
                writer.close();
            } else {
                File inputFile = new File("C:/repos/cs2103t stuff/data/duke.txt");
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                String line;
                StringBuilder sb = new StringBuilder();
                int lineNumCount = 1;
                while((line = reader.readLine()) != null) {
                    if ((lineNumCount == lineNum)) {
                        sb.append("");
                    } else {
                        sb.append(line);
                        sb.append("\n");
                    }
                    lineNumCount++;
                }
                sb.deleteCharAt(sb.lastIndexOf("\n"));
                BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
                writer.write(sb.toString());
                writer.close();
                reader.close();
            }
        } catch( IOException e){
            System.out.println();
        }
    }


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
                "View the task list with List/list, or close the chat with Bye/bye!\n" +
                "Mark/Unmark a task in the list with mark (number) or unmark (number)\n" +
                "Delete a task in the list with delete (number)\n" +
                "Records will be remembered if you close me and reopen me!---\n";
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

        if(Task.currentTaskNum == 0){
            throw new DukeException("Add tasks to list first! Type something other than List/list or Bye/bye.\n");
        } else {
            System.out.println("Here are the tasks in your list:\n");

            for (int i = 0; i < Task.currentTaskNum; i++) {
                System.out.println(i+1 + "." + tasks.get(i).toString());
            }
            System.out.println();
        }
    }

    /**
     * Marks/Unmarks a Task in the Task Arraylist as requested by the user
     * @param echo string to be assessed and operated on
     */
    private void markMechanism(String echo) throws DukeException{
        if (echo.contains("unmark")){
            int value = Integer.parseInt(echo.replaceAll("[^-0-9]", ""));
            if(value <= Task.currentTaskNum && value > 0){
                System.out.println(tasks.get(value-1).unMarkTask());
            } else {
                throw new DukeException("Please unmark a valid task!\n");
            }
        } else if (echo.contains("mark")){
            int value = Integer.parseInt(echo.replaceAll("[^-0-9]", ""));
            if(value <= Task.currentTaskNum && value > 0){
                System.out.println(tasks.get(value-1).markAsDone());
            } else {
                throw new DukeException("Please mark a valid task!\n");
            }
        }
    }

    /**
     * Creates a Task in the Task Arraylist as requested by the user
     * @param echo string to be assessed and operated on
     */
    private Task taskMechanism(String echo) throws DukeException {
        Task taskAdded = new Task("Error. Unable to retrieve Task.");
        try {
            String keyword = echo.split(" ")[0];
            if (keyword.equals("deadline")) {
                String echo1[] = echo.split("deadline", 2);
                String deadline[] = echo1[1].split("/by", 2);
                if((deadline[0]).matches("\\s+") || ((deadline[1]).matches("\\s+"))
                    ||(deadline[1].equals(""))){
                    throw new DukeException("Empty task fields where applicable are not allowed.\n");
                } else {
                    taskAdded = new Deadline(deadline[0], deadline[1]);

                }
            } else if (keyword.equals("event")) {
                String echo1[] = echo.split("event", 2);
                String event[] = echo1[1].split("/from", 2);
                String event1[] = event[1].split("/to", 2);

                if(((event[0]).matches("\\s+")) || (event1[0].matches("\\s+"))
                    || (event1[1].matches("\\s+")) || (event1[1].matches(""))){
                    throw new DukeException("Empty task fields where applicable are not allowed.\n");
                } else {
                    taskAdded = new Event(event[0], event1[0], event1[1]);
                }
            } else if (keyword.equals("todo")) {
                String todo[] = echo.split("todo", 2);
                //test if empty task
                if((todo[1]).matches("\\s+") ||(todo[1]).equals("")){
                    throw new DukeException("Empty task fields where applicable are not allowed.\n");
                } else {
                    taskAdded = new Todo(todo[1]);
                }
            } else {
                throw new DukeException("Error.Please enter a todo, deadline or event with the relevant details!\n" +
                        "Todo: todo + task ;\n" +
                        "Event: event + task + /from... + /to... ;\n" +
                        "Deadline: deadline + task + /by...;\n");
            }

            tasks.add(taskAdded);

            System.out.println("Understood. I've added this task:\n "
                    + Task.currentTaskNum + "."
                    + tasks.get(Task.currentTaskNum - 1)
                    + "\nNow you have " + Task.currentTaskNum
                    + " task(s) in the list.\n");

        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Enter a todo, deadline or event with the relevant details!\n" +
                    "Todo: todo + task ; \n" +
                    "Event: event + task + /from... + /to... ; \n" +
                    "Deadline: deadline + task + /by...;\n");
        }

        return taskAdded;
    }

    /**
     * Delete a Task in the Task Arraylist as requested by the user
     * @param echo string to be assessed and operated on
     */
    private Task deleteMechanism(String echo) {
        String echo1[] = echo.split("delete ", 2);
        int numberToRemove = Integer.parseInt(echo1[1]);
        Task removed = new Task("Task to be deleted");
        try {
            removed = tasks.remove(numberToRemove - 1);
            Task.currentTaskNum--;
            System.out.println("Very well. I have removed this task.\n" + removed
                    + "\nNow you have " + Task.currentTaskNum
                    + " task(s) in the list.\n");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There are only: " + Task.currentTaskNum + " task(s) in the list to delete.\n");
        }
        return removed;
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
                } else if (echo.matches("unmark -?[0-9]*") || echo.matches("mark -?[0-9]*")) {
                    this.markMechanism(echo);
                } else if (echo.matches("delete -?[0-9]*")){
                    String echo1[] = echo.split("delete ", 2);
                    int numberToRemove = Integer.parseInt(echo1[1]);
                    Task removed = this.deleteMechanism(echo);
                    if(numberToRemove <= Task.currentTaskNum + 1) {
                        fileUpdate(removed, 1, numberToRemove);
                    }
                } else {
                    Task added = this.taskMechanism(echo);
                    fileUpdate(added, 0, 0);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println(bye());
    }
    public static void main(String[] args) {
        Duke balom = new Duke();
        balom.dirAndFileSetUp("C:/repos/cs2103t stuff/data");
        System.out.println(balom.greet());
        balom.chatting();
    }
}
