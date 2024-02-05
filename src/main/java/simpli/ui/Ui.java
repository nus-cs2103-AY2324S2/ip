package simpli.ui;

import simpli.configs.SimpliConfiguration;

/**
 * Acts as the bridge between the chatbot and the user.
 */
public class Ui {
    /**
     * Prints the content to the terminal for the user to see.
     * @param content String to be printed.
     */
    public void display(String content) {
        String msg = String.format(SimpliConfiguration.PLACEHOLDER, content.replace("\n", "\n\t\t"));

        System.out.println(msg);
    }
}
