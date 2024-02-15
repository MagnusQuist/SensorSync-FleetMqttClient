package dk.sdu.mmmi.FleetClient.entity;

public class Message {
    private String client_id;
    private String firmware_version;
    private String athena_version;
    private String timestamp;

    public Message(String client_id, String firmware_version, String athena_version, String timestamp) {
        this.client_id = client_id;
        this.firmware_version = firmware_version;
        this.athena_version = athena_version;
        this.timestamp = timestamp;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getFirmware_version() {
        return firmware_version;
    }

    public void setFirmware_version(String firmware_version) {
        this.firmware_version = firmware_version;
    }

    public String getAthena_version() {
        return athena_version;
    }

    public void setAthena_version(String athena_version) {
        this.athena_version = athena_version;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Message{" +
                "client_id=" + client_id +
                ", firmware_version='" + firmware_version + '\'' +
                ", athena_version='" + athena_version + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
