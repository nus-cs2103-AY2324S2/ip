package yapper;

import exception.YapperException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    @Test
    public void helloMessageTest() {
        String expected = "    What's poppin' fam, it's ya boi\n\n"
                + "   :::   :::           :::        :::::::::       :::::::::       ::::::::::       :::::::::\n"
                + "  :+:   :+:         :+: :+:      :+:    :+:      :+:    :+:      :+:              :+:    :+:\n"
                + "  +:+ +:+         +:+   +:+     +:+    +:+      +:+    +:+      +:+              +:+    +:+\n"
                + "  +#++:         +#++:++#++:    +#++:++#+       +#++:++#+       +#++:++#         +#++:++#:\n"
                + "  +#+          +#+     +#+    +#+             +#+             +#+              +#+    +#+\n"
                + " #+#          #+#     #+#    #+#             #+#             #+#              #+#    #+#\n"
                + "###          ###     ###    ###             ###             ##########       ###    ###\n\n"
                + "    Hit me up with those deets and let's vibe together!\n";
        assertEquals(expected, Ui.helloMessage());
    }

    @Test
    public void byeTest() throws YapperException {
        Ui ui = new Ui(new TaskList());
        String expected = "Peace out, fam! Stay lit and keep those good vibes rollin'!\n";
        assertEquals(expected, ui.byeMessage());
    }
}
