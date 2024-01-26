public class Duke {
    public static void main(String[] args) {
        String logo = " _  _   __    ____  ____ \n" +
                "( \\/ ) /__\\  (  _ \\(  _ \\\n" +
                " \\  / /(__)\\  )   / )   /\n" +
                " (__)(__)(__)(_)\\_)(_)\\_)\n";

        System.out.println(logo);
        printDivider(90);
        System.out.println("Ahoy! I be Yarr \nWhat be yer command, me heartie?");
        printDivider(90);
        System.out.println("Fair winds to ye, me hearty! May the tide carry ye safely until our paths cross again.");
        printDivider(90);

    }
    private static void printDivider(int length){
        char divider = 0x2500 ;
        for (int i = 0; i < length; i++) {
            System.out.print(divider);
        }
        System.out.println();
    }
}
