package com.example.integradora.Response;

public class GiroscopioResponse {
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

    public static class Data {
        private MessageDecoded message_decoded;

        public MessageDecoded getMessageDecoded() {
            return message_decoded;
        }

        public void setMessageDecoded(MessageDecoded message_decoded) {
            this.message_decoded = message_decoded;
        }
    }

    public static class MessageDecoded {
        private double pitch;
        private double roll;

        public double getPitch() {
            return pitch;
        }

        public void setPitch(double pitch) {
            this.pitch = pitch;
        }

        public double getRoll() {
            return roll;
        }

        public void setRoll(double roll) {
            this.roll = roll;
        }
    }
}
