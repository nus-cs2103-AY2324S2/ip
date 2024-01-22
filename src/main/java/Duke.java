import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        String logo = " ____              _    \n"
                + "|  _ \\  ___   ___ | | __      ╱|、\n"
                + "| | | |/ _ \\ / _ \\| |/ /    (˚ˎ 。7  \n"
                + "| |_| | |_| | |_| |   <      |、˜〵 \n"
                + "|____/ \\___/ \\___/|_|\\_\\     じしˍ,)ノ\n";
        System.out.println(line);
        System.out.println("Hello from Dook! :D meow\n" + logo);
        System.out.println("What can I do for you? uwu");
        System.out.println(line + "\n");
        Scanner sc = new Scanner(System.in);
        String input = "";
        while (true) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            } else {
                System.out.println(input);
                System.out.println(line);
            }
        }
        System.out.println("Bye :(. Hope to see you again soon! ≽^- ˕ -^≼");
        System.out.println(line);
    }
}
