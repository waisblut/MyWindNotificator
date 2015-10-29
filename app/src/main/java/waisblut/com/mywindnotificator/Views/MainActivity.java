package waisblut.com.mywindnotificator.Views;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONObject;

import waisblut.com.mywindnotificator.HttpRequest;
import waisblut.com.mywindnotificator.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        new ReadWeatherJSONFeedTask<Void, Void, String>().execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class ReadWeatherJSONFeedTask<V, V1, S> extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... urls) {
            String response;

            try {
                response = new HttpRequest("http://api.openweathermap.org/data/2.5/weather?zip=94040,us&appid=bd82977b86bf27fb59a04b61b657fb6f")
                        .preparePost()
                        .sendAndReadString();

            } catch (Exception e) {
                response = e.getMessage();
            }


//            try {
//                URL url = new URL(urls[0]);
//                HttpRequest request = new HttpRequest("http://api.openweathermap.org/data/2.5/weather");
//
//                //Opcao 1
//                request.preparePost().withData("zip=94040,us&appid=bd82977b86bf27fb59a04b61b657fb6f").send();
//
//                //Opcao 2
//                request.prepare().sendAndReadString();
//
//                //Opcao 3
//                HashMap<String, String> params = new HashMap<>();
//                params.put("zip", "94040,us");
//                params.put("appid", "bd82977b86bf27fb59a04b61b657fb6f");
//                request.preparePost().sendAndReadJSON();
//
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }


            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONObject weatherObservationItems = new JSONObject(jsonObject.getString("weatherObservation"));

                Toast.makeText(getBaseContext(), weatherObservationItems.getString("clouds") +
                                " - " + weatherObservationItems.getString("stationName"),
                        Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.d("ReadWeatherJSONFeedTask", e.getLocalizedMessage());
            }
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
            //super.onPostExecute(result);
        }
    }
}
