package kallah;

/**
 * Created by Useless on 17.04.2017.
 */
public class Player {
    private final boolean up;
    private int count = 0;
    Player(boolean up){
        this.up = up;
    }

    public void setCount(int count) {
        this.count = count;
        if (this.count >= 36)
            System.out.println("WIN!!!");
    }
}
