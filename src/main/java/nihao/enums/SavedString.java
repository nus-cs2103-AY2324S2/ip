package nihao.enums;

/**
 * Represents a collection of saved Strings for printing.
 */
public enum SavedString {
    LOGO("::::    ::: ::::::::::: :::    :::     :::      ::::::::  \n"
            + ":+:+:   :+:     :+:     :+:    :+:   :+: :+:   :+:    :+: \n"
            + ":+:+:+  +:+     +:+     +:+    +:+  +:+   +:+  +:+    +:+ \n"
            + "+#+ +:+ +#+     +#+     +#++:++#++ +#++:++#++: +#+    +:+ \n"
            + "+#+  +#+#+#     +#+     +#+    +#+ +#+     +#+ +#+    +#+ \n"
            + "#+#   #+#+#     #+#     #+#    #+# #+#     #+# #+#    #+# \n"
            + "###    #### ########### ###    ### ###     ###  ########  "),
    GREETINGS("Hello! I'm nihao.Nihao.\nI'm lazy and I don't want to do anything for you."),
    GOODBYE("Hope to never see you again. Goodbye!");

    private final String content;

    SavedString(String str) {
        content = str;
    }

    /**
     * Returns the String represented by the specific enum item.
     */
    public String getContent() {
        return content;
    }
}
