package com.damianma.reddog.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.damianma.reddog.R;
import com.damianma.reddog.Util.ComUtils;
import com.damianma.reddog.Util.Log;
import com.damianma.reddog.http.HttpRequest;
import com.damianma.reddog.http.OnHttpResponseListener;

import static com.damianma.reddog.Util.JSON.parseObject;

public class TestActivity extends Activity implements OnHttpResponseListener {

    static String TAG = "TestActivity";

    LinearLayout main;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        main = findViewById(R.id.main);
        mContext=this;
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"点击事件",Toast.LENGTH_SHORT).show();
            }
        });
        main.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(mContext,"长按事件",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public void onHttpResponse(int requestCode, String resultJson, Exception e) {
         try {
                     JSONObject jo = parseObject(resultJson);
                     if (Integer.valueOf(jo.get("status").toString()) == 1) {
                         switch (requestCode) {
                             case HttpRequest.HTTP_GET_CATE:
                                 processInfo(resultJson);
                                 break;
                         }
                     }else{
                         ComUtils.showShortToast(jo.getString("message"));
                         finish();
                     }
                 } catch (Exception e1) {
                     Log.e(TAG, e1.getMessage());
                     ComUtils.showShortToast("网络请求结果错误");
                 }
    }

    private void processInfo(String resultJson) {

    }
}
