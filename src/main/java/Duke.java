public class Duke {
    private static void cat() {
        System.out.println(" |\\ /|");
        System.out.println("=(O O)=");
        System.out.println(" /   \\");
    }

    private static void line() {
        for (int i = 0; i < 72; i++) {
            System.out.print('_');
        }
        System.out.print('\n');
    }

    private static void hello() {
        cat();
        System.out.println("Hello! I'm the cat that lives in your walls.");
        System.out.println("What do you need?");
        line();
    }

    private static void bye() {
        line();
        System.out.println("*The cat recedes into the wall with a bored look on its face*");
        line();
    }
    }

    public static void main(String[] args) {
        hello();
        bye();
    }
}
