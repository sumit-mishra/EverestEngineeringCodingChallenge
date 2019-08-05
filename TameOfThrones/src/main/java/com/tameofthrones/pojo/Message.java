package com.tameofthrones.pojo;

public class Message {

    private String senderKingdom;
    private String receiverKingdom;
    private String text;

    public Message(String senderKingdom, String receiverKingdom, String text) {
        this.senderKingdom = senderKingdom;
        this.receiverKingdom = receiverKingdom;
        this.text = text;
    }

    public String getSenderKingdom() {
        return senderKingdom;
    }

    public void setSenderKingdom(String senderKingdom) {
        this.senderKingdom = senderKingdom;
    }

    public String getReceiverKingdom() {
        return receiverKingdom;
    }

    public void setReceiverKingdom(String kingdom) {
        this.receiverKingdom = kingdom;
    }

    public String getText() {
        return text;
    }

    public void setText(String encryptedText) {
        this.text = encryptedText;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((text == null) ? 0 : text.hashCode());
        result = prime * result + ((receiverKingdom == null) ? 0 : receiverKingdom.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Message other = (Message) obj;
        if (text == null) {
            if (other.text != null)
                return false;
        } else if (!text.equals(other.text))
            return false;
        if (receiverKingdom == null) {
            if (other.receiverKingdom != null)
                return false;
        } else if (!receiverKingdom.equals(other.receiverKingdom))
            return false;
        return true;
    }

}
