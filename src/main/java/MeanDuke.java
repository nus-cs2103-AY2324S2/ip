import java.util.Scanner;

public class MeanDuke{
    public static void main(String[] args) {
        String logo =
                  "███╗░░░███╗███████╗░█████╗░███╗░░██╗░░░░░░██████╗░██╗░░░██╗██╗░░██╗███████╗\n"
                + "████╗░████║██╔════╝██╔══██╗████╗░██║░░░░░░██╔══██╗██║░░░██║██║░██╔╝██╔════╝\n"
                + "██╔████╔██║█████╗░░███████║██╔██╗██║█████╗██║░░██║██║░░░██║█████═╝░█████╗░░\n"
                + "██║╚██╔╝██║██╔══╝░░██╔══██║██║╚████║╚════╝██║░░██║██║░░░██║██╔═██╗░██╔══╝░░\n"
                + "██║░╚═╝░██║███████╗██║░░██║██║░╚███║░░░░░░██████╔╝╚██████╔╝██║░╚██╗███████╗\n"
                + "╚═╝░░░░░╚═╝╚══════╝╚═╝░░╚═╝╚═╝░░╚══╝░░░░░░╚═════╝░░╚═════╝░╚═╝░░╚═╝╚══════╝\n";
        String spacer = "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n";

        String intro = logo + "What do you want this time?\n" + spacer;
        System.out.println(intro);

        //Echoes back user input until the user inputs "end", which stops the conversation
        Scanner inputScanner= new Scanner(System.in);
        String userInput = inputScanner.nextLine();
        while (!userInput.equals("end")) {
            System.out.println(spacer + userInput + "\n" + spacer);
            userInput = inputScanner.nextLine();
        };

        String outro = spacer + "Finally you're finished, thought you would never stop yapping.\n" + spacer;
        System.out.println(outro);
    }
}
