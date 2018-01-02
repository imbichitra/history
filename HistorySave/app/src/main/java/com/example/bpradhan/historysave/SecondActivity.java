package com.example.bpradhan.historysave;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    ListView listView;

    int j=0;
    String a1[];
    String a2[];
    String a3[];
    ArrayList<String> al=new ArrayList<String>();

    public void ab(){
        a1=new String[al.size()];
        a2=new String[al.size()];
        a3=new String[al.size()];
        //Log.e("SecondActivity", "" + al.get(0));
       for(int i=0;i<al.size();i++) {
           String s = al.get(i);
           String ss[] = s.split(" ");
           Log.i("SecondActivity ggg", "" +ss.length);
          for(j=i;j<ss.length;j++){
              if(j==0)
                  a1[i]=ss[j];
              if(j==1)
                  a2[i]=ss[j];
              if(j==2)
                  a3[i]=ss[j];
          }
            for(String dd:a1)
               Log.i("SecondActivity ggg", "" + dd);
           //Log.e("SecondActivity", "" + al.get(0));
           //Log.e("SecondActivity", "length " + al.size());
       }
        //Log.i("SecondActivity jj=",""+arr);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        listView=(ListView)findViewById(R.id.list);
        load();
        ab();
        CustomAdapter customAdapter=new CustomAdapter();
        listView.setAdapter(customAdapter);
    }
    public void load(){
        File Root= Environment.getExternalStorageDirectory();
        File Dir=new File(Root.getAbsolutePath()+"/MyAppFile");
        File file=new File(Dir,"MyMessage.txt");
        String message;
       // StringBuffer sb=new StringBuffer();

        int i=0;
        try {
            FileInputStream fileInputStream=new FileInputStream(file);
            InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer=new StringBuffer();
            while((message=bufferedReader.readLine())!=null){
                j++;
                Log.i("SecondActivity j=",""+j);
                //sb.append(message+" ");
                al.add(message);
               // arr=arr.concat(message+" ");
               // for(String s:ss)
                   // Log.e("SecondActivity",""+sb);
                //String length[]=message.split(" ");

                // array.add(message);
            }
            //textView.setText(stringBuffer.toString());
            //textView.setVisibility(View.VISIBLE);
            // ArrayAdapter<String> adpt=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array);
            //listView.setAdapter(adpt);
           // Log.i("SecondActivity jj=",""+arr);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    class CustomAdapter extends BaseAdapter{
        ArrayList<String> array=new ArrayList<String>();

        @Override
        public int getCount() {
            return a1.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=getLayoutInflater().inflate(R.layout.history,null);
            TextView t1=(TextView)view.findViewById(R.id.textView3);
            TextView t2=(TextView)view.findViewById(R.id.textView4);
            TextView t3=(TextView)view.findViewById(R.id.textView5);
           // Log.e("SecondActivity",""+sb);
            t1.setText(a1[position]);
            t2.setText(a2[position]);
            t3.setText(a3[position]);
            return convertView;
        }

    }



}
