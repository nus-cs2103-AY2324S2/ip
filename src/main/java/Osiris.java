public class Osiris {

    public static final String NAME = "Osiris";

    public void outputIntroductions() {
        this.printSeparator();
        String introductions = String.format("Hello! I'm %s. \nWhat can I do for you?", Osiris.NAME);
        System.out.println(introductions);
    }

    public void outputGoodbyes() {
        this.printSeparator();
        String goodbyes = "Bye. Hope to see you again soon!";
        System.out.println(goodbyes);
        this.printSeparator();
    }

    private void printSeparator() {
        System.out.println("----------------------------------------");
    }
}
