package com.example.apppp2;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.os.Bundle;
import android.widget.TextView;
import android.util.Log;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import okhttp3.OkHttpClient;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private RadioButton rb;
    private RadioButton rb2;
    private EditText editTextDate;
    private EditText editTextTime;
    private TextView output1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        rb = (RadioButton) findViewById(R.id.radioButton1);
        rb2 = (RadioButton)findViewById(R.id.radioButton2);

        editTextDate = (EditText)findViewById(R.id.editTextDate);
        editTextTime = (EditText)findViewById(R.id.editTextTime);
        output1 = (TextView) findViewById(R.id.output1);


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


            String apikey="3449d87ceaf2391c2de38815fcc522f9";
            String url="https://api.openweathermap.org/data/2.5/weather?q=Volgograd&appid=" + apikey;

            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONObject object = response.getJSONObject("main");
                        String temp = object.getString("temp");
                        output1.setText(temp);
                    } catch (JSONException e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(request);
        }



    // Подключение карты с маркером
    @Override
    public void onMapReady(GoogleMap googleMap) {

        // Начальные координаты при открытии приложения
        LatLng volgograd = new LatLng(48.68906521383257, 44.48623091158812);
        googleMap.addMarker(new MarkerOptions()
                .position(volgograd)
                .title("Raboche_Krestianskaya"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(volgograd, 15));

        // Изменение положения карты при клике на RadioButton
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton1:
                        LatLng volgograd = new LatLng(48.68906521383257, 44.48623091158812);
                        googleMap.addMarker(new MarkerOptions()
                                .position(volgograd)
                                .title("Raboche_Krestianskaya"));
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(volgograd, 15));
                        break;
                    case R.id.radioButton2:
                        LatLng volgograd1 = new LatLng(48.70908248480214, 44.5278157336385);
                        googleMap.addMarker(new MarkerOptions()
                                .position(volgograd1)
                                .title("Sovetskaya"));
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(volgograd1, 15));
                    default:
                        break;
                }
            }
        });

    }




}