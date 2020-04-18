package com.example.learnenglish.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.learnenglish.R;
import com.example.learnenglish.model.Vocabulary;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Vocabulary> {

    private Context context;
    private int resource;
    private List<Vocabulary> listvocabulary;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Vocabulary> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.listvocabulary = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_vocabulary,parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvId = (TextView)convertView.findViewById(R.id.tv_id);
            viewHolder.tvName = (TextView)convertView.findViewById(R.id.tv_name);
            viewHolder.tvDefine = (TextView)convertView.findViewById(R.id.tv_define);
            viewHolder.tvDes = (TextView)convertView.findViewById(R.id.tv_description);
            viewHolder.tvEx = (TextView)convertView.findViewById(R.id.tv_example);
            viewHolder.tvSyn = (TextView)convertView.findViewById(R.id.tv_syn);

            convertView.setTag(viewHolder);
        }else{

            viewHolder = (ViewHolder) convertView.getTag();

        }
//        xu ly du lieu tu mainActivity qua Adapter
//        List voca cu 1 voca vao getview de khoi tao 1 view cho voca day
        Vocabulary vocabulary = listvocabulary.get(position);
        viewHolder.tvId.setText(String.valueOf(vocabulary.getmID()));
        viewHolder.tvName.setText(String.valueOf(vocabulary.getmName()));
        viewHolder.tvDefine.setText(String.valueOf(vocabulary.getmDefine()));
        viewHolder.tvDes.setText(String.valueOf(vocabulary.getmDescription()));
        viewHolder.tvEx.setText(String.valueOf(vocabulary.getmExample()));
        viewHolder.tvSyn.setText(String.valueOf(vocabulary.getmSyn()));

        return convertView;
    }

    public class ViewHolder{
//        chi khai bao nhung item dang su dung
        private TextView tvId;
        private TextView tvName;
        private TextView tvDefine;
        private TextView tvDes;
        private TextView tvEx;
        private TextView tvSyn;



    }
}
