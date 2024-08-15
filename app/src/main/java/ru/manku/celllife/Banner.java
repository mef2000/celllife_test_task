package ru.manku.celllife;

import android.content.Context;
import android.graphics.PorterDuff;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Banner extends LinearLayout {
    private final ImageView icon;
    private final TextView who, what;
    private final LinearLayout gradient_back;
    private String nested_message = "";
    public final boolean isLife;
    public Banner(Context context, boolean isLife) {
        super(context);
        this.isLife = isLife;
        LayoutInflater.from(context).inflate(R.layout.banner, this, true);

        who = findViewById(R.id.type);
        what = findViewById(R.id.comma);
        icon = findViewById(R.id.icon);
        gradient_back = findViewById(R.id.gradient);

        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llp.setMargins(0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, context.getResources().getDisplayMetrics()),
                0, (isLife) ? (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 130, context.getResources().getDisplayMetrics()):0); //hack to drop ScrollView autoScrollBug
        this.setLayoutParams(llp);
        this.setBackground(getResources().getDrawable(R.drawable.rounded));
        this.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        this.setOnClickListener(v->
                Toast.makeText(getContext(), nested_message, Toast.LENGTH_SHORT).show());
    }
    public Banner todo(LifeState state) {
        int RES_ID = 0;
        int gradRes = 0;
        switch(state) {
            case IS_DEAD:
                who.setText("Мёртвая");
                what.setText("или прикидывается");
                nested_message = "Бывает... Не будем излишне переживать.";
                RES_ID = R.drawable.skull;
                gradRes = R.drawable.round_die;
                break;
            case IS_LIFE:
                who.setText("Живая");
                what.setText("и шевелится!");
                nested_message = "Только позитив!";
                RES_ID = R.drawable.spark;
                gradRes = R.drawable.round_life;
                break;
            case MUST_DIE:
                who.setText("Когда-то была ею");
                what.setText("её больше нет!");
                nested_message = "Приятного аппетита!";
                RES_ID = R.drawable.chiken_best;
                gradRes = R.drawable.round_be_food;
                break;
            case MUST_LIFE:
                who.setText("Жизнь");
                what.setText("Ку-ку!");
                nested_message = "Ко-ко-ко-ко...";
                RES_ID = R.drawable.chicken;
                gradRes = R.drawable.round_be_life;
                break;
        }
        if(RES_ID != 0) icon.setImageDrawable(getResources().getDrawable(RES_ID));
        if(gradRes != 0) gradient_back.setBackground(getResources().getDrawable(gradRes));
        return this;
    }
}
