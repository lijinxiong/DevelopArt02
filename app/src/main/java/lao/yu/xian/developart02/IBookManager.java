package lao.yu.xian.developart02;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jinxiong on 2017/6/25.
 */

public interface IBookManager extends IInterface {


    abstract class Stub extends Binder implements IBookManager {

        @Override
        protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {

            data.enforceInterface("IBookManager");

            switch (code){
                case GET_BOOK_LIST:{
                    List<Book> books = getBookList();
                    reply.writeList(books);
                    return true;
                }
                case ADD_BOOK:{

                    Book book = data.readParcelable(Thread.currentThread().getContextClassLoader());
                    addBook(book);

                    data.recycle();
                    reply.recycle();

                    return true;
                }
            }


            return super.onTransact(code, data, reply, flags);

        }

        @Override
        public IBinder asBinder() {
            return this;
        }

        private static class Proxy implements IBookManager {

            private IBinder mRemote;

            public Proxy(IBinder remote) {
                mRemote = remote;
            }


            @Override
            public IBinder asBinder() {
                return mRemote;
            }

            @Override
            public List<Book> getBookList() throws RemoteException {

                Parcel parcel = Parcel.obtain();
                Parcel result = Parcel.obtain();

                mRemote.transact(Stub.GET_BOOK_LIST, parcel, result, 1);

                List<Book> list = new ArrayList<>();
                result.readList(list, Thread.currentThread().getContextClassLoader());

                result.recycle();
                parcel.recycle();

                return list;
            }

            @Override
            public void addBook(Book book) throws RemoteException {


                Parcel parcel = Parcel.obtain();
                Parcel result = Parcel.obtain();
                parcel.writeInterfaceToken("IBookManager");

                book.writeToParcel(parcel, 0);

                mRemote.transact(Stub.ADD_BOOK, parcel, result, 0);
                result.readException();

                parcel.recycle();
                result.recycle();
            }
        }

    }

    List<Book> getBookList() throws RemoteException;

    void addBook(Book book) throws RemoteException;

    int GET_BOOK_LIST = IBinder.FIRST_CALL_TRANSACTION + 0;
    int ADD_BOOK = IBinder.FIRST_CALL_TRANSACTION + 1;

}
