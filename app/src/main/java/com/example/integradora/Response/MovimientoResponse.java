package com.example.integradora.Response;

public class MovimientoResponse {
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

    public int getValue() {
        return data != null && data.messageDecoded != null ? data.messageDecoded.value : 0;
    }

    public int getMovimiento() {
        return data != null && data.messageDecoded != null ? data.messageDecoded.movimiento : 0;
    }

    public static class Data {
        private MessageDecoded messageDecoded;

        public static class MessageDecoded {
            private int value;
            private int movimiento;
        }
    }
}
