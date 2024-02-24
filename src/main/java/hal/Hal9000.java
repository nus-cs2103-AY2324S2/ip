package hal;

import hal.graphics.GraphicalUI;
import javafx.application.Application;

/**
 *
 */
public class Hal9000 {
    public static void main(String[] args) {
        String logo = "          _______  _           _____   _______  _______  _______\n"
                + "|\\     /|(  ___  )( \\         / ___ \\ (  __   )(  __   )(  __   )\n"
                + "| )   ( || (   ) || (        ( (   ) )| (  )  || (  )  || (  )  |\n"
                + "| (___) || (___) || |        ( (___) || | /   || | /   || | /   |\n"
                + "|  ___  ||  ___  || |         \\____  || (/ /) || (/ /) || (/ /) |\n"
                + "| (   ) || (   ) || |              ) ||   / | ||   / | ||   / | |\n"
                + "| )   ( || )   ( || (____/\\  /\\____) )|  (__) ||  (__) ||  (__) |\n"
                + "|/     \\||/     \\|(_______/  \\______/ (_______)(_______)(_______)\n";
        System.out.println(logo);
        Application.launch(GraphicalUI.class, args);
    }
}
