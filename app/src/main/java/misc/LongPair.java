package misc;

public class LongPair {
    private long x;
    private long y;

    public LongPair(long _x, long _y){
        x = _x;
        y = _y;
    }

    public long getX(){
        return x;
    }

    public long getY(){
        return y;
    }

    // public double longGetDistance(LongPair othePair){
    //     return Math.sqrt(Math.pow(x, 2L) + Math.pow(y, 2L));
    // }

    public LongPair addPair(LongPair otherPair){
        return new LongPair(getX() + otherPair.getX(), getY() + otherPair.getY());
    }

    @Override
    public String toString(){
        return String.format("(%d, %d)", getX(), getY());
    }
}
