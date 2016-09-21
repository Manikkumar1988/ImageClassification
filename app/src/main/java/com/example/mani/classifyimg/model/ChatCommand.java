package com.example.mani.classifyimg.model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatCommand {

    //List “x” untagged images
    public String list_x_untagged_images_pattern = "(?i)" + // Ignores case sensitivity
            "^" + // Must match at the beginning
            "\\blist\\b " + // Matches a word boundary
            "[\\d]+ " + // Any digit
            "\\buntagged images\\b" +
            "$"; //must match at the end

    //Select x …. y and z images
    public  String selection_pattern = "(?i)" + // Ignores case sensitivity
            "^" + // Must match at the beginning
            "\\bselect\\b " + // Matches a word boundary
            "([\\d]+(rd|st|nd|th)(,)+ )*" +
            "([\\d]+(rd|st|nd|th) )" +
            "((\\band\\b) " +
            "([\\d]+(st|nd|rd|th) ))*" +
            "\\bimages\\b" +
            "$"; //must match at the end


    public String classifyStringPattern = "(?i)" +
            "^" +
            "\\bclassify images as\\b " +
            "([a-zA-Z]+(,)+ )*"+
            "([a-zA-Z]+(\\s)*)"+
            "((\\band\\b) " +
            "[a-zA-Z]+)*"+
            "$";
    //List “x” untagged images
    //Select x …. y and z images
    //List all images
    //Classify images as <tag> …. <tag> and <tag>

    Pattern numberPattern = Pattern.compile("-?" +
            "\\d+"); //matches the digits

    public ArrayList<Integer> getNumbersInTheString(String input) {
        ArrayList<Integer> numbers = new ArrayList<>();
        Matcher m = numberPattern.matcher(input);
        while (m.find()) {
            numbers.add(Integer.valueOf(m.group()));
        }
        return numbers;
    }

    public List<String> getTagKeywords(String input) {
        String fixedInput = input.toLowerCase().replaceAll("(classify |images |as |and )", "");
        return Arrays.asList(fixedInput.split(" "));
    }

}
