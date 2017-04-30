package com.santanawilliams.lostandfound;

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
    private EditText nameTV;
    private EditText subjectTV;
    private EditText msgTV;
    private CheckBox urgentCB;

    private SoundManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        initComponents();
    }

    public void initComponents() {
        nameTV = (EditText) findViewById(R.id.nameInput);
        subjectTV = (EditText) findViewById(R.id.subjectInput);
        msgTV = (EditText) findViewById(R.id.messageInput);
        urgentCB = (CheckBox) findViewById(R.id.urgentChk);
        sm = new SoundManager(this);
    }

    public boolean searchForEmpties() {
        String errorMsg = "";
        boolean isEmpty = false;
        if (nameTV.getText().toString().matches("")) {
            errorMsg = "Please enter your name.";
            isEmpty = true;
        }
        else if (subjectTV.getText().toString().matches("")) {
            errorMsg = "Please enter a subject for your message.";
            isEmpty = true;
        }
        else if (msgTV.getText().toString().matches("")) {
            errorMsg = "Please enter a message";
            isEmpty = true;
        }

        if(isEmpty)
            Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
        return isEmpty;
    }


    public void sendClick(View v){
        if (searchForEmpties())
            return;

        sm.playStapler();

        StringBuilder subject = new StringBuilder(subjectTV.getText().toString());
        StringBuilder message = new StringBuilder(msgTV.getText().toString());
        if (urgentCB.isChecked())
            subject.insert(0, "URGENT: ");
        message.append("\n\n-" + nameTV.getText().toString());

        composeEmail(new String[]{EMAIL}, subject.toString(), message.toString());
    }

    // Source: https://developer.android.com/guide/components/intents-common.html#Email
    public void composeEmail(String[] addresses, String subject, String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);

        // Only email apps should handle this
        intent.setData(Uri.parse("mailto:"));

        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
