package com.example.leeeyou.zhihuribao.view.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.leeeyou.zhihuribao.R;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;

public class StoryDetailActivity2 extends Activity {

    private static PtrClassicFrameLayout ptrClassicFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);

    }

    public static void refreshComplete(){
        if(ptrClassicFrameLayout!=null){
            ptrClassicFrameLayout.refreshComplete();
        }
    }

}
