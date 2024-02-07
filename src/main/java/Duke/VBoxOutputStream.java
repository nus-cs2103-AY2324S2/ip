package Duke;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.OutputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class VBoxOutputStream extends OutputStream {
    private VBox dialogContainer;
    private final StringBuilder buffer = new StringBuilder();
    private BlockingQueue<String> lines = new LinkedBlockingQueue<>();

    public VBoxOutputStream(VBox dialogContainer) {
        this.dialogContainer = dialogContainer;
        Thread thread = new Thread(this::updateDialogContainer);
        thread.setDaemon(true);
        thread.start();
    }

    private void updateDialogContainer() {
        while (true) {
            try {
                String line = lines.take(); // Will block until a line is available
                Platform.runLater(() -> dialogContainer.getChildren().add(new Label(line)));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    @Override
    public void write(int b) {
        // Append the character to the buffer
        buffer.append((char) b);
        // Check if the character is a newline ('\n')
        if (b == '\n') {
            try {
                // Add the buffered line to the queue
                lines.put(buffer.toString());
                // Reset the buffer for the next line of output
                buffer.setLength(0);
            } catch (InterruptedException e) {
                // Restore the interrupted status
                Thread.currentThread().interrupt();
            }
        }
    }

    // Override other write methods as needed, handling byte arrays and buffering output
    // until a newline character is seen, then adding the complete line to 'lines'.
}