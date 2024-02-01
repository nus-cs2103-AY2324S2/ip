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
     * @param tasks the contents of the storage
     */
    public void printStorage(String storageString) {
        System.out.println("| Here's everything that I'd stored!");
        System.out.print(storageString);
    }

    /**
     * Prints the custom message upon a storing command.
     */
    public void printStoring() {
        String msg = "| Sure I'll store it right away!";
        System.out.println(msg);
    }

    /**
     * Prints the custom message upon a invalid command.
     */
    public void printInvalidCommand() {
        String msg = "| I'm not sure what that command is...";
        System.out.println(msg);
    }

    /**
     * Prints the custom message upon an echo request.
     */
    public void echo(String _input) {
        String msg = "| Echoing input \n| " + _input;
        System.out.println(msg);
    }

    /**
     * Prints the custom message when the user enters.
     */
    public void printGreeting() {
        String msg = "| Welcome! I'm JAV\n"
                   + "| How may I sprinkle a bit of happiness into your day today?";
        System.out.println(msg);
    }
    
    /**
     * Prints the custom message when the user exits.
     */
    public void printExit() {
        String msg = "| Farewell!\n"
                   + "| May your days be filled with laughter and warmth!";
        System.out.println(msg);
    }

    /**
     * Prints the custom message upon marking a task.
     */
    public void printMarkingTask() {
        String msg = "| Fantastic news!\n"
                   + "| You've just upgraded that task from a to-do to a ta-daa!";
        System.out.println(msg);
    }

    /**
     * Prints the custom message upon unmarking a task.
     */
    public void printUnmarkingTask() {
        String msg = "| Task status reversed!\n"
                   + "| Sometimes even completed tasks could use an encore.";
        System.out.println(msg);
    }

    /**
     * Prints the custom message upon deleting a task.
     */
    public void printDeletingTask() {
        String msg = "| Sure! Time to delete it from the storage!\n"
                   + "| Now, isn't that a task-tastic disappearing act?";
        System.out.println(msg);
    }

    /**
     * Prints the custom message upon a valid request but invalid parameters.
     */
    public void printInvalidParameters() {
        String msg = "| It appears the parameters might be doing a little dance of confusion!\n"
                   + "| Could you please check the parameters and give them another whirl?";
        System.out.println(msg);
    }
}