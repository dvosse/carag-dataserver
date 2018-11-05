package com.carag.dataserver.model;

        import java.time.LocalDateTime;
        import java.util.Arrays;
        import java.util.LinkedHashMap;
        import java.util.Map;

public class Image {

    private byte [] data;
    private LocalDateTime timestamp;
    private Map<String, String> metadata = new LinkedHashMap<>();


    public Image(byte [] data){
        this.data = data;
        timestamp = LocalDateTime.now();
    }

    public byte[] getData() {
        return data;
    }
    public void setData(byte[] data) {
        this.data = data;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    public Map<String, String> getMetadata() {
        return metadata;
    }
    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        if (!Arrays.equals(data, image.data)) return false;
        if (!timestamp.equals(image.timestamp)) return false;
        return metadata.equals(image.metadata);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(data);
        result = 31 * result + timestamp.hashCode();
        result = 31 * result + metadata.hashCode();
        return result;
    }
}
