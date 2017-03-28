package com.zhoujian.persistentdata;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity
{

    private EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit = (EditText) findViewById(R.id.edit);
        String inputText = loadData();
        if (!TextUtils.isEmpty(inputText))
        {
            edit.setText(inputText);
            edit.setSelection(inputText.length());
            Toast.makeText(this, "恢复数据成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        String inputText = edit.getText().toString();
        saveData(inputText);
    }

    public void saveData(String inputText)
    {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try
        {
            //MODE_PRIVATE:默认模式，表示当指定同样文件名的时候，所写的内容将会覆盖原文件中的内容
            //MODE_APPEND:表示如果文件存在，就往文件里追加内容，不存在就创建
            out = openFileOutput("file", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String loadData()
    {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try
        {
            in = openFileInput("file");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null)
            {
                sb.append(line);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (reader != null)
            {
                try
                {
                    reader.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
