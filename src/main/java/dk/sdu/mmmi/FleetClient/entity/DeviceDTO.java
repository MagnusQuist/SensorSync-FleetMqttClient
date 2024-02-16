package dk.sdu.mmmi.FleetClient.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class DeviceDTO {
    private String name;
    @JsonProperty("firmware_version")
    private String firmwareVersion;
    @JsonProperty("athena_version")
    private String athenaVersion;
    @JsonProperty("device_id")
    private UUID deviceId;

    public DeviceDTO() {}

    public DeviceDTO(String name, String firmwareVersion, String athenaVersion, UUID deviceId) {
        this.name = name;
        this.firmwareVersion = firmwareVersion;
        this.athenaVersion = athenaVersion;
        this.deviceId = deviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public String getAthenaVersion() {
        return athenaVersion;
    }

    public void setAthenaVersion(String athenaVersion) {
        this.athenaVersion = athenaVersion;
    }

    public UUID getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(UUID deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        return "DeviceDTO{" +
                "name='" + name + '\'' +
                ", firmwareVersion='" + firmwareVersion + '\'' +
                ", athenaVersion='" + athenaVersion + '\'' +
                ", deviceId='" + deviceId + '\'' +
                '}';
    }
}
