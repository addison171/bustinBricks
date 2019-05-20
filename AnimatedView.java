package cisc181.m.bustinbricks;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;


public class AnimatedView extends SurfaceView implements SurfaceHolder.Callback{
    private MainThread mainThread;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private Platform platform;
    Brick[] bricks = new Brick[50];
    int numBricks = 0;
    private Projectile projectile;
    boolean touchDown = false;
    boolean touchStarted = false;
    float touchX;
    //ArrayList<RectF> bricks = new ArrayList<>();


    public AnimatedView(Context context) {
        super(context);
        getHolder().addCallback(this);
        mainThread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        int brickWidth = screenWidth / 6;
        int brickHeight = screenHeight / 20;
        for(int column = 0; column < 6; column ++ ) {
            for(int row = 0; row < 3; row ++ ) {
                bricks[numBricks] = new Brick(row, column, brickWidth, brickHeight);
                numBricks++;
            }
        }
        projectile = new Projectile(425, 800);
        platform = new Platform(GameActivity.xScreen, GameActivity.yScreen);
        //platform.x = GameActivity.xScreen;
        //+platform.y = GameActivity.yScreen;
        mainThread.setRunning(true);
        mainThread.start();

        //platThread.setRunning(true);
        //platThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                mainThread.setRunning(false);
                mainThread.join();
                // platThread.setRunning(false);
                // platThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            if(GameActivity.backColor.equals("Light theme")){
                canvas.drawColor(Color.WHITE);
            }
            if(GameActivity.backColor.equals("Dark theme")){
                canvas.drawColor(Color.BLACK);
            }
            if(GameActivity.backColor.equals("Trippy theme")){
                canvas.drawColor(Color.rgb(0, 200, 0));
            }
            Paint paint = new Paint();
            projectile.draw(canvas);
            platform.draw(canvas);
            paint.setColor(Color.rgb(0,255,0));
            for(int i = 0; i < numBricks; i++) {
                if(bricks[i].getVisibility()) {
                    canvas.drawRect(bricks[i].getRect(), paint);
                }
            }

        }
    }

    public boolean onTouchEvent(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            touchDown = true;
            touchStarted = true;
            touchX = e.getX();

            platform.x = touchX;


        }


        else if (e.getAction() == MotionEvent.ACTION_MOVE) {
            touchStarted = false;
            touchX = e.getX();
            platform.x = touchX;
        }


        else if (e.getAction() == MotionEvent.ACTION_UP) {
            touchDown = false;
        }


        else {
            return false;
        }


        return true;
    }
    public void update() {
        RectF ballRect = new RectF(projectile.x, projectile.y, projectile.x, projectile.y );
        for(int i = 0; i < numBricks; i++){
            if (bricks[i].getVisibility()){
                if(RectF.intersects(bricks[i].getRect(), ballRect)) {
                    if ((ballRect.bottom > bricks[i].getRect().top) && (ballRect.left > bricks[i].getRect().left+10) && (ballRect.right < bricks[i].getRect().right-10)) {
                        projectile.yVelocity *= -1;
                        bricks[i].setInvisible();

                    }
                    else if ((ballRect.top < bricks[i].getRect().bottom)  && (ballRect.left > bricks[i].getRect().left+10) && (ballRect.right < bricks[i].getRect().right-10)) {
                        projectile.yVelocity *= -1;
                        bricks[i].setInvisible();

                    }
                    else {
                        projectile.xVelocity *= -1 ;
                        bricks[i].setInvisible();
                    }
                    projectile.score++;
                }
            }
        }
        if(RectF.intersects(platform.getRect(), ballRect)){
            projectile.yVelocity*=-1;
        }
        projectile.update();
        platform.update();
    }
}