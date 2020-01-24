package cardealershipsimulator;
//REMOVE PACKAGE IN ALL
//Brenda Tam 
//500901596
import java.util.*;
import java.io.*;

public class CarDealershipSimulator 
{
  public static void main(String[] args)
  {
    //New CarDealership object
    CarDealership carDealership = new CarDealership();
    //list to hold the car objects
    ArrayList<Car> cars = new ArrayList<Car>();
    
    //reads from cars.txt to fill the car inventory
    try{
        //cars.txt scanner
        Scanner fileReader = new Scanner(new File("cars.txt"));
        
        while(fileReader.hasNextLine()){
            String fileLine = fileReader.nextLine();
            String[] carInfo = fileLine.split("\\s+");
            
            int engine = 1;
            String mfr = carInfo[0];
            String color = carInfo[1];
            String model = carInfo[2];
            int maxRange = Integer.parseInt(carInfo[5]);
            double safetyRating = Double.parseDouble(carInfo[4]);
            String AWD = carInfo[6];
            double price = Double.parseDouble(carInfo[7]);
            
            //for eletric vehicle
            if (carInfo[3].equals("ELECTRIC_MOTOR")){
                engine = 0;
                int rechargeTime = Integer.parseInt(carInfo[8]);
                cars.add(new ElectricCar(mfr, color, engine, 4, model, maxRange, safetyRating, AWD, price, rechargeTime));
            }
            //for gas cars
            else{
                cars.add(new Car(mfr, color, engine, 4, model, maxRange, safetyRating, AWD, price));
            }
        }
    }
    catch (FileNotFoundException e){
        System.out.println("File not found!");
    }

    //initializing variables that will be used within the while-loop
    Car lastCar = null;
    int index = 0;
    double minPrice;
    double maxPrice;
    
    SalesTeam salesTeam = carDealership.getSalesTeam();
    AccountingSystem salesAccount = carDealership.getSalesAccount();
    
    
    Scanner sc = new Scanner(System.in);
    
    while (sc.hasNextLine()){
        //DONT MOVE CALENDAR OUTSIDE LOOP, ELSE DATE WILL POINT TO SAME REFERENCE VAIABLE
        Calendar calendar = new GregorianCalendar();
        String input = sc.nextLine();
        Scanner commandLine = new Scanner(input);
        
        //checks if there is a command inputed
        if (!(input.isEmpty())){
            String command = commandLine.next().toUpperCase();
            
        //COMMANDS
            //inventory display
            if (command.equals("L")){
                carDealership.displayInventory();
            }
            //quit program
            if (command.equals("Q")){
                return;
            }
            //buys car
            try{
            if (command.equals("BUY")){
                //checks to see if index number has been entered
                if (commandLine.hasNext()){
                    index = commandLine.nextInt();
                    //checks if there are cars in the dealership 
                    if (!((carDealership.getCars()).isEmpty())){
                        System.out.println(carDealership.buyCar(index));
                    }
                }
            }
            }
            //tells user if the VIN entered exists
            catch(NullPointerException e){
                System.out.println("The entered VIN does not exist!");
            }
            //returns car
            try{
            if (command.equals("RET")){
                if (commandLine.hasNext()){
                    index = commandLine.nextInt();
                    carDealership.returnCar(index);
                }
            }
            }
            //tells user if the ID entered exists as a RET ID
            catch(NullPointerException e){
                System.out.println("The entered transaction ID is not valid for return!");
            }
            //adds list of cars
            if (command.equals("ADD")){
                carDealership.addCars(cars);
            }
        //SORTERS
            //sorts by price
            if (command.equals("SPR")){
                carDealership.sortByPrice();
            }
            //sorts by safety rating
            if (command.equals("SSR")){
                carDealership.sortBySafetyRating();
            }
            //sorts by max range
            if (command.equals("SMR")){
                carDealership.sortByMaxRange();
            }
        //FILTERS
            //filters by price given a range
            if (command.equals("FPR")){
                //chekcs if user inputed price
                if(commandLine.hasNext()){
                    minPrice = commandLine.nextInt();
                    maxPrice = commandLine.nextInt();
                    carDealership.filterByPrice(minPrice, maxPrice);
                }
            }
            //filters non-electric cars out
            if (command.equals("FEL")){
                carDealership.filterByElectric();
            }
            //filters non-AWD cars out
            if (command.equals("FAW")){
                carDealership.filterByAWD();
            }
            //turns off all current filters
            if (command.equals("FCL")){
                carDealership.filtersClear();
            }
        //SALES COMMANDS
            if(command.equals("SALES")){
                if(commandLine.hasNext()){
                    command = commandLine.next().toUpperCase();
                    
                    //prints all names on the employee list
                    if(command.equals("TEAM")){
                        System.out.println(salesTeam.teamDisplay());
                    }
                    //prints the employee who sold the most cars for the year (print name and number of cars sold)
                    else if(command.equals("TOPSP")){
                        salesTeam.bestEmployee();
                    }
                    //prints stats concerning the sales
                    else if(command.equals("STATS")){
                        try{
                            salesAccount.salesStats();
                        }
                        catch(NullPointerException e){
                            System.out.println("No transactions were made this year!");
                        }
                    }
                    //"SALES m"; prints all the transactions for the month
                    else {
                        //tells user which numbers that are valid to enter when they enter an invalid number
                        try{
                            index = Integer.parseInt(command);
                            salesAccount.monthlyTransactions(index);
                        }
                        catch(NumberFormatException e){
                            String[] months = {"Jan","Feb","Mar","Apr","May","Jun",
                                               "Jul","Aug","Sep","Oct","Nov","Dec"};
                            
                            System.out.println("A valid number was not entered, please enter one of the following numbers:");
                            for(int i = 0; i<12; i++){
                                if (i % 2 == 0){
                                    System.out.print("[" + i + " = " + months[i] + "]");
                                }
                                else{
                                    System.out.println("\t[" + i + " = " + months[i] + "]");
                                }
                            }
                        }
                    }
                }
                //this is the "SALES" command; displays all transactions for this year
                else{
                    salesAccount.annualTransactions();
                }
            }
        }
    }
  }
}
