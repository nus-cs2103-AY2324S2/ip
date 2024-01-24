import java.util.*;

public class Ypxmm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<String>();
        System.out.println("Hallo boss, I'm Ypxmm.");
        System.out.println("Need me do what for you?");
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Okok I zao first, see you again bro!");
                break;
            } else if (input.equals("list")) {
                int count = 1;
                for (String s : tasks) {
                    System.out.println(count + ". " + s);
                    count ++;
                }
            } else {
                tasks.add(input);
                System.out.println(input + " added");
            }
        }
        sc.close();
    }
}
