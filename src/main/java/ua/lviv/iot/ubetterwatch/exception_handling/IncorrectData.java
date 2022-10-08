package ua.lviv.iot.ubetterwatch.exception_handling;

public class IncorrectData {
    private String info;
    public IncorrectData(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
