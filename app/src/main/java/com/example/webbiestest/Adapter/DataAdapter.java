package com.example.webbiestest.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.webbiestest.MyData;
import com.example.webbiestest.R;
import com.example.webbiestest.databinding.RecyclerviewItemBinding;

/*
 *This Adapter will use in place of now recycler adapter
 * this will take the data in paging format.
 */

public class DataAdapter extends PagedListAdapter<MyData, DataAdapter.DataViewHolder> {

    private static final String TAG = "DataAdapter";

    private Context context;

    public DataAdapter(Context context) {
        super(DIFF_CALLBACK);
        Log.v(TAG, "DataAdapter()");
        this.context = context;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.v(TAG, "onCreateViewHolder");
        RecyclerviewItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recyclerview_item, parent, false);
        return new DataViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        Log.v(TAG, "onBindViewHolder()");
        MyData myData = getItem(position);
        if (myData != null) {
            Log.v(TAG, "itemBinding start");
            holder.itemBinding.setProducts(myData);
        } else {
            Log.v(TAG, "Data is null here");
            Toast.makeText(context, "Data kaha reh gaya.... :'( ", Toast.LENGTH_LONG).show();
        }
    }

    //we have to include diffCallback to determine that two object or two list of object are same or not.

    private static DiffUtil.ItemCallback<MyData> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<MyData>() {
                @Override
                public boolean areItemsTheSame(@NonNull MyData oldItem, @NonNull MyData newItem) {
                    Log.v(TAG, "areItemsTheSame()");
                    return oldItem.id == newItem.id;
                }

                @Override
                public boolean areContentsTheSame(@NonNull MyData oldItem, @NonNull MyData newItem) {
                    Log.v(TAG, "areContentsTheSame()");
                    return oldItem.equals(newItem);
                }
            };

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String imageUrl) {
        Log.v(TAG, "setImageUrl()");

        Context context = imageView.getContext();

       /* RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_add_black_24dp);*/

        Glide.with(context)
               // .setDefaultRequestOptions(options) //set byDefault Image in case didn't get data form server.
                .load(imageUrl)
                .into(imageView);
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {

        RecyclerviewItemBinding itemBinding;

        DataViewHolder(RecyclerviewItemBinding itemBinding) {
            super(itemBinding.getRoot());
            Log.v(TAG,"DataViewHolder()");
            this.itemBinding = itemBinding;
        }

    }
}
