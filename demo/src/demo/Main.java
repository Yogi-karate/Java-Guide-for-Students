package demo;

import  java.io.*;  

import  java.sql.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  

public class Main{
    public static void main(String[]args){
try{
String filename="C:/Users/ymadiaju/Desktop/data.csv" ;
@SuppressWarnings("resource")
XSSFWorkbook hwb=new XSSFWorkbook();
XSSFSheet sheet =  hwb.createSheet("new sheet");

XSSFRow rowhead=   sheet.createRow((short)0);
rowhead.createCell((short) 0).setCellValue("SNo");
rowhead.createCell((short) 1).setCellValue("Name");

Class.forName("org.postgresql.Driver");
Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
            "postgres", "admin");
Statement st=con.createStatement();
ResultSet rs=st.executeQuery("Select * from student");
int i=1;
while(rs.next()){
XSSFRow row=   sheet.createRow((short)i);
row.createCell((short) 0).setCellValue(Integer.toString(rs.getInt("id")));
row.createCell((short) 1).setCellValue(rs.getString("name"));
i++;
}
FileOutputStream fileOut =  new FileOutputStream(filename);
hwb.write(fileOut);
fileOut.close();
System.out.println("Your excel file has been generated!");

} catch ( Exception ex ) {
    System.out.println(ex);

}
    }
}