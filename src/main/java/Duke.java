public class Duke {
    public static void main(String[] args) {
        String logo = "    __    _                 \n"
                + "   / /   (_)___  __  _______\n"
                + "  / /   / / __ \\/ / / / ___/\n"
                + " / /___/ / / / / /_/ (__  ) \n"
                + "/_____/_/_/ /_/\\__,_/____/  \n";

        System.out.println("Hello from\n" + logo);

        Program program = new Program();
        program.start();

    }
}

