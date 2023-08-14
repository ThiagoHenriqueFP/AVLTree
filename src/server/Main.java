package server;

import server.application.InMemoryDatabase;
import server.domain.Car;

import java.time.LocalDate;

public class Main {
    public static void main(String[] agrs){
        InMemoryDatabase db = new InMemoryDatabase();

//         db.insertCar(new Car(
//                "ngx1542",
//                "123456789098765",
//                "moto qualquer",
//                "moto",
//                2015,
//                "01797501437"
//            ));
//
//        db.insertCar(new Car(
//                "ngx1542",
//                "123456789098765",
//                "moto qualquer",
//                "moto",
//                2015,
//                "01797501438"
//            ));

        for (int i = 0; i < 10; i++ ){
            db.insertCar(new Car(
                    "ngx1542",
                    "123456789098765",
                    "moto qualquer",
                    "moto",
                    2015,
                    "nome qualquer",
                    "0179750143" + i
            ));
        }

        System.out.println(db.getCar("01797501438"));
        System.out.printf("Foram necessarias %s rotacoes para balancear a arvore", db.getRotations());
    }
}
