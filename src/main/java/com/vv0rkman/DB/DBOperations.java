package com.vv0rkman.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author vv0rkman
 * @version 1.2
 */
public class DBOperations {
    static Connection con;
/**
 * Connecting to DB
 */    
    public static void connect() {          
        String login = "universe";
        String pass = "universe";
        
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; database = language;", login, pass);              
        }
        catch(SQLException sqle) {
        System.out.println("Sql Exception: "+sqle.getMessage());
        }
        catch(ClassNotFoundException e) {
        System.out.println("Class Not Found Exception: " + e.getMessage());
        }      
}
/**
 * Closing connection with DB
 * @throws SQLException 
 */    
    public static void close() throws SQLException {
        con.close();
    }
    
/**
 * Gets all questions of "cat" category
 * @param cat Category of questions
 * @return ResultSet data of all questions
 * @throws SQLException 
 */
    public static ResultSet getQstns(String cat) throws SQLException {    
        if (cat != null) 
            return con.createStatement().executeQuery("SELECT * FROM question_answer "+cat); 
        else 
            return con.createStatement().executeQuery("SELECT * FROM question_answer;"); 
    }
/**
 * Gets all of users data
 * @return ResultSet data of all users
 * @throws SQLException 
 */    
    public static ResultSet getUsers() throws SQLException {            
        return con.createStatement().executeQuery("SELECT * FROM users;"); 
    }
/**
 * Gets all of results
 * @return ResultSet data of all results
 * @throws SQLException 
 */   
    public static ResultSet getRes() throws SQLException {            
        return con.createStatement().executeQuery("SELECT * FROM results;"); 
    }
/**
 * Get count of questions in "cat" category
 * @param cat Category of questions
 * @return Count of questions
 * @throws SQLException 
 */    
    public static int getCount(String cat) throws SQLException {
        ResultSet rs;
        if (cat != null) rs = con.createStatement().executeQuery("SELECT COUNT(*) FROM question_answer "+cat);
        else rs = con.createStatement().executeQuery("SELECT COUNT(*) FROM question_answer;");
        if (rs.next()) return rs.getInt(1); else return 0;
    }
/**
 * Search post of user
 * @param id User ID
 * @param pass User password
 * @return Post of user or zero, if him not found
 * @throws SQLException 
 */    
    public static String srchUser (String id, String pass) throws SQLException{
        ResultSet rs;
        rs = con.createStatement().executeQuery("SELECT activity FROM users WHERE id = '"+id+"' AND password = '"+pass+"';");
        if (rs.next()) return rs.getNString(1); else return "0";
    }
/**
 * Saving results of testing
 * @param trueCount Count of correct answers
 * @param id User ID
 * @param cat Category of testing
 * @return True if user not found in table
 * @throws SQLException 
 */    
    public static boolean sendRes(byte trueCount, String id, String cat) throws SQLException {
        return con.createStatement().execute("IF EXISTS ("
                + "SELECT id FROM results "
                + "WHERE id = '"+id+"' AND category = '"+cat+"') "
                + "UPDATE results SET result = '"+trueCount+"' "
                + "WHERE id = '"+id+"' AND category = '"+cat+"' "
                + "ELSE "
                + "INSERT INTO results VALUES ('"+id+"', '"+cat+"','"+trueCount+"'); ");
    }
/**
 * Executes query of inserting new user in DB
 * @param id User ID
 * @param pass User password
 * @param name User name (first and second names)
 * @param post Post of user
 * @return True if user found in table
 * @throws SQLException 
 */    
    public static boolean newUser(String id, String pass, String name, String post) throws SQLException {
        return con.createStatement().execute("IF NOT EXISTS ("
                        + "SELECT * FROM users "
                        + "WHERE id = '"+id+"') "
                        + "INSERT INTO users VALUES ('"+id+"', '"+pass+"','"+name+"', '"+post+"'); "
                        + "SELECT id FROM users WHERE id='1';");           
    };
/**
 * Removes user from DB
 * @param id User ID
 * @return True if user not found in table
 * @throws SQLException 
 */    
    public static boolean delUser(String id) throws SQLException {
        return con.createStatement().execute(
                "IF EXISTS ("
                + "SELECT id FROM users "
                + "WHERE id = '"+id+"') "
                + "DELETE FROM users WHERE id = '"+id+"';"
                        + "SELECT id FROM users WHERE id='1';");
    }
/**
 * Updates question and answers in DB
 * @param id Identify of question
 * @param q Text of question
 * @param a1 Answer option 1
 * @param a2 Answer option 2
 * @param a3 Answer option 3
 * @param correct Correct answer
 * @param cat Category of question
 * @return True if question not found in table
 * @throws SQLException 
 */    
    public static boolean updQstn(String id, String q, String a1, String a2, String a3, String correct, String cat) throws SQLException {
        return con.createStatement().execute("IF EXISTS ("
                + "SELECT id FROM question_answer "
                + "WHERE id = '"+id+"') "
                + "UPDATE question_answer "
                + "SET id = '"+id+"', "
                + "question = '"+q+"', "
                + "answer1 = '"+a1+"', "
                + "answer2 = '"+a2+"', "
                + "answer3 = '"+a3+"', "
                + "correct = '"+correct+"', "
                + "category = '"+cat+"' "
                + "WHERE id = '"+id+"';"
                    + "SELECT id FROM question_answer WHERE id='1';");
    }
/**
 * Executes query of inserting new question in DB
 * @param id Identify of question
 * @param q Text of question
 * @param a1 Answer option 1
 * @param a2 Answer option 2
 * @param a3 Answer option 3
 * @param correct Correct answer
 * @param cat Category of question
 * @return True if question found in table
 * @throws SQLException 
 */   
     public static boolean insQstn(String id, String q, String a1, String a2, String a3, String correct, String cat) throws SQLException {
        return con.createStatement().execute("IF NOT EXISTS ("
                + "SELECT * FROM question_answer "
                + "WHERE id = '"+id+"') "
                + "INSERT INTO question_answer VALUES ("
                + "'"+id+"', "
                + "'"+q+"', "
                + "'"+a1+"', "
                + "'"+a2+"', "
                + "'"+a3+"', "
                + "'"+correct+"', "
                + "'"+cat+"');"
                    + "SELECT id FROM question_answer WHERE id='1';");
    }
/**
 * Executes query of removing question in DB
 * @param id ID of question what will be removed
 * @return True if question not found in table
 * @throws SQLException 
 */     
     public static boolean delQstn(String id) throws SQLException {
        return con.createStatement().execute(
                "IF EXISTS ("
                + "SELECT id FROM question_answer "
                + "WHERE id = '"+id+"') "
                + "DELETE FROM question_answer WHERE id = '"+id+"';"
                        + "SELECT id FROM question_answer WHERE id='1';");
    }
    
}
