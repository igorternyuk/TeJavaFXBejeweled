package bejeweled.model;

import java.util.Objects;

/**
 * Created by igor on 24.03.18.
 */
public class Jewel {
    private int x, y;
    private JewelType jewelType;

    public Jewel(){
        this(0, 0, JewelType.CYAN);
    }

    public Jewel(int x, int y, JewelType jewelType) {
        this.x = x;
        this.y = y;
        this.jewelType = jewelType;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public JewelType getJewelType() {
        return this.jewelType;
    }

    public void setJewelType(JewelType jewelType) {
        this.jewelType = jewelType;
    }

    public void swap(final Jewel other){
        final JewelType otherType = other.getJewelType();
        other.setJewelType(this.jewelType);
        this.jewelType = otherType;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Jewel)) return false;
        final Jewel otherJewel = (Jewel) other;
        return Objects.equals(getJewelType(), otherJewel.getJewelType())
                && getX() == otherJewel.getX()
                && getY() == otherJewel.getY();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = getX();
        result = prime * result + getY();
        result = prime * result + getJewelType().hashCode();
        return result;
    }

    @Override
    public String toString(){
        return String.format("Jewel --> x: %d y: %d %s", this.x, this.y, this.jewelType.toString());
    }
}
