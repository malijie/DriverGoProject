package com.driver.go.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.driver.go.R;
import com.driver.go.activity.PracticeOrderActivity;
import com.driver.go.control.IntentManager;

//科目一
public class SubjectOneFragment extends Fragment implements View.OnClickListener{
    private ImageButton mButtonOrderPractise;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {   View v = inflater.inflate(R.layout.subject_one_fragment, container, false);
        initViews(v);
        return v;
    }

    private void initViews(View v) {
        mButtonOrderPractise = (ImageButton) v.findViewById(R.id.id_main_image_order_practice);
        mButtonOrderPractise.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.id_main_image_order_practice:
                IntentManager.startActivity(PracticeOrderActivity.class);
                break;
        }
    }
}
