package com.santanawilliams.lostandfound;

/*
* The Contact activity class
* Parent: MainActivity
 */

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class Contact extends Activity {
    private static final String EMAIL = "ars5964@psu.edu";

    private EditText nameET;
    private EditText subjectET;
    private EditText messageET;
    private CheckBox urgentCB;

    private SoundManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        initComponents();
    }

    // Initialize components
    public void initComponents() {
        nameET = (EditText) findViewById(R.id.nameInput);
        subjectET = (EditText) findViewById(R.id.subjectInput);
        messageET = (EditText) findViewById(R.id.messageInput);
        urgentCB = (CheckBox) findViewById(R.id.urgentChk);
        sm = new SoundManager(this);
    }

    // Search EditTexts to make sure they aren't empty
    public boolean searchForEmpties() {
        String errorMsg = "";
        boolean isEmpty = false;
        // If name
        if (nameET.getText().toString().matches("")) {
            errorMsg = "Please enter your name.";
            isEmpty = true;
        }
        else if (subjectET.getText().toString().matches("")) {
            errorMsg = "Please enter a subject for your message.";
            isEmpty = true;
        }
        else if (messageET.getText().toString().matches("")) {
            errorMsg = "Please enter a message";
            isEmpty = true;
        }

        if(isEmpty)
            Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
        return isEmpty;
    }

    // OnClick function for Send button
    public void sendClick(View v){
        // Make sure we have the necessary components
        if (searchForEmpties())
            return;

        // Get the subject and message
        StringBuilder subject = new StringBuilder(subjectET.getText().toString());
        StringBuilder message = new StringBuilder(messageET.getText().toString());

        // Add urgent to subject if Urgent was checked
        if (urgentCB.isChecked())
            subject.insert(0, "URGENT: ");

        // Append name to message
        message.append("\n\n-" + nameET.getText().toString());

        sm.playStapler();

        // Send the e-mail
        composeEmail(new String[]{EMAIL}, subject.toString(), message.toString());
    }

    // Launch an implicit intent that supplies a mail app with the provided information
    // Source: https://developer.android.com/guide/components/intents-common.html#Email
    public void composeEmail(String[] addresses, String subject, String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);

        // Only email apps should handle this
        intent.setData(Uri.parse("mailto:"));

        // Add email to To: line
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        // Add subject
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        // Add message
        intent.putExtra(Intent.EXTRA_TEXT, message);

        // If user has a mail app, launch
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "We were unable to send the e-mail at this time.", Toast.LENGTH_SHORT).show();
        }
    }
}
