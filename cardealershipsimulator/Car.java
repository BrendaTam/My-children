package cardealershipsimulator;
//Brenda Tam 
//500901596

/*Car class is a Vehicle, implements Comparable
 *contains methods:
 *      getters for each Car variable
 *      String display()
 *      bollean equals(Object)
 *      int compareTo(Car)
*/
public class Car extends Vehicle implements Comparable<Car>{
    
    private String model;
    private int maxRange;
    private double safetyRating;
    private boolean AWD;
    private double price;
    
    //*UNUSED*//
    private static final String SEDAN = "SEDAN";
    private static final String SUV = "SUV";
    private static final String SPORTS = "SPORTS";
    private static final String MINIVAN = "MINIVAN";
    
    //car constructor
    public Car(String mfr, String color, int power, int numWheels, String model, int maxRange, double safetyRating, String AWD, double price){
        super(mfr,color,power,numWheels);
        this.model = model;
        this.maxRange = maxRange;
        this.safetyRating = safetyRating;
        this.price = price;
        
        if (AWD.equals("AWD")){
            this.AWD = true;
        }
        else{
            this.AWD = false;
        }
    }
    
    //returns price 
    public double getPrice(){
        return this.price;
    }
    
    //return model of car
    public String getModel(){
        return this.model;
    }
    
    //returns if cars has AWD
    public boolean getAWD(){
        return this.AWD;
    }
    
    //returns safety rating of car
    public double getSafetyRating(){
        return this.safetyRating;
    }
    
    //return the max range of the car
    public int getMaxRange(){
        return this.maxRange;
    }
    
    /*returns whether this class is an electric car
     *used for displaying cars with filters
    */
    public boolean isElectric(){
        return false;
    }
    
    //displays car info
    public String display(){
        return super.display()+" "+model+" "+maxRange+" "+safetyRating+" "+price;
    }
    
    //checks if two objects(cars) are the same based on Vehicle's equals(), model, and AWD
    public boolean equals(Object other){
        Car c = (Car)other;
        if (super.equals(c)){
            if (((this.getModel()).equals(c.getModel())) && (this.getAWD() && c.getAWD())){
                return true;
            }
            else{
                return false;
            }
        }
        else {
            return false;
        }
    }
    
    /*Compares the price of two cars
     * 1 = first is greater 
     * -1 = first is less
     * 0 = same price
    */
    @Override
    public int compareTo(Car c){
        if (this.getPrice() > c.getPrice()){
            return 1;
        }
        else if (this.getPrice() < c.getPrice()){
            return -1;
        }
        else {
            return 0;
        }
    }
}
