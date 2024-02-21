package seedu.banter.ui;


/**
 * Represents a card that can be printed to the user.
 */
public class Card {
    private String message;
    private String smallSpace = "    ";
    private String bigSpace = "     ";
    private String line = "____________________________________________________________";

    /**
     * Constructs a new Card object.
     * @param message
     */
    public Card(String message) {
        this.message = message;
        indentMessageBody();
    }

    private void indentMessageBody() {
        String[] lines = message.split("\n");
        for (int i = 0; i < lines.length; i++) {
            lines[i] = bigSpace + lines[i];
        }
        message = String.join("\n", lines);
    }

    /**
     * Prints the card to the user.
     */
    public void print() {
        System.out.println(smallSpace + line);
        System.out.println(message);
        System.out.println(smallSpace + line);
    }

    /**
     * Returns a string representation of the card.
     * @return String representation of the card.
     */
    public String getString() {
        return message;
    }
}
