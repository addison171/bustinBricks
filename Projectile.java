package cisc181.m.bustinbricks;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.Random;

import static cisc181.m.bustinbricks.GameActivity.xScreen;
import static cisc181.m.bustinbricks.GameActivity.yScreen;

public class Projectile {
    int x;
    int y;
    int r;


    Random num = new Random();
    public int xVelocity = 7;
    public int yVelocity = 7;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

    int score = 0;
    int lives = 3;
    RectF ballRect;
    Platform platform = new Platform(xScreen, yScreen);

    public Projectile(int x1, int y1) {
        if(GameActivity.difficulty.equals("Easy")){
            xVelocity = 5;
            yVelocity = 5;
        }
        if(GameActivity.difficulty.equals("Medium")){
            xVelocity = 7;
            yVelocity = 7;
        }
        if(GameActivity.difficulty.equals("Hard")){
            xVelocity = 10;
            yVelocity = 10;
        }
        x = x1;
        y = y1;
        r = 20;
    }

    public void update() {
        x += xVelocity;
        y += yVelocity;
        if (x >= screenWidth || x <= 0) {
            xVelocity *= -1;
        }
        if (y <= 0) {
            yVelocity *= -1;
        }
    }



    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        if(GameActivity.ballColor.equals("Green")){
            paint.setColor(Color.rgb(0, 170, 0));
        }
        else {
            paint.setColor(Color.rgb(250, 0, 0));
        }
        canvas.drawCircle(x, y, r, paint);
    }


}