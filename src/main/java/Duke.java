import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " KASSIM ";
        System.out.println("YOO I AM " + logo);
        System.out.println("WHat can i do for you?");

        ArrayList<String> myList = new ArrayList<>();
        int count = 1;

        Scanner sc = new Scanner(System.in);
        System.out.print(" ");
        String in = sc.nextLine();

        while(!in.equals("bye")) {

            if (!in.equals("list")) {
                myList.add(count + "." + in);
                System.out.println("added: " + in);
                count +=1;
    
                System.out.print(" ");
                in = sc.nextLine();
            } else {
                for (String task : myList) {
                    System.out.println(task);
                }
                System.out.print(" ");
                in = sc.nextLine();
            }
        }


        System.out.println("Bye! hope to see you again!!");
    }
}
