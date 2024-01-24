import java.util.*;
public class    Duke {
    public static void main(String[] args) {
        String intro = "____________________________________________________________\n"
                + "        Hello! I'm sibehupzcoder9000\n"
                + "        What you want sia\n"
                + "____________________________________________________________\n";
        String outro = "____________________________________________________________\n"
                + "        wow so ur gg to leave me...\n"
                + "____________________________________________________________\n";

        Scanner sc= new Scanner(System.in);
        System.out.println(intro);
        String str = sc.nextLine();
        while (!str.equals("exit")) {
            String output = "____________________________________________________________\n"
                    + str
                    + "\n____________________________________________________________\n";
            System.out.print(output);
            str = sc.nextLine();
        }
        System.out.print(outro);
    }
}
