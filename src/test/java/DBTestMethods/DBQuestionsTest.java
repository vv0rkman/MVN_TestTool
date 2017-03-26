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
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Victor
 */


@SuppressWarnings("static-access")
public class DBQuestionsTest{
    private static final DBOperations DB = new DBOperations();
    private static IDatabaseTester tester = null;
    private static IDataSet backupDataSet;
    private static IDataSet actualDataSet;
    private static IDataSet newQuestionDataSet;
    private static IDataSet updQuestionDataSet;
    private static IDataSet delQuestionDataSet;
    private static final String DBCLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DBURL = "jdbc:sqlserver://localhost:1433; database = language;";
    private static final String USERNAME = "universe";
    private static final String PASSWORD = "universe";
    private static final String FILEPATH = "src/test/data/question_answer/";
       
    @BeforeClass
    public static void setUpClass() throws Exception {
        DB.connect();
        
        tester = new JdbcDatabaseTester(DBCLASS, DBURL, USERNAME, PASSWORD);
        tester.setSetUpOperation(DatabaseOperation.NONE);
        tester.setTearDownOperation(DatabaseOperation.CLEAN_INSERT);   

        actualDataSet = tester.getConnection().createDataSet();
        
        //DBDataLoad.loadDB("question_answer");        

        updQuestionDataSet = new FlatXmlDataSet (new File (FILEPATH+"upd_question_answer-dataset.xml"));
        delQuestionDataSet = new FlatXmlDataSet (new File (FILEPATH+"del_question_answer-dataset.xml"));        
        newQuestionDataSet = new FlatXmlDataSet (new File (FILEPATH+"new_question_answer-dataset.xml"));
        
        backupDataSet = new FlatXmlDataSet (new File (FILEPATH+"question_answer-dataset.xml"));        
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
    public void testInsQstn() throws Exception {  
        // create correct question
        DB.insQstn("7", "wrHV9ASq2VmvDkKNNip",
                "0Mg84UHzKwFpz1epnPL",
                "x1dK3qhFoNmHR1H01BT",
                "PUbJA0KLDxMt41qe8cj",
                "PUbJA0KLDxMt41qe8cj",
                "plus");
        
        // insert data into a registered question
        Assert.assertTrue(DB.insQstn("1", "ю", "ы", "0", "я", "0", "test"));
        
        ITable actualTable = actualDataSet.getTable("question_answer");        
        ITable expectedTable = newQuestionDataSet.getTable("question_answer");
        
        Assertion.assertEquals(expectedTable, actualTable);
    } 
    
    @Test 
    public void testUpdQstn() throws Exception{
        // update registered question
        DB.updQstn("3", "Какой из ниже перечисленных операторов, не является циклом в С++?",
                "test1",
                "test2",
                "test3",
                "test4",
                "test");
        
        // update unregistered question
        Assert.assertTrue(DB.updQstn("32676", "Какой из ниже перечисленных операторов, не является циклом в С++?",
                "test1",
                "test2",
                "test3",
                "test4",
                "test"));
        
        ITable actualTable = actualDataSet.getTable("question_answer");        
        ITable expectedTable = updQuestionDataSet.getTable("question_answer");
        
        Assertion.assertEquals(expectedTable, actualTable);
    }
    
    @Test 
    public void testDelQstn() throws Exception{
        // deleting registered user
        DB.delQstn("1");
        DB.delQstn("6");
        
        // deleting nnregistered user
        Assert.assertTrue(DB.delQstn("32765"));        
        
        ITable actualTable = actualDataSet.getTable("question_answer");   
        actualTable = DefaultColumnFilter.includedColumnsTable(actualTable, new String[] {"id"});
        
        ITable expectedTable = delQuestionDataSet.getTable("question_answer");
        expectedTable = DefaultColumnFilter.includedColumnsTable(expectedTable, new String[] {"id"});
        
        Assertion.assertEquals(expectedTable, actualTable);
    }
    
    @Test
    public void testGetCount() throws Exception{
        int actualCount;
        int countPlus = DB.getCount("plus");
        
        ITable actualTable = tester.getConnection().createQueryTable("question_answer",
                "SELECT COUNT(*) AS count FROM question_answer WHERE category = 'plus';");
        
        actualCount = Integer.parseInt(actualTable.getValue(0, "count").toString());
        //System.out.println(actualCount);
        //System.out.println(countPlus);
        
        Assert.assertEquals(actualCount, countPlus);
        
    }
    
}
