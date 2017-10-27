package com.xyz.leeeyou.zhihuribao;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

public class SampleApplication extends TinkerApplication {
    public SampleApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "com.xyz.leeeyou.zhihuribao.SampleApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", true);
    }
}