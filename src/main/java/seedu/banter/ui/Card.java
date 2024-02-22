package seedu.banter.ui;


/**
 * Represents a card that can be printed to the user.
 */
public class Card {
    private String message;

    /**
     * Constructs a new Card object.
     * @param message The message to be printed on the card.
     */
    public Card(String message) {
        this.message = message;
        indentMessageBody();
    }

    private void indentMessageBody() {
        String[] lines = message.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String bigSpace = "     ";
            lines[i] = bigSpace + lines[i];
        }
        message = String.join("\n", lines);
    }

    /**
     * Returns a string representation of the card.
     * @return String representation of the card.
     */
    public String getString() {
        return message;
    }
}
