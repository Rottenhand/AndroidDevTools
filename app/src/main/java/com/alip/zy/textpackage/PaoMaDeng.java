package com.alip.zy.textpackage;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alip.zy.tools.R;
import com.alip.zy.view.activity.BaseImmersiveActivity;


public class PaoMaDeng extends BaseImmersiveActivity {

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
//    private TransitionManager transitionManager;
    private ViewGroup viewRoot;
    private View viewRoo;

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button topButton;
    private FrameLayout mContentView;
    private View mMaskView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pao_ma_deng);
        viewRoot = (ViewGroup) findViewById(R.id.scene_root);
        viewRoo = findViewById(R.id.relativeLayout);

        tv1 = (TextView) findViewById(R.id.text1);
        tv2 = (TextView) findViewById(R.id.text2);
        tv3 = (TextView) findViewById(R.id.text3);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        topButton = (Button) findViewById(R.id.topButton);
        final ViewGroup contentView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.poplayout, null);
        final PopupWindow pop = new PopupWindow(contentView);
        final ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mContentView = (FrameLayout) PaoMaDeng.this.findViewById(android.R.id.content);
        mMaskView = new View(this);
        mMaskView.setBackgroundColor(Color.parseColor("#000000"));
        mMaskView.setAlpha(0.4f);




        topButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PaoMaDeng.this, "button!", Toast.LENGTH_SHORT).show();
            }
        });

        //        ArrayAdapter<String> as = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,)


//        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.text_property_anim);
//        animator.setTarget();
//        animator.start();
    }




    public void btn1click(View view) {
        btn2.setVisibility(View.GONE);
    }

    public void btn2click(View view) {
    }

    public void btn3click(View view) {
        btn2.setVisibility(View.VISIBLE);
    }

    public void btn4click(View view) {
        btn2.setVisibility(View.INVISIBLE);

    }

    public void btn5click(View view) {
    }
}
