package com.driver.go.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.driver.go.base.DriverGoApplication;
import com.driver.go.db.DBConstants;
import com.driver.go.db.SQLiteManager;
import com.driver.go.db.SubjectOneSQLiteBehavior;
import com.driver.go.entity.QuestionItem;
import com.driver.go.utils.Logger;
import com.driver.go.wap.WapManager;

/**
 * Created by malijie on 2017/3/15.
 */

public class BaseFragment extends Fragment {
    protected SQLiteManager mSQLiteManager = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initData();
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    private void initData(){
        mSQLiteManager = SQLiteManager.getInstance();

    }

}
