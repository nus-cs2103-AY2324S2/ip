package oak;

import oak.controller.TerminalController;

/**
 * Base Entry Class for Oak-dex
 */
public class Oak {
    public static void main(String[] args) {
        TerminalController controller = new TerminalController();

        controller.start();
    }
}
