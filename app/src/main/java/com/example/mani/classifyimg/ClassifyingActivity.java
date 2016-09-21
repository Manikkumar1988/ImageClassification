package com.example.mani.classifyimg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mani.classifyimg.adapter.ImagesAdapter;
import com.example.mani.classifyimg.model.ImageItem;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClassifyingActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classifying);
        initializeViews();
    }

    private void initializeViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),COLUMN_COUNT);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        inputField = (EditText) findViewById(R.id.input_edit_text);

        findViewById(R.id.send_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        String inputText = String.valueOf(inputField.getText());

        switch (view.getId()) {
            case R.id.send_button: {
                resetInputField(inputText);
                validateString(inputText);
                break;
            }
        }
    }

    private void resetInputField(String inputText) {
        inputField.setText("");
        inputField.setHint(inputText);
    }

    private void validateString(String validationString) {
        if (validationString.equalsIgnoreCase(getString(R.string.list_all_images))) {
            loadAllImages();
        } else if (validationString.contains("untagged")) {
            ArrayList<Integer> numbers = getNumbersInTheString(validationString);
            loadXUntaggedImages(numbers);
        } else if (validationString.contains("select")){
            ArrayList<Integer> numbers = getNumbersInTheString(validationString);
            selectXImages(numbers);
        }else if (validationString.contains("classify")){
            classifyXImages();
        } else {
            Toast.makeText(getApplicationContext(), "Please enter correct command",Toast.LENGTH_SHORT).show();
        }

    }

    private void classifyXImages() {
        adapter.removeSelectedData();
    }

    private void selectXImages(ArrayList<Integer> numbers) {
        if (numbers.size() > 0 && numbers.get(0)>1) {
            for(int number:numbers)
                adapter.toggleSelection(number);
        }
    }

    private void loadXUntaggedImages(ArrayList<Integer> count) {
        if (count.size() > 0 && count.get(0)>1) {
            imageItems = ImageItem.createImageList(count.get(0));
            adapter = new ImagesAdapter(imageItems);
            mRecyclerView.setAdapter(adapter);
        }
    }

    private void loadAllImages() {
        imageItems = ImageItem.createImageList(20);
        ImagesAdapter adapter = new ImagesAdapter(imageItems);
        mRecyclerView.setAdapter(adapter);
    }

    private ArrayList<Integer> getNumbersInTheString(String input) {
        ArrayList<Integer> numbers = new ArrayList<>();
        Matcher m = numberPattern.matcher(input);
        while (m.find()) {
            numbers.add(Integer.valueOf(m.group()));
        }
        return numbers;
    }

    private static final int COLUMN_COUNT = 2;
    private ArrayList<ImageItem> imageItems;
    private RecyclerView mRecyclerView;
    private ImagesAdapter adapter;
    private EditText inputField;

    //List “x” untagged images
    String list_x_untagged_images_pattern = "(?i)" + // Ignores case sensitivity
            "^" + // Must match at the beginning
            "\\blist \\b" + // Matches a word boundary
            "\\d " + // Any digit
            "\\b untagged images\\b" +
            "$"; //must match at the end

    //Select x …. y and z images
    String selection_pattern = "(?i)" + // Ignores case sensitivity
            "^" + // Must match at the beginning
            "\\bselect \\b" + // Matches a word boundary
            "(((\\d)(rd|st|nd|th))+) \\b" +
            //"\\band \\b" +
            //"((\\d)(rd|st|nd|th)) \\b " +
            "\\bimages\\b" +
            "$"; //must match at the end

    Pattern numberPattern = Pattern.compile("-?" +
            "\\d+"); //matches the digits

}
