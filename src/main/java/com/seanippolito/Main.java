package com.seanippolito;

import com.seanippolito.Car;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        // write your code here
        Scanner kb = new Scanner(System.in);
        System.out.println("How many floors are available in your parking structure? Input: [Number]");
        int numFloors = kb.nextInt();
        FloorList floors = new FloorList();

        //Loop through floors starting at 1
        for(int floorNum = 1; floorNum <= numFloors; floorNum++){
            System.out.println("How many spaces are available on floor number " + floorNum + "? Input: [Number]");
            int numSpaces = kb.nextInt();
            SpacesList spaces = new SpacesList();

            System.out.println("Please insert spaces in structure in the following format: Input: [Type, Number] ");
            System.out.println("Types available: SPECIAL_NEEDS, ELECTRIC, COMPACT, EXPECTANT_MOTHER, REGULAR, MOTORCYCLE");

            //Create the spaces list for the current floor
            for (int spaceNum = 1; spaceNum <= numSpaces; spaceNum++) {
                String type = kb.next();
                //Accept input determining if space is available
                String avail = kb.next();
                boolean isAvail = avail.toLowerCase().equals("yes");
                //Get number for space starting with floor number followed by space number.
//                int number = Integer.valueOf(String.valueOf(floorNum) + String.valueOf(spaceNum));

                //Create Space instances and add pointers for linkedlist and easy traversal
                if (spaces.isEmpty()) {
                    Space firstSpace = Space.newSpace(SpaceType.valueOf(type.toUpperCase()), spaceNum,null, null, isAvail);
                    spaces.add(firstSpace);
                } else {
                    Space newSpace = Space.newSpace(SpaceType.valueOf(type.toUpperCase()), spaceNum, null, spaces.getLast(), isAvail);
                    spaces.getLast().setNextSpace(newSpace);
                    spaces.add(newSpace);
                }
            }

            //Create Floor instances and add pointers for linkedlist and easy traversal
            if(floors.isEmpty()) {
                Floor firstFloor = Floor.newFloor(spaces.size(), floorNum, spaces, null, null);
                floors.add(firstFloor);
            } else {
                Floor newFloor = Floor.newFloor(spaces.size(), floorNum, spaces, floors.getLast(), null);
                floors.getLast().setNextFloor(newFloor);
                floors.add(newFloor);
            }
        }

        //Build parking structure with floors list
        ParkingStructure ps = ParkingStructure.newParkingStructure("com.seanippolito.Main Structure", floors);

        //run forever to allow for easy testing without restarting program. Remove loop after.
        while(true) {
            System.out.println("Parking structure input accepted!\n" +
                    "What type of car do you have and what spot is your car closest to? Input: [Type, Number]");
            String spaceType = kb.next();
            int nearestNumber = kb.nextInt();
            Car car = Car.newCar(SpaceType.valueOf(spaceType.toUpperCase()), nearestNumber);
            Space nearestAvailableSpace = ps.findNearestSpace(car);
            if (nearestAvailableSpace != null) {
                System.out.println(nearestAvailableSpace);
            } else {
                System.out.println("No available spaces in garage!");
            }
        }
    }
}

/*
Input test case:
3
30
expectant_mother no
special_needs no
special_needs no
electric no
electric yes
electric no
compact yes
compact no
motorcycle yes
regular no
compact no
regular yes
compact no
regular no
regular no
expectant_mother no
special_needs no
special_needs no
electric no
electric yes
electric no
compact yes
compact no
motorcycle yes
regular no
compact no
regular yes
compact no
regular no
regular no
15
expectant_mother no
special_needs no
special_needs no
electric no
electric no
electric no
compact no
compact no
motorcycle no
regular no
compact no
regular no
compact no
regular no
regular no
5
expectant_mother yes
special_needs no
special_needs no
electric no
electric yes
compact 12
motorcycle 34
regular 22
 */
