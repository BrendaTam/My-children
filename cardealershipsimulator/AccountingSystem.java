package cardealershipsimulator;
//Brenda Tam 
//500901596

import java.util.*;

/*AccountingSystem
 *contains methods:
 *      String add()
 *      Transaction getTransaction(int)
 *      void annualTransactions()
 *      void monthlyTransactions()
 *      void monthlyCarTally()
 *      double annualSales()
 *      double monthlySalesAverage()
 *      String carsSoldStat()
 *      void returnTally()
 *      void salesStat()
*/
public class AccountingSystem {
    
    ArrayList<Transaction> transactions;
    Map<String, List<Double>> monthlySales;
    int returns;
    
    //constructor 
    public AccountingSystem(){
        transactions = new ArrayList();
        monthlySales = new TreeMap();
        this.returns = 0;
    }
    
    //creates transactionID and displays the transaction
    public String add(Calendar date, Car car, String salesPerson, String type, double salePrice){
        
        Random r = new Random();
        Transaction transaction = new Transaction((r.nextInt(98)+1), date,car,salesPerson,type,salePrice);
        transactions.add(transaction);
        
        return transaction.display();
    }
    
    //returns transaction ID if it is in the list
    public Transaction getTransaction(int id){
       for (int i = 0; i < transactions.size(); i++){
           if((transactions.get(i)).getTransactionID() == id ){
               return transactions.get(i);
           }
       }
       return null;
    }
    
    //pritns all transactions, used for SALES command
    public void annualTransactions(){
        for(int i = 0; i<transactions.size(); i++){
            System.out.println((transactions.get(i)).display());
        }
    }
    
    //prints all transactions in that month 
    public void monthlyTransactions(int month){
        for(int i = 0; i<transactions.size(); i++){
            if(month == transactions.get(i).getMonth()){
                System.out.println((transactions.get(i)).display());
            }
        }
    }
    
    //adds the price to a list that is related to the month it was sold in
    public void monthlyCarTally(){
        String month = "";
        double price = 0;
        
        for (int i = 0; i<transactions.size(); i++){
            month = transactions.get(i).getMonthString();
            price = transactions.get(i).getCar().getPrice();
            
            //only adds if it is a car bought
            if(transactions.get(i).getType().equals("BUY")){
                if (monthlySales.get(month) == null){
                    monthlySales.put(month, new ArrayList<Double>());
                }
                monthlySales.get(month).add(price);
            }
            //removes the price of the car returned
            else{
                if (!(monthlySales.get(month) == null)){
                    monthlySales.get(month).remove(price);
                }
            }
        }
    }
    
    //calculates and returns the total sales for the year
    public double annualSales(){
        double total = 0;
        Set<String> saleMonths = monthlySales.keySet();
        
        for (String month : saleMonths){
            //goes through the list (value of the map)
            for (int i = 0; i< monthlySales.get(month).size(); i++){
                total += monthlySales.get(month).get(i);
            }
        }
        return total;
    }
    //calculate and returns the average monthly sales 
    public double monthlySalesAverage(){
        return annualSales()/monthlySales.size();
    }
    
    //calculates the highest sales month based on cars and the total cars sold
    //returns either one based on a boolean
    public String carsSoldStats(boolean isTotal){
        int highest = 0;
        int total = 0;
        String highestMonth = "";
        Set<String> months = monthlySales.keySet();
        
        //goes through the map
        for (String month : months){
            total += monthlySales.get(month).size();
            //updates the highest number of cars sold
            if (monthlySales.get(month).size() > highest){
                highest = monthlySales.get(month).size();
                highestMonth = month;
            }
        }
        
        if (isTotal){
            return Integer.toString(total);
        }
        return highestMonth;
    }
    
    //increments the every time a car is returned
    public void returnTally(){
        this.returns++;
    }
    
    //what the SALES STATS command will call
    public void salesStats(){
        monthlyCarTally();
        
        //adds total returned cars to total bought car
        //since it the Map only counts bought cars that have not been returned
        int totalCars = Integer.parseInt(carsSoldStats(true))+this.returns;
        
        System.out.println("Total Sales: "+annualSales()+"\tAverage Sales: "+String.format("%.2f",monthlySalesAverage())+
                "\nTotal Cars Bought: "+totalCars+"\tHighest Sales Month: ["+carsSoldStats(false)
                +monthlySales.get(carsSoldStats(false)).size()+" cars]"+"\nTotal Cars Returned: "+this.returns);
    }
}
