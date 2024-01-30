package duke;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    private boolean isActive;

    public Ui() {
        this.scanner = new Scanner(System.in);
        this.isActive = true;
        this.greet();
    }

    public void sendSystemMessage(String... strs) {
        for (String s: strs) {
            System.out.println(s);
        }
    }
    private void greet() {
        this.sendSystemMessage(TextTemplate.LINE_BREAK, TextTemplate.GREETING, TextTemplate.LINE_BREAK);
    }
    public boolean isActive() {
        return this.isActive;
    }

    public void exit() {
        this.scanner.close();
        this.isActive = false;
    }

    public String readNextLine() {
        return this.scanner.nextLine().trim();
    }


}
