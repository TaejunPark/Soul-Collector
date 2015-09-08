package nive.soulcollector.Part2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import nive.soulcollector.R;

public class GBBActivity extends FragmentActivity {
    public static String TAG  ="GBBActivity";

    int kind;
    String name;
    int power;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ggb);

        Intent intent =getIntent();
        kind =intent.getIntExtra(GhostStatusActivity.GHOST_KIND, -1);
        name = intent.getStringExtra(GhostStatusActivity.GHOST_NAME);
        power = intent.getIntExtra(GhostStatusActivity.GHOST_POWER, -1);

        ((ImageView)findViewById(R.id.ggb_picture)).setImageResource(kind);
    }

}
