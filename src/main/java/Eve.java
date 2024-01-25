import java.util.Scanner;
import java.util.*;

public class Eve {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(" Hello! I'm Eve");
        System.out.println(" What can I do for you?");

        String input = "";
        ArrayList<String> list = new ArrayList<>();

        while(!input.equals("bye")){
            input = sc.nextLine();

            if (input.equals("bye")){
                System.out.println(" Bye. Hope to see you again soon !");
            } else if (input.equals("list")) {
                for (int i = 0; i < list.size(); i++ ){
                    int j = i + 1;
                    String temp = list.get(i);
                    System.out.println(j + "." + temp);
                }
                
            } else {
                list.add(input);
                System.out.println("added: " + input);
            }
        }

            

        sc.close();
    }
}
