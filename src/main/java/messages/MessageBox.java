package messages;

import java.util.ArrayList;

class MessageBox {
    private ArrayList<String> messages;
    private String smallSpace = "    ";
    private String bigSpace = "     ";
    private String line = "____________________________________________________________";

    MessageBox(ArrayList<String> messages) {  // default access modifier
        this.messages = messages;
    }

    void print() {  // default access modifier
        System.out.println(smallSpace + line);
        for (String message : messages) {
            System.out.println(bigSpace + message);
        }
        System.out.println(smallSpace + line);
    }
}
