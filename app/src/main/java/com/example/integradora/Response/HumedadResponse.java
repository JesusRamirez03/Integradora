package com.example.integradora.Response;

public class HumedadResponse {
    private Data data;

    public int getHumidityValue() {
        return data != null && data.message_decoded != null ? data.message_decoded.value : 0;
    }

    public static class Data {
        private MessageDecoded message_decoded;

        public static class MessageDecoded {
            private int value;
        }
    }
}
