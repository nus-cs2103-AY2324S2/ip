import java.util.Scanner;
public class Duke {

    private static String name = "GanAnWo";

    private static Storage storage = new Storage();
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        System.out.println("Hello! I'm " + name + "\n"
                + "What can I do for you?\n");
        String input;
        Boolean running = true;

        while (running){
            input = inp.nextLine();
            if(input.equals("bye")){
                running = false;
                break;
            } else if (input.equals("list")) {
                storage.showItem();
            } else {
                storage.addItem(input);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");

    }
}
