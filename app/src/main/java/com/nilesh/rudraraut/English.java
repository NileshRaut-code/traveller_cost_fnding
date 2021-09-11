package com.nilesh.rudraraut;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class English extends AppCompatActivity {
    Button bt;
    EditText fr;
    EditText too;
    TextView lo;
    TextView la;
    String lat1;
    String lon1;
    TextView ansa;
    TextView prc;
    EditText ra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english2);
        bt=findViewById(R.id.check);
        fr=findViewById(R.id.from);
        ansa=findViewById(R.id.ans);
        too=findViewById(R.id.to);
        prc=findViewById(R.id.price);
        ra=findViewById(R.id.rate);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String apiKey = "0074f0b05461c854b54c6fcf89bef764";
                String city1 = fr.getText().toString();
                String city2 = too.getText().toString();
                int rat=Integer.parseInt(ra.getText().toString());
                String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city1 + "&units=metric&appid=0074f0b05461c854b54c6fcf89bef764";
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                        url,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONObject object = response.getJSONObject("main");
                                    JSONObject object1 = response.getJSONObject("coord");
                                    String lat1 = object1.getString("lat");
                                    String log1 = object1.getString("lon");
                                    double la1 = Double.parseDouble(lat1);
                                    double lo1 = Double.parseDouble(log1);
                                    String url="https://api.openweathermap.org/data/2.5/weather?q="+city2+"&units=metric&appid=0074f0b05461c854b54c6fcf89bef764";
                                    RequestQueue queue2= Volley.newRequestQueue(getApplicationContext());
                                    JsonObjectRequest request1=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            try {

                                                JSONObject object = response.getJSONObject("main");

                                                JSONArray objec = response.getJSONArray("weather");

                                                JSONObject object1 = response.getJSONObject("coord");
                                                String lat2 =object1.getString("lat");
                                                String log2=object1.getString("lon");
                                                double la2 = Double.parseDouble(lat2);
                                                double lo2 = Double.parseDouble(log2);




                                                Location selected_location = new Location("locationA");
                                                selected_location.setLatitude(la1);
                                                selected_location.setLongitude(lo1);
                                                Location near_locations = new Location("locationB");
                                                near_locations.setLatitude(la2);
                                                near_locations.setLongitude(lo2);
                                                double distance = selected_location.distanceTo(near_locations)/1000;
                                                int dist= (int)distance;
                                                if(dist<100){

                                                }
                                                else if(dist>100 && dist<100 ){
                                                    double ex=dist*0.2;
                                                    int ex2=(int)ex;
                                                    dist=dist+ex2;
                                                }
                                                else if(dist>200 && dist <200){
                                                    double ex=dist*0.25;
                                                    int ex2=(int)ex;
                                                    dist=dist+ex2;
                                                }
                                                else if (dist>200 && dist <300){
                                                    double ex=dist*0.28;
                                                    int ex2=(int)ex;
                                                    dist=dist+ex2;
                                                }
                                                else {
                                                    double ex=dist*0.35;
                                                    int ex2=(int)ex;
                                                    dist=dist+ex2;
                                                }
                                                int sa=dist*rat;
                                                String as=String.valueOf(sa);
                                                String anss=String.valueOf(dist);
                                                ansa.setText(anss+"KM   Price : "+as+"To ckeck Map Click Below ,stay happy");



                                                Toast.makeText(English.this, "Sucess", Toast.LENGTH_SHORT).show();




                                            } catch (JSONException e) {
                                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    },
                                            error -> Toast.makeText(English.this,error.toString(), Toast.LENGTH_SHORT
                                            ).show());

                                    queue2.add(request1);

                                } catch (JSONException e) {
                                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Toast.makeText(English.this, error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                queue.add(request);


            }
        });

    }
}
