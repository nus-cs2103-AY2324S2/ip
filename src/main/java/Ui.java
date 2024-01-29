public class Ui {

    public void start() {
        System.out.println("   ____________________________________________________________\n");
        System.out.println("   Hello! I'm Tiny!\n" +
                "   What can I do for you?\n");
        System.out.println("   ____________________________________________________________\n");

    }

    public void showLoadingError() {
        System.out.println("Error loading the data!");
    }

    public void showError(String message) {
        System.out.println(message);
    }

}
