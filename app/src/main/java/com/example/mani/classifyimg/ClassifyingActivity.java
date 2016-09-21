package com.example.mani.classifyimg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mani.classifyimg.adapter.ImagesAdapter;
import com.example.mani.classifyimg.model.ChatCommand;
import com.example.mani.classifyimg.model.ImageItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClassifyingActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classifying);
        initializeViews();
        getData();
    }

    private void initializeViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),COLUMN_COUNT);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        inputField = (EditText) findViewById(R.id.input_edit_text);

        findViewById(R.id.send_button).setOnClickListener(this);

        inputField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    processInputText();
                    return true;
                }
                return false;
            }
        });


    }

    private void processInputText() {
        String inputText = String.valueOf(inputField.getText());
        resetInputField(inputText);
        validateAndExecuteCommand(inputText);
    }

    private void getData() {
        imagesHashMap = ImageItem.createImageList();
    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.send_button: {
                processInputText();
                break;
            }
        }
    }


    private void resetInputField(String inputText) {
        inputField.setText("");
        inputField.setHint(inputText);
    }

    private void validateAndExecuteCommand(String validationString) {
        ChatCommand chatCommand = new ChatCommand();
        if (validationString.equalsIgnoreCase(getString(R.string.list_all_images))) {
            loadAllImages();
        } else if (validationString.matches(chatCommand.list_x_untagged_images_pattern)) {
            ArrayList<Integer> numbers = chatCommand.getNumbersInTheString(validationString);
            loadXUntaggedImages(numbers);
        } else if (validationString.matches(chatCommand.selection_pattern)){
            ArrayList<Integer> numbers = chatCommand.getNumbersInTheString(validationString);
            selectXImages(numbers);
        }else if (validationString.matches(chatCommand.classifyStringPattern)){
            classifyXImages(chatCommand.getTagKeywords(validationString));
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.enter_command),Toast.LENGTH_SHORT).show();
        }

    }

    private void classifyXImages(List<String> tagKeywords) {
        if(adapter!=null) {
        if(adapter.getSelectedItems().size()>0) {
            for(int itemIndex:adapter.getSelectedItems()) {
                 imagesHashMap.get(imageItems.get(itemIndex).getmImageName()).addTags(tagKeywords);
            }
            adapter.removeSelectedData();
        }
        } else {
            Toast.makeText(getApplicationContext(),getString(R.string.list_images),Toast.LENGTH_SHORT).show();
        }
    }

    private void selectXImages(ArrayList<Integer> numbers) {
        if(adapter!=null) {
            if (adapter.getItemCount() > 1 && numbers.size() > 0) {
                for (int number : numbers) {
                    adapter.toggleSelection(number);
                }
            }
        } else {
            Toast.makeText(getApplicationContext(),getString(R.string.list_images),Toast.LENGTH_SHORT).show();
        }
    }

    private void loadXUntaggedImages(ArrayList<Integer> count) {
        if (count.size() > 0 && count.get(0)>1) {
            imageItems.clear();
            int noOfItems = 0;
            for(ImageItem imageItem:imagesHashMap.values())
            {
                if(!imageItem.hasTag()) {
                    noOfItems +=1;
                    ImageItem untaggedItem = new ImageItem();
                    untaggedItem.setmImageName(imageItem.getmImageName());
                    imageItems.add(untaggedItem);
                }
                if(noOfItems==count.get(0))
                    break;
            }
            adapter = new ImagesAdapter(imageItems);
            mRecyclerView.setAdapter(adapter);
        } else {
            Toast.makeText(getApplicationContext(),getString(R.string.list_images),Toast.LENGTH_SHORT).show();
        }
    }

    private void loadAllImages() {
        imageItems.clear();
        imageItems = new ArrayList<ImageItem>(imagesHashMap.values());
        adapter = new ImagesAdapter(imageItems);
        mRecyclerView.setAdapter(adapter);
    }



    private static final int COLUMN_COUNT = 2;
    private ArrayList<ImageItem> imageItems = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private ImagesAdapter adapter;
    private EditText inputField;
    public HashMap<Integer,ImageItem> imagesHashMap;

}
