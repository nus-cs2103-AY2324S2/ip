# AI Prompts for enhancing the UI

> **ChatGPT 4 (Plus)** was used in terms of generating some of the prompts. 

> The feedback from ChatGPT was largely taken into consideration (but not much code), as it pointed out which JavaFX was
required to be modified.

## 1. Can you show me simple fixes that will result in a better UI? No CSS (DialogBox.java)
- Thoughts 🤔: Unsurprisingly, I feel that some of the suggestions of the ChatGPT was too broad, and does not help much with the recommendation.
    Overall not much time saved, but felt like I was motivated to make a better UI.

## 2. How should I set the radius of this image?
- Thoughts 🤔: The solution that I found on StackOverFlow was working great, but I randomly selected a radius value and found that it was very ugly. Decided that this was probably not a very good idea to "hard-code" the values. Saved about 3 hours of work, due to testing on different resolutions / screensize etc.

## 3. Surround the label with an outline. Make the border rounded.
- Thoughts 🤔: ChatGPT demonstrated a CSS way to set the colors, and this made me more familiarised with how the design works in JavaFX for iP at least.
   About 2 hours saved, as I don't have to go and google how to manually add certain graphical elements. 
   (Of course this cannot be avoided in tP, but at least I kind of know how it works now)

## 4. how to set the color from code? `-fx-border-color`
- Thoughts 🤔: This was a CSS approach also.
    And now I know that JavaFX uses something like CSS, so it could be a direction that my team could take in tP.
    About 2 hours saved, as the previous method I used generated hideous graphics.


## 5. (MainWindow.java) How to make the elements auto take the full height / width?
- Thoughts 🤔: This was probably not a very good prompt as the wrong file was being asked to ChatGPT. 
    For the UI side, I should have uploaded the FXML file so that it was able to understand my doubt.
    Not much time saved, and not much value added also.
    But this probably made me such that I needed to properly prompt and learn JavaFX's advanced features.
    After more experience with JavaFX, it simply feels like another web language (React etc) with a slightly different syntax.

## 6. Auto-fit the dialogContainer to the max width available from ScrollPane
    ```
    <ScrollPane fx:id="scrollPane" fitToHeight="true" AnchorPane.topAnchor="0" AnchorPane.rightAnchor="0" AnchorPane.bottomAnchor="42.0" AnchorPane.leftAnchor="0" hbarPolicy="NEVER" vvalue="1.0">
       <content>
         <VBox fx:id="dialogContainer" prefHeight="552.0" prefWidth="388.0" fillWidth="true" />
       </content>
     </ScrollPane>
    ```
- Thoughts 🤔: ChatGPT provided with useful flags to add to ScrollPane, which I was unaware of, because I do not really have a lot of experience with desktop app development, but I am more experienced on the mobile / web side.
The flags that was  `fitToHeight="true" fitToWidth="true"` gave me somewhere to start looking for in the JavaFX documents to begin improving my UI.
A lot of time was saved, I estimate maybe about 3 hours worth of time.


