package com.example.texttospeech_ver03;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.example.texttospeech_ver03.speechtotextgroup3.R;
import java.util.Locale;

public class MainActivity extends Activity {
    String text;
    EditText et;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et=(EditText)findViewById(R.id.editText1);
        tts=new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                // TODO Auto-generated method stub
                if(status == TextToSpeech.SUCCESS){
                    int result=tts.setLanguage(Locale.US);
                    if(result==TextToSpeech.LANG_MISSING_DATA ||
                            result==TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("error", "This Language is not supported");
                    }
                    else{
                        ConvertTextToSpeech();
                    }
                }
                else {
                    int error = Log.e("error", "Initilization Failed!");
                }
            }
        });
    }
@Override
protected void onPause() {
    if(tts != null){
        tts.stop();
        tts.shutdown();
    }
    super.onPause();
}
    public void onClick(View v){
        ConvertTextToSpeech();
    }
    private void ConvertTextToSpeech() {
        text = et.getText().toString();
        if(text==null||"".equals(text))
        {
            text = "Please enter content";
            // Nếu user k enter text, sẽ đọc lên Please....
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }else
            // trước khi đọc text user enter, đọc START.
            // sau khi đọc xong text của user thì đọc " THis project ..."
            tts.speak("Start"+" "+text+"This project is from Group 3", TextToSpeech.QUEUE_FLUSH, null);
    }
}