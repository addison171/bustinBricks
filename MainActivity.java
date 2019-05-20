package cisc181.m.bustinbricks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
//Addison Kuykendall Jonathan Zhang Akshat Katoch

/*
This is our main activity. It has the title, a picture, and 3 settings: difficulty, platform color,
and theme
 */
public class MainActivity extends AppCompatActivity {
    ImageView image;
    Spinner spin1;
    Spinner spin2;
    Spinner spin3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = (ImageView) findViewById((Integer)R.id.image);

        spin1 = setupSpinner();
        spin2 = setupSpinner1();
        spin3 = setupSpinner2();
        Button button = findViewById(R.id.start);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity();
            }
        });


    }

    public void nextActivity() {
        Intent intent = new Intent(this,GameActivity.class);
        Spinner spinner1 = findViewById(R.id.difficulty);
        Spinner spinner2 = findViewById(R.id.platformcolor);
        Spinner spinner3 = findViewById(R.id.backgroundcolor);
        intent.putExtra("Difficulty", spinner1.getSelectedItem().toString());
        intent.putExtra("Platform Color", spinner2.getSelectedItem().toString());
        intent.putExtra("Background Color", spinner3.getSelectedItem().toString());
        startActivity(intent);
    }

    public Spinner setupSpinner() {

        Spinner spin = findViewById(R.id.difficulty);

        ArrayList<String> list = new ArrayList<String>();
        list.add("Easy");
        list.add("Medium");
        list.add("Hard");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(dataAdapter);

        return spin;
    }

    public Spinner setupSpinner1() {

        Spinner spin = findViewById(R.id.platformcolor);

        ArrayList<String> list = new ArrayList<String>();
        list.add("Blue");
        list.add("Green");
        list.add("Red");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(dataAdapter);

        return spin;
    }

    public Spinner setupSpinner2() {

        Spinner spin = findViewById(R.id.backgroundcolor);

        ArrayList<String> list = new ArrayList<String>();
        list.add("Light theme");
        list.add("Dark theme");
        list.add("Trippy theme");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(dataAdapter);

        return spin;
    }
}