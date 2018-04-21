package com.darren.fileexplorer;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

import java.io.File;
import java.io.IOException;

public class MainActivity extends Activity implements OnItemClickListener{
    private ListView listview;
    private Button btnParent;
    private final String sdcardDir = "/mnt/sdcard/";

    private File currentParent = new File(sdcardDir);
    private File[] currentFiles;
    private ListItemAdapter fileAdater;
    private TextView tv_path;
    private TextView tv_parent;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        tv_path = (TextView)findViewById(R.id.tv_path);
        tv_parent = (TextView)findViewById(R.id.tv_parent);
        listview = (ListView)findViewById(R.id.lv_files);

        tv_parent.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(currentParent.getCanonicalPath().equals("/")) {
                        Toast.makeText(MainActivity.this,"root directory",Toast.LENGTH_SHORT).show();
                        return ;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                currentParent = currentParent.getParentFile();
                currentFiles = currentParent.listFiles();
                updateView(currentParent,currentFiles);
            }
        });

        listview.setOnItemClickListener(MainActivity.this);

        if(currentParent.exists()) {
            System.out.println("/mnt/sdcard/  exits");
            currentFiles = currentParent.listFiles();
            updateView(currentParent,currentFiles);
        } else{
            System.out.println("/mnt/sdcard/ doesn't exits");
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        System.out.println("itemClick position:"+position);
        if(position >= currentFiles.length ){
            System.out.println("BoundsException position"+position+" length:"+currentFiles.length);
            return ;
        }
        if(currentFiles[position].isFile()){
            System.out.println("not folder");
            return ;
        }
        File[] items = currentFiles[position].listFiles();
        if(items == null || items.length == 0) {
            System.out.println("empty folder");
            Toast.makeText(MainActivity.this,"Empty folder",Toast.LENGTH_SHORT).show();
        } else {
            currentParent = currentFiles[position];
            currentFiles = currentParent.listFiles();
            updateView(currentParent,currentFiles);
        }
    }

    private void updateView(File parrent,File[] files){
        try {
            tv_path.setText(parrent.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        tv_parent.setText("../（返回上级目录）");

        fileAdater = new ListItemAdapter(MainActivity.this,files);
        listview.setAdapter(fileAdater);
    }
}
