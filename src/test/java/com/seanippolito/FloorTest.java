package com.seanippolito;

import com.seanippolito.Floor;
import com.seanippolito.Space;
import com.seanippolito.SpaceType;
import com.seanippolito.SpacesList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FloorTest {
    static private Space oneSpace = Space.newSpace(SpaceType.REGULAR, 1, null, null, false);
    static private Space twoSpaces = Space.newSpace(SpaceType.COMPACT, 2, null, oneSpace, true);
    static private Space threeSpaces = Space.newSpace(SpaceType.ELECTRIC, 3, null, twoSpaces, false);
    static private Space fourSpaces = Space.newSpace(SpaceType.SPECIAL_NEEDS, 4, null, threeSpaces, true);

    @BeforeAll
    static void setNextSpaces(){
        oneSpace.setNextSpace(twoSpaces);
        twoSpaces.setNextSpace(threeSpaces);
        threeSpaces.setNextSpace(fourSpaces);
    }

    static private SpacesList spaces = new SpacesList();

    @BeforeAll
    static void fillSpacesList(){
        spaces.add(oneSpace);
        spaces.add(twoSpaces);
        spaces.add(threeSpaces);
        spaces.add(fourSpaces);
    }

    static private Floor floor1 = Floor.newFloor(spaces.size(), 1, spaces, null, null);
    static private Floor floor2 = Floor.newFloor(spaces.size(), 2, spaces, floor1, null);
    static private Floor floor3 = Floor.newFloor(spaces.size(), 3, spaces, floor2, null);

    @BeforeAll
    static void setNextFloors(){
        floor1.setNextFloor(floor2);
        floor2.setNextFloor(floor3);
    }

    @Test
    void getSpaces() {
        assertEquals(spaces, floor1.getSpaces());
        assertEquals(spaces, floor2.getSpaces());
        assertEquals(spaces, floor3.getSpaces());
    }

    @Test
    void getSpace() {
        assertEquals(spaces.get(0), floor1.getSpace(1));
        assertNotEquals(spaces.get(0), floor1.getSpace(2));
        assertNotEquals(null, floor1.getSpace(1));
        assertEquals(null, floor1.getSpace(5));
        assertEquals(spaces.get(1), floor1.getSpace(2));
        assertEquals(spaces.get(2), floor1.getSpace(3));
        assertEquals(spaces.get(3), floor1.getSpace(4));

    }

    @Test
    void getFloorNumber() {
        assertEquals(1, floor1.getFloorNumber());
        assertNotEquals(0, floor1.getFloorNumber());
        assertNotEquals(null, floor1.getFloorNumber());
    }

    @Test
    void getPrevFloor() {
        assertEquals(null, floor1.getPrevFloor());
        assertEquals(floor1, floor2.getPrevFloor());
        assertEquals(floor2, floor3.getPrevFloor());
    }

    @Test
    void getNextFloor() {
        assertEquals(floor2, floor1.getNextFloor());
        assertEquals(floor3, floor2.getNextFloor());
        assertEquals(null, floor3.getNextFloor());
    }

    @Test
    void toStringTest() {
        assertEquals("On floor number 1 ", floor1.toString());
        assertEquals("On floor number 2 ", floor2.toString());
        assertEquals("On floor number 3 ", floor3.toString());
    }

}