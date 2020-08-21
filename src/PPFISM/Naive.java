
package PPFISM;
import java.util.*;
import java.sql.*;
import javax.swing.*;
import static PPFISM.Improved_Apriori.itemsTransactions;
import static PPFISM.Improved_Apriori.totalTransactions;
import static PPFISM.DisplayTables.sensitivePatterns;
import static PPFISM.SelectAlgo.psi;
import static PPFISM.Improved_Apriori.db;
import static PPFISM.Improved_Apriori.noOfTablesGenerated;




public class Naive
{   
 
    public void Test()
    {
        
//        System.out.println(noOfTablesGenerated);
//        System.out.println(sensitivePatterns);  // Input from the jtable
//        System.out.println(sensitivePatterns.size());
//        System.out.println("---------------------");
         ArrayList<ArrayList<Integer>> itemsTransactionsClone = copyArrayList(itemsTransactions);
        ArrayList<Integer> sensitiveTransactions = new ArrayList<>();
         
       // System.out.println(itemsTransactionsClone); // All transactions table
        for( int k = 0 ; k < sensitivePatterns.size();k++)
        {
            for( int i = 0 ; i < totalTransactions ; i++)
            {
                if(itemsTransactionsClone.get(i).containsAll(sensitivePatterns.get(k))==true)
                {
                    sensitiveTransactions.add(i+1);
                    
                }
            }
        }
       
//        System.out.println(sensitivePatterns); // Tid's of pattern containing transactions
//        System.out.println("--------------");
//        
        
        
              
        Map<Integer, Integer> map = new HashMap<>(); //https://www.geeksforgeeks.org/sort-elements-by-frequency-set-5-using-java-map/
        
	List<Integer> outputArray = new ArrayList<>(); 

	// Assign elements and their count in the list and map 
	for (Integer current :sensitiveTransactions ) 
        { 
			int count = map.getOrDefault(current, 0); 
			map.put(current, count + 1); 
			outputArray.add(current); 
	} 
                // Sort the map using Collections CLass 
		//Collections.sort(outputArray, comp); 
                SortComparator comp= new SortComparator(map);
          		Collections.sort(outputArray, comp); 
        		Set<Integer> sortedSensitiveTransactionSet = new LinkedHashSet<Integer>(outputArray);
		       for(Integer i : outputArray) 
                       { 
			   sortedSensitiveTransactionSet.add(i);
                	} 
                      // System.out.println(sortedSensitiveTransactionSet);
                       ArrayList<Integer> sortedSensitiveTransactions = new ArrayList<>();
                       for(Integer x: sortedSensitiveTransactionSet)
                       {
                           sortedSensitiveTransactions.add(x);
                       }
        
                       
                      int num  =(int) Math.ceil(sortedSensitiveTransactions.size() * (1 - psi));
                      
                      // System.out.println(num); // Number of transactions to be cleared
                       
       // System.out.println(sortedSensitiveTransactions.subList(0, num)); // Number of transactions to be cleared based on input PSI
         
        ArrayList<Integer> inputSensitiveTransactions = new ArrayList<>(sortedSensitiveTransactions.subList(0, num));
        
        for(int k = 0; k<sensitivePatterns.size();k++)
        {
        for( int i = 0; i<inputSensitiveTransactions.size();i++)
        {
            itemsTransactionsClone.get((inputSensitiveTransactions.get(i)) - 1).removeAll(sensitivePatterns.get(k));
        }
        }
        
//        System.out.println(itemsTransactionsClone); // Final frequent itemsets after privacy preserving
//        System.out.println("ArrayListMaxSize= " +ArrayListMaxSize(itemsTransactionsClone));
//        System.out.println("--------------------");     
        
        ArrayListToTableWithCopy(itemsTransactionsClone , "temp");
        ArrayList<ArrayList<Integer>> Result = tableToArrayListExceptLastColumn("l"+noOfTablesGenerated);
//        System.out.println(sensitivePatterns);
//        System.out.println(Result);
        Result.removeAll(sensitivePatterns);
//        System.out.println(Result);
        ArrayListToTable(Result, "FinalItemSets");
        itemsTransactionsClone.clear();
        Result.clear();
        //JOptionPane.showMessageDialog(null,"Changes have been made to the table and the data is stored in TEMP table");
        //System.exit(0);
        
        

    }
    
    
    
    
    
    
    
    // ------------------------------------------------------------------------------------------------------
    
