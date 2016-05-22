package com.yesorno.zgq.yesorno.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.glomadrian.dashedcircularprogress.DashedCircularProgress;
import com.yesorno.zgq.yesorno.R;
import com.yesorno.zgq.yesorno.StartYesOrNoListThread;
import com.yesorno.zgq.yesorno.customview.YesOrNoView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ThreeItemsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FourItemsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //1 in 4
    private static int MODE1 = 1;
    //2 in 4
    private static int MODE2 = 2;
    //3 in 4
    private static int MODE3 = 3;
    private int selectMode;


    private YesOrNoView yesOrNoView1;
    private YesOrNoView yesOrNoView2;
    private YesOrNoView yesOrNoView3;
    private YesOrNoView yesOrNoView4;
    private ArrayList<YesOrNoView> yesOrNoViewsList = new ArrayList<>();

    private int[] targetTitle = new int[4];

    private DashedCircularProgress circularProgress;

    private Spinner modeSelect;
    private TextView startButton;
    private TextView resetButton;

    private boolean isRunning;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThreeItemsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FourItemsFragment newInstance(String param1, String param2) {
        FourItemsFragment fragment = new FourItemsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FourItemsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_four_items,container,false);
        isRunning = false;
        selectMode = MODE1;
        initView(view);
        setViewContent();
        return view;
    }

    private void initView(View view){
        circularProgress = (DashedCircularProgress) view.findViewById(R.id.circle_progress);
        yesOrNoView1 = (YesOrNoView)view.findViewById(R.id.yes_or_no1);
        yesOrNoView2 = (YesOrNoView)view.findViewById(R.id.yes_or_no2);
        yesOrNoView3 = (YesOrNoView)view.findViewById(R.id.yes_or_no3);
        yesOrNoView4 = (YesOrNoView)view.findViewById(R.id.yes_or_no4);
        yesOrNoViewsList.add(yesOrNoView1);
        yesOrNoViewsList.add(yesOrNoView2);
        yesOrNoViewsList.add(yesOrNoView3);
        yesOrNoViewsList.add(yesOrNoView4);

        modeSelect =(Spinner) view.findViewById(R.id.bottom_buttons_1);
        startButton = (TextView) view.findViewById(R.id.bottom_buttons_2);
        resetButton = (TextView) view.findViewById(R.id.bottom_buttons_3);
    }
    private void setViewContent(){
        circularProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake_x);//加载动画资源文件
                circularProgress.startAnimation(shake); //给组件播放动画效果
            }
        });
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRunning) {
                    startSelect();
                }
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetSelect();
            }
        });
        circularProgress.setOnValueChangeListener(new DashedCircularProgress.OnValueChangeListener() {
            @Override
            public void onValueChange(float v) {
                int i = (int) v;
                switch (i) {
                    case 20:
                        stopViewList(yesOrNoViewsList, 0);
                        break;
                    case 35:
                        stopViewList(yesOrNoViewsList, 1);
                        break;
                    case 45:
                        stopViewList(yesOrNoViewsList, 2);
                        break;
                    case 59:
                        stopViewList(yesOrNoViewsList, 3);
                        break;
                }
            }
        });
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.fragment_four_items_spinner_title, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        modeSelect.setAdapter(adapter);
        modeSelect.setSelection(0, true);
        modeSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    selectMode = MODE1;
                }
                if (position == 1){
                    selectMode = MODE2;
                }
                if (position == 2){
                    selectMode = MODE3;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void setYesOrNoViewsTargetTitle(){
        getTargetTitleList(selectMode);
        yesOrNoView1.setTargetTitle(targetTitle[0]);
        yesOrNoView2.setTargetTitle(targetTitle[1]);
        yesOrNoView3.setTargetTitle(targetTitle[2]);
        yesOrNoView4.setTargetTitle(targetTitle[3]);
    }
    private void startViewList(List<YesOrNoView> list,int dalayTime){
        if (list.size() == 0) return;
        for (YesOrNoView yesOrNoView: list){
            yesOrNoView.start();
        }
    }

    private void stopViewList(List<YesOrNoView> list,int index){
        if (list.size() == 0) return;
        list.get(index).stop();
    }
    private void resetViewList(List<YesOrNoView> list){
        if (list.size() == 0) return;
        for (YesOrNoView yesOrNoView: list){
            yesOrNoView.reset();
        }
    }
    private void startCircleProgress(){
        circularProgress.setIcon(R.drawable.myview_counter_top_icon_running);
        circularProgress.setDuration(6000);
        circularProgress.setValue(circularProgress.getMax());
    }
    private void resetCircleProgress(){
        circularProgress.setIcon(R.drawable.myview_counter_top_icon_reset);
        circularProgress.setDuration(500);
        circularProgress.setValue(0);
        circularProgress.reset();
    }

    private void startSelect(){
        isRunning = true;
        startCircleProgress();
        setYesOrNoViewsTargetTitle();
        startViewList(yesOrNoViewsList, 2000);
    }
    private void resetSelect() {
        isRunning = false;
        resetViewList(yesOrNoViewsList);
        resetCircleProgress();
    }

    //parameter i means the counter of Y title
    private void getTargetTitleList(int mode){
        if (mode == MODE1){
            int i =(int) (Math.random()*4);
            targetTitle[i] = YesOrNoView.FIRSTTITLEISTARGET;
            for (int j = 0;j<targetTitle.length;j++){
                if (j != i){
                    targetTitle[j] = YesOrNoView.SECONDTITLEISTARGET;
                }
            }
        }
        if (mode == MODE2){
            int i1 =(int) (Math.random()*3);
            int i2 = i1;
            while(i1 == i2){
                i2 = (int) (Math.random()*3);
            }
            targetTitle[i1] = YesOrNoView.FIRSTTITLEISTARGET;
            targetTitle[i2] = YesOrNoView.FIRSTTITLEISTARGET;
            for (int j = 0;j<targetTitle.length;j++){
                if (j != i1 && j != i2){
                    targetTitle[j] = YesOrNoView.SECONDTITLEISTARGET;
                }
            }
        }
        if (mode == MODE3){
            int i =(int) (Math.random()*4);
            targetTitle[i] = YesOrNoView.SECONDTITLEISTARGET;
            for (int j = 0;j<targetTitle.length;j++){
                if (j != i){
                    targetTitle[j] = YesOrNoView.FIRSTTITLEISTARGET;
                }
            }
        }
    }
}
