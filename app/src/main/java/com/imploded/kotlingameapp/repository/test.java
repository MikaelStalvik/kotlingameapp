package com.imploded.kotlingameapp.repository;

import android.util.Log;

/**
 * Created by l19548726 on 2017-11-30.
 */

public interface Callback {
    void updateUi();
}
public class CallbackImpl implements Callback {
    public void updateUi() {
        Log.d("Hej", "I have callbacked");
    }
}

public class master {
    public void register(Callback callback)
    {
        callback.updateUi();
    }
    public void doCall(){
        Callback callback = new CallbackImpl();
        register(callback);
    }
}
