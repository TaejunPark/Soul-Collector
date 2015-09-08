package nive.soulcollector.Part1.Rader;

import android.app.Activity;
import android.os.Bundle;

import com.nhn.android.maps.NMapView;

import nive.soulcollector.R;

public class FragmentActivity extends Activity {
	
	private NMapView mMapView;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.framents);
        
        mMapView = (NMapView)findViewById(R.id.mapView);
        
		// initialize map view
		mMapView.setClickable(true);
		mMapView.setEnabled(true);
		mMapView.setFocusable(true);
		mMapView.setFocusableInTouchMode(true);
		mMapView.requestFocus();
    }
}