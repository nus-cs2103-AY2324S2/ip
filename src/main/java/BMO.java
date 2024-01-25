public class BMO {
    public static void main(String[] args) {
        greet();
        salute();
    }

    static void greet() {
        String hi = "-----------------------------------------\n"
                    + "Hello! I'm BMO\nWhat can I do for you?\n"
                    + "-----------------------------------------";
        System.out.println(hi);
    }

    static void salute() {
        String bye = "-----------------------------------------\n"
                    + "Bye. Hope to see you again soon!\n"
                    + "-----------------------------------------";
        System.out.println(bye);
    }
}
