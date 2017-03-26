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
package MainTestMethods;

import com.vv0rkman.mvntesttool.AdminFrame;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

/**
 * Testing method that trims the length of string
 * @author Victor
 */
@RunWith(Parameterized.class)
@SuppressWarnings("static-access")
public class AFTrimmingLengthsTest {
    AdminFrame lengthTrimmer;  
    
    @Before
    public void setUp() {               
        lengthTrimmer = new AdminFrame();
    }
    
    /**
     * String for a test
     */
    @Parameter(value = 0)
    public String inputStr;

    /**
     * Correct string
     */
    @Parameter(value = 1)
    public String expectedStr;
    
    /**
     * Correct value of a string length
     */
    @Parameter(value = 2)
    public int cl;
    
    /**
     * Test data
     * @return String for a test and a string what a expected
     */    
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {  
            //letters
            {"mDTUjLlbfkXMgotrXUhZw", "mDTUjLlbfkXMgotrXUhZ", 20},
            {"ркгаОрНроудЗъоНиыоШцб", "ркгаОрНроу", 10},
            {"kvыSuBЮIskFЭЭPqupVog", "kvыSuBЮIskFЭЭPqupV",18},            
            {"ыZ", "ы", 1},
            //numbers
            {"397253581476256096014", "39", 2},
            {"853813721608746228961046926446", "8538137216087416", 15},
            {"99dlEnWIWQnYLWdeMxd67kwyghBFFcaxdsHcZvkHEJzQjIhE5lJ2nuHJnxfWvHBPM33ynx0", 
                "99dlEnWIWQnYLWdeMxd67kwyg", 25},
            //letters + symbols
            {"%?ЪMJwja", "%?ЪMJwj", 7},
            {"ю*", "ю", 1},            
            //symbols + numbers
            {")'%~@*_-2#310{.0:-(0.\\\\'$[@5+}54<!43!6!+:", ")'%~@*_-2#31", 12},
            {"*2_-2#310{.0:-(0.\\\\'$[_-2#310{.0:-(0.\\\\'$[_-2#310{.0:-(0.\\\\'$[", "*2_-2#", 6},
            {" 8", "", 0}
           });
    }


    /**
     * Checks strings with length => 20.
     * Correct string is not checked because this method works only if it has incorrect length.
     */
    @Test
    public void testInCorrectStrings(){                
        assertEquals(expectedStr,lengthTrimmer.lengthCut(inputStr, cl));        
    }
}
