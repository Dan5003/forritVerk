package com.example.verk21;

import Vinnsla.Strengir;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;

public class HelloController {
    @FXML
    private TextArea fxLeitarord;

    @FXML
    private TextArea fxTextinn;

    @FXML
    private Label fxStadsetningOrds;

    @FXML
    private Label fxFjoldiOrda;

    @FXML
    private TextArea fxMeaningDisplay;

    private Strengir strengir = new Strengir();


    @FXML
    public void onLeit(ActionEvent actionEvent) {
        try {
            String leitarord = fxLeitarord.getText();
            if (fxLeitarord.getText().isEmpty()) {
                fxStadsetningOrds.setText("Sláðu inn leitarorð!");
            } else if (strengir.leita(leitarord) == -1) {
                fxStadsetningOrds.setText(leitarord+"\nekki í texta");
            } else {
                String numer = Integer.toString(strengir.leita(leitarord));
                fxStadsetningOrds.setText("Orð er númer "+ numer);
            }
        } catch (NullPointerException e) {
            fxStadsetningOrds.setText("Enginn texti vistaður");
        }
    }

    /**
     * núllstillum reitinn.
     * */
    @FXML
    public void onLeitarord(KeyEvent actionEvent) {
        fxStadsetningOrds.setText("0");
    }

    /**
     * Skoðum hvað eru mörg orð í í strengnum.
     */
    @FXML
    public void onTeljaOrd(ActionEvent actionEvent) {
        try {
            String fjoldi = Integer.toString(strengir.fjoldiOrda());
            fxFjoldiOrda.setText("Textinn er "+fjoldi + " orð");
        } catch (NullPointerException e) {
            fxFjoldiOrda.setText("Vantar að bæta við texta!");
        }
    }

    /**
     * Athugum hvort að strengurinn sé tómur og gefum villu skilaboð.
     * */
    @FXML
    public void onVistaTexta(ActionEvent actionEvent) {
        String lesinnTexti = fxTextinn.getText();
        if (fxTextinn.getText().isEmpty()) {
            fxFjoldiOrda.setText("Enginn texti hefur verið sleigin inn,\nvinsamlegast bætið við texta.");
        } else {
            strengir.setTexti(lesinnTexti);
            fxStadsetningOrds.setText("0");
        }
    }

    /**
     * núllstillum reitinn.
     * */
    @FXML
    public void onTextaBox(KeyEvent actionEvent) {
        fxFjoldiOrda.setText("0");
    }


    public void onDictionaryCheck(ActionEvent actionEvent) {
        String searchWord = fxLeitarord.getText().trim(); // Get and trim the input from fxLeitarord

        if (searchWord.isEmpty()) {
            fxMeaningDisplay.setText("Sláðu inn leitarorð!"); // Prompt for input if empty
        } else {
            // Here you'll call your dictionary lookup function with searchWord
            // Assuming you have a dictionary lookup method similar to:
            // String meaning = lookupInDictionary(searchWord);
            // For now, we'll simulate not finding the word:
            String meaning = null; // Simulate your dictionary lookup

            if (meaning == null) {
                // If no meaning found, display an error message
                fxMeaningDisplay.setText(searchWord + " fannst ekki í orðabókinni.");
            } else {
                // Display the found meaning
                fxMeaningDisplay.setText(meaning);
            }
        }
    }
//
//    @FXML
//    public void onSave(){
//        savedText = textSave.getText();
//        System.out.println("\"" + savedText + "\"");
//        textSave.setText("");
//        textSave.setPromptText("write text here...");
//
//    }
//
//    @FXML
//    public void onCount(){
//        int counter = 0;
//        String trim = savedText.trim();
//        if (trim.isEmpty())
//             counter = 0;
//        else{ counter = trim.split("\\s+").length;}
//        wordCount.setText(String.valueOf(counter));
//    }

}

//
//    private String savedText = "";
//
//    @FXML
//    public Label searchNum;
//
//    @FXML
//    public Label wordCount;
//
//    @FXML
//    public TextArea textSave;
//
//    @FXML
//    public TextField textSearch;
//
//
//    @FXML
//    public void onSearch(){
//        int counter = 0;
//        int lCounter = 0;
//        String searchText = textSearch.getText();
//        for (int i = 0; i < savedText.length(); i++){
//
//            System.out.println(savedText.charAt(i) + " " + searchText.charAt(lCounter));
//
//            //Compares current character in savedText with the current character of searchText
//            if(Character.toLowerCase(savedText.charAt(i)) == Character.toLowerCase(searchText.charAt(lCounter))){
//                lCounter++;
//            }
//            else {
//                lCounter = 0;
//            }
//            //if lCounter gets as long as the length of searchText then it raises counter by one
//            if (lCounter == searchText.length()){
//                counter++;
//                lCounter = 0;
//            }
//            System.out.println("i: " + i);
//            System.out.println("lCounter: " + lCounter);
//            System.out.println("Counter: " + counter);
//            System.out.println(" ");
//
//        }
//        searchNum.setText(String.valueOf(counter));;
//
//    }
//