package com.alip.zy.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alip.zy.tools.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * json tool
 * Created by ZY on 2018/2/7.
 */
public class JSONToolsActivity extends BaseImmersiveActivity {

    private Button mBtnJsonInput;
    private TextView mTextShowJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_tools);

        initViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initViews() {
        mBtnJsonInput = findViewById(R.id.btn_json_input);
        mTextShowJson = findViewById(R.id.tv_json_show);

        mBtnJsonInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showJson();
            }
        });
        mBtnJsonInput.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
    }


    private void showJson() {
        String json = "{\"fwc_clean_badge\":[\"2088122638858980\",\"2088102119165770\"]}";
        try {
            JSONObject data = new JSONObject(json);
            JSONArray dataArray = data.optJSONArray("fwc_clean_badge");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < dataArray.length(); i++) {
                sb.append(dataArray.optString(i));
                sb.append(" / ");
            }
            mTextShowJson.setText(sb);
        } catch (JSONException e) {
            Log.e("fuck", "msg", e);
        }


    }
}
