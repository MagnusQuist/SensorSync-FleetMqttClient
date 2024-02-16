package dk.sdu.mmmi.FleetClient.entity;

import java.util.Arrays;

public class MqttResponse {
    private String topic;
    private byte[] message;

    public MqttResponse(String topic, byte[] message) {
        this.topic = topic;
        this.message = message;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public byte[] getMessage() {
        return message;
    }

    public void setMessage(byte[] message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MqttResponse{" +
                "topic='" + topic + '\'' +
                ", message=" + Arrays.toString(message) +
                '}';
    }
}
