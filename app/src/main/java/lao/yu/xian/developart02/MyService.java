package lao.yu.xian.developart02;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.List;

/**
 * Created by jinxiong on 2017/6/26.
 */

public class MyService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {


        return new IBookManager.Stub() {
            @Override
            public List<Book> getBookList() throws RemoteException {
                return null;
            }

            @Override
            public void addBook(Book book) throws RemoteException {

            }
        };
    }



}
