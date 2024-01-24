import java.util.*;
import java.io.*;

public class Kaipybara {
    /**
     * FIELDS
     */
    private static final String LINE = "____________________________________________________________\n";
    private static final String START = "Whatsuppp I'm yo personal chatbot :)\n\n"
            + "          /'  _/                                /'                           \n"
            + "        /' _/~                                /'                             \n"
            + "     ,/'_/~  ____     O   ____              /'__     ____      ____     ____ \n"
            + "    /\\/~   /'    )  /'  /'    )--/'    /  /'    )  /'    )   )'    )--/'    )\n"
            + "  /'  \\  /'    /' /'  /'    /' /'    /' /'    /' /'    /'  /'       /'    /' \n"
            + "/'     \\(___,/(__(__/(___,/'  (___,/(__(___,/(__(___,/(__/'        (___,/(__ \n"
            + "                  /'             /'                                          \n"
            + "                /'       /     /'                                            \n"
            + "              /'        (___,/'                                              \n\n"
            + "Whakan I do 4 yu?\n";
    private static final String END = "see u later alligator\n";
    /**
     * METHODS
     */
    private static final ArrayList<String> HISTORY=new ArrayList<>();
    private static void store(String info){
        HISTORY.add(info);
    }
    private static void printHistory(){
        for (int i=0;i<HISTORY.size();i++){
            System.out.print((i+1)+": ");
            System.out.println(HISTORY.get(i));
        }
    }

    public static void main(String[] args){
        // opening statement
        System.out.println(START);

        // read inputs from user
        Scanner scanner = new Scanner(System.in);
        String userInput="";
        while(true) {
            userInput = scanner.nextLine();
            System.out.print(LINE);
            if(userInput.equals("bye")){
                break;
            }gi
            if (userInput.equals("list")){
                printHistory();
            } else {
                store(userInput);
                System.out.println("added: " + userInput);
            }
            System.out.print(LINE);
        };

        // closing statement
        System.out.println(END + LINE);
    }
}
