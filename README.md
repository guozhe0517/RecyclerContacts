# RecyclerContacts
전화번호 RecyclerView로 뛰우기
```java
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
