package sample.board;

public class Field {
    private Field upLeft;
    private Field upRight;
    private Field left;
    private Field right;
    private Field downLeft;
    private Field downRight;

    private Colours colour;
    private int id;

    private int X;
    private int Y;

    Field(int id, int x, int y){
        this.id = id;
        this.X  = x;
        this.Y = y;
        this.colour = Colours.ORANGE;
        System.out.println("utworzono: " + this + " param: " + X + "\t" + Y);

    }

    Field(int id, int x, int y, Colours colour){
        this(id, x, y);
        this.colour = colour;
    }

    public String toString(){
        return Integer.toString(id);
    }

    public int getX(){
        return X;
    }

    public int getY(){
        return Y;
    }
    void setColour(Colours colour){
        this.colour = colour;
    }

    Colours getColour(){
        return colour;
    }

    void setUpLeft(Field upLeft) {
        this.upLeft = upLeft;
        if(upLeft.getDownRight() == null)
            upLeft.setDownRight(this);
    }


    void setUpRight(Field upRight) {
        this.upRight = upRight;
        if(upRight.getDownLeft() == null)
            upRight.setDownLeft(this);
    }


    void setLeft(Field left) {
        this.left = left;
        if(left.getRight() == null)
            left.setRight(this);
    }


    void setRight(Field right) {
        this.right = right;
        if (right.getLeft() == null)
            right.setLeft(this);
    }


    void setDownLeft(Field downLeft) {
        this.downLeft = downLeft;
        if (downLeft.getUpRight() == null)
            downLeft.setUpRight(this);
    }


    void setDownRight(Field downRight) {
        this.downRight = downRight;
        if (downRight.getUpLeft() == null)
            downRight.setUpLeft(this);
    }

    Field getUpLeft() {
        return upLeft;
    }
    Field getUpRight() {
        return upRight;
    }
    Field getLeft() {
        return left;
    }
    Field getRight() {
        return right;
    }
    Field getDownLeft() {
        return downLeft;
    }
    Field getDownRight() {
        return downRight;
    }


}
