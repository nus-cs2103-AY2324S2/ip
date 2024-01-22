import java.util.Scanner;

public class Jivox {
    public void greet(){
        addDivider();
        System.out.println(" Hello! I'm Jivox");
        System.out.println(" What can I do for you?");
        addDivider();
    }

    public void exit(){
        addDivider();
        System.out.println(" Bye. Hope to see you again soon!");
        addDivider();
    }

    public void addDivider(){
        System.out.println("============================================================");
    }

    public void run(){
        boolean isRunning = true;
        Scanner sc = new Scanner(System.in);
        do {
            String input = sc.nextLine();
            if(input.equals("bye")){
                isRunning = false;
            }
            else{
                addDivider();
                System.out.println(input);
                addDivider();
            }
        } while (isRunning);

    }

}
