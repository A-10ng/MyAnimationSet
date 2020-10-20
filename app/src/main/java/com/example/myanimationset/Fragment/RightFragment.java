package com.example.myanimationset.Fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myanimationset.R;

/**
 * author：LongSh1z
 * email：2674461089@qq.com
 * time：2019/08/26
 * desc:
 */
public class RightFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_right,container,false);
        return view;
    }
}
