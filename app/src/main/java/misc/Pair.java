package misc;

public class Pair {
    private components.HotelEntry l;
    private components.RestaurantEntry r;
    private long score;
    public Pair(components.HotelEntry l, components.RestaurantEntry r){
        this.l = l;
        this.r = r;
    }
    
    public components.HotelEntry getHotel() {
        return l;
    }

    public components.RestaurantEntry getRest() {
        return r;
    }

    public void setScore(int stays, int visits, DoublePair starting){ score = (l.getScore() * 3) + r.getScore() + components.TotalScoreCalculator.getScore(l, r, stays, visits, starting);}
    public long getScore(){ return score;}
}
