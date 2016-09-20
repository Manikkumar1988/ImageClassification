package com.example.mani.classifyimg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class ClassifyingActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classifying);

        findViewById(R.id.send_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        String inputText = String.valueOf(((EditText)findViewById(R.id.input_edit_text)).getText());
        switch (view.getId()){
            case R.id.send_button: {
                validateString(inputText);
                break;
            }
        }
    }

    private void validateString(String validationString) {
        if(validationString.equalsIgnoreCase(getString(R.string.list_all_images))){

        } else if(validationString.matches(pattern)) {
            Toast.makeText(getApplicationContext(),"True",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(),"False",Toast.LENGTH_SHORT).show();
        }

    }

    //List “x” untagged images
    Pattern pattern1 = Pattern.compile("^" + //must match at the beginning
            "\\blist \\b" +
            "\\d" +
            "\\ untagged images\\b" +
            "$"); //must match at the end
    String pattern = "(?i)" +
            "^" + //must match at the beginning
            "\\bList \\b" +
            "\\d" +
            "\\ untagged images\\b" +
            "$"; //must match at the end
}
