package bejeweled.model;

/**
 * Created by igor on 24.03.18.
 */
public enum JewelType {
    RED(10, "RED"),
    GREEN(7, "GREEN"),
    BLUE(5, "BLUE"),
    YELLOW(3, "YELLOW"),
    MAGENTA(2, "MAGENTA"),
    CYAN(1, "CYAN");

    private int value;
    private String text;

    JewelType(final int value, final String text){
        this.value = value;
        this.text = text;
    }

    public int getValue(){
        return this.value;
    }

    @Override
    public String toString(){
        return this.text;
    }

}
