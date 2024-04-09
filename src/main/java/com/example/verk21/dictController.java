package com.example.verk21;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;
import java.io.*;
import java.net.*;


public class dictController {

    private final String dictAPI = "https://api.dictionaryapi.dev/api/v2/entries/en/";
    @FXML
    private Button Conf;
    private TextField dictQuery;

    public void onDict() throws MalformedURLException{
        String Query = dictAPI.concat("hello");
        URL url = new URL(dictAPI);
        try {
            URLConnection urlc = url.openConnection();
            urlc.setRequestProperty("Query","hello");

            BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
            String l = null;
            while((l=br.readLine())!=null){
                System.out.println(l);
            }
        }
        catch (IOException e){
            System.out.println("ERR:IOEXCEPTION");
        }

    }



}
