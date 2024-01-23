import java.util.*;
public class BingBong{
    public static void formalities(String context) {
        if (context.equals("greet")) {
            String newLogo = "\n" +
                    "$$$$$$$\\  $$\\                     $$$$$$$\\                                \n" +
                    "$$  __$$\\ \\__|                    $$  __$$\\                               \n" +
                    "$$ |  $$ |$$\\ $$$$$$$\\   $$$$$$\\  $$ |  $$ | $$$$$$\\  $$$$$$$\\   $$$$$$\\  \n" +
                    "$$$$$$$\\ |$$ |$$  __$$\\ $$  __$$\\ $$$$$$$\\ |$$  __$$\\ $$  __$$\\ $$  __$$\\ \n" +
                    "$$  __$$\\ $$ |$$ |  $$ |$$ /  $$ |$$  __$$\\ $$ /  $$ |$$ |  $$ |$$ /  $$ |\n" +
                    "$$ |  $$ |$$ |$$ |  $$ |$$ |  $$ |$$ |  $$ |$$ |  $$ |$$ |  $$ |$$ |  $$ |\n" +
                    "$$$$$$$  |$$ |$$ |  $$ |\\$$$$$$$ |$$$$$$$  |\\$$$$$$  |$$ |  $$ |\\$$$$$$$ |\n" +
                    "\\_______/ \\__|\\__|  \\__| \\____$$ |\\_______/  \\______/ \\__|  \\__| \\____$$ |\n" +
                    "                        $$\\   $$ |                              $$\\   $$ |\n" +
                    "                        \\$$$$$$  |                              \\$$$$$$  |\n" +
                    "                         \\______/                                \\______/ \n";
            System.out.println(newLogo);
            System.out.println("____________________________________________________________");
            System.out.println(" Hello I'm BingBong");
            System.out.println(" What can I do for you?");
            System.out.println("____________________________________________________________");
        } else if (context.equals("farewell")) {
            System.out.println("____________________________________________________________");
            System.out.println(" Bye. Don't come back. jk!");
            System.out.println("____________________________________________________________");
        }

    }

    public static void main(String[] args) {
        formalities("greet");
        while (true) {
            Scanner reader = new Scanner(System.in);
            String s = reader.next();
            if (!s.toLowerCase().equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" " + s);
                System.out.println("____________________________________________________________");
            } else {
                formalities("farewell");
                break;
            }
        }

    }
}
