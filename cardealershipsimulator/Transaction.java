package cardealershipsimulator;
//Brenda Tam 
//500901596
import java.util.*;
import java.text.*;

/*Transaction
 *contains methods:
 *      int getTransactionID()
 *      int getMonth()
 *      String getMonthString()
 *      String getType()
 *      Calendar getDate()
 *      Car getCar()
 *      String display()
*/
public class Transaction {
    
    private int transactionId;
    private Calendar date;
    private Car c;
    private String type;
    private String salesPerson;
    private double salePrice;
    private SimpleDateFormat sdf;
    
    //constructor
    public Transaction(int transactionId, Calendar date, Car c, String salesPerson,String type, double salePrice ){
        this.transactionId = transactionId;
        this.c = c;
        this.salesPerson = salesPerson;
        this.type = type;
        this.salePrice = salePrice;
        this.date = date;
        sdf = new SimpleDateFormat("yyyy MMM dd");
    }
    
    //returns transactionID
    public int getTransactionID(){
        return transactionId;
    }
    
    //returns month in the form of an integer (jan = 0)
    public int getMonth(){
        return this.date.get(Calendar.MONTH);
    }
    //returns month in its string rep
    public String getMonthString(){
        return new SimpleDateFormat("MMM").format(this.date.getTime());
    }
    
    //returns the transaction type
    public String getType(){
        return this.type;
    }
    
    //returns the date of the transaction
    public Calendar getDate(){
        return this.date;
    }
    
    //returns the car object associated with the transaction
    public Car getCar(){
        return this.c;
    }
    
    //displays type of transaction and car info with date
    public String display(){
        return ("ID: " + this.transactionId +", "+ sdf.format(date.getTime()) + ", Sales Person: " + this.salesPerson +" "+ this.type + " CAR: VIN:" + c.display());
    }
}
