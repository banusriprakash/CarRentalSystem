import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Car> carList = new ArrayList<>();
        List<Customer> customerList = new ArrayList<>();
        List<Rental> rentalList = new ArrayList<>();
        CarRentalSystem rentalSystem =new CarRentalSystem(carList,customerList,rentalList);

        Car car1=new Car("C0001","Toyoto","Camry",900.0);
        Car car2=new Car("C0002","Toyoto","Land crusier",1500.0);
        rentalSystem.addCar(car1);
        rentalSystem.addCar(car2);


        rentalSystem.menu();
    }
}
