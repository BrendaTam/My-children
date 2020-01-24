package cardealershipsimulator;
//Brenda Tam 
//500901596

import java.util.*;

/*CarDealership
 *contains methods:
 *      getter for list "cars"
 *      void addCars(ArrayList<Car>)
 *      Car buyCar(int)
 *      void returnCar(int)
 *      void displayInventory()
*/
public class CarDealership {
    ArrayList<Car> cars;
    SalesTeam salesTeam;
    AccountingSystem salesAccount;
    
    //filter variables
    boolean fElec = false;
    boolean fAWD = false;
    boolean fPrice = false;
        
    double minPrice = 0;
    double maxPrice = 0;
    
    //CarDealership constructor
    public CarDealership(){
        cars = new ArrayList();
        salesTeam = new SalesTeam();
        salesAccount = new AccountingSystem();
    }
    
    //adds a new list of cars
    public void addCars(ArrayList<Car> newCar){
        cars.addAll(newCar);
    }
    
    //buy car based on the entered VIN, removes car from list
    public String buyCar(int VIN) throws NullPointerException{
        Calendar cal = new GregorianCalendar();
        Car carBought = null;
        
        //the actual buy/ removal from inventory
        for (int i = 0; i<cars.size(); i++){
            if (VIN == cars.get(i).getVin()){
                carBought = cars.get(i);
                cars.remove(i);
            }
        }
        String employee = salesTeam.getRandomEmployee();
        //for SALES TOPSP; increments tally of employee car sales
        salesTeam.employeeSalesTally(employee);
        
        //randomly generates a date
        Random r = new Random();
        int month = r.nextInt(12);
        int day = r.nextInt(cal.getActualMaximum(month)+1);
        
        cal.set(Calendar.YEAR, 2019);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        
        //adds transaction to the list of transadctions and displays info
        return salesAccount.add(cal, carBought, employee, "BUY", carBought.getPrice());
    }
    
    //adds a returned car back into the list, based on the transaction ID
    public void returnCar(int transaction) throws NullPointerException{
        Transaction t = salesAccount.getTransaction(transaction);
        Calendar cal = new GregorianCalendar();
        
        //only looks at the BUY so that you cannot return an already returned vehicle
        if ((t.getType().equals("BUY"))){
            //randomly generates a date between the end of the month and the buy day
            Random r = new Random();
            int buyDay = Calendar.DAY_OF_MONTH;
            int maxDay = t.getDate().getActualMaximum(buyDay);
            int currentDay = r.nextInt(maxDay-buyDay) + buyDay;
            
            int month = t.getDate().get(Calendar.MONTH);
            int year = t.getDate().get(Calendar.YEAR);
            cal.set(year,month,currentDay);
            
            //adds a RET transaction
            String employee = salesTeam.getRandomEmployee();
            Car car = t.getCar();
            salesAccount.add(cal, car, employee, "RET", car.getPrice());
            
            cars.add(car);
            //adds to total returns
            salesAccount.returnTally();
            
            System.out.println(t.display());
        }
    }
    
    //getter for cars list
    public ArrayList<Car> getCars(){
        return this.cars;
    }
    //getter for salesAccount object
    public AccountingSystem getSalesAccount(){
        return salesAccount;
    }
    //getter for salesTeam object
    public SalesTeam getSalesTeam(){
        return salesTeam;
    }
    
    //displays inventory based on filters set to true
    public void displayInventory(){
        
        Car currentCar;
        boolean output = true;
        
        //displays only if list is not empty
        if (!(this.cars).isEmpty()){
            for (int i = 0; i<cars.size(); i++){
                
                currentCar = cars.get(i);
                output = true;
                
                //if electric car filter is on
                if((fElec) && (output)){
                    if(!(currentCar.isElectric())){
                        output = false;
                    }
                }
                //if AWD filter is on
                if((fAWD) && (output)){
                    if(!(currentCar.getAWD())){
                        output = false;
                    }
                }
                //if price filter is on
                if((fPrice) && (output)){
                    if (((currentCar.getPrice()) < minPrice) || (currentCar.getPrice() > maxPrice)){
                        output = false;
                    }
                }
                //outputs only if currentCar meets the conditioins of the filters applied
                if (output){
                    System.out.println("VIN: "+ currentCar.display());
                }
                
            }
        }
        else{
            System.out.println("No cars here!");
        }
    }
    
//FILTERS
    //turn on electric car filter
    public void filterByElectric(){
        fElec = true;
    }
    //turns on AWD filter
    public void filterByAWD(){
        fAWD = true;
    }
    //turns on price filter and sets minPrice/maxPrice
    public void filterByPrice(double minPrice, double maxPrice){
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        fPrice = true;
    }
    //turns off all filters
    public void filtersClear(){
        fElec = false;
        fAWD = false;
        fPrice = false;
    }
    
//SORTERS
    //sorts using compareTo() in Car class
    public void sortByPrice(){
        Collections.sort(cars);
    }
    //next two methods uses their own corresponding inner classes to sort
    public void sortBySafetyRating(){
        Collections.sort(cars, new SafetySort());
    }
    public void sortByMaxRange(){
        Collections.sort(cars, new MaxRangeSort());
    }
    
    //Class to compare safety rating, orders from least to most safe
    class SafetySort implements Comparator<Car>{
        
        @Override
        public int compare(Car c1, Car c2){
            if (c1.getSafetyRating() < c2.getSafetyRating()){
                return -1;
            }
            else if (c1.getSafetyRating() > c2.getSafetyRating()){
                return 1;
            }
            else{
                return 0;
            }
        }
    }
    //Class to compare max range, orders from least to highest range
    class MaxRangeSort implements Comparator<Car>{
        
        @Override
        public int compare(Car c1, Car c2){
            if (c1.getMaxRange() < c2.getMaxRange()){
                return -1;
            }
            else if (c1.getMaxRange() > c2.getMaxRange()){
                return 1;
            }
            else{
                return 0;
            }
        }
    }
}