     public  void ArrayListToTableWithCopy(ArrayList<ArrayList<Integer>> input , String tableName)
     {
         MyDataBase.createConnection(db);
         int size = ArrayListMaxSize(input);
         
         try
        {    
        String drp1="drop table "+tableName;
        String drp2="drop table NaiveOutput";
        MyDataBase.executeUpdate(drp1);
        MyDataBase.executeUpdate(drp2);
        }
        catch(Exception e){}
         
         String createString="" ;

         
         for(int i = 1; i <= size ; i++)
         {
             createString+=", item"+i+" integer  ";
             
         }
         
        // System.out.println("create table tempTable( Tid integer"+createString+")");
         
         try
               {
                String q1="create table "+tableName+"( Tid integer"+createString+")";
                String q2="create table NaiveOutput( Tid integer"+createString+")"; //Cloning the table for future use
                MyDataBase.executeUpdate(q1);
                MyDataBase.executeUpdate(q2);
               }
               catch(Exception e)
               {
                   JOptionPane.showMessageDialog(null,"Exception while creating Table "+e);
               }
          
          try
          {
              for(int i = 0; i<input.size();i++)
              {
                  String insertValues="";
                  String insertString="";
                  
                for(int j=0;j<input.get(i).size();j++)
                {
                    insertString+=", item"+(j+1);
                    insertValues+=","+input.get(i).get(j);
                }    
                
                 // System.out.println("insert into tempTable(tid"+insertString+") values("+(i+1)+insertValues+")");
                
                String str="insert into "+tableName+"(Tid"+insertString+") values("+(i+1)+insertValues+")";
                PreparedStatement ps=MyDataBase.createStatement(str);
                  
                  ps.executeUpdate();
              }
              String cloneTable="insert NaiveOutput select * from "+tableName;
              MyDataBase.executeUpdate(cloneTable);
          }
          catch(Exception e)
          {
              JOptionPane.showMessageDialog(null,"Exception while inserting into tempTable : "+e);
          }
          
         
         
     }
    
     public void ArrayListToTable(ArrayList<ArrayList<Integer>> input , String tableName)
     {
         MyDataBase.createConnection(db);
         int size = ArrayListMaxSize(input);
         
         try
        {    
        String drp1="drop table "+tableName;
        MyDataBase.executeUpdate(drp1);
        }
        catch(Exception e){}
         
         String createString="item1 integer" ;

         
         for(int i = 2; i <= size ; i++)
         {
             createString+=", item"+i+" integer  ";
             
         }
         
        // System.out.println("create table tempTable( Tid integer"+createString+")");
         
         try
               {
                String q1="create table "+tableName+"( "+createString+")";
                
                MyDataBase.executeUpdate(q1);
               
               }
               catch(Exception e)
               {
                   JOptionPane.showMessageDialog(null,"Exception while creating TempTable "+e);
               }
          
          try
          {
              for(int i = 0; i<input.size();i++)
              {
                  String insertValues=""+input.get(i).get(0);
                  String insertString="item1";
                  
                for(int j=1;j<input.get(i).size();j++)
                {
                    insertString+=", item"+(j+1);
                    insertValues+=","+input.get(i).get(j);
                }    
                
                 // System.out.println("insert into tempTable(tid"+insertString+") values("+(i+1)+insertValues+")");
                
                String str="insert into "+tableName+"("+insertString+") values("+insertValues+")";
                PreparedStatement ps=MyDataBase.createStatement(str);
                  
                  ps.executeUpdate();
              }
              
          }
          catch(Exception e)
          {
              JOptionPane.showMessageDialog(null,"Exception while inserting into tempTable : "+e);
          }
          
         
         
     }
     
     
     public int ArrayListMaxSize(ArrayList<ArrayList<Integer>> input)
    {
        int size = 0;
        for(int i = 0; i< input.size();i++)
        {
            if(input.get(i).size() > size)
                size = input.get(i).size();
        }
        
        return size;
    }
     
     public static ArrayList<ArrayList<Integer>> copyArrayList(ArrayList<ArrayList<Integer>> input) {

        ArrayList<ArrayList<Integer>> copy = new ArrayList<ArrayList<Integer>>();

        for(int i = 0; i < input.size(); i++) {
            ArrayList<Integer> line = new ArrayList<Integer>();

            for(int j = 0; j < input.get(i).size(); j++) 
            { 
                line.add(input.get(i).get(j));
            }

            copy.add(line);
        }
        return copy;
    }
     
     
    public static ArrayList<ArrayList<Integer>> tableToArrayListExceptLastColumn(String input)
     {
         ArrayList<ArrayList<Integer>> output = new ArrayList<ArrayList<Integer>>();
         MyDataBase.createConnection(db);
         String table = input;
         try
         {
             String q="select * from "+table;    
        PreparedStatement stm=MyDataBase.createStatement(q);
	ResultSet r=stm.executeQuery();
        ResultSetMetaData rsmd=r.getMetaData();
        int cols=rsmd.getColumnCount();
        
        while(r.next())
        {
            ArrayList<Integer> singleRow=new ArrayList<Integer>();
            for(int i = 1; i < cols ; i++) // i < cols because... we do not need the last column values
            {
                singleRow.add(r.getInt(i));
            }
            
            output.add(singleRow);
        }
        
         }
         catch(Exception e)
         {
             
         }
         
         
         return output;
     }
     
    

    
}






// Implement Comparator Interface to sort the values 
class SortComparator implements Comparator<Integer> { 
	private final Map<Integer, Integer> freqMap; 

	// Assign the specified map 
	SortComparator(Map<Integer, Integer> tFreqMap) 
	{ 
		this.freqMap = tFreqMap; 
	} 

	// Compare the values 
	@Override
	public int compare(Integer k1, Integer k2) 
	{ 

		// Compare value by frequency 
		int freqCompare = freqMap.get(k2).compareTo(freqMap.get(k1)); 

		// Compare value if frequency is equal 
		int valueCompare = k1.compareTo(k2); 

		// If frequency is equal, then just compare by value, otherwise - 
		// compare by the frequency. 
		if (freqCompare == 0) 
			return -valueCompare; 
		else
			return freqCompare; 
	} 
} 
