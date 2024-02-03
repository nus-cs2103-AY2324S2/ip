package util;

import java.util.Scanner;

public class TextUi {
    private Scanner textReader;

    public TextUi() {
        this.textReader = new Scanner(System.in);
    }

    public String readNextLine() {
        return this.textReader.nextLine();
    }

    public void printMessage(String messages) {
        System.out.println(messages);
        System.out.println(Messages.MESSAGE_END_LINE);
    }

    public void closeReader() {
        this.textReader.close();
    }
}
