public class Poe {
    String line = "=================================================";
    public void greetings(){
        String hello =  line + "\nYo! I'm Poe\nWhat can I do for you bro\n" + line;
        System.out.println(hello);
    }

    public void bye(){
        String byebye = line + "\nBye come again\n" + line;
        System.out.println(byebye);
    }

    public static void main(String[] args) {
        Poe poe1 = new Poe();
        poe1.greetings();
        poe1.bye();

    }
}
