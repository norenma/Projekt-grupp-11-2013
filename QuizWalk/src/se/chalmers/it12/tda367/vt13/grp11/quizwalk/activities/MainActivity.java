package se.chalmers.it12.tda367.vt13.grp11.quizwalk.activities;

import se.chalmers.it12.tda367.vt13.grp11.quizwalk.R;
import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends Activity {
  static final LatLng HAMBURG = new LatLng(53.558, 9.927);
  static final LatLng KIEL = new LatLng(53.551, 9.993);
  private GoogleMap map;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
        .getMap();
    
    if (map != null){
      Marker hamburg = map.addMarker(new MarkerOptions().position(HAMBURG)
          .title("Hamburg"));
      Marker kiel = map.addMarker(new MarkerOptions()
          .position(KIEL)
          .title("Kiel")
          .snippet("Kiel is cool")
          .icon(BitmapDescriptorFactory
              .fromResource(R.drawable.ic_launcher)));
    }
    
  } 
  
}