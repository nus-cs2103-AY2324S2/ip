package banter.ui;

public class Card {
    private String message;
    private String smallSpace = "    ";
    private String bigSpace = "     ";
    private String line = "____________________________________________________________";

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

    public void print() {
        System.out.println(smallSpace + line);
        System.out.println(message);
        System.out.println(smallSpace + line);
    }
}
