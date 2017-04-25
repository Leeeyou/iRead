package com.example.leeeyou.zhihuribao.adapter.one;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.leeeyou.zhihuribao.R;
import com.example.leeeyou.zhihuribao.vi.one.OneIndexMultipleItem;

import java.util.List;

public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<OneIndexMultipleItem> {

    public MultipleItemQuickAdapter(List data) {
        super(data);
        addItemType(OneIndexMultipleItem.Companion.getWEATHER(), R.layout.item_one_index_weather);
        addItemType(OneIndexMultipleItem.Companion.getTOP(), R.layout.item_one_index_top);
        addItemType(OneIndexMultipleItem.Companion.getREAD(), R.layout.item_one_index_read);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, OneIndexMultipleItem oneIndexMultipleItem) {

    }
}