import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCase {

    private final PrintStream originalOut = System.out;
    private final java.io.InputStream originalIn = System.in;
    private ByteArrayOutputStream outContent;

    @Before
    public void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    private void provideInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }

    @Test
    public void testNEqualsOne() {
        provideInput("1");
        Main.main(new String[]{});
        assertEquals("a\n", outContent.toString());
    }

    @Test
    public void testNEqualsTwo() {
        provideInput("2");
        Main.main(new String[]{});
        assertEquals("ab\n", outContent.toString());
    }

    @Test
    public void testNEqualsThree() {
        provideInput("3");
        Main.main(new String[]{});
        assertEquals("cab\n", outContent.toString());
    }

    @Test
    public void testNEqualsZero() {
        provideInput("0");
        Main.main(new String[]{});
        assertEquals("\n", outContent.toString());
    }

    @Test
    public void testAlphabetWrap() {
        provideInput("27");
        Main.main(new String[]{});
        assertEquals("a\n", outContent.toString().substring(
                outContent.toString().length() - 2));
    }
}
