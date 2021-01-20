package yalantis.com.sidemenu.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Administrator on 2016-07-18.
 */
public class living4 extends Activity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.living4_info);
    }

    public void yejeol(View view) {
        //Toast.makeText(getApplicationContext(), "생활예절", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(living4.this, yejeol.class);
        startActivity(intent);

    }

    public void transportation(View view) {
        Intent intent = new Intent(living4.this, life_transportation.class);
        startActivity(intent);

    }

    public void kr_culture(View view) {
        Intent intent = new Intent(living4.this, kr_culture.class);
        startActivity(intent);

    }

    public void calendar(View view) {
        Intent intent = new Intent(living4.this, calendar.class);
        startActivity(intent);

    }

}
