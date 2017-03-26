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
 *
 * @author Victor
 */
@RunWith(Parameterized.class)
@SuppressWarnings("static-access")
public class AFSymbolsRemoverTest {
    AdminFrame frame;

    @Before
    public void setUp(){
        frame = new AdminFrame();
    }
    
    /**
     * String for a test
     */   
    @Parameter(value = 0)
    public String inputStr;

    /**
     * Expected string
     */
    @Parameter(value = 1)
    public String expectedStr;

    /**
     * Test data
     * @return String for a test and a string what a expected
     */
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {  
            //correct values
            //letters
            {"aBaodYrAVwqbfbJPNfLFQ", "aBaodYrAVwqbfbJPNfLFQ"},
            {"sSfdUZZdЫvfBxQkwerЪbfQGBpVoЮeFWlJ", "sSfdUZZdЫvfBxQkwerЪbfQGBpVoЮeFWlJ"},
            {"ю", "ю"},            
            {"ЫвапрпршГЪмпиртоББю", "ЫвапрпршГЪмпиртоББю"},
            //incorrect values
            //numbers
            {"870585505717512484", ""},
            {"853813721608746228961046926446", ""},
            {"0", ""},
            //letters + symbols
            {"aBa/odYrAVw*qbfb JPN fLF()'Q", "aBaodYrAVwqbfbJPNfLFQ"},
            {"ю*", "ю"},            
            //letters + numbers
            {"5EjD1NTGtfI4omIYJUPDVjSpn1юVYPYy", "EjDNTGtfIomIYJUPDVjSpnюVYPYy"},
            {"0Ъ00Gp8Ymd1Ю4Uu0", "ЪGpYmdЮUu"},
            {" 8", ""},
            //letters + numbers + symbols
            {"\"0 U_u'Z'X'Xv???vAnk(iaiBG9HrwWvc..", "UuZXXvvAnkiaiBGHrwWvc"},
            {" ё rRuAЫЫN`}'{])?'+)#]5^+*'8BюpPэkg", "rRuAЫЫNBюpPэkg"}
           });
    }
    
    /**
     * Tests of method with correct strings
     */    
    @Test 
    public void TestCorrectValues(){
        assertEquals(expectedStr, frame.smblRemvr(inputStr));
    }
    /**
     * Tests of method with incorrect strings
     */    
    @Test 
    public void TestInCorrectValues(){
       assertEquals(expectedStr, frame.smblRemvr(inputStr));
    }
}
