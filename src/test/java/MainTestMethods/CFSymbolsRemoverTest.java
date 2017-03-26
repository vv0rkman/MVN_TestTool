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
@SuppressWarnings("static-access")
public class CFSymbolsRemoverTest {
    ChooseFrame frame;
    
    @Before
    public void setUp(){
        frame = new ChooseFrame();
    }
    
    @Test 
    public void TestCorrectValues(){
        //only letters
        assertEquals("aBaodYrAVwqbfbJPNfLFQ", frame.SymblRemvr("aBaodYrAVwqbfbJPNfLFQ"));
        assertEquals("ю", frame.SymblRemvr("ю"));
        assertEquals("sSfdUZZdЫvfBxQkwerЪbfQGBpVoЮeFWlJ", frame.SymblRemvr("sSfdUZZdЫvfBxQkwerЪbfQGBpVoЮeFWlJ"));
        //letters + numbers
        assertEquals("5EjD1NTGtfI4omIYJUPDVjSpn1VYPYy", frame.SymblRemvr("5EjD1NTGtfI4omIYJUPDVjSpn1VYPYy"));
        assertEquals("0Ъ00Gp8Ymd1Ю4Uu0", frame.SymblRemvr("0Ъ00Gp8Ymd1Ю4Uu0"));
        assertEquals("0а", frame.SymblRemvr("0а"));
    }
    
    @Test 
    public void TestInCorrectValues(){
        //only letters
        assertEquals("aBaodYrAVwqbfbJPNfLFQ", frame.SymblRemvr("aBa/odYrAVw*qbfbJPN fLF()'Q"));
        assertEquals("ю", frame.SymblRemvr(".ю"));
        assertEquals("sSfdUZZdЫvfBxQkwerЪbfQGBpVoЮeFWlJ", frame.SymblRemvr("sSfdUZZdЫvfBx**QkwerЪbfQGBpVo _ЮeFWlJ."));
        //letters + numbers
        assertEquals("iTxsqYrhNnZ10652h8MoY", frame.SymblRemvr(" iTxsqYrhN*nZ'1'0652'h'[]8Mo.Y"));
        assertEquals("0Ъ00Gp8Ymd1Ю4Uu0", frame.SymblRemvr("0Ъ00G  p8Y')'md1Ю4U./u0"));
        assertEquals("0а", frame.SymblRemvr(" 0а"));
        //symbols
        assertEquals("", frame.SymblRemvr("\\$-\\)?;##)]"));
        assertEquals("", frame.SymblRemvr(">>\"-_)!`*@`$?(||+{@')\"$-~=_?`\">"));
        assertEquals("", frame.SymblRemvr(" "));
    }
}
