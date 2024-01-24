import java.util.*;
import java.io.*;

public class Kaipybara {
    private static final String LINE = "____________________________________________________________\n";
    public static void main(String[] args){
        // opening statement
        String start = LINE
                + "Whatsuppp I'm yo personal chatbot :)\n\n"
                + "          /'  _/                                /'                           \n"
                + "        /' _/~                                /'                             \n"
                + "     ,/'_/~  ____     O   ____              /'__     ____      ____     ____ \n"
                + "    /\\/~   /'    )  /'  /'    )--/'    /  /'    )  /'    )   )'    )--/'    )\n"
                + "  /'  \\  /'    /' /'  /'    /' /'    /' /'    /' /'    /'  /'       /'    /' \n"
                + "/'     \\(___,/(__(__/(___,/'  (___,/(__(___,/(__(___,/(__/'        (___,/(__ \n"
                + "                  /'             /'                                          \n"
                + "                /'       /     /'                                            \n"
                + "              /'        (___,/'                                              \n\n"
                + "Whakan I do 4 yu?\n"
                + LINE;
        System.out.println(start);

        // read inputs from user
        Scanner scanner = new Scanner(System.in);
        String userInput="";
        while(!userInput.equals("bye")) {
            userInput = scanner.nextLine();
            System.out.println(LINE+userInput+"\n"+LINE);
        };

        // closing statement
        String exit = "see u later alligator\n" + LINE;
        System.out.println(exit);





    }
}
