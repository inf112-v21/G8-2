package sid.roborally.application_functionality;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TileIDReferenceTest {

    @Test
    public void ConvertFlagIndexToFlagIdTest() {
        assertEquals(1, TileIDReference.flagIndexToId(55));
        assertEquals(2, TileIDReference.flagIndexToId(63));
        assertEquals(3, TileIDReference.flagIndexToId(71));
        assertEquals(4, TileIDReference.flagIndexToId(79));
    }
}
