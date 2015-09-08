package nive.soulcollector.Part2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import nive.soulcollector.R;

public class GhostStatusActivity extends Activity {
    public static String GHOST_KIND ="ghost_kind";
    public static String GHOST_NAME="ghost_name";
    public static String GHOST_POWER="ghost_power";
    public static String GHOST_SCRIPT_WIN="ghost_script_win";
    public static String GHOST_SCRIPT_LOSE="ghost_script_lose";

    int kind;
    String name;
    int power;
    String scriptLose;
    String scriptWin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost_status);
        Intent intent =getIntent();
        kind =intent.getIntExtra(GHOST_KIND, -1);
        name = intent.getStringExtra(GHOST_NAME);
        power = intent.getIntExtra(GHOST_POWER, -1);
        scriptLose = intent.getStringExtra(GHOST_SCRIPT_LOSE);
        scriptWin= intent.getStringExtra(GHOST_SCRIPT_WIN);


        ((ImageView)(findViewById(R.id.status_ghost))).setImageResource(kind);
        ((TextView)(findViewById(R.id.status_name))).setText(name);
        ProgressBar pb =((ProgressBar) (findViewById(R.id.status_health)));
        pb.setProgress(power + 1);
        ((TextView)(findViewById(R.id.status_speech))).setText(scriptLose);
//        if(power<3) {
//            pb.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
//        }else {
//            pb.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
//        }
    }

    public void onSC(View v){
        Intent intent = new Intent(this, GBBActivity.class);
        intent.putExtra(GHOST_KIND, kind);
        intent.putExtra(GHOST_NAME, name);
        intent.putExtra(GHOST_POWER, power);

        intent.putExtra(GHOST_SCRIPT_LOSE, scriptLose);
        intent.putExtra(GHOST_SCRIPT_WIN, scriptWin);

        startActivity(intent);

    }

}