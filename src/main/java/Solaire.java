public class Solaire {
    public static void main(String[] args) {
        greet();
        waveBye();
    }

    private static void greet() {
        String greetingMessage = "--------------------------------------------------\n"
        + "Oh hello there. I'm Solaire of Astora.\n"
        + "The sun is a wondrous body. Like a magnificent father!\n"
        + "If only I could be so grossly incandescent!\n"
        + "--------------------------------------------------\n";

        System.out.println(greetingMessage);
    }

    private static void waveBye() {
        String farewellMessage = "Farewell!" +
        "--------------------------------------------------\n";
        System.out.println(farewellMessage);
    }


}
