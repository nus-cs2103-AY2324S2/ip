package duke;

import java.util.Scanner;

/**
 * UI - Interactive command line user interface for task maintenance application Duke.
 */
public class UI {
    private String greeting = "Hi, I'm Lighthouse.\nHow can I help you?";
    private String separatorLine = "__________________________________________________";
    private Scanner scanner;
    private String commandOutput = "";

    /**
     * Instantiates a new Ui.
     */
    public UI(){
        scanner = new Scanner(System.in);
    }

    /**
     * Show welcome message.
     */
    public void showWelcome() {
        System.out.println(separatorLine);
        System.out.println(greeting);
        System.out.println(separatorLine);
    }

    /**
     * Sets command output.
     * Called by Command objects upon execution of commands
     *
     * @param outputtext output of the command execution
     */
    public void setCommandOutput(String outputtext) {
        this.commandOutput = outputtext;
    }

    /**
     * Returns output of the command execution.
     *
     * @return the string
     */
    public String getCommandOutput() {
        return this.commandOutput;
    }

    /**
     * Read command string entered by user.
     *
     * @return the string
     */
    public String readCommand(){
        return scanner.nextLine();
    }

    /**
     * Clear command output after command execution process is completed.
     */
    public void clearCommandOutput(){
        this.commandOutput = "";
    }

    /**
     * Shows the result of command execution to user.
     */
    public void showLine(){
        if (null != commandOutput && !"".equals(commandOutput)) {
            System.out.println(separatorLine);
            System.out.println(this.commandOutput);
            System.out.println(separatorLine);
        }
        this.clearCommandOutput();
    }

    /**
     * Show error messages to user in interactive command line ui.
     *
     * @param errMessage the err message
     */
    public void showError(String errMessage) {
        this.clearCommandOutput();
        System.out.println(separatorLine);
        System.out.println(errMessage);
        System.out.println(separatorLine);
    }
}
