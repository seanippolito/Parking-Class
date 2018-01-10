package com.seanippolito.parking;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@RestController
public class ParkingController {

    public ParkingStructure getParkingStructure(){
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("resources/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int numFloors = Integer.parseInt(prop.getProperty("numOfFloors"));
        FloorList floors = new FloorList();

        //Loop through floors starting at 1
        for(int floorNum = 1; floorNum <= numFloors; floorNum++){
            int numSpaces = Integer.parseInt(prop.getProperty("numOfSpaces" + Integer.toString(floorNum)));
            SpacesList spaces = new SpacesList();
            String[] allSpaces = prop.getProperty("spaces" + Integer.toString(floorNum)).split(",");

            int spaceWalker = 0;
            //Create the spaces list for the current floor
            for (int spaceNum = 1; spaceNum <= numSpaces; spaceNum++) {
                String type = allSpaces[spaceWalker++];
                String avail = allSpaces[spaceWalker++];
                boolean isAvail = avail.toLowerCase().equals("yes");

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

        ParkingStructure ps = ParkingStructure.newParkingStructure("Main Structure", floors);
        return ps;
    }

    @RequestMapping("/")
    public String getNearestSpace(){
        Car car = Car.newCar(SpaceType.valueOf("MOTORCYCLE"), 11);
        Space nearestAvailableSpace = getParkingStructure().findNearestSpace(car);
        if (nearestAvailableSpace != null) {
            return nearestAvailableSpace.toString();
        } else {
            return "No available spaces in garage!";
        }
    }

}
