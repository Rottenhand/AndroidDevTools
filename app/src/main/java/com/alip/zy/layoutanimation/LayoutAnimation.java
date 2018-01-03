package com.alip.zy.layoutanimation;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.alip.zy.tools.R;

public class LayoutAnimation extends Activity {

    private RelativeLayout rootView;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private String[] listItem = {"btn1", "btn2", "btn3", "btn4", "btn5", "btn6"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_animation);

        listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);
        listView.setAdapter(adapter);



//        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1);
//        sa.setDuration(1500);
//
//        LayoutAnimationController layoutAnimationController = new LayoutAnimationController(sa, 0.5f);
//        layoutAnimationController.setOrder(LayoutAnimationController.ORDER_NORMAL);
//
//        listView.setLayoutAnimation(layoutAnimationController);
    }

//    private void addbutton(){
//        Button button = new Button(this);
//        button.setText("Remove");
//        rootView.addView(button);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                rootView.removeView(v);
//            }
//        });
//    }

}
