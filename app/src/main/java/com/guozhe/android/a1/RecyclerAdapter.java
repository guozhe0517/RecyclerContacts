package com.guozhe.android.a1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by guozhe on 2017. 6. 2..
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder> {

    List<Data> datas;
        public RecyclerAdapter(List<Data> datas){
            this.datas = datas;
        }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Data data = datas.get(position);
        holder.setName(data.getName());
        holder.setNumber(data.getNumber());

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        TextView name;
        TextView number;

        public Holder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.name);
            number = (TextView)itemView.findViewById(R.id.number);

        }

        public String getName() {
            return name.getText().toString();
        }

        public void setName(String name) {
            this.name .setText(name);
        }

        public String getNumber() {
            return number.getText().toString();
        }

        public void setNumber(String number) {
            this.number.setText(number);
        }
    }

}
