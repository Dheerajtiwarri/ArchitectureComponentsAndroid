package com.example.webbiestest.Fragments;


import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.webbiestest.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogTestFragment extends DialogFragment {

  private static final String TAG = "DialogTestFragment";
  private View dialogTestFragmentView;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    dialogTestFragmentView = inflater.inflate(R.layout.fragment_dialog_test, container, false);

    return dialogTestFragmentView;
  }

}
