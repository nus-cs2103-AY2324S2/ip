import java.util.ArrayList;

/**
 * Represent the class that deals with interactions with the user
 * CS2103T
 * AY23/24 Semester 2
 * Author: Chua Zen Khoon
 */
public class Ui {

    private Parser parser;
    public Ui(){
        parser = new Parser();
    }


    public void showLoadingError(DukeException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Analyses the input of the user and parses out the relevant details
     * for the correct action to be taken in the main Duke class
     *
     * @param echo input to be analysed
     * @return an integer array with the correct action and number in the TaskList
     *          to operate on
     */
    public int[] analyseUserInput(String echo) {
        int[] result = new int[] {0,0};

        if (echo.equals("bye") || echo.equals("Bye")) {
            result[0] = 1;
        } else if (echo.equals("list") || echo.equals("List")) {
            result[0] = 2;
        } else if (echo.matches("mark -?[0-9]*")) {
            result[0] = 3;
            result[1] = parser.digOutInt(echo);
        } else if (echo.matches("unmark -?[0-9]*")) {
            result[0] = 4;
            result[1] = parser.digOutInt(echo);
        } else if (echo.matches("delete -?[0-9]*")){
            result[0] = 5;
            result[1] = parser.digOutInt(echo);
        } else {
            result[0] = 6;
        }
        return result;
    }

    /**
     * Converts the input of the user into a Task to be added to the taskList
     *
     * @param echo input to be analysed
     * @return a Task to be added later on
     */
    public Task analyseTask(String echo) throws DukeException {
        Task taskAdded = new Task("Error. Unable to retrieve Task.");
        String[] result = parser.decryptInput(echo);

        if(result[1] == null && result[2] == null){
            taskAdded = new Todo(result[0]);
        } else if (result[2] == null){
            taskAdded = new Deadline(result[0], result[1]);
        } else {
            taskAdded = new Event(result[0], result[1], result[2]);
        }
        return taskAdded;
    }
}
