package lite;


public class Lite {
    /**
     * Outputs the LITE logo
     */
    public static void main(String[] args) {
        String logo = "  LLLLL      IIIIIIIII  TTTTTTTTTT  EEEEEEEEEE  \n" +
                      "  LLLLL         III         TTT     EEEEEEEEEE  \n" +
                      "  LLLLL         III         TTT     EEE         \n" +
                      "  LLLLL         III         TTT     EEEEEEEE   \n" +
                      "  LLLLL         III         TTT     EEEEEEEE   \n" +
                      "  LLLLL         III         TTT     EEE        \n" +
                      "  LLLLLLLLL  IIIIIIIII      TTT     EEEEEEEEEE  \n";

        System.out.println("Hello from\n" + logo);
        Chatbot chatbot = new Chatbot();
        chatbot.start();
    }
}
