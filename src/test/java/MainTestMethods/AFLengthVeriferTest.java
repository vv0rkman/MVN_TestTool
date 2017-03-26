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
 * Testing method which check a length of string
 * @author Victor
 */
@RunWith(Parameterized.class)
public class AFLengthVeriferTest {
    StubLengthCut stub;

/**
 * Stub for "lengthCut" method
 */
    public class StubLengthCut extends AdminFrame{
        @Override
        public String lengthCut(String incorrectStr, int size){
        return "Incorrect text";
    }
    }
    
    @Before
    public void setUp() {                       
        stub = new StubLengthCut();
    }

    /**
     * String for a test
     */
    @Parameter(value = 0)
    public String inputStr;
    
    /**
     * Correct value of a string length
     */
    @Parameter(value = 1)
    public int cl;
    
    /**
     * Incorrect value of a string length (bigger than the length of "input" string)
     */
    @Parameter(value = 2)
    public int incl;

    /**
     * Test data
     * @return String for a test, correct and incorrect values of a string length
     */
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {  
            //letters
            {"mDZhVQUzzsHxQmDp", 19, 5},
            {"оПрнЪЭрушгЕртГийешГчяе", 29, 18},
            {"юmnzЪzlmpuыыuooГЕЕп", 22, 10},
            {"ы", 2, 0},
            //letters + numbers
            {"0n4nDD3I7VMUygD59", 20, 2},
            {"7RхZva4D", 10, 5},
            {"99dlEnWIWQnYLWdeMxd67kwyghBFFcaxdsHcZvkHEJzQjIhE5lJ2nuHJnxfWvHBPM33ynx0", 1290, 15},
            //letters + symbols
            {"И%?ЪцMОъъJwюja", 20, 7},
            {"0*", 3, 1},
            {"%?NjXGvj[H%O?", 15, 1},
            //symbols + numbers
            {")'%~@*_-2#310{.0:-(0.\\\\'$[@5+}54<!43!6!+:", 128, 12},
            {"*2_-2#310{.0:-(0.\\\\'$[_-2#310{.0:-(0.\\\\'$[_-2#310{.0:-(0.\\\\'$[", 64, 6},
            {"*", 3, 0}
           });
    }

    /**
     * Tests of method with correct a length of string
     */
    @Test
    public void testCorrectStrings(){                        
        assertEquals(inputStr, stub.lengthChckCut(inputStr, cl));
    }
    /**
     * Tests of method with incorrect length of string
     */
    @Test
    public void testInCorrectStrings(){           
        assertEquals("Incorrect text",stub.lengthChckCut(inputStr, incl));
    }
   
}

