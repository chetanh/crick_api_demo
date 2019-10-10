package com.android.demo.framework.ui;

import android.content.Context;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.android.demo.framework.injectors.Injectable;

/**
 * This is base fragment for common code.
 * <p>
 * Common analytics evens can be written as per requirements.
 */
public class BaseFragment extends Fragment implements Injectable {

    protected Context _context;
    protected AppCompatActivity _parentActivity;
    protected View _rootView;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        _context = context;
        _parentActivity = (AppCompatActivity) context;
    }


}