package cisc181.m.bustinbricks;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Spinner;

public class GameActivity extends AppCompatActivity {
    public static int xScreen;
    public static int yScreen;
    public static String color;
    public static String difficulty;
    public static String backColor;
    public static String ballColor = "blue";
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        xScreen = size.x;
        yScreen = size.y;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new AnimatedView(this));
        intent = getIntent();
        difficulty = intent.getStringExtra("Difficulty");
        color = intent.getStringExtra("Platform Color");
        backColor = intent.getStringExtra("Background Color");
        if(backColor.equals("Trippy theme")){
            color = "Green";
            ballColor = "Green";
        }
    }
}