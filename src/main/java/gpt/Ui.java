package gpt;

class Ui {

    /**
     * Prints the welcome message.
     */
    public void printWelcomeMsg() {
        System.out.println("Hello!!!! I'm  CHAD GPT");
        System.out.println("What can I do for you?\n");
    }

    /**
     * Prints the goodbye message.
     */
    public void printGoodbyeMessage() {

        System.out.println("ByeBye. Hope to see you soon");
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
