import java.util.Scanner;

public class Duke {

    private void greet(){
        System.out.println("Hello! I'm Balom.\n" +
                "What can I do for you today?\n" +
                "Please start typing something.\n");
    }
    private void bye(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    private void chatting(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            String echo = scanner.nextLine();
            System.out.println(echo);
            System.out.println();

            if(echo.equals("bye") || echo.equals("Bye")){
                break;
            }
        }
        bye();
    }
    public static void main(String[] args) {
        Duke Balom = new Duke();
        Balom.greet();
        Balom.chatting();
    }
}
