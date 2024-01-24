import  java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";*/
        System.out.println("Hi! This is Bit!\nWhat shall we do today?\n");
        Scanner scanner = new Scanner(System.in);

        while(true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }
            System.out.println(input);

        }


        System.out.println("Alright. See you soon!");
    }
}
