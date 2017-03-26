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

import com.vv0rkman.mvntesttool.ChooseFrame;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Victor
 */
public class CFLengthVeriferTest {
    ChooseFrame lengthVerifer;  
    
    @Before
    public void setUp() {               
        lengthVerifer = new ChooseFrame();
    }

    @Test
    public void testCorrectPassword(){                
        //contains numbers + letters
        assertFalse(lengthVerifer.lengthVrfr("SH2Gya8yfSXNt", 20));
        assertFalse(lengthVerifer.lengthVrfr("7RхZva4D", 10));
        assertFalse(lengthVerifer.lengthVrfr("thJmюdw9", 1290));
        //only letters
        assertFalse(lengthVerifer.lengthVrfr("fMwrmyup", 12));
        assertFalse(lengthVerifer.lengthVrfr("rmnzdzlmpuvuoobw", 19));
        assertFalse(lengthVerifer.lengthVrfr("ы", 2));
        //letters + symbols
        assertFalse(lengthVerifer.lengthVrfr("%?ЪMJwja", 10));
        assertFalse(lengthVerifer.lengthVrfr("*", 2));
        assertFalse(lengthVerifer.lengthVrfr("N0}qdBo|},]FX", 15));
        //symbols + numbers
        assertFalse(lengthVerifer.lengthVrfr(")'%~@*@5+}54<!43!6!+:", 23));
        assertFalse(lengthVerifer.lengthVrfr("(=-6=*.3=#\\/^+6", 120));
        assertFalse(lengthVerifer.lengthVrfr("*2", 3));       
    }
    @Test
    public void testInCorrectPassword(){        
        //contains numbers + letters
        assertTrue(lengthVerifer.lengthVrfr("SH2Gya8yfSXNt", 1));
        assertTrue(lengthVerifer.lengthVrfr("7RхZva4D", 0));
        assertTrue(lengthVerifer.lengthVrfr("n4nDD3I7VMUygD59", 12));
        assertTrue(lengthVerifer.lengthVrfr("dlEnWIWQnYLWdeMxdkwyghBFFcaxynx\n" +
                    "dsHcZvkHEJzQjIhElJnuHJnxfWvHBPM\n" +
                    "DXDEcvgydgorCrrTHeMqEKYHchDNHtO", 29));
        //only letters
        assertTrue(lengthVerifer.lengthVrfr("mDZhVQUzzsHxQmDpHsM", 5));
        assertTrue(lengthVerifer.lengthVrfr("rmnzdzlmpuvuoobw", 0));
        assertTrue(lengthVerifer.lengthVrfr("sssssss", 1));
        //letters + symbols
        assertTrue(lengthVerifer.lengthVrfr("%?NjXGvj[H%O?", 10));
        assertTrue(lengthVerifer.lengthVrfr("//", 2));
        assertTrue(lengthVerifer.lengthVrfr("N0}_|G\\[Z\"(Dj=|Pd)&V]sqdBo|},]FX", 15));
        //symbols + numbers
        assertTrue(lengthVerifer.lengthVrfr(")'%~@*_-2#310{.0:-(0.\\'$[@5+}54<!43!6!+:", 23));
        assertTrue(lengthVerifer.lengthVrfr("(=-6=*.3=#\\/^+6", 1));
        assertTrue(lengthVerifer.lengthVrfr("*2_-2#310{.0:-(0.\\'$[_-2#310{.0:-(0.\\'$[_-2#310{.0:-(0.\\'$[", 3));   
    }
    
    }
