package com.example.integradora.Response;

import com.google.gson.annotations.SerializedName;

public class TemperaturaResponse {
    private String title;
    private String message;
    private String type;
    private Data data;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public double getValue() {
        return data != null && data.messageDecoded != null ? data.messageDecoded.value : 0.0;
    }

    public static class Data {
        @SerializedName("message_decoded")
        private MessageDecoded messageDecoded;

        public MessageDecoded getMessageDecoded() {
            return messageDecoded;
        }

        public void setMessageDecoded(MessageDecoded messageDecoded) {
            this.messageDecoded = messageDecoded;
        }
    }

    public static class MessageDecoded {
        private double value;

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }
    }
}
