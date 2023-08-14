package server.domain;

public class Car implements Comparable<String>{
    private String plate;
    private String renavan;
    private String name;
    private String brand;
    private int year;
    private Driver driver;

    public Car() {
    }

    public Car(String plate, String renavan, String name, String brand, int year, String driverName, String driverCpf) {
        this.setDriver(new Driver(driverName, driverCpf));
        this.plate = plate;
        this.renavan = renavan;
        this.name = name;
        this.brand = brand;
        this.year = year;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getRenavan() {
        return renavan;
    }

    public void setRenavan(String renavan) {
        this.renavan = renavan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int compareTo(String o) {
        return 0;
    }


    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "Car{" +
                "renavan='" + renavan + '\'' +
                ", plate='" + plate + '\'' +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", year=" + year +
                ", driver=" + driver +
                '}';
    }
}
