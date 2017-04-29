package kallah;

/**
 * Created by Useless on 17.04.2017.
 */
class Player {
    private int count;
    Player(){
        this.count = 0;
    }

    void setCount(int count) {
        this.count = count;
        if (this.count >= 36)
            System.out.println("WIN!!!");
    }

    int getCount() {
        return count;
    }
}
