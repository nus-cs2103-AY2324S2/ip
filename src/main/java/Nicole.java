import UserRequests.Ui;

import java.io.IOException;

public class Nicole {

    /**
     * Initialises the chatbot and triggers user interactions
     *
     */
    public Nicole() {
        try {
            System.out.println(new Ui());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new Nicole();
    }
}
