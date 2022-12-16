package com.chemaev.view;

import com.chemaev.ChatApplication;
import javafx.scene.Parent;

public abstract class BaseView {

    private static ChatApplication chatApplication;

    public static ChatApplication getChatApplication() throws Exception {
        if (chatApplication != null) {
            return chatApplication;
        }
        throw new Exception("No app in base com.chemaev.view");
    }

    public static void setChatApplication(ChatApplication chatApplication) {
        BaseView.chatApplication = chatApplication;
    }

    public abstract Parent getView();


}