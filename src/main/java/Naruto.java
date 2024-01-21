public class Naruto {
    public static void main(String[] args) {
        spacer();
        greet();
        spacer();
        exit();
        spacer();
    }

    static void greet() {
        System.out.println("Hello! I'm Naruto, and I'm gonna become the next Hokage!" +
                "\nBelieve it!");

    }

    static void exit() {
        System.out.println("See you next time!");
    }

    static void spacer() {
        System.out.println("--------------------------------------------------------" +
                "----------");
    }

}
