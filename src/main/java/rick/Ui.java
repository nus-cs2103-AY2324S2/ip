package rick;

public class Ui {

    public void showLoadingMessage () {
        reply("Loading local data...");
    }
    public static void hello() {
        String hello = "Hello! I'm rick.Rick\n"+
                "Tell me about your plan !";
        reply(hello);
    }

    public static void reply(String arg) {
        String divider = "____________________________________________________________";
        System.out.println(divider + "\n" + arg + "\n" + divider);
    }
    public static void exit(){
        String exit = "Bye. Hope to see you again soon !";
        reply(exit);
    }
    public void showLoadingError () {
        reply("Loading error!");
    }
}
