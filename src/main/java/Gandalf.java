import java.util.Scanner;
import java.util.Hashtable;

public class Gandalf {

    public static void main(String[] args) {
        Hashtable<Integer, String> list = new Hashtable<>();
        int numOfActions = 0;
        System.out.println("Through fire and shadow, I'm Gandalf");
        System.out.println("What can I do for you?\n");
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                scanner.close();
                System.out.println("So here at last, comes the end of our fellowship. I will not say: Do not weep. For not all tears are an evil.");
                break;
            }
            if(input.equals("list")){
                for(int i = 1; i <= numOfActions; i++){
                    String action = list.get(i);
                    System.out.println(i + ". " + action);
                }
                continue;
            }
            numOfActions++;
            list.put(numOfActions, input);
            System.out.println("added: " + input);
        }
    }
}
