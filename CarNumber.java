package GIBDD;


public class CarNumber {
    private String series;
    private String number;
    private String region;
    String fio;

    public CarNumber(String series, String number, String region, String fio) {
        this.series = series;
        this.number = number;
        this.region = region;
        this.fio = fio;
    }

    public String getNumber() {
        return number;
    }

    public String getSeries() {
        return series;
    }

    public String getRegion() {
        return region;
    }

    public String getFio() {
        return fio;
    }

    @Override
    public String toString() {
        return "CarNumber{" +
                "series='" + series + '\'' +
                ", number='" + number + '\'' +
                ", region='" + region + '\'' +
                ", fio='" + fio + '\'' +
                '}';
    }
}
