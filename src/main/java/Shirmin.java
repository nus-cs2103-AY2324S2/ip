import java.util.Scanner;
public class Shirmin {

    public static void displayLine() {
        System.out.println("_".repeat(50));
    }
    public static void echo(String line) {
        displayLine();
        System.out.println(line);
        displayLine();
    }
    public static void greet() {
        displayLine();
        System.out.println("Hello! I'm Shirmin" + "\n" + "What can I do for you?");
        displayLine();
    }
    public static void exit() {
        displayLine();
        System.out.println("Bye. Hope to see you again soon!");
        displayLine();
    }
    public static void main(String[] args){
        greet();
        Scanner scanner = new Scanner(System.in);
        String curr = scanner.nextLine();
        while(!curr.equals("bye")) {
            echo(curr);
            curr = scanner.nextLine();
        }
        exit();
    }
}
