/*
    Simple car class with type and nearest spot in garage.
 */
class Car {
    private SpaceType type;
    private int nearestNumber;

    private Car(SpaceType type, int nearestNumber) {
        this.type = type;
        this.nearestNumber = nearestNumber;
    }

    public static Car newCar(SpaceType type, int nearestNumber){
        return new Car(type, nearestNumber);
    }

    public SpaceType getSpaceType(){
        return this.type;
    }

    public int getNearestNumber(){
        return this.nearestNumber;
    }
}
