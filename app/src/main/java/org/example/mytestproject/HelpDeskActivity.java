package org.example.mytestproject;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


public class HelpDeskActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_help_desk);
        // connect the spinner
        Spinner spinnerNatures = (Spinner) findViewById(R.id.spinnerNature);
        // Create an ArrayAdapter using the string array and a defauly spinner layour
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.natures_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerNatures.setAdapter(adapter);

        spinnerNatures.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            // Set the check to false
            boolean check = false;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // If this has been checked, show the toast.
                if (check) {
                    Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " Selected", Toast.LENGTH_LONG).show();
                }
                check = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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
