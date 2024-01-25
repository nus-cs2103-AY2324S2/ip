import java.util.Scanner;

public class Eve {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(" Hello! I'm Eve");
        System.out.println(" What can I do for you?");

        String input = "";

        while(!input.equals("bye")){
            input = sc.nextLine();

            if (input.equals("bye")){
                System.out.println(" Bye. Hope to see you again soon !");
            } else {
                System.out.println(input);
            }
        }

            

        sc.close();
    }
}
