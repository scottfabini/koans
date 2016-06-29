package java7;

import com.sandwich.koan.Koan;

import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;

public class AboutJava7LiteralsEnhancements {

    @Koan
    public void binaryLiterals() {
        //binary literals are marked with 0b prefix
        short binaryLiteral = 0b1111;
        assertEquals(binaryLiteral, (short) 0xF);
    }

    @Koan
    public void binaryLiteralsWithUnderscores() {
        //literals can use underscores for improved readability
        short binaryLiteral = 0b1111_1111;
        assertEquals(binaryLiteral, (short) 0xFF);
    }

    @Koan
    public void numericLiteralsWithUnderscores() {
        long literal = 111_111_111L;
        //notice capital "B" - a valid binary literal prefix
        short multiplier = 0B1_000; // * 8
        assertEquals(literal * multiplier, 888_888_888L);
    }

    @Koan
    public void negativeBinaryLiteral() {
        int negativeBinaryLiteral = 0b1111_1111_1111_1111_1111_1111_1111_1100 / 4;
        assertEquals(negativeBinaryLiteral, 0xFFFFFFFF);
    }

    @Koan
    public void binaryLiteralsWithBitwiseOperator() {
        int binaryLiteral = ~0b1111_1111;
        assertEquals(binaryLiteral, 0xFFFFFF00);
    }

}
