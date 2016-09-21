package com.example.mani.classifyimg.model;


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
}
