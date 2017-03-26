/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBTestMethods;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

/**
 *
 * @author Victor
 */
public class DBDataLoad {
    static String filePath = "src/test/data/";
    
    public static void loadDB(String tableName) throws Exception{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection jdbcConnection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; database = language;", "universe", "universe");
	IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);    
        QueryDataSet partialDataSet = new QueryDataSet(connection);
        partialDataSet.addTable(tableName); 
        //CSV
        //CsvDataSetWriter.write(partialDataSet, new File(filePath));
        //XML
        FlatXmlDataSet.write(partialDataSet, new FileOutputStream(filePath+tableName+"/"+tableName+"-dataset.xml"));
        System.out.println(filePath+tableName+"/"+tableName+"-dataset.xml");
        //XLS
        //XlsDataSet.write(partialDataSet, new FileOutputStream("src/test/data/DB-dataset.xls"));
    }    
}
