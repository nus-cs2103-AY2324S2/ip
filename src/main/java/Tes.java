public class Tes {
    private Ui ui;
    private Parser parser;

    public Tes(){
        this.ui = new Ui();
        this.parser = new Parser(ui);
    }

    /**
     * Initialize the chatbot.
     */
    public void run() {
        this.ui.greet();

        this.parser.parse();
    }
    public static void main(String[] args){
        Tes chatbot = new Tes();
        chatbot.run();
    }
}
