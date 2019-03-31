package Jarro;

public class Jarro {

    private int water;
    private int maxCapacity;

    public Jarro(int maxCapacity) {
        setMaxCapacity(maxCapacity);
        setWater(0);
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getWater() {
        return water;
    }

    public boolean setWater(int water) {
        if (water <= getMaxCapacity()) {
            this.water = water;
            return true;
        } else {
            //System.out.println("Não foi modificado.");
            return false;
            //throw new Exception("Water out of bounds.");
        }
    }

}
