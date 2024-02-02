package Dook;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ui {

    private static final String LINE_SEPARATOR = "____________________________________________________________";

    private Scanner scanner;

    private static ArrayList<String> danceString = new ArrayList<String>(List.of("♪\n" +
            "　　　  　∧＿∧　　　♪\n" +
            "　　　 （´・ω・｀∩\n" +
            "　　 　　o　　　,ﾉ\n" +
            "　　　　Ｏ＿　.ﾉ\n" +
            "♪　　　 　 (ノ",
            "　　　 　∧＿∧　♪\n" +
            "　　　 ∩・ω・｀）\n" +
            "　　　 |　　 ⊂ﾉ\n" +
            "　　 　｜　　 _⊃　　♪\n" +
            "　　　 し ⌒,"));
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void printSeparator() {
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Prints the introduction for the bot.
     */
    public void introduce() {
        String logo = " ____              _    \n"
                + "|  _ \\  ___   ___ | | __      ╱|、\n"
                + "| | | |/ _ \\ / _ \\| |/ /    (˚ˎ 。7  \n"
                + "| |_| | |_| | |_| |   <      |、˜〵 \n"
                + "|____/ \\___/ \\___/|_|\\_\\     じしˍ,)ノ\n";
        printSeparator();
        System.out.println("Hello from Dook! :D meow\n" + logo);
        System.out.println("What can I do for you? uwu");
        printSeparator();
    }

    public void printException(Exception e) {
        System.out.println(e.getMessage());
    }

    public void println(String s) {
        System.out.println(s);
    }

    public String getInput() {
        return scanner.nextLine();
    }

    public void printDance() {
        try {
            for (int i = 0; i < 20; i++) {
                Thread.sleep(250);
                println(danceString.get(i % 2));
            }
        } catch (InterruptedException e) {
            println("Okay, I'll stop :(");
        }
    }
}
