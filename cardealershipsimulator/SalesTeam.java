
package cardealershipsimulator;
//Brenda Tam 
//500901596
import java.util.*;

/*SalesTeam
 *contains methods:
 *      String getRandomEmployee()
 *      String teamDisplay()
 *      void employeeSalesTally(String)
 *      void bestEmployee()
*/
public class SalesTeam {
    LinkedList<String> employees;
    ListIterator<String> itr;
    Map<String,Integer> employeeSales;
    
    //constructor, also adds names to employees
    public SalesTeam(){
        employees = new LinkedList();
        employeeSales = new TreeMap();
        
        employees.add("Janet");
        employees.add("Aiya");
        employees.add("Mensher");
        employees.add("Skulltulon");
        employees.add("Lisa");
        
        itr = employees.listIterator();
    }
    
    //returns a random employee
    public String getRandomEmployee(){
        Random r = new Random();
        return employees.get(r.nextInt(employees.size()-1));
    }
    
    //displays alll the employsees on the team
    public String teamDisplay(){
        String team = "";
        while(itr.hasNext()){
            team += ("  " +itr.next() + "\n");
        }
        return team;
    }
    
    //keeps track of who sold how many cars
    public void employeeSalesTally(String employee){
        Integer carsSold = employeeSales.get(employee);
        if (carsSold == null){
            carsSold = 1;
        }
        else{
            carsSold++;
        }
        employeeSales.put(employee, carsSold);
    }
    
    //displays which employee sold the most cars
    public void bestEmployee(){
        int largest = 0;
        Set<String> employeeNames = employeeSales.keySet();
        
        //determines the largest amount of cars sold by an employee
        for (String name : employeeNames){
            int value = employeeSales.get(name);
            if (value > largest){
                largest = value;
            }
        }
        //checks if more than one person has sold the "largest" amount
        for (String name : employeeNames){
            int value = employeeSales.get(name);
            if(value == largest){
                System.out.println(name + ": " + largest);
            }
        }
    }
}
