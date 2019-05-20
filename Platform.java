package cisc181.m.bustinbricks;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.SurfaceHolder;

public class Platform {
    public float x;
    public float y;
    public RectF rect;
    private int length;
    private int width;

    public Platform(int x1, int y1) {
        x = x1/2;
        y = y1;
        length = 110;
        width = 20;
        rect = new RectF(x, (y/2+y/3+y/20), x + length, y);


    }

    public RectF getRect() {
        return this.rect;
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.rgb(0, 0, 250));
        canvas.drawRect(rect, paint);
    }

    public void update() {
        rect = new RectF(x,(y/2+y/3+y/20), x + length, y);


    }
}