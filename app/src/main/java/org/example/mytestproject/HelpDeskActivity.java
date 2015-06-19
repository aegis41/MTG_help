package org.example.mytestproject;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class HelpDeskActivity extends ActionBarActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_help_desk);

        /**** Setting up the Spinner Adapter ****/
        // connect the spinner
        Spinner spinnerNatures = (Spinner) findViewById(R.id.spinnerNature);
        // Create an ArrayAdapter using the string array and a defauly spinner layout
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
                    Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " Selected", Toast.LENGTH_SHORT).show();
                }
                check = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        /**** End Spinner Adapter ****/


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


    public void sendHelpDesk(View view) {
        EditText etName = (EditText)findViewById(R.id.editTextName);
        EditText etEmail = (EditText)findViewById(R.id.editTextEmail);
        EditText etPhone = (EditText)findViewById(R.id.editTextPhone);
        EditText etIssue = (EditText)findViewById(R.id.editTextIssue);
        // connect the spinner
        Spinner spinnerNatures = (Spinner) findViewById(R.id.spinnerNature);

        String name = etName.getText().toString(); // sender's name
        String email = etEmail.getText().toString(); // sender's email
        String phone = etPhone.getText().toString(); // sender's phone
        String nature = spinnerNatures.getSelectedItem().toString(); // selected nature of this issue
        String issue = etIssue.getText().toString(); // multiline text of this issue
        String recipient[] = {"helpdesk@marathontechgroup.com"};
        String subject;
        String timeSegment;
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);
        int dayNight = c.get(Calendar.AM_PM);
        timeSegment = hour + ":" + minute;
        if (dayNight == Calendar.AM) {
            timeSegment += " AM";
        } else {
            timeSegment += " PM";
        }

        issue = name + "\n" + issue + "\n" + phone + "\n" + email;

        subject = timeSegment + " " + name + " " + nature;

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL,recipient);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT,issue);
        emailIntent.setType("message/rfc822");
        startActivity(emailIntent);

    }
}
