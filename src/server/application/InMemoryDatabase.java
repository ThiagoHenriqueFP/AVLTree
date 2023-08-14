package server.application;

import server.domain.Car;
import server.infraestructure.TreeAVL;

public class InMemoryDatabase {
    public TreeAVL cars = new TreeAVL();

    public void insertCar(Car car){
        this.cars.insert(car);
    }

    public Car getCar(String cpf){
        return this.cars.search(cpf);
    }

    public int getRotations() {
        return TreeAVL.getRotations();
    }

    public Car removeCar(String keyToRemove) {
        return cars.remove(keyToRemove);
    }

    public int getHeight() {
        return cars.getRoot().getHeight();
    }

    public void getAll() {
        cars.getAll();
    }
}
