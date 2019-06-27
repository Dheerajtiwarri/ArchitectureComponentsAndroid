package com.example.webbiestest.BindingEvents;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class AddProductFragmentEventHandlers {
    Context context;

    public AddProductFragmentEventHandlers(Context context) {
        this.context = context;
    }
    public void onSaveDataClick(View view)
    {
        Toast.makeText(context, "Save button clicked", Toast.LENGTH_SHORT).show();
    }
}
