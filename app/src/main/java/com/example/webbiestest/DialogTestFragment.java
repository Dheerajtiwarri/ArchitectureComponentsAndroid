package com.example.webbiestest;


import android.app.Dialog;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.webbiestest.Adapter.DialogRecyclerViewAdapter;
import com.example.webbiestest.MyData;
import com.example.webbiestest.ProductViewModel;
import com.example.webbiestest.R;
import com.example.webbiestest.databinding.FragmentDialogTestBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogTestFragment extends DialogFragment {

    private static final String TAG = "DialogTestFragment";
    private View dialogTestFragmentView;
    private FragmentDialogTestBinding binding;
    private List<MyData> myDataList = new ArrayList<>();


    /*
     * For displaying Dialog on full Screen
     * Dheeraj
     * */
    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
            // dialog.getWindow().setWindowAnimations(R.style.dialog_slide);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_dialog_test, container, false);
        // dialogTestFragmentView = inflater.inflate(R.layout.fragment_dialog_test, container, false);
        dialogTestFragmentView = binding.getRoot();
        binding.getCheckStatus();
        binding.setMyData(new MyData());
        binding.dialogRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.dialogRecyclerView.setHasFixedSize(true);

        ProductViewModel productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);

        productViewModel.getAllData().observe(this, new Observer<PagedList<MyData>>() {
            @Override
            public void onChanged(PagedList<MyData> myData) {
                myDataList = myData;
                Log.i(TAG, "onChanged: getting data from pagedList");
            }
        });


        DialogRecyclerViewAdapter adapter = new DialogRecyclerViewAdapter(getContext(), myDataList);

        binding.dialogRecyclerView.setAdapter(adapter);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return dialogTestFragmentView;
    }


}
