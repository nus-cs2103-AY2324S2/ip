public class Luke {

    //Logo created using https://patorjk.com/software/taag/#p=display&f=Varsity&t=Luke
    public static String logo = "  _____             __             \n"
            + " |_   _|           [  |  _         \n"
            + "   | |     __   _   | | / ] .---.  \n"
            + "   | |   _[  | | |  | '' < / /__\\\\ \n"
            + "  _| |__/ || \\_/ |, | |`\\ \\| \\__., \n"
            + " |________|'.__.'_/[__|  \\_]'.__.' ";

    public static void greet() {
        System.out.println("Hello! I'm\n" + logo + "\n");
    }

    public static void bye() {
        System.out.println("See you later, alligator!\n");
    }

    public static void main(String[] args) {
        greet();
        bye();
    }
}
