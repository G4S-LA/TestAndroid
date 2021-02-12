package com.example.infinityloop;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    MutableLiveData<Long> data;

//    public LiveData<Long> getData() {
//        if (data == null) {
//            data = new MutableLiveData<>();
//            loadData();
//        }
//        return data;
//    }
//
//    private void loadData() {
//        dataRepository.loadData(new Callback<String>() {
//            @Override
//            public void onLoad(String s) {
//                data.postValue(s);
//            }
//        });
//    }
}
