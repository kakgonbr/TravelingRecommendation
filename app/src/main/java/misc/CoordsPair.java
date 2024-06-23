package misc;

public class CoordsPair {
    private double x;
    private double y;

    public CoordsPair(double _x, double _y){
        x = _x;
        y = _y;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double doubleGetDistance(CoordsPair othePair){
        return Math.sqrt(Math.pow(x, 2.d) + Math.pow(y, 2.d));
    }

    public CoordsPair addPair(CoordsPair otherPair){
        return new CoordsPair(getX() + otherPair.getX(), getY() + otherPair.getY());
    }

    @Override
    public String toString(){
        return String.format("(%.2f, %.2f)", getX(), getY());
    }
}
