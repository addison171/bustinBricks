package cisc181.m.bustinbricks;

import android.graphics.RectF;

/*
This is the brick class. We use this to make brick objects that create rectangles and have functions
to make the rectangles visible or invisible
 */
public class Brick {
    private RectF rect;
    private boolean isVisible;

    public Brick(int row, int col, int width, int height) {
        isVisible = true;
        int padding = 1;
        rect = new RectF(col * width + padding,
                row * height + padding,
                col * width + width - padding,
                row * height + height - padding);
    }

    public RectF getRect() {
        return this.rect;
    }

    public void setInvisible() {
        isVisible = false;
    }

    public void setVisible(){
        isVisible = true;
    }

    public boolean getVisibility() {
        return isVisible;
    }
}
