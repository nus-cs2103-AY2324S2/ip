public class UI {
    private static final String line = "      ________________________________________________________\n";

    public static void printResponse(String output){
        System.out.print(UI.line + output + "\n" + UI.line);
    }

    public static void printResponse(int num, String output, boolean isLast){ //printing of list items
        if (!isLast) {
            if (num == 1) {
                System.out.print(UI.line + "      " + String.valueOf(num) + ". " + output + "\n");
            } else {
                System.out.print("      " + String.valueOf(num) + ". " + output + "\n");
            }
        } else {
            System.out.print("      " + String.valueOf(num) + ". " + output + "\n" + UI.line);
        }
    }

    public static void printBye(){
        System.out.print(UI.line + "      Bye! Have a great day ahead :>\n" + UI.line);
    }
}
