package server.application;

import server.domain.Car;

// tree manipulation protocol
public class TMP {
    InMemoryDatabase db;
    public TMP() {
        this.db = new InMemoryDatabase();
    }

    public void insertCar(Car car){
        this.db.insertCar(car);
    }

    public Car getCar(String key){
        return this.db.getCar(key);
    }

    public int getRotations() {
        return db.getRotations();
    }

    public Car deleteCar(String keyToRemove) {
        return db.removeCar(keyToRemove);
    }

    public int getHeight() {
        return db.getHeight();
    }

    public void getAll(){
        db.getAll();
    }

    public Car updateCar(Car car) {
        return db.cars.put(car);
    }
}
