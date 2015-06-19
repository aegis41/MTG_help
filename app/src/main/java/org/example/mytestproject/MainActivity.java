package org.example.mytestproject;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import java.util.Calendar;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupCallButton();
    }

    // called when the user clicks the helpdesk button
    public void contactHelpdesk(View view){
        Intent helpIntent = new Intent(this,HelpDeskActivity.class);
        startActivity(helpIntent);
        // make the other helpdesk activity show up

    }

    private void setupCallButton() {
        // 1. get a reference to the button
        Button callButton = (Button) findViewById(R.id.button_call);
        // 2. set the click listener
        callButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String phoneNo = "tel:12606330053";

                // get a calendar object
                Calendar c = Calendar.getInstance();
                // grab the hour of day (24 hour format) from the calendar
                int hour = c.get(Calendar.HOUR_OF_DAY);
                // grab the day of the week from the calendar
                int day = c.get(Calendar.DAY_OF_WEEK);
                // if the day is not Saturday or Sunday
                if (day != 1 && day != 7) {
                    // if the hour is >=8 && <=17
                    if (hour >= 8 && hour <= 17) {
                        phoneNo = "tel:12604714316";
                    }
                }

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse(phoneNo));
                    startActivity(callIntent);
            }
        });
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
}
