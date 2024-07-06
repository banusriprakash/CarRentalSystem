public class Customer {

    private String Name;
    private String number;

    public Customer(String Name,String number){
        this.Name=Name;
        this.number=number;
    }

    public String getName(){
        return Name;
    }
    public String getNumber(){
        return number;
    }
}
