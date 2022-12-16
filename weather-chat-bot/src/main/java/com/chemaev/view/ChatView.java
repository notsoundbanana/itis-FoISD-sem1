package com.chemaev.view;

import com.chemaev.ChatApplication;
import com.chemaev.util.OpenWeatherUtil;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import org.json.JSONObject;

import java.io.IOException;

public class ChatView extends BaseView {

    private TextArea input;
    private TextArea conversation;
    private AnchorPane pane = null;
    private final ChatApplication application = BaseView.getChatApplication();
    private final EventHandler onKeyEvent = new EventHandler<KeyEvent>() {

        @Override
        public void handle(KeyEvent event) {  // Bot response
            if (event.getCode() == KeyCode.ENTER) {
                String sender = "Bot";
                String message;
                if (input.getText().toLowerCase().contains("/weather")) {
                    String[] inputMessageArray = input.getText().split(" ");
                    if (inputMessageArray.length == 2 && inputMessageArray[0].equals("/weather")) {
                        String city = inputMessageArray[1];

                        String weatherJSON = null;
                        try {
                            weatherJSON = (String) OpenWeatherUtil.getWeather(city);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        JSONObject obj = new JSONObject(weatherJSON);
                        Double temperature = obj.getJSONObject("main").getDouble("temp");

                        System.out.println(weatherJSON);

                        message = String.format("Temperature in %s: %.2f °C\n", city, temperature - 273.15);
                    } else {
                        message = "Can't understand you. Example: /weather Ulyanovsk \n";
                    }

                } else {
                    message = input.getText() + "\n";

                }
                application.getChatClient().sendMessage(sender + ": " + message);
                conversation.appendText("you: " + input.getText() + "\n");

                input.clear();
                event.consume();
            }
        }
    };

    public ChatView() throws Exception {
    }


    @Override
    public Parent getView() {
        if (pane == null) {
            this.createView();
        }

        return pane;
    }

    private void createView() {
        pane = new AnchorPane();

        conversation = new TextArea();
        conversation.setEditable(false);
        conversation.setWrapText(true);


        AnchorPane.setTopAnchor(conversation, 10.0);
        AnchorPane.setLeftAnchor(conversation, 10.0);
        AnchorPane.setRightAnchor(conversation, 10.0);

        input = new TextArea();
        input.setMaxHeight(50);

        AnchorPane.setBottomAnchor(input, 10.0);
        AnchorPane.setLeftAnchor(input, 10.0);
        AnchorPane.setRightAnchor(input, 10.0);

        input.addEventHandler(KeyEvent.KEY_PRESSED, onKeyEvent);
        pane.getChildren().addAll(input, conversation);
    }

    public void appendMessageToConversation(String message) {
        if (message != null) conversation.appendText(message + "\n");
    }
}
