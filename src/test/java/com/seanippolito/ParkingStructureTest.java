package com.seanippolito;

import com.seanippolito.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingStructureTest {
    static private Space oneSpace = Space.newSpace(SpaceType.REGULAR, 1, null, null, false);
    static private Space twoSpaces = Space.newSpace(SpaceType.COMPACT, 2, null, oneSpace, true);
    static private Space threeSpaces = Space.newSpace(SpaceType.ELECTRIC, 3, null, twoSpaces, false);
    static private Space fourSpaces = Space.newSpace(SpaceType.SPECIAL_NEEDS, 4, null, threeSpaces, true);
    static private Space fiveSpaces = Space.newSpace(SpaceType.MOTORCYCLE, 5, null, fourSpaces, true);
    static private Space sixSpaces = Space.newSpace(SpaceType.EXPECTANT_MOTHER, 6, null, fiveSpaces, false);
    static private Space sevenSpace = Space.newSpace(SpaceType.REGULAR, 7, null, sixSpaces, true);
    static private Space eightSpaces = Space.newSpace(SpaceType.COMPACT, 8, null, sevenSpace, false);
    static private Space nineSpaces = Space.newSpace(SpaceType.ELECTRIC, 9, null, eightSpaces, true);
    static private Space tenSpaces = Space.newSpace(SpaceType.SPECIAL_NEEDS, 10, null, nineSpaces, false);
    static private Space elevenSpaces = Space.newSpace(SpaceType.MOTORCYCLE, 11, null, tenSpaces, true);
    static private Space twelveSpaces = Space.newSpace(SpaceType.EXPECTANT_MOTHER, 12, null, elevenSpaces, false);


    @BeforeAll
    static void setNextSpaces(){
        oneSpace.setNextSpace(twoSpaces);
        twoSpaces.setNextSpace(threeSpaces);
        threeSpaces.setNextSpace(fourSpaces);
        fourSpaces.setNextSpace(fiveSpaces);
        fiveSpaces.setNextSpace(sixSpaces);
        sixSpaces.setNextSpace(sevenSpace);
        sevenSpace.setNextSpace(eightSpaces);
        eightSpaces.setNextSpace(nineSpaces);
        nineSpaces.setNextSpace(tenSpaces);
        tenSpaces.setNextSpace(elevenSpaces);
        elevenSpaces.setNextSpace(twelveSpaces);
    }

    static private SpacesList spaces = new SpacesList();

    @BeforeAll
    static void fillSpacesList(){
        spaces.add(oneSpace);
        spaces.add(twoSpaces);
        spaces.add(threeSpaces);
        spaces.add(fourSpaces);
        spaces.add(fiveSpaces);
        spaces.add(sixSpaces);
        spaces.add(sevenSpace);
        spaces.add(eightSpaces);
        spaces.add(nineSpaces);
        spaces.add(tenSpaces);
        spaces.add(elevenSpaces);
        spaces.add(twelveSpaces);
    }

    static private Floor floor1 = Floor.newFloor(spaces.size(), 1, spaces, null, null);
    static private Floor floor2 = Floor.newFloor(spaces.size(), 2, spaces, floor1, null);
    static private Floor floor3 = Floor.newFloor(spaces.size(), 3, spaces, floor2, null);

    @BeforeAll
    static void setNextFloors(){
        floor1.setNextFloor(floor2);
        floor2.setNextFloor(floor3);
    }

    static private FloorList floors = new FloorList();

    @BeforeAll
    static void fillFloorsList(){
        floors.add(floor1);
        floors.add(floor2);
        floors.add(floor3);
    }

    static private ParkingStructure ps = ParkingStructure.newParkingStructure("com.seanippolito.Main", floors);

    @Test
    void getFloor() {
        assertEquals(floor1, ps.getFloor(1));
        assertEquals(floor2, ps.getFloor(2));
        assertEquals(floor3, ps.getFloor(3));
        assertEquals(null, ps.getFloor(4));
        assertEquals(null, ps.getFloor(0));

    }

    @Test
    void findNearestSpace() {
        Car car1 = Car.newCar(SpaceType.REGULAR, 11);
        Car car2 = Car.newCar(SpaceType.MOTORCYCLE, 11);
        Car car3 = Car.newCar(SpaceType.EXPECTANT_MOTHER, 11);
        Car car4 = Car.newCar(SpaceType.ELECTRIC, 11);
        Car car5 = Car.newCar(SpaceType.SPECIAL_NEEDS, 11);
        Car car6 = Car.newCar(SpaceType.COMPACT, 11);

        Car car7 = Car.newCar(SpaceType.REGULAR, 312);
        Car car8 = Car.newCar(SpaceType.MOTORCYCLE, 312);
        Car car9 = Car.newCar(SpaceType.EXPECTANT_MOTHER, 312);
        Car car10 = Car.newCar(SpaceType.ELECTRIC, 312);
        Car car11 = Car.newCar(SpaceType.SPECIAL_NEEDS, 312);
        Car car12 = Car.newCar(SpaceType.COMPACT, 312);

        Car car13 = Car.newCar(SpaceType.REGULAR, 212);
        Car car14 = Car.newCar(SpaceType.MOTORCYCLE, 18);
        Car car15 = Car.newCar(SpaceType.EXPECTANT_MOTHER, 33);
        Car car16 = Car.newCar(SpaceType.ELECTRIC, 38);
        Car car17 = Car.newCar(SpaceType.SPECIAL_NEEDS, 19);
        Car car18 = Car.newCar(SpaceType.COMPACT, 22);

        assertEquals(sevenSpace, ps.findNearestSpace(car1));
        assertEquals(fiveSpaces, ps.findNearestSpace(car2));
        assertEquals(null, ps.findNearestSpace(car3));
        assertEquals(nineSpaces, ps.findNearestSpace(car4));
        assertEquals(fourSpaces, ps.findNearestSpace(car5));
        assertEquals(twoSpaces, ps.findNearestSpace(car6));

        assertEquals(sevenSpace, ps.findNearestSpace(car7));
        assertEquals(elevenSpaces, ps.findNearestSpace(car8));
        assertEquals(null, ps.findNearestSpace(car9));
        assertEquals(nineSpaces, ps.findNearestSpace(car10));
        assertEquals(fourSpaces, ps.findNearestSpace(car11));
        assertEquals(twoSpaces, ps.findNearestSpace(car12));

        assertEquals(sevenSpace, ps.findNearestSpace(car13));
        assertEquals(fiveSpaces, ps.findNearestSpace(car14));
        assertEquals(null, ps.findNearestSpace(car15));
        assertEquals(nineSpaces, ps.findNearestSpace(car16));
        assertEquals(fourSpaces, ps.findNearestSpace(car17));
        assertEquals(twoSpaces, ps.findNearestSpace(car18));
    }

    @Test
    void toStringTest() {
        assertEquals("com.seanippolito.Main has the following spaces [On floor number 1 , On floor number 2 , On floor number 3 ]", ps.toString());
    }

}