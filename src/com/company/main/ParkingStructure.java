package com.company.main;

import java.util.LinkedList;

/*
    Enum for Type of car :
    Special needs, electric vehicle, compact, expectant mother, regular, motorcycle
 */
enum SpaceType {
    SPECIAL_NEEDS,
    ELECTRIC,
    COMPACT,
    EXPECTANT_MOTHER,
    REGULAR,
    MOTORCYCLE
}

/*
    ParkingStructure class
    Stores Floorlist and title of garage.
    Special method returns nearest spot in the garage.
 */
class ParkingStructure {
    private String title;
    private FloorList floors;

    private ParkingStructure(String title, FloorList floors){
        this.title = title;
        this.floors = floors;
    }

    public static ParkingStructure newParkingStructure(String title, FloorList floors){
        return new ParkingStructure(title, floors);
    }

    public Floor getFloor(int floorNum){
        for(Floor f : this.floors){
            if(f.getFloorNumber() == floorNum){
                return f;
            }
        }
        System.err.println("Invalid Floor Number");
        return null;
    }

    // Find the available space closest to your car for your cars type
    public Space findNearestSpace(Car car){
        Space curSpace = null;
        Floor curFloor = null;
        int floorNum = 0;
        int spaceNum = 0;
        String number = Integer.toString(car.getNearestNumber());

        //parse nearest number to extract floor and space information
        if(this.floors.size() < 10){
            floorNum = Integer.valueOf(number.substring(0,1));
            spaceNum = Integer.valueOf(number.substring(1, number.length()));
        } else if (this.floors.size() < 100) {
            floorNum = Integer.valueOf(Integer.toString(car.getNearestNumber()).substring(0,2));
            spaceNum = Integer.valueOf(number.substring(2, number.length()));
        } else {
            floorNum = Integer.valueOf(Integer.toString(car.getNearestNumber()).substring(0,3));
            spaceNum = Integer.valueOf(number.substring(3, number.length()));
        }

        curFloor = getFloor(floorNum);
        if(curFloor == null) { return null; }
        curSpace = curFloor.getSpace(spaceNum);
        if(curSpace == null) { return null; }

        if(curSpace.isAvailable() && car.getSpaceType() == curSpace.getType()){
            return curSpace;
        }

        // Keep track of pointers for forward and backward search
        Space prevSpace = curSpace.getPrevSpace();
        Space nextSpace = curSpace.getNextSpace();
        Floor prevFloor = curFloor;
        Floor nextFloor = curFloor;

        //Logic for navigating parking structure floors and spaces
        while((prevSpace != null || prevFloor.getFloorNumber() != 1) ||
                (nextSpace != null || nextFloor.getFloorNumber() != this.floors.size())) {
            if(prevSpace != null) {
                if(prevSpace.isAvailable() && car.getSpaceType() == prevSpace.getType()){
                    System.out.print(prevFloor.toString());
                    return prevSpace;
                }
                prevSpace = prevSpace.getPrevSpace();
            } else {
                if(prevFloor.getFloorNumber() > 1){
                    prevFloor = prevFloor.getPrevFloor();
                    prevSpace = prevFloor.getSpaces().getLast();
                }
            }
            if(nextSpace != null) {
                if(nextSpace.isAvailable() && car.getSpaceType() == nextSpace.getType()){
                    System.out.print(nextFloor.toString());
                    return nextSpace;
                }
                nextSpace = nextSpace.getNextSpace();
            } else {
                if(nextFloor.getFloorNumber() < this.floors.size()){
                    nextFloor = nextFloor.getNextFloor();
                    nextSpace = nextFloor.getSpaces().getFirst();
                }
            }
        }
        return null;
    }

    @Override
    public String toString(){
        return this.title + " has the following spaces " + floors.toString();
    }
}

// Extend Linkedlist class to acquire properties
class FloorList extends LinkedList<Floor> {}

/*
    Floor Class
    Keeps track of the current floor, number of spaces on the floor, and spaces list
 */
class Floor {
    private int numberOfSpaces;
    private int floorNumber;
    private Floor prevFloor;
    private Floor nextFloor;
    private SpacesList spaces;

    private Floor (int numberOfSpaces, int floorNumber, SpacesList spaces, Floor prevFloor, Floor nextFloor){
        this.numberOfSpaces = numberOfSpaces;
        this.floorNumber = floorNumber;
        this.spaces = spaces;
        this.prevFloor = prevFloor;
        this.nextFloor = nextFloor;
    }

    public static Floor newFloor(int numberOfSpaces, int floorNumber, SpacesList spaces, Floor prevFloor, Floor nextFloor) {
        return new Floor(numberOfSpaces, floorNumber, spaces, prevFloor, nextFloor);
    }

    public SpacesList getSpaces() {
        return this.spaces;
    }

    public Space getSpace(int spaceNum){
        for(Space s : this.spaces){
            if(s.getNumber() == spaceNum){
                return s;
            }
        }
        System.err.println("Invalid Space Number");
        return null;
    }

    public int getFloorNumber() {
        return this.floorNumber;
    }

    public Floor getPrevFloor(){
        return this.prevFloor;
    }

    public Floor getNextFloor(){
        return this.nextFloor;
    }

    @Override
    public String toString() {
        return "On floor number " + this.floorNumber + " ";
    }

    public void setNextFloor(Floor nextFloor) {
        this.nextFloor = nextFloor;
    }
}

// Extend Linkedlist class to acquire properties
class SpacesList extends LinkedList<Space> {}

/*
    Spaces class
    Keeps track of an individual spaces properties type, number, and whether it's available to park in.
 */
class Space{
    private SpaceType type;
    private int number;
    private Space nextSpace;
    private Space prevSpace;
    private boolean available;

    private Space(SpaceType type, int number, Space nextSpace, Space prevSpace, boolean available){
        this.type = type;
        this.number = number;
        this.nextSpace = nextSpace;
        this.prevSpace = prevSpace;
        this.available = available;
    }

    public static Space newSpace(SpaceType type, int number, Space nextSpace, Space prevSpace, boolean available){
        return new Space(type, number, nextSpace, prevSpace, available);
    }

    public void setNextSpace(Space nextSpace) {
        this.nextSpace = nextSpace;
    }

    public SpaceType getType(){
        return this.type;
    }

    public int getNumber(){
        return this.number;
    }

    public Space getNextSpace(){
        return this.nextSpace;
    }
    public Space getPrevSpace(){
        return this.prevSpace;
    }

    public boolean isAvailable(){
        return this.available;
    }

    @Override
    public String toString(){
        return this.type + " space open at spot number " + number + "!";
    }
}
