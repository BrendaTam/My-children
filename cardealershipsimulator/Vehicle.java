package cardealershipsimulator;
import java.util.*;
//Brenda Tam 
//500901596

/*Vehicle class
 *contains methods:
 *      setters and getters for each variable(not constants)
 *      boolean equals(Object)
 *      String display()
*/
public class Vehicle{
    
    private String mfr;
    private String color;
    private int power;
    private int numWheels;
    private int Vin;
    
    //constant variables -  *UNUSED*
    private static final int ELECTRIC_MOTOR = 0;
    private static final int GAS_ENGINE = 1;

    // Vehicle class constructor
    public Vehicle(String mfr, String color, int power, int numWheels){
        this.mfr = mfr;
        this.color = color;
        this.power = power;
        this.numWheels = numWheels;
        
        Random r = new Random();
        this.Vin = r.nextInt(399)+100;
    }
    
    //sets mfr varable to parameter input
    public void setMfr(String mfr){
        this.mfr = mfr;
    }
    //returns mfr variable
    public String getMfr(){
        return this.mfr;
    }
    
    //sets color varable to parameter input
    public void setColor(String color){
        this.color = color;
    }
    //returns color variable
    public String getColor(){
        return this.color;
    }
    
    //sets power varable to parameter input
    public void setPower(int power){
        this.power = power;
    }
    //returns power variable
    public int getPower(){
        return this.power;
    }
    
    //sets numWheels varable to parameter input
    public void setNumWheels(int numWheels){
        this.numWheels = numWheels;
    }
    //returns numWheels variable
    public int getNumWheels(){
        return this.numWheels;
    }
    
    //returns VIN variable
    public int getVin(){
        return this.Vin;
    }
    
    // checks if two vehicles are the same based on their power, number of wheels, and manufacturer
    @Override
    public boolean equals(Object other){
        Vehicle v = (Vehicle) other;
        
        if ((v.getPower()==this.getPower()) && (v.getNumWheels()==this.getNumWheels()) && ((v.getMfr()).equals(this.getMfr()))){
            return true;
        }
        else{
            return false;
        }
    }
    
    // return manufacturer name and color of vehicle
    public String display(){
        return (this.Vin +" " +this.mfr + " " + this.color);
    }
    
}
