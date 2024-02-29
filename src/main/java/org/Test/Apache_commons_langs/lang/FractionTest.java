package org.Test.Apache_commons_langs.lang;


import org.apache.commons.lang3.math.Fraction;
import org.junit.Test;

public class FractionTest {

    @Test
    public void testFraction() {
        Fraction f1 = Fraction.getFraction(1, 3);
        Fraction f2 = Fraction.getFraction(1, 4);

        Fraction add = f1.add(f2); 				// 7/12
        Fraction subtract = f1.subtract(f2); 	// 1/12
        Fraction multi = f1.multiplyBy(f2); 	// 1/12
        Fraction divide = f1.divideBy(f2); 		// 4/3

        System.out.println(add + ", " + subtract + ", " + multi + ", " + divide);
        //7/12, 1/12, 1/12, 4/3

        //获取分子分母
        //f1.getNumerator();  	// 1
        //f1.getDenominator();	// 3

        //根据字符串，double, 分子分母获取分数
        //Fraction.getFraction("12.3"); // 123/100
        //Fraction.getFraction(12.3d);  // 123/100
        //Fraction.getFraction(123, 100); //123/100
    }


}
