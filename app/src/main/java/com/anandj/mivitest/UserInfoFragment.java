package com.anandj.mivitest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anandj.mivitest.model.InfoModel;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserInfoFragment extends Fragment
{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
         return inflater.inflate(R.layout.info_fragment, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<InfoModel> list = new ArrayList<>();
        String infoText = getArguments().getString("info");

        try {
            //Parsing user information data
            JSONObject jsonObject = new JSONObject(infoText);
            Iterator<String> keys = jsonObject.keys();

            while (keys.hasNext()){
                String label = keys.next();
                list.add(new InfoModel(label, jsonObject.getString(label)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        RecyclerView rvList = view.findViewById(R.id.list);
        rvList.setClipToPadding(false);
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvList.setAdapter(new ListAdapter(list));

    }


    //Adapter for Recycler view
    class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{

        private List<InfoModel> list;

        public ListAdapter(List<InfoModel> list) {
            this.list = list;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view  = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.tvName.setText(list.get(position).getLabel());
            holder.tvValue.setText(list.get(position).getValue());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{

            TextView tvName, tvValue;

            public ViewHolder(View itemView) {
                super(itemView);

                tvName = itemView.findViewById(R.id.tvLabel);
                tvValue = itemView.findViewById(R.id.tvValue);
            }
        }
    }
}
