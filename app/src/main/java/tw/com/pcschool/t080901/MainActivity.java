package tw.com.pcschool.t080901;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    LocationManager lm;
    TextView textView1, textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        textView1 = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        MyLocationListener myLocationListener = new MyLocationListener();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                0,
                0,
                myLocationListener
        );
    }

    class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            Log.d("MYGPS", "GPS:" + location.getLatitude() + "," + location.getLongitude());
            textView1.setText("Location-GPS" + "\n" +
                    " 緯度-Latitude：" + location.getLatitude() + "\n" +
                    " 經度-Longitude：" + location.getLongitude() + "\n" +
                    " 精確度-Accuracy：" + location.getAccuracy() + "\n" +
                    " 標高-Altitude：" + location.getAltitude() + "\n" +
                    " 時間-Time：" + new Date(location.getTime()) + "\n" +
                    " 速度-Speed：" + location.getSpeed() + "\n" +
                    " 方位-Bearing：" + location.getBearing());
            setTitle("GPS 位置資訊已更新");

            Location loc = new Location("MyLoc");

            loc.setLatitude(25.0337);
            loc.setLongitude(121.5645);
            float distance = location.distanceTo(loc);
            textView2.setText("To 101: " + distance);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }
}
