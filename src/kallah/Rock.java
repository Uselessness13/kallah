package kallah;

import javafx.scene.image.Image;

/**
 * Created by Useless on 11.04.2017.
 */
public class Rock {
    Image image;
    String colour;

    Rock(String colour) {
        this.colour = colour;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
