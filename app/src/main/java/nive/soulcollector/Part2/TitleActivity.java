package nive.soulcollector.Part2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import nive.soulcollector.R;

public class TitleActivity extends Activity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
    }

    public void onTitleClick(View v){
        Intent intent = new Intent(TitleActivity.this, GhostPrisonActivity.class);
        startActivity(intent);
    }
}
