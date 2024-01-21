import java.util.Scanner;

public class Shirmin {
    public static void displayLine() {
        System.out.println(" ".repeat(4) + "_".repeat(50));
    }
    public static void wrapInLines(String line){
        displayLine();
        System.out.println(line);
        displayLine();
    }
    public static void echo(String line) {
        wrapInLines(line);
    }
    public static void greet() {
        wrapInLines(" ".repeat(4) + "Hello! I'm Shirmin" + "\n"
                + " ".repeat(4)+ "What can I do for you?");
    }
    public static void exit() {
        wrapInLines(" ".repeat(4) +"Bye. Hope to see you again soon!");
    }


    static String[] list = new String[100];
    static int currIndex = 0;
    public static void addList(String line){
        if (line.equals("list")) {
            displayList(list);
        } else {
            wrapInLines(" ".repeat(4)+"added: " + line);
            list[currIndex] = line;
            currIndex++;
        }
    }
    public static void displayList(String[] list) {
        displayLine();
        int i = 1;
        for (String s: list) {
            if (s != null) {
                System.out.println(" ".repeat(4) + i + ". " + s);
                i++;
            }
        }
        displayLine();
    }

    public static void main(String[] args){
        greet();
        Scanner scanner = new Scanner(System.in);
        String currLine = scanner.nextLine();
        while(!currLine.equals("bye")) {
            addList(currLine);
            currLine = scanner.nextLine();
        }
        exit();
    }
}
