package com.example.recycleviewtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NumbersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final List<Object> list;
    private final Context context;

    public NumbersAdapter(List<Object> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;

        if(viewType == 0){
            view  = inflater.inflate(R.layout.number_list_item,parent,false);
            return new NumberViewHolder(view);
        }
        view  = inflater.inflate(R.layout.horizontal_recyclerview,parent,false);
        return new NumberViewHolder2(view);
    }

    @Override
    public int getItemViewType(int position) {
        if(list.get(position) instanceof List){
            return 1;
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 0){
            NumberViewHolder viewHolderOne = (NumberViewHolder) holder;

            viewHolderOne.bind((Integer) list.get(position));
        }
        else {
            NumberViewHolder2 viewHolderTwo = (NumberViewHolder2) holder;

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL , false);
            viewHolderTwo.numbersHorizontalList.setLayoutManager(linearLayoutManager);

            HorizontalNumberAdapter horizontalNumberAdapter = new HorizontalNumberAdapter((List<Integer>) list.get(position));
            viewHolderTwo.numbersHorizontalList.setAdapter(horizontalNumberAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class NumberViewHolder extends RecyclerView.ViewHolder {

        private final TextView listItemNumberView;

        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);

            listItemNumberView = itemView.findViewById(R.id.tv_number_item);
        }

        void bind(int number) {
            listItemNumberView.setText(String.valueOf(number));
        }
    }

    static class NumberViewHolder2 extends RecyclerView.ViewHolder {

        private final RecyclerView numbersHorizontalList;

        public NumberViewHolder2(@NonNull View itemView) {
            super(itemView);

            numbersHorizontalList = itemView.findViewById(R.id.rv_horizontal_numbers);
        }
    }
}
