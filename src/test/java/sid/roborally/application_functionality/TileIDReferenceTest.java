package sid.roborally.application_functionality;

import org.junit.Test;
import sid.roborally.application_functionality.reference.TileIDReference;

import static org.junit.Assert.assertEquals;

public class TileIDReferenceTest {

    @Test
    public void ConvertFlagIndexToFlagIdTest() {
        assertEquals(1, TileIDReference.flagIndexToId(55));
        assertEquals(2, TileIDReference.flagIndexToId(63));
        assertEquals(3, TileIDReference.flagIndexToId(71));
        assertEquals(4, TileIDReference.flagIndexToId(79));
    }

    @Test (expected = IllegalArgumentException.class)
    public void FlagIndexIllegalArgumentThrowsException() {
        assertEquals(1,TileIDReference.flagIndexToId(54));
    }
}
