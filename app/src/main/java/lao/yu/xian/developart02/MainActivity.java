package lao.yu.xian.developart02;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    IBinder mRemote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(MainActivity.this, MyService.class);


        String s = "";
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mRemote = service;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Service.BIND_AUTO_CREATE);


        this.findViewById(R.id.click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Parcel data = Parcel.obtain();

                data.writeInterfaceToken("Test");

                Parcel reply = Parcel.obtain();
                try {
                    mRemote.transact(1, data, reply, 0);

                    Log.d("Color", String.valueOf(reply.readInt()));
                    reply.recycle();

                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
        });


    }
}
