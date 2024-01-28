public class Ui {
    public void greet() {
        String logo = "     _______.___________. ___________    ____  _______ \n" +
                        "    /       |           ||   ____\\   \\  /   / |   ____|\n" +
                        "   |   (----`---|  |----`|  |__   \\   \\/   /  |  |__   \n" +
                        "    \\   \\       |  |     |   __|   \\      /   |   __|  \n" +
                        ".----)   |      |  |     |  |____   \\    /    |  |____ \n" +
                        "|_______/       |__|     |_______|   \\__/     |_______|";

        System.out.println("\n" + logo);
        System.out.println(border);
        System.out.println("What's up! I'm steve");
        System.out.println("What do you want to do?");
    }

    public static String border = "____________________________________________________________";

    public void showLoadingError() {
        System.out.println("Failed to read tasks file!");
    }

    public static void main(String[] args) {
        Ui ui  = new Ui();
        ui.greet();
    }

}