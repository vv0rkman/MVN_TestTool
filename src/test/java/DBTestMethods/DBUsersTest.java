/*
 * The MIT License
 *
 * Copyright 2017 Victor.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package DBTestMethods;

import com.vv0rkman.DB.DBOperations;
import java.io.File;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;

/**
 *
 * @author Victor
 */


@SuppressWarnings("static-access")
public class DBUsersTest{
    private static final DBOperations DB = new DBOperations();
    private static IDatabaseTester tester = null;
    private static IDataSet backupDataSet;
    private static IDataSet actualDataSet;
    private static IDataSet newUserDataSet;
    private static IDataSet delUserDataSet;
    private static final String DBCLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DBURL = "jdbc:sqlserver://localhost:1433; database = language;";
    private static final String USERNAME = "universe";
    private static final String PASSWORD = "universe";
    private static final String FILEPATH = "src/test/data/users/";
       
    @BeforeClass
    public static void setUpClass() throws Exception {
        DB.connect();
        
        tester = new JdbcDatabaseTester(DBCLASS, DBURL, USERNAME, PASSWORD);
        tester.setSetUpOperation(DatabaseOperation.NONE);
        tester.setTearDownOperation(DatabaseOperation.CLEAN_INSERT);   

        actualDataSet = tester.getConnection().createDataSet();
        
        //DBDataLoad.loadDB("users");        

        newUserDataSet = new FlatXmlDataSet (new File (FILEPATH+"newUser-dataset.xml"));
        delUserDataSet = new FlatXmlDataSet (new File (FILEPATH+"delUser-dataset.xml"));
        
        backupDataSet = new FlatXmlDataSet (new File (FILEPATH+"users-dataset.xml"));        
        tester.setDataSet(backupDataSet);   
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException {
        DB.close();
    }
    
    @Before
    public void setUp() throws Exception {
        tester.onSetup();
    }
    
    @After
    public void tearDown() throws Exception {
        tester.onTearDown();
    }
   

    /**
     * Test of getUsers method, of class DBOperations.
     * @throws java.lang.Exception
     */
    @Test
    public void testNewUser() throws Exception {  
        DB.newUser("99", "qwerty11", "testName", "testPost");
        
        // user already registered in DB
        Assert.assertTrue(DB.newUser("2", "qwerty11", "testName", "testPost"));
        
        ITable actualTable = actualDataSet.getTable("users");        
        ITable expectedTable = newUserDataSet.getTable("users");  

        Assertion.assertEquals(expectedTable, actualTable);
    } 
    
    @Test
    public void testDelUser() throws Exception{        
        DB.delUser("99");
        
        // user does not registered in DB
        Assert.assertTrue(DB.delUser("128")); 
    
        ITable actualTable = actualDataSet.getTable("users");
        actualTable = DefaultColumnFilter.includedColumnsTable(actualTable, new String[] {"id"});
        
        ITable expectedTable = delUserDataSet.getTable("users");
        expectedTable = DefaultColumnFilter.includedColumnsTable(expectedTable, new String[] {"id"});

        Assertion.assertEquals(expectedTable, actualTable);   
    }
    
    @Test 
    public void srchUser() throws Exception{
        // registered user with incorrect password
        String actualUsrActvt =  DB.srchUser("2", "jjyfuio");
        Assert.assertEquals("0",actualUsrActvt);
        
        // student
        actualUsrActvt =  DB.srchUser("1", "poluyrgh3 ");
        String expectedUsrActvt = backupDataSet.getTable("users").getValue(0, "activity").toString();        
        Assert.assertEquals(expectedUsrActvt,actualUsrActvt);
        
        // teacher
        actualUsrActvt =  DB.srchUser("4", "7heiihjo");
        expectedUsrActvt = backupDataSet.getTable("users").getValue(3, "activity").toString();        
        Assert.assertEquals(expectedUsrActvt,actualUsrActvt);
        
        // unregistered user
        actualUsrActvt =  DB.srchUser("0", "incorrectPassword");
        Assert.assertEquals("0",actualUsrActvt);
    }

}
