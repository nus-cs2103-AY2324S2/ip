public class Earl {
    public static void main(String[] args) {
        divider();
        String logo = " ______           _ \n"
                + "|  ____|         | |\n"
                + "| |__   __ _ _ __| |\n"
                + "|  __| / _` | '__| |\n"
                + "| |___| (_| | |  | |\n"
                + "|______\\__,_|_|  |_|\n";
        System.out.println("Hello from\n" + logo);
        divider();
        System.out.println("Hello! I'm Earl\nWhat can I do for you?");
        divider();
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void divider() {
        System.out.println("_".repeat(50));
    }
}
