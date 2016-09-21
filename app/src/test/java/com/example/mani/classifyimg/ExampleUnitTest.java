package com.example.mani.classifyimg;

import android.util.Log;

import com.example.mani.classifyimg.model.ChatCommand;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void listXUntaggedImage_iscorrect() throws Exception {
        ChatCommand chatCommand = new ChatCommand();
        assertEquals("LiSt 25 uNTagged images".matches(chatCommand.list_x_untagged_images_pattern),true);
        assertEquals("LiSt 25 untagged images".matches(chatCommand.list_x_untagged_images_pattern),true);
        assertEquals("LiSt 125 untagged images".matches(chatCommand.list_x_untagged_images_pattern),true);
        assertEquals("LiSt 125 untagged".matches(chatCommand.list_x_untagged_images_pattern),false);
        assertEquals("LiSt 125.445 untagged".matches(chatCommand.list_x_untagged_images_pattern),false);
    }

    @Test
    public void selectXImages_iscorrect() throws Exception {
        ChatCommand chatCommand = new ChatCommand();
        assertEquals("Select 1st and 3rd images".matches(chatCommand.selection_pattern),true);
        assertEquals("Select 2nd And 44th images".matches(chatCommand.selection_pattern),true);
        assertEquals("sElect 4th & images".matches(chatCommand.selection_pattern),false);
        assertEquals("Select 1st, 2nd 5th and 44th images".matches(chatCommand.selection_pattern),false);
        assertEquals("Select 1st, 2nd, 5th and 44th images".matches(chatCommand.selection_pattern),true);
        assertEquals("Select 1st images".matches(chatCommand.selection_pattern),true);
    }

    @Test
    public void classify_iscorrect() throws Exception {
        ChatCommand chatCommand = new ChatCommand();
        assertEquals("clAssify images as sky".matches(chatCommand.classifyStringPattern),true);
        assertEquals("classify images as sky and water".matches(chatCommand.classifyStringPattern),true);
        assertEquals("classify iMages as sky, fire and water".matches(chatCommand.classifyStringPattern),true);
        assertEquals("Classify images as sky fire & water".matches(chatCommand.classifyStringPattern),false);
        assertEquals("Classify images as sky ,fire & water".matches(chatCommand.classifyStringPattern),false);
    }

    @Test
    public void extractString_iscorrect() throws Exception{
        String input = "hello I'm a java dev" +
                "no job experience needed" +
                "senior software engineer" +
                "java job available for senior software engineer";

        String fixedInput = input.replaceAll("(java |job |senior )", "");
        System.out.print(fixedInput);
    }

}