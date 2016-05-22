package com.yesorno.zgq.yesorno.customview;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.yesorno.zgq.yesorno.R;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by 37902 on 2016/4/4.
 */
public class CtmFgtResultDialog extends Dialog {
    private static ArrayList<Integer> arrayList = new ArrayList<>();
    private GridView gridView;
    private MyAdapter adapter;
    private Context context;

    public CtmFgtResultDialog(Context context) {
        super(context);
        this.context =context;
    }

    public CtmFgtResultDialog(Context context, int themeResId,ArrayList<Integer> arrayList) {
        super(context, themeResId);
        this.arrayList = arrayList;
        this.context =context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }
    private void initView(){
        setContentView(R.layout.custom_fragment_result_dialog);
        gridView = (GridView) findViewById(R.id.result_gv);
        gridView.setVerticalScrollBarEnabled(false);
        adapter = new MyAdapter(context,0);
        gridView.setAdapter(adapter);
    }


    public class MyAdapter extends ArrayAdapter{

        private TextView number;
        private TextView result;

        public MyAdapter(Context context, int resource) {
            super(context, resource);
        }

        @Override
        public Object getItem(int position) {
            return arrayList.get(position);
        }

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = LayoutInflater.from(context).inflate(R.layout.custom_fragment_result_dialog_item,parent,false);
                number = (TextView)convertView.findViewById(R.id.number_tv);
                number.setTag("number");
                result = (TextView)convertView.findViewById(R.id.result_tv);
                result.setTag("result");
            }
            number = (TextView) convertView.findViewWithTag("number");
            result = (TextView) convertView.findViewWithTag("result");
            number.setText(""+(position+1));
            if (arrayList.get(position) == 0) {
                result.setTextColor(context.getResources().getColor(R.color.color_resultdialog_resulttext));
                result.setText("N");
            }else {
                result.setTextColor(context.getResources().getColor(R.color.colorAccent));
                result.setText("Y");
            }
            return convertView;
        }
    }
}
