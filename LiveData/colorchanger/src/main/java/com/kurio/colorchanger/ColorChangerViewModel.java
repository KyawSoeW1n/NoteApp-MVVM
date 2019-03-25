package com.kurio.colorchanger;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class ColorChangerViewModel extends ViewModel {

    MutableLiveData<Integer> colorId=new MutableLiveData<>();

    {
        colorId.setValue(0xfff);
    }
}
