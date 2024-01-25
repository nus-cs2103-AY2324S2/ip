import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Area.\n" +
                "What can I do for you?\n"
        );

        Scanner sc = new Scanner(System.in);
        while(true){
            String instruction = sc.nextLine();
            if(instruction.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }else{
                System.out.println(instruction);
            }
        }
    }
}
