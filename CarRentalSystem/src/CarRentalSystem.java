import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarRentalSystem {

    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;

    public CarRentalSystem(List<Car> cars, List<Customer> customers, List<Rental> rentals) {
        this.cars = cars;
        this.customers = customers;
        this.rentals = rentals;
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public void rentCars(Car car, Customer customer, int days) {
        if (car.isAvailable()) {
            car.rent();
            rentals.add(new Rental(car, customer, days));
        } else {
            System.out.println("Car is not available for renting");
        }
    }

    public void returnCar(Car car) {
        car.returnCar();
        Rental rentaltoRemove = null;
        for (Rental rental : rentals) {
            if (rental.getCar() == car) {
                rentaltoRemove = rental;
                break;
            }
        }
        if (rentaltoRemove != null) {
            rentals.remove(rentaltoRemove);
            System.out.println("Car successfully returned");
        } else {
            System.out.println("Car is not returned");
        }
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("------- Rent a Car -----");
            System.out.println("1. Rent a car");
            System.out.println("2. Return a Car");
            System.out.println("3. Exit");
            System.out.print("Enter Your Choice: ");
            int choice = sc.nextInt();

            sc.nextLine(); // Consume newline

            if (choice == 1) {
                System.out.println("\n === Rent a Car === \n");
                System.out.println("Enter Your Name: ");
                String customerName = sc.nextLine();

                System.out.println("\n Available Cars");

                for (Car car : cars) {
                    if (car.isAvailable()) {
                        System.out.println(car.getCarId() + "  " + car.getBrand() + " " + car.getModel());
                    }
                }
                System.out.println("Enter car id you need to rent");
                String carid = sc.nextLine();

                System.out.println("Enter the numbers of days needed");
                int rentdays = sc.nextInt();
                sc.nextLine();

                Customer newCustomer = new Customer("CUS" + (customers.size() + 1), customerName);
                addCustomer(newCustomer);

                Car selectedCar = null;
                for (Car car : cars) {
                    if (car.getCarId().equals(carid) && car.isAvailable()) {
                        selectedCar = car;
                        break;
                    }
                }

                if (selectedCar != null) {
                    double totalPrice = selectedCar.calculateprice(rentdays);
                    System.out.println("\n == Rental information == \n");
                    System.out.println("Customer Name: " + newCustomer.getNumber());
                    System.out.println("Customer Id: " + newCustomer.getName());
                    System.out.println("Car: " + selectedCar.getBrand() + " " + selectedCar.getModel());
                    System.out.println("Rentals Days: " + rentdays);
                    System.out.printf("Total Price: Rs %.2f%n ", totalPrice);

                    System.out.println("Confirm Rental (Y/N)? ");
                    String cnfrm = sc.nextLine();

                    if (cnfrm.equalsIgnoreCase("Y")) {
                        rentCars(selectedCar, newCustomer, rentdays);
                        System.out.println("\n Car rented successfully\n");
                    } else {
                        System.out.println("\n Car rental Cancelled\n");
                    }

                } else {
                    System.out.println("Invalid car id or car not available\n");
                }
            } else if (choice == 2) {
                System.out.println("\n == Return Car == \n");
                System.out.println("Enter the Car Id you want return:");
                String carid = sc.nextLine();

                Car carToreturn = null;

                for (Car car : cars) {
                    if (car.getCarId().equals(carid) && !car.isAvailable()) {
                        carToreturn = car;
                        break;
                    }
                }
                if (carToreturn != null) {
                    Customer customer = null;
                    for (Rental rental : rentals) {
                        if (rental.getCar() == carToreturn) {
                            customer = rental.getCustomer();
                            break;
                        }
                    }
                    if (customer != null) {
                        returnCar(carToreturn);
                        System.out.println("Car returned successfully by:" + customer.getNumber());
                        System.out.println();
                    } else {
                        System.out.println("Car was not returned or rental information were missing");
                        System.out.println();
                    }
                } else {
                    System.out.println("Invalid CarId or car is not rented");
                    System.out.println();
                }

            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice. Please enter the valid choice.");
                System.out.println();
            }
        }
        System.out.println("\n Thank you for using Car rental System!");
        System.out.println();
    }

    public static void main(String[] args) {
        List<Car> carList = new ArrayList<>();
        List<Customer> customerList = new ArrayList<>();
        List<Rental> rentalList = new ArrayList<>();
        CarRentalSystem rentalSystem = new CarRentalSystem(carList, customerList, rentalList);

        Car car1 = new Car("C0001", "Toyota", "Camry", 900.0);
        Car car2 = new Car("C0002", "Toyota", "Land Cruiser", 1500.0);
        rentalSystem.addCar(car1);
        rentalSystem.addCar(car2);

        rentalSystem.menu();
    }
}
