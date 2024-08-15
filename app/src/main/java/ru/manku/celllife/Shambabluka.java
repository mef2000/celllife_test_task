package ru.manku.celllife;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class Shambabluka extends Activity {
    private Button make;
    private LinearLayout content;
    private ScrollView sv;
    private final AppState globalState = new AppState();

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);

        make = findViewById(R.id.make);
        content = findViewById(R.id.alife);
        sv = findViewById(R.id.sv);

        sv.setVerticalFadingEdgeEnabled(true);
        make.setOnClickListener(v -> {
                    globalState.trySpawn();
                    readChanges();
                }
        );
    }
    private Banner life;
    public void readChanges() {
        if(life==null) {
            life = new Banner(this, true);
            life.setVisibility(View.GONE);
        }

        content.removeView(life);

        Banner b = new Banner(this, false).todo(globalState.STATES[2]);
        content.addView(b);

        if(globalState.LIFE.compareTo(LifeState.UNDEFINED)!=0) {
            life.setVisibility(View.VISIBLE);
            content.addView(life.todo(globalState.LIFE));
        }else content.addView(life);

        sv.fullScroll(View.FOCUS_DOWN);
    }
}
