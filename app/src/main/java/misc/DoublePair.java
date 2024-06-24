package misc;

public class DoublePair {
    private double x;
    private double y;

    public DoublePair(double _x, double _y){
        x = _x;
        y = _y;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double doubleGetDistance(DoublePair othePair){
        return Math.sqrt(Math.pow(x, 2.d) + Math.pow(y, 2.d));
    }

    public DoublePair addPair(DoublePair otherPair){
        return new DoublePair(getX() + otherPair.getX(), getY() + otherPair.getY());
    }

    @Override
    public String toString(){
        return String.format("(%.2f, %.2f)", getX(), getY());
    }
}
