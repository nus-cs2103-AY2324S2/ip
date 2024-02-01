import java.util.Scanner;

/**
 * Represent the chatbot class to be used for interaction with the user
 * CS2103T
 * AY23/24 Semester 2
 * Author: Chua Zen Khoon
 */
public class Duke {


    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor for a Duke instance (Different chatbot instances for different users)
     *
     * @param  directoryPath directory for Storage to check
     * @param  fileName name of file for Storage to check
     *
     */
    public Duke(String directoryPath, String fileName){
        ui = new Ui();
        storage = new Storage(directoryPath, fileName);
        try {
            tasks = new TaskList(storage.dirAndFileSetUp());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
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
                "Event: event + task + /from yyyy-MM-dd HH:mm + /to yyyy-MM-dd HH:mm;\n" +
                "Deadline: deadline + task + /by yyyy-MM-dd HH:mm;\n" +
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
     * Chatbot's main loop: keeps getting user input until called to shut down
     */
    private void chatting(){
        System.out.println(this.greet());
        Scanner scanner = new Scanner(System.in);
        boolean isChatting = true;
        while(isChatting == true){
            //get input
            String echo = scanner.nextLine();
            try {
                //Ui grabs echo, tells parser to analyse and returns a result to Ui.
                //Ui then knows what command to execute, and passes back here for taskList + storage to execute

                int[] result = ui.analyseUserInput(echo);
                switch(result[0]) {
                    case 1:
                        isChatting = false;
                        break;
                    case 2:
                        tasks.showTasks();
                        break;
                    case 3:
                        tasks.markMechanism(result[1]);
                        break;
                    case 4:
                        tasks.unmarkMechanism(result[1]);
                        break;
                    case 5:
                        Task removed = tasks.deleteMechanism(result[1]);
                        if(result[1] <= tasks.getSize() + 1) {
                            storage.fileUpdate(removed, 1, result[1]);
                        }
                        break;
                    case 6:
                        //tell ui to parse and return task to make
                        //pass to tasklist to add the task only, no other computation needed
                        Task taskToAdd = ui.analyseTask(echo);
                        tasks.taskMechanism(taskToAdd);
                        storage.fileUpdate(taskToAdd, 0, 0);
                        break;
                }
            } catch (DukeException e){
                System.out.println(e.getMessage());
            }
        }

        System.out.println(bye());
    }

    public static void main(String[] args) {
        Duke balom = new Duke("C:/repos/cs2103t stuff/data", "duke.txt");
        balom.chatting();
    }
}
