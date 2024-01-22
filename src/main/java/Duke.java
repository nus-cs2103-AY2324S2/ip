import java.util.Scanner;
public class Duke {

    private static String name = "GanAnWo";
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm " + name + "\n"
                + "What can I do for you?\n");
        String input;
        Boolean running = true;

        while (running){
            input = inp.nextLine();
            if(input.equals("bye")){
                running = false;
                break;
            }
            String ans = response(input);
            System.out.println(ans);
        }
        System.out.println("Bye. Hope to see you again soon!");

    }

    private static String response(String input){
        return input;
    }
}
