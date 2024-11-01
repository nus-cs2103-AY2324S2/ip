package gpt;

class Ui {

    private String welcomeMsg = "Hello!!!! I'm  CHAD GPT\nWhat can I do for you?\n";

    /**
     * Prints the welcome message.
     */
    public void printWelcomeMsg() {
        System.out.println("Hello!!!! I'm  CHAD GPT");
        System.out.println("What can I do for you?\n");
    }

    /**
     * Returns the goodbye message.
     *
     * @return The goodbye message.
     */
    public String getByeMsg() {
        return "ByeBye. Hope to see you soon";
    }

    /**
     * Prints the goodbye message.
     */
    public void printGoodbyeMessage() {

        System.out.println("ByeBye. Hope to see you soon");
    }

    /**
     * Returns the welcome message.
     *
     * @return The welcome message.
     */
    public String getWelcomeMsg() {
        return welcomeMsg;
    }

    /**
     * Prints an error message.
     *
     * @param errorMessage The error message to be printed.
     */
    public void printErrorMessage(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }
}
