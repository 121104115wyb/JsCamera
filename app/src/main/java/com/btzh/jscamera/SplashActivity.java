package com.btzh.jscamera;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.tbruyelle.rxpermissions2.RxPermissions;

/**
 * Created by wyb on 2018/5/9.
 */

public class SplashActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        dynamicPermissions();
    }

    /******************************************权限检测**************************************************/
    //权限申请经过测试（只需要，申请 Manifest.permission.ACCESS_COARSE_LOCATION，即可定位成功）
    //注意：使用该权限申请jdk要升为1.8，否则报错
    private void dynamicPermissions() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.CAMERA)
                .subscribe(granted -> {
                    if (granted) {
                        lanuchApp();
                    } else {
                        lanuchApp();
                    }
                });
    }

    private void lanuchApp() {
        Handler handler = new Handler();
        //延迟1s启动界面
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                enterLogin();
                finish();
            }
        }, 1500);
    }


    void enterLogin() {
        Intent intent = new Intent(mContext, MainActivity.class);
        startActivity(intent);
    }


}
