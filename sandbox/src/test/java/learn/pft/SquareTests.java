package learn.pft;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SquareTests {

    public void testArea() {
        Square s = new Square(5);
        Assert.assertEquals(s.area(), 25.0);

    }
}
