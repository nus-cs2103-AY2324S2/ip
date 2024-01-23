public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        String logo="ZZZZZ   H   H  EEEEE  N   N\n" +
                "   Z    H   H  E      NN  N\n" +
                "  Z     HHHHH  EEEE   N N N\n" +
                " Z      H   H  E      N  NN\n" +
                "ZZZZZ   H   H  EEEEE  N   N\n";
        System.out.println("Hello from\n" + logo);
        print_line();
        print_message("Hello! I'm ZHEN");
        print_message("What can I do for you?");
        print_line();
        print_message("Bye. Hope to see you again soon");
    }
    public static void print_line(){
        System.out.println("\n----------------------------------");
    }
    public static void print_message(String msg){
        System.out.println(" "+msg);
    }
}
