package com.example.apppp2;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ImageView;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private RadioGroup rg;
    private RadioButton rb;
    private RadioButton rb2;
    private ImageView img;
    private EditText editTextDate;
    private EditText editTextTime;
    double lat;
    double lon;



    // [START_EXCLUDE]
    // [START maps_marker_get_map_async]
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_main);

        // Get the SupportMapFragment and request notification when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        rb = (RadioButton) findViewById(R.id.radioButton1);
        //rb.setOnCheckedChangeListener(radioButtonClickListener);
        rb2 = (RadioButton)findViewById(R.id.radioButton2);
        //rb2.setOnCheckedChangeListener(onCheckedChanged);


        editTextDate = (EditText)findViewById(R.id.editTextDate);
        editTextTime = (EditText)findViewById(R.id.editTextTime);

        // Текущее время
        Date currentDate = new Date();
        // Форматирование времени как "день.месяц.год"
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);
        // Форматирование времени как "часы:минуты:секунды"
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String timeText = timeFormat.format(currentDate);

        editTextDate.setText(dateText);
        editTextTime.setText(timeText);




    }
    // [END maps_marker_get_map_async]
    // [END_EXCLUDE]

    // [START_EXCLUDE silent]
    /**
     * Manipulates the map when it's available.
     * The API invokes this callback when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user receives a prompt to install
     * Play services inside the SupportMapFragment. The API invokes this method after the user has
     * installed Google Play services and returned to the app.
     */
    // [END_EXCLUDE]






    // [START maps_marker_on_map_ready_add_marker]
    @Override
    public void onMapReady(GoogleMap googleMap) {

        lat = 48.68906521383257;
        lon = 44.48623091158812;
        LatLng volgograd = new LatLng(lat, lon);
        googleMap.addMarker(new MarkerOptions()
                .position(volgograd)
                .title("Marker in Raboche_Krestianskaya"));
        // [START_EXCLUDE silent]
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(volgograd, 15));



        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {



            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton1:
                        lat = 48.68906521383257;
                        lon = 44.48623091158812;
                        LatLng volgograd = new LatLng(lat, lon);
                        googleMap.addMarker(new MarkerOptions()
                                .position(volgograd)
                                .title("Marker in Raboche_Krestianskaya"));
                        // [START_EXCLUDE silent]
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(volgograd, 15));
                        // [END_EXCLUDE]
                        break;
                    case R.id.radioButton2:
                        lat = 48.70908248480214;
                        lon = 44.5278157336385;
                        LatLng volgograd1 = new LatLng(lat, lon);
                        googleMap.addMarker(new MarkerOptions()
                                .position(volgograd1)
                                .title("Marker in Raboche_Krestianskaya"));
                        // [START_EXCLUDE silent]
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(volgograd1, 15));
                    default:
                        break;
                }
            }
        });


        // [START_EXCLUDE silent]
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        // [END_EXCLUDE]




    }







}