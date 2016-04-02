package com.yesorno.zgq.yesorno.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.glomadrian.dashedcircularprogress.DashedCircularProgress;
import com.yesorno.zgq.yesorno.R;
import com.yesorno.zgq.yesorno.customview.YesOrNoView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ThreeItemsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OneItemsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private YesOrNoView yesOrNoView1;
    private ArrayList<YesOrNoView> yesOrNoViewsList = new ArrayList<>();

    private int[] targetTitle = new int[3];

    private DashedCircularProgress circularProgress;

    private TextView settingButton;
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
    public static OneItemsFragment newInstance(String param1, String param2) {
        OneItemsFragment fragment = new OneItemsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public OneItemsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_one_items, container, false);
        isRunning = false;
        initView(view);
        setViewContent();
        return view;
    }


    private void initView(View view) {
        circularProgress = (DashedCircularProgress) view.findViewById(R.id.circle_progress);
        yesOrNoView1 = (YesOrNoView) view.findViewById(R.id.yes_or_no1);

        settingButton = (TextView) view.findViewById(R.id.bottom_buttons_1);
        startButton = (TextView) view.findViewById(R.id.bottom_buttons_2);
        resetButton = (TextView) view.findViewById(R.id.bottom_buttons_3);
    }

    private void setViewContent() {
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
                if (v == circularProgress.getMax()) {
                    yesOrNoView1.stop();
                }
            }
        });
    }

    private void setYesOrNoViewsTargetTitle() {
        yesOrNoView1.setTargetTitle((int) (Math.random() * 2 + 1));
    }

    private void startYesOrNoView() {
        yesOrNoView1.start();
    }

    private void resetYesOrNOView() {
        yesOrNoView1.reset();
    }

    private void startCircleProgress() {
        circularProgress.setIcon(R.drawable.myview_counter_top_icon_running);
        circularProgress.setDuration(3000);
        circularProgress.setValue(circularProgress.getMax());
    }

    private void resetCircleProgress() {
        circularProgress.setIcon(R.drawable.myview_counter_top_icon_reset);
        circularProgress.setDuration(500);
        circularProgress.setValue(0);
        circularProgress.reset();
    }

    private void startSelect() {
        isRunning = true;
        startCircleProgress();
        setYesOrNoViewsTargetTitle();
        startYesOrNoView();
    }

    private void resetSelect() {
        isRunning = false;
        resetYesOrNOView();
        resetCircleProgress();
    }
}
