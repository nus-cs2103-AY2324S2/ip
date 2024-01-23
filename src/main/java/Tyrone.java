import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Tyrone {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out, true);

        String logo =
                "    ████████╗██╗   ██╗██████╗  ██████╗ ███╗   ██╗███████╗\n" +
                        "    ╚══██╔══╝╚██╗ ██╔╝██╔══██╗██╔═══██╗████╗  ██║██╔════╝\n" +
                        "       ██║    ╚████╔╝ ██████╔╝██║   ██║██╔██╗ ██║█████╗  \n" +
                        "       ██║     ╚██╔╝  ██╔══██╗██║   ██║██║╚██╗██║██╔══╝  \n" +
                        "       ██║      ██║   ██║  ██║╚██████╔╝██║ ╚████║███████╗\n" +
                        "       ╚═╝      ╚═╝   ╚═╝  ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚══════╝\n";

        writer.println(logo +
                "    Yo, what's crackin' fam! I'm Tyrone, your digital homie.\n" +
                "    What's the word? So I can help you out today.\n" +
                "\n    ____________________________________________________________\n");

        while (true) {
            String cmd = reader.nextLine();
            if (cmd.equals("bye")) {
                writer.println(Tyrone.formatStringOutput("\"Peace out! Crossin' my fingers for a speedy reunion, ya feel?"));
                break;
            } else {
                writer.println(Tyrone.formatStringOutput(cmd));
            }
        }
    }

    public static String formatStringOutput(String content) {
        return ("\n    ____________________________________________________________\n" +
                "    " + content + "\n" +
                "\n    ____________________________________________________________\n");
    }
}
