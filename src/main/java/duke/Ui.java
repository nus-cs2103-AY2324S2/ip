package duke;

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
        int[] results = new int[] {0,0};

        if (echo.equals("bye") || echo.equals("Bye")) {
            results[0] = 1;
        } else if (echo.equals("list") || echo.equals("List")) {
            results[0] = 2;
        } else if (echo.matches("mark -?[0-9]*")) {
            results[0] = 3;
            results[1] = parser.digOutInt(echo);
        } else if (echo.matches("unmark -?[0-9]*")) {
            results[0] = 4;
            results[1] = parser.digOutInt(echo);
        } else if (echo.matches("delete -?[0-9]*")){
            results[0] = 5;
            results[1] = parser.digOutInt(echo);
        } else if (echo.matches("find [a-zA-Z0-9]*")) {
            results[0] = 6;
        } else {
            results[0] = 7;
        }

        return results;
    }



    public String analyseFind(String echo) throws DukeException {
        return parser.digOutSearch(echo);
    }
    /**
     * Converts the input of the user into a Task to be added to the taskList
     *
     * @param echo input to be analysed
     * @return a Task to be added later on
     * @throws DukeException when user input is invalid
     */
    public Task analyseTask(String echo) throws DukeException {
        Task taskAdded = new Task("Error. Unable to retrieve Task.");
        String[] results = parser.decryptInput(echo);

        if(results[1] == null && results[2] == null) {
            taskAdded = new Todo(results[0]);
        } else if (results[2] == null) {
            taskAdded = new Deadline(results[0], results[1]);
        } else {
            taskAdded = new Event(results[0], results[1], results[2]);
        }

        return taskAdded;
    }
}
