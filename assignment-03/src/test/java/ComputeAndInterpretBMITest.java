// Project Prolog
// Name: Al Baker
// CS3250 Section 601
// Project: Assignment 03
// Date: 09/14/2024
// Purpose: To test the ComputeAndInterpretBMI class.

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.almariebaker.ComputeAndInterpretBMI;

public class ComputeAndInterpretBMITest {
    // Test the calculateHeight method, including using heights with decimals in the inches values
    @Test
    public void testCalculateHeight() {
        assertEquals(60, ComputeAndInterpretBMI.calculateHeight(5, 0));
        assertEquals(75.5, ComputeAndInterpretBMI.calculateHeight(6, 3.5));
        assertEquals(70, ComputeAndInterpretBMI.calculateHeight(5, 10));
        assertEquals(72.5, ComputeAndInterpretBMI.calculateHeight(6, 0.5));
    }

    // Test the calculateBMI method, using some weights with decimals and the heights from the last test
    @Test
    public void testCalculateBMI() {
        assertEquals(30.37, ComputeAndInterpretBMI.calculateBMI(155.5, 60), 0.01);
        assertEquals(22.20, ComputeAndInterpretBMI.calculateBMI(180, 75.5), 0.01);
        assertEquals(18.15, ComputeAndInterpretBMI.calculateBMI(126.5, 70), 0.01);
        assertEquals(27.69, ComputeAndInterpretBMI.calculateBMI(207, 72.5), 0.01);
    }

    // Test the interpretBMI method, using the BMIs from the last test
    @Test
    public void testInterpretBMI() {
        assertEquals("Obese", ComputeAndInterpretBMI.interpretBMI(30.37));
        assertEquals("Normal", ComputeAndInterpretBMI.interpretBMI(22.20));
        assertEquals("Underweight", ComputeAndInterpretBMI.interpretBMI(18.15));
        assertEquals("Overweight", ComputeAndInterpretBMI.interpretBMI(27.69));
    }
}
