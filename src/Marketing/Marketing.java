/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Marketing;

/**
 *
 * @author Zizo
 */


import java.sql.*;
import DatabaseConncection.*;
import java.time.LocalDate;
import java.util.Vector;



public class Marketing {
    
    DatabaseConnection dc=new DatabaseConnection("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost:3306/market","root", "");
    
    ResultSet rs;
    
    public Vector makeReports(String name) {
        Vector v=new Vector();
        
        Date date;
        
        try {
            rs = dc.executeQuery("SELECT * from stock where name ='"+name+"'");
            
            if(rs.next())
            {
                double p=rs.getDouble("price");
                double o=rs.getDouble("offer");
                
                o=p*o/100;
                p-=o;
                
                v.add(rs.getString("id"));
                v.add(rs.getString("name"));
                v.add(rs.getString("category"));
                date=rs.getDate("production_date");
                v.add(date.toLocalDate().toString());
                date=rs.getDate("expiry_date");
                v.add(date.toLocalDate().toString());
                v.add(String.valueOf(p));
                v.add(rs.getString("quantity"));
                v.add(rs.getString("damages"));
                v.add(rs.getString("shortage"));
                v.add(rs.getString("offer"));
            }
            else
            {
                v.add(0,-1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return v;
    }
    
    public int makeOffer(String product_name,double offer,String ldate){
         try {
             rs=dc.executeQuery("select offer,offerEndDate from stock where name ='"+product_name+"'");
             if(rs.next())
             {
                if(Double.parseDouble(rs.getString("offer"))>0)
                    if(OfferEnded(LocalDate.parse(rs.getString("offerEndDate"))))
                        return dc.excuteUpdate("update stock set offer='"+offer+"',offerEndDate='"+LocalDate.parse(ldate)+"' where name ='"+product_name+"'");
                    else
                        return 0;
                else
                    return dc.excuteUpdate("update stock set offer='"+offer+"',offerEndDate='"+LocalDate.parse(ldate)+"' where name ='"+product_name+"'");
             }
         }     
         catch (Exception e){
             System.out.println(e.getMessage());
        }
        return 0;
    }

    public boolean OfferEnded(LocalDate endDate)
    {
        return (endDate.isBefore(LocalDate.now()));
        //endDate.equals(LocalDate.now())
    }
}
    
     
     
    

