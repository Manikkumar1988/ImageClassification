package com.example.mani.classifyimg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ClassifyingActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classifying);

        findViewById(R.id.send_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        String inputText = String.valueOf(((EditText) findViewById(R.id.input_edit_text)).getText());
        switch (view.getId()) {
            case R.id.send_button: {
                validateString(inputText);
                break;
            }
        }
    }

    private void validateString(String validationString) {
        if (validationString.equalsIgnoreCase(getString(R.string.list_all_images))) {

        } else if (validationString.matches(list_x_untagged_images_pattern)) {
            Toast.makeText(getApplicationContext(), "list \"x\" untagged images", Toast.LENGTH_SHORT).show();
        } else if (validationString.matches(selection_pattern)){
            Toast.makeText(getApplicationContext(), "Select x …. y and z images", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "False",Toast.LENGTH_SHORT).show();
        }

    }

    //List “x” untagged images
    String list_x_untagged_images_pattern = "(?i)" + // Ignores case sensitivity
            "^" + // Must match at the beginning
            "\\blist \\b" + // Matches a word boundary
            "\\d " + // Any digit
            "\\ untagged images\\b" +
            "$"; //must match at the end

    //Select x …. y and z images
    String selection_pattern = "(?i)" + // Ignores case sensitivity
            "^" + // Must match at the beginning
            "\\bselect \\b" + // Matches a word boundary
            "((\\d)(rd|st|nd|th)) \\b" +
            "\\band \\b" +
            //"((\\d)(rd|st|nd|th)) \\b " +
            "\\bimages\\b" +
            "$"; //must match at the end
}
