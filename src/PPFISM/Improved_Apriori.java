
package PPFISM;
import java.awt.Color;
import java.util.*;
import java.sql.*;
import javax.swing.*;

public class Improved_Apriori extends javax.swing.JFrame {
    
 public static ArrayList<ArrayList<Integer>> transactions=new ArrayList<ArrayList<Integer>>();//To store all the transactions
    public static ArrayList<ArrayList<Integer>> itemsTransactionList=new ArrayList<ArrayList<Integer>>(); //To store the items and their corresponding transaction numbers
public static ArrayList<ArrayList<Integer>> itemsTransactions = new ArrayList<ArrayList<Integer>>();
 public static ArrayList<ArrayList<Integer>> l2=new ArrayList<ArrayList<Integer>>();//To store l2 table
 public static int count[]=new int[500];
 public static String db;
 public static int columns;
 public static int totalTransactions;
 public static int rangeOfItems;
 public static int min_sup;
 public static int noOfTablesGenerated;
 String comboBoxChanged="",comboBoxValue="";
int textBoxValue=0,textBoxChanged=0;
 long time;
 Vector<Vector> data=new Vector<Vector>();
Vector colNames=new Vector();
   
    public Improved_Apriori() {
        initComponents();
        setLocationRelativeTo(null);
        jLabel4.setText("9 transactions");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTimeForExecution = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Improved Apriori Algorithm");
        setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Transaction1", "Transaction2", "Transaction3", " " }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        
        
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Select the database:");
        jLabel1.setForeground(Color.DARK_GRAY);

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField1.setText("3");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Enter the minimum support:");
        jLabel2.setForeground(Color.RED);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Perform Improved Apriori Algorithm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText(" ");
        

        jLabel4.setText("  ");
        jLabel4.setForeground(Color.orange);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTimeForExecution, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(33, 33, 33))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField1)
                        .addComponent(jComboBox1, 0, 191, Short.MAX_VALUE)))
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(39, 39, 39)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(jTimeForExecution)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    void textCheck()
    {
        try
        {
        textBoxValue=Integer.parseInt(jTextField1.getText());
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"The entered value is not an integer assuming minimum support as 3");
            jTextField1.setText("3");
            textCheck();
        }
        return;
    }
    DisplayTables display=null;
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
          try
        {
        textBoxValue=Integer.parseInt(jTextField1.getText());
        }
        catch(Exception e)
        {
            textCheck();
        }
        comboBoxValue=(String)jComboBox1.getSelectedItem();         
        if(textBoxValue==textBoxChanged&&comboBoxValue==comboBoxChanged)
        {                
                display.setVisible(true);
                return;
        }
        else//(textBoxValue!=textBoxChanged&&comboBoxValue!=comboBoxChanged)
        {            
            textBoxChanged=textBoxValue;
            comboBoxChanged=comboBoxValue;
         noOfTablesGenerated=0; 
        
        db = (String) jComboBox1.getSelectedItem();
        MyDataBase.createConnection(db);
        try{
        min_sup=Integer.parseInt(jTextField1.getText());
        }
        catch(NumberFormatException  ne)
        {
                JOptionPane.showMessageDialog(null,"Please enter only integer data in Min_support Field"+ne);
                return;
        }
         switch(db)
        {
            case "Transaction1":  columns=4;
                             rangeOfItems=5;
                             totalTransactions=9;
                            try{
                            min_sup=Integer.parseInt(jTextField1.getText());
                             }
                            catch(NumberFormatException  ne)
                            {
                                    JOptionPane.showMessageDialog(null,"Please enter the minimum support less than "+columns);
                                    return;
                            }
                            break;
            case "Transaction2":rangeOfItems=500;
                        columns=10;              
                        totalTransactions=100;
                        try{
                            min_sup=Integer.parseInt(jTextField1.getText());
                            }
                            catch(NumberFormatException  ne)
                            {
                                    JOptionPane.showMessageDialog(null,"Please enter the minimum support less than "+columns);
                                    return;
                            }
                        break; 
            case "Transaction3":columns=10;
                        rangeOfItems=500;
                        totalTransactions=1000;
                        try{
                                min_sup=Integer.parseInt(jTextField1.getText());
                               
                                }
                                catch(NumberFormatException  ne)
                                {
                                        JOptionPane.showMessageDialog(null,"Please enter the minimum support less than "+columns);
                                        return;
                                }
                        break;            
        }       
         jLabel4.setText(totalTransactions+" transactions");
        long start=System.currentTimeMillis();//To find the time taken for execution
        for(int i=0;i<count.length;i++)
        {
            count[i]=0;
        }    
        try
        {    
        String q2="select * from transactions";
        PreparedStatement stm2=MyDataBase.createStatement(q2);
	ResultSet r2=stm2.executeQuery();
        ResultSetMetaData rsmd2=r2.getMetaData();
        int cols=rsmd2.getColumnCount();
	int val=0,flag=0,i,tid=0;
	while(r2.next())
	{
               
                ArrayList<Integer> al=new ArrayList<Integer>();
		for(int j=1;j<=cols;j++)
                {
                        if(j==1)
                        {                         
                        tid=r2.getInt(1);   
                        al.add(tid);
                        }                                                      
                        else
                        {    
                                val=r2.getInt(j);
                                al.add(val);
                                if(val==0)
                                {                
                                    break;                                
                                }
                                flag=0;                
                                for(i=0;i<itemsTransactionList.size();i++)
                                {                    
                                        if(itemsTransactionList.get(i).get(0)==val)
                                        {
                                        flag=1;
                                        itemsTransactionList.get(i).add(tid);
                                       // System.out.println(itemsTransactionList);
                                        break;
                                        }                                        
                                }
                                
                               if(flag==0)
                                {	
                                        ArrayList al2 = new ArrayList<Integer>();
                                        al2.add(val);
                                        al2.add(tid);
                                        itemsTransactionList.add(al2);    
                                    // System.out.println(al2); 
                                } 
                        }
                }  
         
                transactions.add(al);    
                //System.out.println(al);
                
        }

       // System.out.println(itemsTransactionList);
      //  System.out.println(transactions);
          //  itemsTransactions = itemsTransactionList.clone();
          
            itemsTransactions.clear();
            itemsTransactions = copyArrayListExceptFirstRow(transactions);
            //System.out.println(itemsTransactions);

        r2.close();
        stm2.close();
        }     
        catch(Exception e){} 
           
        for(int k=0;k<transactions.size();k++)
        {
            //System.out.println(transactions.get(k).size());
            for(int l=1;l<transactions.get(k).size();l++)
            {                   
               
                 count[transactions.get(k).get(l)]++;
                 
            }  
             
        } 
        
       
        try
        {    
        String drp="drop table l1";
        MyDataBase.executeUpdate(drp);
        }
        catch(Exception e){}
        try
        {
        String s="create table l1(item1 Integer ,count Integer )";    
        PreparedStatement stmt1=MyDataBase.createStatement(s);
        stmt1.executeUpdate();
        stmt1.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Exception while creating l1 table : "+e);
        }    
        try
        {
        String s1="insert into l1 values(?,?)";
        PreparedStatement ps=MyDataBase.createStatement(s1);
        for(int k=1;k<count.length;k++)
        {
            if(count[k]>=min_sup)
            {    
            ps.setInt(1,k);
            ps.setInt(2,count[k]);
            ps.executeUpdate();
            }
        }  
      /*  for(int k=1;k<count.length;k++)
        {
            System.out.println(count[k]);
        } */
        }
        catch(Exception e){JOptionPane.showMessageDialog(null,"Exception while inserting into l1 : "+e);}
        
        int stop=1 , l;
        for( l=2;l<=10;l++)
        {            
         stop=itemsetGeneration(l);
         if(stop==1||l==10)
         {
             noOfTablesGenerated=l-1;
            display(l-1); 
            break;
         }
            
        }
       // System.out.println(noOfTablesGenerated);
         MyDataBase.close();
         long end=System.currentTimeMillis();
         time=end-start;
         //jTimeForExecution.setSize(0xc);
        jTimeForExecution.setText("Time for execution = "+(end-start)+" ms ("+(float)(end-start)/1000+" seconds)");
        display=new DisplayTables();
        display.setValues(totalTransactions,time,data,colNames,noOfTablesGenerated,db,"ImprovedAprioriAlgorithm");
        display.setVisible(true);
        }
       
    }//GEN-LAST:event_jButton1ActionPerformed

    public static ArrayList<ArrayList<Integer>> copyArrayListExceptFirstRow(ArrayList<ArrayList<Integer>> input) {

        ArrayList<ArrayList<Integer>> copy = new ArrayList<ArrayList<Integer>>();

        for(int i = 0; i < input.size(); i++) {
            ArrayList<Integer> line = new ArrayList<Integer>();

            for(int j = 1; j < input.get(i).size(); j++) { // j = 1 because.... to copyArrayListExceptFirstRow all elements except first
                line.add(input.get(i).get(j));
            }

            copy.add(line);
        }
        return copy;
    }

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
          if(jComboBox1.getSelectedItem()=="Transaction1")
            jLabel4.setText("9 transactions");
        else if(jComboBox1.getSelectedItem()=="Transaction2")
            jLabel4.setText("100 transactions");
        else if(jComboBox1.getSelectedItem()=="Transaction3")
            jLabel4.setText("1000 transactions");
    }//GEN-LAST:event_jComboBox1ActionPerformed

    public static int minArray(int a[])
    {
        int min=a[0],j=0;
        for( int i=1;i<a.length;i++)
        {
            if(a[i]<min)
            {
                min=a[i];             
                j=i;
            }
        }    
        return j;
    }       
    public static int itemsetGeneration(int p)
    {        
        //MyDataBase.commit();
        int stop=1;
        try
	{
		int count1;
                try
                {
                        String drp1="drop table l"+p; // Okavela l2 already unte
                        MyDataBase.executeUpdate(drp1);
                }
                catch(Exception e){}
                String enter="";
                for(int i=1;i<=p;i++)
                {
                    enter+="item"+i+" integer , "; // adds item1,... itemp columns
                }    
               try 
               {
                String q1="create table l"+p+"("+enter+"count integer)";
                MyDataBase.executeUpdate(q1);
               }
               catch(Exception e)
               {
                   JOptionPane.showMessageDialog(null,"Exception while creating l"+p+" : "+e);
               }
                
                String select="";
                int p1,p2;
                p1=p-1;
                p2=p-2;
                for(int j=1;j<=p1;j++)
                {
                        select+=" t1.item"+j+" , ";
                }    
                select+="t2.item"+p1;
                String condition="";
                for(int k=1;k<=p2;k++)
                {
                         condition+=" t1.item"+k+"=t2.item"+k+" and ";
                }    
                condition+="t1.item"+p1+"<t2.item"+p1;   
                String q1="select "+select+" from l"+p1+" as t1 inner join l"+p1+" as t2 on "+condition;
                PreparedStatement stmt2=MyDataBase.createStatement(q1);
                ResultSet rs1=stmt2.executeQuery();
                String s="";
                for(int i=1;i<=p;i++)
                {
                    s+="?,";
                }    
                s+="?";
                String q4="insert into l"+p+" values("+s+")";
                PreparedStatement pstmt2=MyDataBase.createStatement(q4);                
		int a[]=new int[p],j; 
                int b[]=new int[p];
                int m=0;
		while(rs1.next())
		{           
                        for(int i=1;i<=p;i++)
                        {    
			a[i-1]=rs1.getInt(i); // element 
                        b[i-1]=count[a[i-1]]; // count 
                            //System.out.println(a[i-1]);
                           // System.out.println(b[i-1]);
                        
                        }                      
                        int min=minArray(b);
                        
                         boolean flag=false;
                        if(p==2)
                        {         
                            count1=getCount(a,a[min]);                     
                            if(count1>=min_sup) 
                            {    
                                l2.add(new ArrayList<Integer>());
                                for(j=0;j<a.length;j++)
                                {    
                                    pstmt2.setInt(j+1,a[j]);
                                    l2.get(m).add(a[j]);                     
                                }
                                pstmt2.setInt(j+1,count1); 
                                l2.get(m).add(count1);
                                m++;                            
                                int ep=pstmt2.executeUpdate();                       
                                stop=0;                             
                            }
                        }
                        else
                        {    
                            try
                            {                              
                                for(int i=0;i<l2.size();i++)
                                {                                                               
                                       if(l2.get(i).get(0)==a[p-2])
                                       {                                
                                          if(l2.get(i).get(1)==a[p-1])
                                          {    
                                           flag=true;    
                                            break;   
                                          }
                                       }                                                             
                                }
                                if(flag==true)
                                {    
                                 count1=getCount(a,a[min]);   
                                    if(count1>=min_sup)
                                    {
                                        int g;
                                        for(g=0;g<a.length;g++)
                                        {
                                            pstmt2.setInt(g+1,a[g]);
                                        }    
                                        pstmt2.setInt(g+1,count1);
                                        pstmt2.executeUpdate();     
                                        stop=0;
                                    }    
                                }
                            }
                            catch(Exception e)
                            {
                                  JOptionPane.showMessageDialog(null,"Exeception in itemsetGeneration"+p+e);
                            }    
                        }
                 }
                // MyDataBase.commit();
                
                 rs1.close();
                 stmt2.close();
                 pstmt2.close();               
        } 
        catch(Exception e)
        {JOptionPane.showMessageDialog(null,"Exception after condition "+e);}                                
        //System.out.println(l2); 
        return stop;                
    }
    
    public static int getCount(int a[],int b)
	{
		int count=0;                
               
                int trans[]=new int[1000];
		try
		{
                    int available=0;                           
                    int flag;
                    for(int i=0;i<itemsTransactionList.size();i++)
                    {
                        if(itemsTransactionList.get(i).get(0)==b)
                        {    
                        for(int j=0;j<itemsTransactionList.get(i).size();j++)
                        {
                            if(j==0){}
                            else
                            trans[j]=(itemsTransactionList.get(i).get(j));
                        }
                        break;
                        }
                    }
                    int p,q;
                    for(p=0,q=1;p<transactions.size()&&q<trans.length;)
                    {
                        if(transactions.get(p).get(0)==trans[q])
                        {
                                     available=0;
                             for(int j=0;j<a.length;j++)
                             {
                               for(int l=0;l<transactions.get(p).size();l++)
                               {
                                 if(l==0){}
                                 else
                                 if(a[j]==transactions.get(p).get(l))
                                 {
                                    available++;
                                    break;
                                 }    
                               }
                              if(available!=j+1)
                              break;
                             } 
                         if(available==a.length)        
                              count++;	
                         p++;q++;
                        }
                        else
                            p++;
                    }                                                                  
		}
		catch(Exception e)
                {JOptionPane.showMessageDialog(null,"Exception in getCount()"+e);}
		return count;             
	}
    public void display(int p)
    {
            try
            {                
                
                PreparedStatement stmt1=MyDataBase.createStatement("select count(*) from l"+p);
                ResultSet rs1=stmt1.executeQuery();
                rs1.next();
                //int total=rs1.getInt(1);
                rs1.close();
                stmt1.close();
                
                String query="select * from l"+p; 
                PreparedStatement stmt=MyDataBase.createStatement(query);
                ResultSet rs=stmt.executeQuery();             
                ResultSetMetaData rsmd=rs.getMetaData();
                int cols=rsmd.getColumnCount();                
                int i,k,e;   
                data.clear();
                colNames.clear();                
                for(int l=0;l<p;l++)
                {
                       e=l+1;
                    colNames.add("Item"+e);
                }  
                colNames.add("Count");
                k=0;
                while(rs.next())
                {
                     data.add(new Vector());
                    for(int j=1;j<=p+1;j++)
                    {
                        i=rs.getInt(j);
                        data.get(k).add(""+i);
                    }
                    k++;
                }  
               
                rs.close();
                stmt.close();
                l2.clear();
                //transactions.clear();
                //itemsTransactionList.clear();
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"Exception while displaying"+e);
            }
    }
    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Improved_Apriori.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Improved_Apriori.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Improved_Apriori.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Improved_Apriori.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Improved_Apriori().setVisible(true);
            }
        });
    }

    
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel jTimeForExecution;
    
}
