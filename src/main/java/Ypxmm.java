import java.util.*;

public class Ypxmm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hallo boss, I'm Ypxmm.");
        System.out.println("Need me do what for you?");
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Okok I zao first, see you again bro!");
                break;
            }
            System.out.println(input);
        }
        sc.close();
    }
}
