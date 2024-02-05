public class  Dude {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        System.out.println(greet());
        System.out.println(bye());

    }




    private static String greet(){
        return "-----------------------------------\n" +
                "Hello! I'm Dude\n" +
                "What can I do for you?\n" +
                "-----------------------------------";
    }


    private static String bye(){
        String bye_msg = "-----------------------------------";
        bye_msg += "\nBye. Hope to see you again soon!";
        bye_msg +=  "\n-----------------------------------";

        return bye_msg;
    }

}
