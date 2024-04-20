package com.example.verk21;

import Vinnsla.Dict;
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
    private TextArea fxMeaning;

    @FXML
    private Label dictName;


    private Strengir strengir = new Strengir();

    private Dict dict = new Dict();


    @FXML
    public void onLeit(ActionEvent actionEvent) {
        try {
            String leitarord = fxLeitarord.getText();
            if (fxLeitarord.getText().isEmpty()) {
                fxStadsetningOrds.setText("No word inputted");
            } else if (strengir.leita(leitarord) == -1) {
                fxStadsetningOrds.setText(leitarord + " is not in text");
            } else {
                String numer = Integer.toString(strengir.leita(leitarord));
                fxStadsetningOrds.setText("The "+ leitarord + " is number: " + numer);
            }
        } catch (NullPointerException e) {
            fxStadsetningOrds.setText("No text was inputted");
        }
    }

    /**
     * núllstillum reitinn.
     */
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
            if (fjoldi.equals("1")) {
                fxFjoldiOrda.setText("There is only 1 word");
            } else {
                fxFjoldiOrda.setText("There are " + fjoldi + " words");
            }
        } catch (NullPointerException e) {
            fxFjoldiOrda.setText("No words found");
        }
    }

    /**
     * Athugum hvort að strengurinn sé tómur og gefum villu skilaboð.
     */
    @FXML
    public void onVistaTexta(ActionEvent actionEvent) {
        String lesinnTexti = fxTextinn.getText();
        if (fxTextinn.getText().isEmpty()) {
            fxFjoldiOrda.setText("No words inputted");
        } else {
            strengir.setTexti(lesinnTexti);
            fxStadsetningOrds.setText("0");
        }
    }

    /**
     * núllstillum reitinn.
     */
    @FXML
    public void onTextaBox(KeyEvent actionEvent) {
        fxFjoldiOrda.setText("0");
    }


    public void onDictionaryCheck(ActionEvent actionEvent) {

        String searchWord = fxMeaning.getText().trim();
        if (searchWord.isEmpty()) {
            fxMeaning.setPromptText("write a word for the dictionary!");
        } else {

            String meaning = dict.lookupInDictionary(searchWord);


            if (meaning.equals(searchWord + " was not found in the dictionary") || meaning.equals("Error searching for a word")) {
                dictName.setText(meaning);
            } else {
                dictName.setText(meaning);
            }
        }
    }

    @FXML
    public void onGetRandomWord(ActionEvent actionEvent) {
        String randomWord = dict.getRandomWord();
        dictName.setText(randomWord);
    }

}