package dk.sdu.mmmi.FleetClient.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeviceDTO {
    @JsonProperty("name")
    private String name = "blob";
    @JsonProperty("firmware_version")
    private String firmware_version;
    @JsonProperty("athena_version")
    private String athena_version = "0.10.0";
    @JsonProperty("device_id")
    private String device_id;

    public DeviceDTO(String name, String firmware_version, String athena_version, String device_id) {
        this.name = name;
        this.firmware_version = firmware_version;
        this.athena_version = athena_version;
        this.device_id = device_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    @Override
    public String toString() {
        return "DeviceDTO{" +
                "name='" + name + '\'' +
                ", firmware_version='" + firmware_version + '\'' +
                ", athena_version='" + athena_version + '\'' +
                ", device_id='" + device_id + '\'' +
                '}';
    }
}
