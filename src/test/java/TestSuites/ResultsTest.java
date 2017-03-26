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
package TestSuites;

import com.vv0rkman.mvntesttool.Results;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Victor
 */
public class ResultsTest {
    Results res;
    int CountQuestions;
    int CountCorrectAnswers;
    
    @Before
    public void setUp() {
        CountQuestions = 20;
        CountCorrectAnswers = 15;
        res = new Results(CountQuestions,CountCorrectAnswers);
    }
    
    @After
    public void setDown(){
        CountQuestions = 10;
        CountCorrectAnswers = 15;        
    }

    /**
     * Test of main method, of class Results.
     */
    @Test
    public void testConstructorWithCorrectValues() {
        System.out.println("Correct Results");        
        Assert.assertEquals(CountQuestions, res.questionCount);
        Assert.assertEquals(CountCorrectAnswers, res.correctAnswers);
        Assert.assertEquals(5, res.incorrectAnswers);
    }
   
    @Test
    public void testConstructorWithInCorrectValues() {
        setDown();
        System.out.println("Incorrect Results");
        Assert.assertNotEquals(10, res.incorrectAnswers);
    }
}
