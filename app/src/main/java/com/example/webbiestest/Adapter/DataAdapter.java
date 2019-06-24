package com.example.webbiestest.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.webbiestest.MyData;
import com.example.webbiestest.R;

import static androidx.constraintlayout.widget.Constraints.TAG;

/*
 *This Adapter will use in place of now recycler adapter
 * this will take the data in paging format.
 */

public class DataAdapter extends PagedListAdapter<MyData, DataAdapter.DataViewHolder> {

    private Context context;

    public DataAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context=context;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.recyclerview_item,parent,false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        MyData myData=getItem(position);
        if(myData!=null)
        {

            Glide.with(context)
                    .load(myData.image)
                    .into(holder.imageView);

            holder.textView.setText(myData.name);
        }
        else
        {
            Log.v(TAG,"Data is null here, onBindViewHolder()");
            Toast.makeText(context,"Data is not coming",Toast.LENGTH_LONG).show();
        }
    }

    //we have to include diffCallback to determine that two object or two list of object are same or not.

   public static DiffUtil.ItemCallback<MyData> DIFF_CALLBACK =
           new DiffUtil.ItemCallback<MyData>() {
        @Override
        public boolean areItemsTheSame(@NonNull MyData oldItem, @NonNull MyData newItem) {
            return oldItem.id==newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull MyData oldItem, @NonNull MyData newItem) {
           // return oldItem.equals(newItem);
           return oldItem.equals(newItem);
        }
    };

    public class DataViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public ImageView imageView;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);


            textView = (TextView) itemView.findViewById(R.id.product_display_name);
            imageView = (ImageView) itemView.findViewById(R.id.product_display_image);
        }
    }
}
