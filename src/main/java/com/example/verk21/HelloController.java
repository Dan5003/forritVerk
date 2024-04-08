package com.example.verk21;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.concurrent.TimeUnit;

public class HelloController {

    private String savedText = "";

    @FXML
    public Label searchNum;

    @FXML
    public Label wordCount;

    @FXML
    public TextArea textSave;

    @FXML
    public TextField textSearch;


    @FXML
    public void onSearch(){
        int counter = 0;
        int lCounter = 0;
        String searchText = textSearch.getText();
        for (int i = 0; i < savedText.length(); i++){

            System.out.println(savedText.charAt(i) + " " + searchText.charAt(lCounter));

            //Compares current character in savedText with the current character of searchText
            if(Character.toLowerCase(savedText.charAt(i)) == Character.toLowerCase(searchText.charAt(lCounter))){
                lCounter++;
            }
            else {
                lCounter = 0;
            }
            //if lCounter gets as long as the length of searchText then it raises counter by one
            if (lCounter == searchText.length()){
                counter++;
                lCounter = 0;
            }
            System.out.println("i: " + i);
            System.out.println("lCounter: " + lCounter);
            System.out.println("Counter: " + counter);
            System.out.println(" ");

        }
        searchNum.setText(String.valueOf(counter));;

    }

    @FXML
    public void onSave(){
        savedText = textSave.getText();
        System.out.println("\"" + savedText + "\"");
        textSave.setText("");
        textSave.setPromptText("write text here...");

    }

    @FXML
    public void onCount(){
        int counter = 0;
        String trim = savedText.trim();
        if (trim.isEmpty())
             counter = 0;
        else{ counter = trim.split("\\s+").length;}
        wordCount.setText(String.valueOf(counter));
    }

}