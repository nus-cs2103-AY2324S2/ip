package Duke;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.OutputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class VBoxOutputStream extends OutputStream {
    private final VBox dialogContainer;
    private final StringBuilder buffer = new StringBuilder();
    private final BlockingQueue<String> lines = new LinkedBlockingQueue<>();



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
        buffer.append((char) b);
        if (b == '\n') {
            try {
                lines.put(buffer.toString());
                buffer.setLength(0);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}