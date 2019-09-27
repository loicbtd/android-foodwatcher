package ca.qc.cgmatane.foodwatcher.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeTestViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeTestViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}