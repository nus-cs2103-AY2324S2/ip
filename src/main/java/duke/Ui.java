package duke;

/**
 *  This class handles the logic and ui for the chatbot.
 */
public class Ui {
    /**
     * Introduces our chatbot and prompts the user to start asking questions
     * @param name is the name given to our chatbot
     * */
    public String introduce(String name) {
        String introText = "What's up. I'm " + name + "\n"
                + "I am here to help you create and manage your task list :)";
        return introText;
    }

    /**
     * Introduces our chatbot and prompts the user to start asking questions
     */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("-------------------------");
    }

}
