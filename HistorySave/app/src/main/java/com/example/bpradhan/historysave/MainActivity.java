package com.example.bpradhan.historysave;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    SimpleDateFormat dateFormat;
    String currentDateTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=(EditText)findViewById(R.id.editText);
        textView=(TextView)findViewById(R.id.textView);
        textView.setVisibility(View.GONE);
        dateFormat = new SimpleDateFormat("  yyyy-MM-dd  HH:mm:ss");
    }
    public void saveData(View view){
        String state;
        state=Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)) {
            File Root= Environment.getExternalStorageDirectory();
            File Dir=new File(Root.getAbsolutePath()+"/MyAppFile");
            if(!Dir.exists()){
                Dir.mkdir();
            }
            File file=new File(Dir,"MyMessage.txt");
            String message=editText.getText().toString();
            try{
                currentDateTime= dateFormat.format(Calendar.getInstance().getTime());
                FileOutputStream fileOutputStream=new FileOutputStream(file,true);
                fileOutputStream.write(message.getBytes());

                fileOutputStream.write(currentDateTime.getBytes(Charset.forName("UTF-8")));

                fileOutputStream.write("\n".getBytes());
                Log.e("yyy",currentDateTime.getBytes(Charset.forName("UTF-8")).toString());
                fileOutputStream.close();
                editText.setText("");
                Toast.makeText(getApplication(),"messsage saved",Toast.LENGTH_LONG).show();

            }catch (FileNotFoundException e){
                e.printStackTrace();
                Log.e("yyy","file not found");
            }catch (IOException e){
                e.printStackTrace();
                Log.e("yyy","ioex");
            }


        }
        else{
            Toast.makeText(getApplication(),"sd card not found",Toast.LENGTH_LONG).show();
        }



    }
    public void loadData(View view){
        Intent i = new Intent(getApplicationContext(),SecondActivity.class);
        startActivity(i);


    }

}
