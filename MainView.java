package com.example.solana_chat_ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route("")
public class MainView extends VerticalLayout {
    private SolanaService solanaService;
    private TextField messageField;
    private Button sendButton;
    private Grid<Message> messageGrid;
    private List<Message> messages;

    public MainView() {
        solanaService = new SolanaService("https://api.devnet.solana.com", "YourProgramIdHere");

        messageField = new TextField("Message");
        sendButton = new Button("Send");
        sendButton.addClickListener(e -> sendMessage());

        messageGrid = new Grid<>(Message.class);
        messages = new ArrayList<>();
        messageGrid.setItems(messages);

        add(messageField, sendButton, messageGrid);
    }

    private void sendMessage() {
        String messageContent = messageField.getValue();
        try {
            solanaService.sendMessage("UserPublicKeyHere", messageContent);
            messages.add(new Message("UserPublicKeyHere", messageContent, System.currentTimeMillis() / 1000L));
            messageGrid.getDataProvider().refreshAll();
            messageField.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class Message {
        private String sender;
        private String content;
        private long timestamp;

        public Message(String sender, String content, long timestamp) {
            this.sender = sender;
            this.content = content;
            this.timestamp = timestamp;
        }

        public String getSender() {
            return sender;
        }

        public String getContent() {
            return content;
        }

        public long getTimestamp() {
            return timestamp;
        }
    }
}
