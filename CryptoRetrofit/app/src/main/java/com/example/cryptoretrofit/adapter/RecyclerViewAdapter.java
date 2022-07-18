package com.example.cryptoretrofit.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cryptoretrofit.databinding.RowLayoutBinding;
import com.example.cryptoretrofit.entity.CryptoModel;
import java.util.ArrayList;
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RowHolder> {

    private ArrayList<CryptoModel> cryptoModelArrayList;

    public void setCryptoModelArrayList(ArrayList<CryptoModel> cryptoModelArrayList) {
        this.cryptoModelArrayList = cryptoModelArrayList;
    }

    public RecyclerViewAdapter(ArrayList<CryptoModel> cryptoModelArrayList) {
        this.cryptoModelArrayList = cryptoModelArrayList;
    }

    @Override
    public RowHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        RowLayoutBinding rowLayoutBinding=RowLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new RowHolder(rowLayoutBinding);
   //     View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout,parent,false);
    //    return new RowHolder(view);

    }



    @Override
    public void onBindViewHolder( RecyclerViewAdapter.RowHolder holder, int position) {
        holder.binding.textName.setText(cryptoModelArrayList.get(position).getCurrency());
        holder.binding.textPrice.setText(cryptoModelArrayList.get(position).getPrice());
    }


    @Override
    public int getItemCount() {
        return cryptoModelArrayList.size();
    }


    public class RowHolder extends RecyclerView.ViewHolder {
        private RowLayoutBinding binding;

        public RowHolder(RowLayoutBinding binding) {
                super(binding.getRoot());
                this.binding=binding;
        }
    }



}
