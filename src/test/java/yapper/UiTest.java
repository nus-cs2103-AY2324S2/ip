package yapper;

import exception.YapperException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.Task;
import task.Todo;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UiTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void helloMessageTest() {
        Ui.helloMessage();
        String expected = "    ____________________________________________________________\n"
                + "    What's poppin' fam, it's ya boi\n\n"
                + "       :::   :::           :::        :::::::::       :::::::::       ::::::::::       :::::::::\n"
                +  "      :+:   :+:         :+: :+:      :+:    :+:      :+:    :+:      :+:              :+:    :+:\n"
                + "      +:+ +:+         +:+   +:+     +:+    +:+      +:+    +:+      +:+              +:+    +:+\n"
                + "      +#++:         +#++:++#++:    +#++:++#+       +#++:++#+       +#++:++#         +#++:++#:\n"
                + "      +#+          +#+     +#+    +#+             +#+             +#+              +#+    +#+\n"
                + "     #+#          #+#     #+#    #+#             #+#             #+#              #+#    #+#\n"
                + "    ###          ###     ###    ###             ###             ##########       ###    ###\n\n"
                + "    Hit me up with those deets and let's vibe together!\n"
                + "    ____________________________________________________________\n";
        assertEquals(expected, outputStreamCaptor.toString());
    }

    @Test
    public void byeTest() throws YapperException {
        Ui ui = new Ui(new TaskList());
        ui.bye();
        String expected = "    Peace out, fam! Stay lit and keep those good vibes rollin'!\n";
        assertEquals(expected, outputStreamCaptor.toString());
        assertTrue(ui.hasEnded());
    }
}
