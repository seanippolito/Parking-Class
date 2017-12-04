
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpaceTest {
    private Space oneSpace = Space.newSpace(SpaceType.REGULAR, 1, null, null, false);
    private Space twoSpaces = Space.newSpace(SpaceType.COMPACT, 2, null, oneSpace, true);
    private Space threeSpaces = Space.newSpace(SpaceType.ELECTRIC, 3, null, twoSpaces, false);
    private Space fourSpaces = Space.newSpace(SpaceType.SPECIAL_NEEDS, 4, null, threeSpaces, true);

    @Test
    void getType() {
        assertEquals(SpaceType.REGULAR, oneSpace.getType());
        assertEquals(SpaceType.COMPACT, twoSpaces.getType());
        assertEquals(SpaceType.ELECTRIC, threeSpaces.getType());
        assertEquals(SpaceType.SPECIAL_NEEDS, fourSpaces.getType());

        assertNotEquals(SpaceType.COMPACT, oneSpace.getType());
        assertNotEquals(SpaceType.REGULAR, twoSpaces.getType());
        assertNotEquals(SpaceType.EXPECTANT_MOTHER, threeSpaces.getType());
        assertNotEquals(SpaceType.MOTORCYCLE, fourSpaces.getType());
    }

    @Test
    void setNextSpace() {
        assertEquals(twoSpaces, oneSpace.getNextSpace());
        assertEquals(threeSpaces, twoSpaces.getNextSpace());
        assertEquals(fourSpaces, threeSpaces.getNextSpace());
        assertEquals(null, fourSpaces.getNextSpace());

        assertNotEquals(null, oneSpace.getNextSpace());
        assertNotEquals(oneSpace, twoSpaces.getNextSpace());
        assertNotEquals(fourSpaces, twoSpaces.getNextSpace());
        assertNotEquals(null, threeSpaces.getNextSpace());
    }

    @Test
    void getNumber() {
        assertEquals(1, oneSpace.getNumber());
        assertEquals(2, twoSpaces.getNumber());
        assertEquals(3, threeSpaces.getNumber());
        assertEquals(4, fourSpaces.getNumber());
    }

    @Test
    void getNextSpace() {
        oneSpace.setNextSpace(twoSpaces);
        twoSpaces.setNextSpace(threeSpaces);
        threeSpaces.setNextSpace(fourSpaces);

        assertEquals(twoSpaces, oneSpace.getNextSpace());
        assertEquals(threeSpaces, twoSpaces.getNextSpace());
        assertEquals(fourSpaces, threeSpaces.getNextSpace());
        assertEquals(null, fourSpaces.getNextSpace());

        assertNotEquals(null, oneSpace.getNextSpace());
        assertNotEquals(oneSpace, twoSpaces.getNextSpace());
        assertNotEquals(fourSpaces, twoSpaces.getNextSpace());
        assertNotEquals(null, threeSpaces.getNextSpace());
    }

    @Test
    void getPrevSpace() {
        assertEquals(oneSpace, twoSpaces.getPrevSpace());
        assertEquals(twoSpaces, threeSpaces.getPrevSpace());
        assertEquals(threeSpaces, fourSpaces.getPrevSpace());
        assertEquals(null, oneSpace.getPrevSpace());

        assertNotEquals(fourSpaces, oneSpace.getPrevSpace());
        assertNotEquals(threeSpaces, twoSpaces.getPrevSpace());
        assertNotEquals(fourSpaces, twoSpaces.getPrevSpace());
        assertNotEquals(null, threeSpaces.getPrevSpace());
    }

    @Test
    void isAvailable() {
        assertEquals(false, oneSpace.isAvailable());
        assertEquals(true, twoSpaces.isAvailable());
        assertEquals(false, threeSpaces.isAvailable());
        assertEquals(true, fourSpaces.isAvailable());

        assertNotEquals(true, oneSpace.isAvailable());
        assertNotEquals(false, twoSpaces.isAvailable());
        assertNotEquals(true, threeSpaces.isAvailable());
        assertNotEquals(false, fourSpaces.isAvailable());
    }

    @Test
    void toStringTest() {
        oneSpace.setNextSpace(twoSpaces);
        twoSpaces.setNextSpace(threeSpaces);
        threeSpaces.setNextSpace(fourSpaces);

        assertEquals("REGULAR space open at spot number 1!", oneSpace.toString());
        assertEquals("COMPACT space open at spot number 2!", twoSpaces.toString());
        assertEquals("ELECTRIC space open at spot number 3!", threeSpaces.toString());
        assertEquals("SPECIAL_NEEDS space open at spot number 4!", fourSpaces.toString());
    }

}