package nihao.enums;

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

    private final String CONTENT;

    SavedString(String str) {
        CONTENT = str;
    }

    public String getContent() {
        return CONTENT;
    }
}
