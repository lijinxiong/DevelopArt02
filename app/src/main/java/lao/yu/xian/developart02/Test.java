package lao.yu.xian.developart02;

import android.os.Binder;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * Created by jinxiong on 2017/6/26.
 */

public class Test extends Binder {


    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {

        data.enforceInterface("Test");

        if (code == 1) {
            reply.writeInt(start());
            return true;
        }

        return super.onTransact(code, data, reply, flags);
    }

    public int start() {
        return 1 + 2;

    }


}
