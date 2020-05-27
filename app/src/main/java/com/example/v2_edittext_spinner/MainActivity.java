package com.example.v2_edittext_spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText et;
    private String edit;
    List<String> mlist = new ArrayList<String>();
    private long c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }


    private void init() {
        //得到edittext的引用
        et = (EditText) findViewById(R.id.et01);

        //在List中添加数据
        mlist.add("soccer");
        mlist.add("basketball");
        //得到spinner的引用
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        //自訂調變器
        final BaseAdapter ba = new BaseAdapter() {

            @Override
            public View getView(int arg0, View arg1, ViewGroup arg2) {
                int index = new Long(c).intValue();

                // 得到Linearlayout的引用
                LinearLayout ll = new LinearLayout(MainActivity.this);
                //设置排列方向
                ll.setOrientation(LinearLayout.VERTICAL);
                TextView tv = new TextView(MainActivity.this);
                //设置内容
                tv.setText(mlist.get(arg0));
                //将控件添加到ll中
                ll.addView(tv);


                Log.v("hank", "getView:" + " ,arg0:" + arg0 + ",index:" + index);
                return ll;
            }

            @Override
            public long getItemId(int arg0) {
                // TODO Auto-generated method stub
                Log.v("hank", "getItemId:" + arg0);
                return arg0;
            }

            @Override
            public Object getItem(int arg0) {
                // TODO Auto-generated method stub

                Log.v("hank", "getItem:" + mlist.get(arg0));
                return mlist.get(arg0);
            }

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                Log.v("hank", "getCount:" + mlist.size());
                return mlist.size();
            }
        };

        //設定當SpinnerSelected事件
        spinner.setOnItemSelectedListener(new ProvOnItemSelectedListener());

        //設定調變器為自訂調變器
        spinner.setAdapter(ba);

        //得到buttton的引用
        Button bt = (Button) findViewById(R.id.bt);
        //为button添加监听事件
        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //得到文本框的内容
                edit = et.getText().toString();
                //在list中添加文本框内容
                mlist.add(edit);
                Log.v("hank", "onClick:" + "View:" + arg0);
            }
        });
    }

    //當Spinner的Item被選到時呼叫
    private class ProvOnItemSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            et.setText(mlist.get(new Long(id).intValue())); //設定editText文字為,這個Spinner list裡的第(item筆)
            Log.v("hank", "ProvOnItemSelectedListener: longid:" + id);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }


}
