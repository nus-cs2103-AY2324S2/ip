package jav.manager;

/**
* UiManager outputs custom messages based on the given actions.
*/
public class UiManager {
    // Singleton pattern but lazy loaded from wiki https://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom
    // Wanted a singleton pattern and this seemed the best.
    private UiManager() {}
    private static class LazyHolder {
        static final UiManager INSTANCE = new UiManager();
    }
    public static UiManager getInstance() {
        return LazyHolder.INSTANCE;
    }

    /**
     * Prints the custom message along with the contents of the storage.
     *
     * @param storageString the contents of the storage
     * @return String response upon recieving a list command.
     */
    public String printStorage(String storageString) {
        String msg = "Here's everything that I'd stored!\n" + storageString;
        return msg;
    }

    /**
     * Prints the custom message upon a storing command.
     *
     * @return String response upon recieving a storing command.
     */
    public String printStoring() {
        String msg = "Sure I'll store it right away!";
        return msg;
    }

    /**
     * Prints the custom message upon a invalid command.
     *
     * @return String response upon recieving an invalid command.
     */
    public String printInvalidCommand() {
        String msg = "I'm not sure what that command is...";
        return msg;
    }

    /**
     * Prints the custom message upon an echo request.
     *
     * @param input the user's input.
     * @return String of the given input.
     */
    public String echo(String input) {
        String msg = "Echoing input \n" + input;
        return msg;
    }

    /**
     * Prints the custom message when the user enters.
     *
     * @return String for when the bot is greeting the user.
     */
    public String printGreeting() {
        String msg = "Welcome! I'm JAV\n"
                   + "How may I sprinkle a bit of happiness into your day today?";
        return msg;
    }

    /**
     * Prints the custom message when the user exits.
     *
     * @return String for when the user exits.
     */
    public String printExit() {
        String msg = "Farewell!\n"
                   + "May your days be filled with laughter and warmth!";
        return msg;
    }

    /**
     * Prints the custom message upon marking a task.
     *
     * @return String response upon recieving a mark request.
     */
    public String printMarkingTask() {
        String msg = "Fantastic news!\n"
                   + "You've just upgraded that task from a to-do to a ta-daa!";
        return msg;
    }

    /**
     * Prints the custom message upon unmarking a task.
     *
     * @return String response upon recieving an unmark request.
     */
    public String printUnmarkingTask() {
        String msg = "Task status reversed!\n"
                   + "Sometimes even completed tasks could use an encore.";
        return msg;
    }

    /**
     * Prints the custom message upon deleting a task.
     *
     * @return String response upon recieving a delete request.
     */
    public String printDeletingTask() {
        String msg = "Sure! Time to delete it from the storage!\n"
                   + "Now, isn't that a task-tastic disappearing act?";
        return msg;
    }

    /**
     * Prints the custom message upon a valid request but invalid parameters.
     *
     * @return String response upon recieving invalid parameters for a request.
     */
    public String printInvalidParameters() {
        String msg = "It appears the parameters might be doing a little dance of confusion!\n"
                   + "Could you please check the parameters and give them another whirl?";
        return msg;
    }

    /**
     * Prints the custom message upon undoing.
     *
     * @return String response upon completing undo request.
     */
    public String printUndo() {
        String msg = "Yep, between you and me, that never happened.";
        return msg;
    }

    /**
     * Prints the custom message upon finding tasks with the given keyword.
     *
     * @param foundTasksString the tasks with the given keyword.
     * @return String response upon recieving a find request.
     */
    public String printFindTask(String foundTasksString) {
        String msg = "";
        if (foundTasksString == "") {
            msg = "It appears that this road is a deadend and no tasks were found with the keyword!\n"
                + "Shall we recalibrate our keyword quest to find a different task?";
        } else {
            msg = "Let the search commence!\n"
                + "Here's what I've found:\n"
                + foundTasksString;
        }

        return msg;
    }
}
