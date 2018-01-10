package com.seanippolito;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

        // write your code here
//        Scanner kb = new Scanner(System.in);
//
//        Properties prop = new Properties();
//        try {
//            prop.load(new FileInputStream("resources/config.properties"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        int numFloors = Integer.parseInt(prop.getProperty("numOfFloors"));
//        FloorList floors = new FloorList();
//
//        //Loop through floors starting at 1
//        for(int floorNum = 1; floorNum <= numFloors; floorNum++){
//            int numSpaces = Integer.parseInt(prop.getProperty("numOfSpaces" + Integer.toString(floorNum)));
//            SpacesList spaces = new SpacesList();
//            String[] allSpaces = prop.getProperty("spaces" + Integer.toString(floorNum)).split(",");
//
//            int spaceWalker = 0;
//            //Create the spaces list for the current floor
//            for (int spaceNum = 1; spaceNum <= numSpaces; spaceNum++) {
//                String type = allSpaces[spaceWalker++];
//                String avail = allSpaces[spaceWalker++];
//                boolean isAvail = avail.toLowerCase().equals("yes");
//
//                //Create Space instances and add pointers for linkedlist and easy traversal
//                if (spaces.isEmpty()) {
//                    Space firstSpace = Space.newSpace(SpaceType.valueOf(type.toUpperCase()), spaceNum,null, null, isAvail);
//                    spaces.add(firstSpace);
//                } else {
//                    Space newSpace = Space.newSpace(SpaceType.valueOf(type.toUpperCase()), spaceNum, null, spaces.getLast(), isAvail);
//                    spaces.getLast().setNextSpace(newSpace);
//                    spaces.add(newSpace);
//                }
//            }
//
//            //Create Floor instances and add pointers for linkedlist and easy traversal
//            if(floors.isEmpty()) {
//                Floor firstFloor = Floor.newFloor(spaces.size(), floorNum, spaces, null, null);
//                floors.add(firstFloor);
//            } else {
//                Floor newFloor = Floor.newFloor(spaces.size(), floorNum, spaces, floors.getLast(), null);
//                floors.getLast().setNextFloor(newFloor);
//                floors.add(newFloor);
//            }
//        }
//
//        //Build parking structure with floors list
//        ParkingStructure ps = ParkingStructure.newParkingStructure("Main Structure", floors);

        //run forever to allow for easy testing without restarting program. Remove loop after.
//        while(true) {
//            System.out.println("Parking structure input accepted!\n" +
//                    "What type of car do you have and what spot is your car closest to? Input: [Type, Number]");
//            String spaceType = kb.next();
//            int nearestNumber = kb.nextInt();
//            Car car = Car.newCar(SpaceType.valueOf(spaceType.toUpperCase()), nearestNumber);
//            Space nearestAvailableSpace = ps.findNearestSpace(car);
//            if (nearestAvailableSpace != null) {
//                System.out.println(nearestAvailableSpace);
//            } else {
//                System.out.println("No available spaces in garage!");
//            }
//        }
    }
}