package cardealershipsimulator;
//Brenda Tam 
//500901596

/*ElectricCar class extends Car
 *contains methods:
 *      getters for ElectricCar class variables, setter for rechargeTime only
 *      boolean isElectric()
 *      String display()
*/
public class ElectricCar extends Car{
    
    private int rechargeTime;
    private String batteryType;
    
    //ElectricCar constructor
    public ElectricCar(String mfr, String color, int power, int numWheels, String model, int maxRange, double safetyRating, String AWD, double price, int rechargeTime){
        
        super(mfr, color, power, numWheels, model, maxRange, safetyRating, AWD, price);
        this.rechargeTime = rechargeTime;
        this.batteryType = "Lithium";
    }
    
    //sets rechargeTime varable to parameter input
    public void setRechargeTime(int rechargeTime){
        this.rechargeTime = rechargeTime;
    }
    //returns rechargeTime variable
    public int getRechargeTime(){
        return rechargeTime;
    }
    
    //returns numWheels variable
    public String getBatteryType(){
        return batteryType;
    }
    
    /*returns whether this class is an electric car
     *used for displaying cars with filters
    */
    public boolean isElectric(){
        return true;
    }
    
    //displays info for elctric car
    public String display(){
        return super.display()+" RCH:"+rechargeTime+" Bat:"+batteryType;
    }
}
