# RecyclerContacts
전화번호 RecyclerView로 뛰우기
```java
package com.guozhe.android.a1;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            checkPermission();
        }else {
            init();
        }

    }
    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermission(){
        if(checkSelfPermission(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED){
            init();
        }else {
            String permission []= {Manifest.permission.READ_CONTACTS};
            requestPermissions(permission,100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                init();
            }else {
                Toast.makeText(this,"권한승인 하셔야 사용할수잇음",Toast.LENGTH_LONG).show();
            }
        }

    }

    private void init(){
        List<Data> datas = Load.getData(getBaseContext());
        RecyclerAdapter adapter = new RecyclerAdapter(datas);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView) ;
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
    }
}
package com.guozhe.android.a1;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guozhe on 2017. 6. 2..
 */

public class Load {

    public static List<Data> getData(Context context){

        List<Data> datas = new ArrayList<>();
        ContentResolver contentResolver = context.getContentResolver();
        Uri phone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String projections[] = {ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                ,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                                ,ContactsContract.CommonDataKinds.Phone.NUMBER};
        Cursor cursor = contentResolver.query(phone,projections,null,null,null);
        if(cursor != null){
            while(cursor.moveToNext()){
               int index = cursor.getColumnIndex(projections[0]);
                int id = cursor.getInt(index);
                index = cursor.getColumnIndex(projections[1]);
                String name = cursor.getString(index);
                index = cursor.getColumnIndex(projections[2]);
                String number = cursor.getString(index);
                Data data =new Data();
                data.setId(id);
                data.setName(name);
                data.setNumber(number);
                datas.add(data);
            }
        }
        cursor.close();
        return datas;
    }

}
