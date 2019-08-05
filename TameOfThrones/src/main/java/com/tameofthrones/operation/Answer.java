package com.tameofthrones.operation;

import com.tameofthrones.constants.Script;


public class Answer {

    private String content;

    private Answer() {
    }

    private static class Holder {
        private static final Answer INSTANCE = new Answer();
    }

    public static Answer instance() {
        return Holder.INSTANCE;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Answer invalidQuestion() {
        instance().setContent(Script.INVALID_QUESTION);
        return instance();
    }

    public Answer empty() {
        instance().setContent(Script.EMPTY);
        return instance();
    }

    public Answer canNotProcessMessage() {
        instance().setContent(Script.CAN_NOT_PROCESS_MESSAGE);
        return instance();
    }

}
