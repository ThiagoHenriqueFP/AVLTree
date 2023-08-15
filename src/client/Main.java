package client;

import server.application.TMP;
import server.domain.Car;

import java.util.Scanner;

public class Main {

    private static String HARDCODED_USERNAME = "a";
    private static String HARDCODED_PASSWORD = "a";

    static TMP protocol = new TMP();
    public static void main(String[] args){
        int option = 0;
        boolean authenticated = false;
        Scanner sc = new Scanner(System.in);

        do {
            try{
            System.out.println("+-----+---------------------+\n" +
                    "| 1)  | Login               |\n" +
                    "| 2)  | Salvar carro        |\n" +
                    "| 3)  | Buscar carro        |\n" +
                    "| 4)  | Remover carro       |\n" +
                    "| 5)  | Atualizar carro     |\n" +
                    "| 6)  | Visualizar rotacoes |\n" +
                    "| 7)  | Pegar altura        |\n" +
                    "| 8)  | Listar carros       |\n" +
                    "| 9)  | Popular banco       |\n" +
                    "| 0)  | Sair                |\n" +
                    "+-----+---------------------+");
            option = sc.nextInt();

            switch (option){
                case 1:
                    String username = sc.next();
                    String password = sc.next();

                    if(!login(username, password)){
                        System.out.println("username invalido, tente novamente");
                        break;
                    }
                    authenticated = true;
                    break;
                case 2:
                    if(!authenticated){
                        System.out.println("esta funcao precisa de autenticacao");
                        break;
                    }

                    System.out.println("Informe o renavan");
                    String renavan = sc.next();
                    System.out.println("Informe o nome");
                    String name = sc.next();
                    System.out.println("Informe a marca");
                    String brand = sc.next();
                    System.out.println("Informe o ano");
                    Integer year = sc.nextInt();
                    System.out.println("Informe a placa (sem espaços)");
                    String plate = sc.next();
                    System.out.println("Informe o nome do condutor (sem espaco)");
                    String driverName = sc.next();
                    System.out.println("Informe o cpf do condutor (sem pontos e/ou hifen)");
                    String cpf = sc.next();

                    Car car = new Car(
                            plate,
                            renavan,
                            name,
                            brand,
                            year,
                            driverName,
                            cpf
                    );

                    protocol.insertCar(car);
                    break;

                case 3:
                    if(!authenticated){
                        System.out.println("esta funcao precisa de autenticacao");
                        break;
                    }

                    System.out.println("informe o renavan do carro");
                    String key = sc.next();

                    Car result = protocol.getCar(key);
                    if (result == null) {
                        System.out.println("car not found");
                        break;
                    }
                    System.out.println(result);
                    break;
                case 4:
                    if(!authenticated){
                        System.out.println("esta funcao precisa de autenticacao");
                        break;
                    }

                    System.out.println("informe o renavan do carro");
                    String keyToRemove = sc.next();

                    Car deleteResult = protocol.deleteCar(keyToRemove);
                    if (deleteResult == null) {
                        System.out.println("car not found");
                        break;
                    }
                    System.out.println(deleteResult);
                    break;
                case 5:
                    if(!authenticated){
                        System.out.println("esta funcao precisa de autenticacao");
                        break;
                    }

                    System.out.println("Informe o renavan");
                    renavan = sc.next();
                    System.out.println("Informe o nome");
                    name = sc.next();
                    System.out.println("Informe a marca");
                    brand = sc.next();
                    System.out.println("Informe o ano");
                    year = sc.nextInt();
                    System.out.println("Informe a placa (sem espaços)");
                    plate = sc.next();
                    System.out.println("Informe o nome do condutor (sem espaco)");
                    driverName = sc.next();
                    System.out.println("Informe o cpf do condutor (sem pontos e/ou hifen)");
                    cpf = sc.next();


                    System.out.println("Updated car : " + protocol.updateCar(
                            new Car(
                                    plate,
                                    renavan,
                                    name,
                                    brand,
                                    year,
                                    driverName,
                                    cpf
                            )
                    ));

                    break;

                case 6:
                    if(!authenticated){
                        System.out.println("esta funcao precisa de autenticacao");
                        break;
                    }

                    System.out.printf("numero de rotacoes da arvore %d%n", protocol.getRotations());
                    break;
                case 7:
                    if(!authenticated){
                        System.out.println("esta funcao precisa de autenticacao");
                        break;
                    }

                    System.out.printf("altura da arvore %d%n", protocol.getHeight());
                    break;

                case 8:
                    if(!authenticated){
                        System.out.println("esta funcao precisa de autenticacao");
                        break;
                    }

                    protocol.getAll();
                    break;
                case 9:
                    populate();
                    break;
                case 0:
                    System.out.println("FIN ACK\nclosing connection!");
                    break;
                default:
                    break;
            }
            } catch (Exception e){
                System.out.println("An error occurred\n" + e.getMessage());
            }

        } while (option !=0);
    }

    private static boolean login(String username, String password){
        return username.equalsIgnoreCase(HARDCODED_USERNAME) && password.equalsIgnoreCase(HARDCODED_PASSWORD);
    }

    private static void populate() {
        for (int i = 1; i <= 50; i++ ){
            Integer random = (int) (Math.random() * 10000);

            protocol.insertCar(new Car(
                    "ngx"+random,
                    ""+random*13,
                    "moto qualquer",
                    "moto",
                    2015,
                    "nome qualquer",
                    "00000" + random
            ));
        }
    }
}
