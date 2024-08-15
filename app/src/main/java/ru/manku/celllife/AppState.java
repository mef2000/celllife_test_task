package ru.manku.celllife;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Random;

public class AppState {
    public LifeState LIFE = LifeState.UNDEFINED;
    private final Random random = new Random();
    public LifeState[] STATES = new LifeState[] { LifeState.UNDEFINED, LifeState.UNDEFINED, LifeState.UNDEFINED};

    public void trySpawn() {
        LIFE = LifeState.UNDEFINED;
        LifeState next_state = (random.nextInt(100)+1 >= 50) ? LifeState.IS_LIFE : LifeState.IS_DEAD;
        STATES[0] = STATES[1];
        STATES[1] = STATES[2];
        STATES[2] = next_state;
        int tmp = 0;
        for(LifeState lf: STATES)
            if(lf.compareTo(LifeState.IS_LIFE)==0) tmp++;
            else if(lf.compareTo(LifeState.IS_DEAD)==0) tmp--;

        if(tmp == 3) LIFE = LifeState.MUST_LIFE;
        else if(tmp == -3) LIFE = LifeState.MUST_DIE;
    }
}
