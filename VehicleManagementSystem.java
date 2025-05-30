package Project;

import java.util.*;

abstract class Vehicle {
    String brand;
    String model;
    int year;

    Vehicle(String brand, String model, int year){
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    abstract void displayInfo();

    void startEngine(){
        System.out.println("Starting the engine...");
    }
}

class Car extends Vehicle {
    int numberOfDoors;

    Car(String brand, String model, int year, int numberOfDoors){
        super(brand, model, year);
        this.numberOfDoors = numberOfDoors;
    }

    @Override
    void displayInfo() {
        System.out.println("Brand of the car: " + brand);
        System.out.println("Model of the car: " + model);
        System.out.println("Year Of Launching: " + year);
        System.out.println("No. of Doors: " + numberOfDoors);
    }

}
class Bike extends Vehicle {
    boolean hasCarrier;

    Bike(String brand, String model, int year, boolean hasCarrier){
        super(brand, model, year);
        this.hasCarrier = hasCarrier;
    }

    void displayInfo(){
        System.out.println("Brand of the bike: " + brand);
        System.out.println("Model of the bike: " + model);
        System.out.println("Year Of Launching: " + year);
        System.out.println("Has a carrier or not: " + hasCarrier);
    }
}

public class VehicleManagementSystem {
    public static void runMenu(ArrayList<Vehicle> vehicles){
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("\n Select the option: ");
            System.out.println("1. Search by brand");
            System.out.println("2. Display all vehicles");
            System.out.println("3. Add new vehicle");
            System.out.println("4. Exit");

            int choice = sc.nextInt();

            switch(choice){
                case 1:
                    searchByBrand(vehicles, sc);
                    break;
                case 2:
                    displayAllVehicles(vehicles);
                    break;
                case 3:
                    addNewVehicle(vehicles, sc);
                    break;
                case 4:
                    System.out.println("Exiting the program...");
                    return;
                default:
                    System.out.println("Invalid choice ! Try again .");
            }

            System.out.println();
        }
    }

    public static void searchByBrand(ArrayList<Vehicle> vehicles, Scanner sc){
        System.out.println("Enter the brand name: ");
        sc = new Scanner(System.in);
        String searchBrand = sc.nextLine();

        boolean found = false;
        for (Vehicle v : vehicles){
            // equalsIgnoreCase -> Used for case Sensitive (ignores whether it is a upper or lower case)
            if (v.brand.equalsIgnoreCase(searchBrand)){
                v.displayInfo();
                System.out.println();
                found = true;
            }
        }

        if (!found) {
            System.out.print("No vehicles found from this brand: " + searchBrand);
        }
    }

    public static void displayAllVehicles(ArrayList<Vehicle> vehicles){

        if (vehicles.isEmpty()){
            System.out.println("No vehicles available");
            return;
        }

        System.out.println("\n List of all Vehicles: ");
        for (Vehicle v: vehicles){
            v.displayInfo();
            System.out.println();
        }
    }

    public static void addNewVehicle(ArrayList<Vehicle> vehicles, Scanner sc){
        sc.nextLine();
        System.out.println("Enter type of vehicle: Car/Bike");
        String type = sc.nextLine();

        System.out.println("Enter brand: ");
        String brand = sc.nextLine();

        System.out.println("Enter model: ");
        String model = sc.nextLine();

        System.out.println("Enter year of launch: ");
        int year = sc.nextInt();

        if (type.equalsIgnoreCase("Car")){
            System.out.println("Enter number of doors: ");
            int doors = sc.nextInt();
            Car newCar = new Car(brand, model, year, doors);
            vehicles.add(newCar);
            System.out.println("Car added successfully !");
        } else if (type.equalsIgnoreCase("Bike")){
            System.out.println("Does it have a carrier: ");
            boolean hasCarrier = sc.nextBoolean();
            Bike newBike = new Bike(brand, model, year, hasCarrier);
            vehicles.add(newBike);
            System.out.println("Bike added successfully");
        } else {
            System.out.println("Incorrect vehicle type ! Only cars and bikes are applicable !");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Vehicle> vehicles = new ArrayList<>();

        Car car1 = new Car("Bugatti", "Veyron", 2018, 2);
        Car car2 = new Car("Audi", "A4 Advance", 2020, 4);
        Bike bike1 = new Bike("Suzuki", "Hayabusa", 2022, true);
        Bike bike2 = new Bike("Hardley Davidson", "Prime", 2023, false);

        vehicles.add(car1);
        vehicles.add(car2);
        vehicles.add(bike1);
        vehicles.add(bike2);

        runMenu(vehicles);
    }
}