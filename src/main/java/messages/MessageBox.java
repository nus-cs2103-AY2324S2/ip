package messages;

import java.util.ArrayList;

class MessageBox {  // default access modifier
    private String message;
    private String smallSpace = "    ";
    private String bigSpace = "     ";
    private String line = "____________________________________________________________";

    MessageBox(String message) {  // default access modifier
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

    void print() {  // default access modifier
        System.out.println(smallSpace + line);
        System.out.println(message);
        System.out.println(smallSpace + line);
    }
}
