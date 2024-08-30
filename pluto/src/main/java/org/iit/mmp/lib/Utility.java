package org.iit.mmp.lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utility {

	public static String generateFutureDate(int n,String format )
	{

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, n);
		Date d = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String formattedDate = sdf.format(d);
		System.out.println("Formatted Date:::" + formattedDate);
		return formattedDate;
	}

	public static String[][] readXLSX(String fileName,String sheetName) throws IOException
	//public static void main(String args[]) throws IOException
	{
		File f = new File(fileName);
		FileInputStream fis = new FileInputStream(f);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet(sheetName);
		int rows = sheet.getPhysicalNumberOfRows();
		int cols = sheet.getRow(0).getPhysicalNumberOfCells();
		String dataArr[][] = new String[rows][cols];
		for(int i=0;i<rows;i++) {

			for(int j=0;j<cols;j++) {

				XSSFCell cell = sheet.getRow(i).getCell(j);
				CellType ctype=	cell.getCellType();
				switch(ctype)
				{
					case STRING:
						dataArr[i][j] =	cell.getStringCellValue();
						break;
					case NUMERIC:
						dataArr[i][j] =	cell.getNumericCellValue()+"";
						break;
				}

				System.out.println(dataArr[i][j]);
			}


		}
		return dataArr;


	}
	public static String generateRandomString()
	{
		
		Random rand = new Random();
		int n = rand.nextInt(1000);//0 to 999
		int upperCaseChar = 65+ rand.nextInt(26);
		int lowerCaseChar = 97+ rand.nextInt(26);
		String randString =(char)upperCaseChar+(char)lowerCaseChar+n+"";
		System.out.println("Random String:::"+ randString);
		return randString;
	}
	public static String[][] getDBValues(String uname,String pword,String dbname,String tableName,String hostip) throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		//	Driver driver = new Driver();
		/*
		 * url a database url of the form jdbc:subprotocol:subnameuser 
		 * the database user on whose behalf the connection is being madepassword 
		 * the user's password
		 */
		String url="jdbc:mysql://"+hostip+":3306/"+dbname;
		String username=uname;
		String password=pword;

		Connection con = DriverManager.getConnection(url, username, password);
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		//int  value = stmt.executeUpdate("INSERT INTO `mmp`.`patient_data` VALUES (11,'James','22/11/2021');");
		//System.out.println("The rows are updated "+ value);

		ResultSet rs =  stmt.executeQuery("Select * from "+dbname+"."+tableName);
		rs.last();

		int rows = rs.getRow();
		System.out.println("Number of rows " + rows);
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		System.out.println("Number of cols: "+ cols);

		String data[][]= new String[rows][cols];
		int i=0;
		rs.beforeFirst();
		///System.out.println(rs.getMetaData());
		
		while(rs.next())
		{
			for(int j=0;j<cols;j++)
			{

				data[i][j]=rs.getString(j+1);
				System.out.print(data[i][j]+"--");
				System.out.print("i :::"  + i +"@@@@"+"j:::::" + j);

			}
			System.out.println();
			i++;
		} 
		return data;
	}

}

