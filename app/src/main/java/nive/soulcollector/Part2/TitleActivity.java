package nive.soulcollector.Part2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import nive.soulcollector.R;

public class TitleActivity extends Activity  {
    private LinearLayout titleBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        titleBack = (LinearLayout) findViewById(R.id.titleBack);

        Bitmap size =  BitmapFactory.decodeResource(getResources(), R.drawable.start_bg);
        size = Bitmap.createScaledBitmap(size,1080,1980, true);
        titleBack.setBackground(new BitmapDrawable(size));

    }

    public void onTitleClick(View v){
        Intent intent = new Intent(TitleActivity.this, GhostPrisonActivity.class);
        startActivity(intent);
    }
}
