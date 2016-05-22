package com.yesorno.zgq.yesorno.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.github.glomadrian.dashedcircularprogress.DashedCircularProgress;
import com.yesorno.zgq.yesorno.R;
import com.yesorno.zgq.yesorno.customview.CtmFgtResultDialog;

import net.frakbot.jumpingbeans.JumpingBeans;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link CustomItemsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomItemsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static final int STARTSTATUS = 1;
    private static final int RUNNINGSTATUS = 2;
    private static final int STOPSTATUS = 3;
    private int status;

    private static final int OPTIONALCOUNTMODE = 1;
    private static final int TARGETCOUNTMODE = 2;
    private int countChangeMode;

    private int targetCount;
    private int optionalCount;
    private HashSet targetResult;
    private ArrayList<Integer> resultList;

    private TextView startTv;
    private JumpingBeans jumpingBeans;

    private DashedCircularProgress circularProgress;

    private TextView settingButton;
    private TextView startButton;
    private TextView resetButton;

    private TextView plusTv;
    private TextView minusTv;
    private TextView showCount;

    private String startText;
    private String tryingText;
    private String doneText;

    private Context context;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CustomItemsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CustomItemsFragment newInstance(String param1, String param2) {
        CustomItemsFragment fragment = new CustomItemsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public CustomItemsFragment() {
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
        // Inflate the layout for this fragment
        initData();
        View view = inflater.inflate(R.layout.fragment_custom_items, container, false);
        initView(view);
        initViewContent();
        return view;
    }

    private void initData() {
        context = getActivity();
        resultList = new ArrayList<>();
        targetResult = new HashSet<>();
        status = STARTSTATUS;
        countChangeMode = OPTIONALCOUNTMODE;
        targetCount = 1;
        optionalCount = 5;
        startText = getActivity().getResources().getString(R.string.ctm_items_start_title);
        tryingText = getActivity().getResources().getString(R.string.ctm_items_tyring_title);
        doneText = getActivity().getResources().getString(R.string.ctm_items_done_title);
    }

    private void initView(View view) {
        startTv = (TextView) view.findViewById(R.id.start_tv);
        circularProgress = (DashedCircularProgress) view.findViewById(R.id.circle_progress);
        settingButton = (TextView) view.findViewById(R.id.bottom_buttons_1);
        startButton   = (TextView) view.findViewById(R.id.bottom_buttons_2);
        resetButton   = (TextView) view.findViewById(R.id.bottom_buttons_3);
        plusTv        = (TextView) view.findViewById(R.id.plus_tv);
        minusTv       = (TextView) view.findViewById(R.id.minus_tv);
        showCount     = (TextView) view.findViewById(R.id.show_count_tv);
    }

    private void initViewContent() {
        updateShowCountTv();
        showCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeCountMode();
                updateShowCountTv();
            }
        });
        circularProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake_x);//加载动画资源文件
                circularProgress.startAnimation(shake); //给组件播放动画效果
            }
        });
        startTv.setText("" + optionalCount + "选" + targetCount);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSelect();
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetSelect();
            }
        });
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResultDialog();
            }
        });
        circularProgress.setOnValueChangeListener(new DashedCircularProgress.OnValueChangeListener() {
            @Override
            public void onValueChange(float v) {
                if (v == circularProgress.getMax()) {
                    stopSelect();
                }
                if (v == circularProgress.getMin()) {
                    resetStartTv();
                }
            }
        });
        plusTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status == STARTSTATUS) {
                    switch (countChangeMode) {
                        case OPTIONALCOUNTMODE:
                            optionalCount++;
                            if (isAppropriate()) {
                                updateShowCountTv();
                            } else {
                                optionalCount--;
                            }
                            break;
                        case TARGETCOUNTMODE:
                            targetCount++;
                            if (isAppropriate()) {
                                updateShowCountTv();
                            } else {
                                targetCount--;
                            }
                            break;
                    }
                    updateStartTv();
                }
            }
        });
        minusTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status == STARTSTATUS) {
                    switch (countChangeMode) {
                        case OPTIONALCOUNTMODE:
                            optionalCount--;
                            if (isAppropriate()) {
                                updateShowCountTv();
                            } else {
                                optionalCount++;
                            }
                            break;
                        case TARGETCOUNTMODE:
                            targetCount--;
                            if (isAppropriate()) {
                                updateShowCountTv();
                            } else {
                                targetCount++;
                            }
                            break;
                    }
                    updateStartTv();
                }
            }
        });
    }


    private void startSelect() {
        if (status == STARTSTATUS) {
            getTargetSet();
            status = RUNNINGSTATUS;
            startCircleProgress();
            startTv.setText(tryingText);
            jumpingBeans = JumpingBeans.with(startTv).makeTextJump(0, startTv.getText().toString().length()).setIsWave(true).setLoopDuration(1000).build();
        }
    }

    private void stopSelect() {
        if (status == RUNNINGSTATUS) {
            status = STOPSTATUS;
            jumpingBeans.stopJumping();
            startTv.setText(doneText);
            showResultDialog();
        }
    }

    private void resetSelect() {
        if (status == STOPSTATUS) {
            status = STARTSTATUS;
            resetCircleProgress();
        }
    }

    private void showResultDialog(){
        getResultList();
        CtmFgtResultDialog ctmFgtResultDialog = new CtmFgtResultDialog(getActivity(),0,resultList);
        ctmFgtResultDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ctmFgtResultDialog.show();
    }

    private void resetStartTv() {
        if (status == RUNNINGSTATUS) return;
        startTv.setText("" + optionalCount + "选" + targetCount);
    }

    private void startCircleProgress() {
        circularProgress.setIcon(R.drawable.myview_counter_top_icon_running);
        circularProgress.setDuration(5000);
        circularProgress.setValue(circularProgress.getMax());
    }

    private void resetCircleProgress() {
        circularProgress.setIcon(R.drawable.myview_counter_top_icon_reset);
        circularProgress.setDuration(500);
        circularProgress.setValue(0);
        circularProgress.reset();
    }

    private void updateShowCountTv() {
        if (countChangeMode == OPTIONALCOUNTMODE) {
            showCount.setTextColor(getActivity().getResources().getColor(R.color.colorAccent));
            showCount.setText("" + optionalCount);
        }
        if (countChangeMode == TARGETCOUNTMODE) {
            showCount.setTextColor(getActivity().getResources().getColor(R.color.color_customfragment_target_text));
            showCount.setText("" + targetCount);
        }
    }

    private boolean isAppropriate() {
        if (optionalCount <= targetCount) return false;
        if (optionalCount <= 4 || targetCount <= 0 || optionalCount > 50) return false;
        return true;
    }
    private void changeCountMode(){
        if (countChangeMode == OPTIONALCOUNTMODE) {
            countChangeMode = TARGETCOUNTMODE;
            return;
        }
        if (countChangeMode == TARGETCOUNTMODE) {
            countChangeMode = OPTIONALCOUNTMODE;
            return;
        }
    }

    private void updateStartTv() {
        String text = "" + optionalCount + "选" + targetCount;
        startTv.setText(text);
    }

    private void getResultList(){
        Iterator iterator = targetResult.iterator();
        resultList.clear();
        for (int i = 1;i<=optionalCount;i++){
            resultList.add(0);
        }
        while (iterator.hasNext()) {
            resultList.set((Integer) iterator.next() - 1, 1);
        }
    }

    private void getTargetSet() {
        targetResult.clear();
        randomSet(1, optionalCount, targetCount, targetResult);
    }

    private void randomSet(int min, int max, int n, HashSet<Integer> set) {
        if (n > (max - min + 1) || max < min) {
            return;
        }
        int count = 0;
        while (count < n) {
            int num = (int) (Math.random() * (max - min + 1)) + min;
            set.add(num);
            count = set.size();
        }
    }
}
