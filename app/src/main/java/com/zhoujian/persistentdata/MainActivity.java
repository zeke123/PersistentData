package com.zhoujian.persistentdata;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText mEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEdit = (EditText) findViewById(R.id.edit);


    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        String text = mEdit.getText().toString().trim();
        saveData(text);
    }

    //保存数据
    private void saveData(String text) {


        FileOutputStream out = null;

        BufferedWriter bfWriter = null;

        try {

            //第一个参数是文件名，所有文件都默认存储到data/data/包名/files/目录下的
            //第二个参数是文件的操作模式
            //MODE_PRIVATE:默认的操作模式，表示指定同样文件名的时候，所写的内容会覆盖原文件中的内容
            //MODE_APPEND:表示如果该文件存在，就往文件里追加内容，不存在就创建新文件
            out = openFileOutput("file", Context.MODE_PRIVATE);

            bfWriter = new BufferedWriter(new OutputStreamWriter(out));

            bfWriter.write(text);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (bfWriter != null) {
                    bfWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
