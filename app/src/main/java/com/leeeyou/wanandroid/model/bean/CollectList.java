package com.leeeyou.wanandroid.model.bean;

import java.util.List;

public class CollectList {


    /**
     * curPage : 1
     * datas : [{"author":"张风捷特烈","chapterId":159,"chapterName":"OpenGL ES","courseId":13,"desc":"","envelopePic":"","id":44209,"link":"https://www.jianshu.com/p/23b00beccbed","niceDate":"2小时前","origin":"","originId":7808,"publishTime":1547782953000,"title":"Android多媒体之GLES2战记第三集--圣火之光","userId":16198,"visible":0,"zan":0},{"author":"xujun9411","chapterId":159,"chapterName":"OpenGL ES","courseId":13,"desc":"","envelopePic":"","id":44208,"link":"https://www.jianshu.com/p/6b7bdb8e5a22","niceDate":"2小时前","origin":"","originId":7809,"publishTime":1547782951000,"title":"Android Fragment 的妙用 - 优雅地申请权限和处理 onActivityResult","userId":16198,"visible":0,"zan":0},{"author":"浪淘沙xud","chapterId":60,"chapterName":"Android Studio相关","courseId":13,"desc":"","envelopePic":"","id":44126,"link":"https://www.jianshu.com/p/88e32ef66ef2","niceDate":"20小时前","origin":"","originId":3373,"publishTime":1547720116000,"title":"Android 技能图谱学习路线","userId":16198,"visible":0,"zan":0},{"author":"吴朝彬","chapterId":60,"chapterName":"Android Studio相关","courseId":13,"desc":"IDE插件介绍。","envelopePic":"","id":44122,"link":"https://mp.weixin.qq.com/s?__biz=MzI2NzI4MTEwNA==&mid=2247484397&idx=1&sn=d9101788b7b086fdf5f64bd404e2c825&chksm=ea807452ddf7fd44fa57dbf83dd0d7cea177abeb4a21da4b352b69063ac753c0200ade9aacf8&mpshare=1&scene=1&srcid=0717uXYITmTAZjt8NaUuF5FU","niceDate":"20小时前","origin":"","originId":1162,"publishTime":1547720017000,"title":"彬姐教你基于IntelliJ IDEA的插件开发实践","userId":16198,"visible":0,"zan":0},{"author":"辰之猫","chapterId":60,"chapterName":"Android Studio相关","courseId":13,"desc":"","envelopePic":"","id":44109,"link":"https://www.jianshu.com/p/e54db232df62","niceDate":"20小时前","origin":"","originId":7629,"publishTime":1547718859000,"title":"让你明明白白的使用RecyclerView&mdash;&mdash;SnapHelper详解","userId":16198,"visible":0,"zan":0},{"author":" coder-pig","chapterId":60,"chapterName":"Android Studio相关","courseId":13,"desc":"","envelopePic":"","id":43997,"link":"https://juejin.im/post/5c09f9daf265da61193ba4f2","niceDate":"1天前","origin":"","originId":7654,"publishTime":1547692682000,"title":"逮虾户！Android程序调试竟简单如斯","userId":16198,"visible":0,"zan":0},{"author":" Jomeslu","chapterId":78,"chapterName":"性能优化","courseId":13,"desc":"","envelopePic":"","id":43992,"link":"https://juejin.im/post/5be698d4e51d452acb74ea4c","niceDate":"1天前","origin":"","originId":7781,"publishTime":1547691564000,"title":"Android ANR日志分析指南","userId":16198,"visible":0,"zan":0},{"author":"区长","chapterId":169,"chapterName":"gradle","courseId":13,"desc":"","envelopePic":"","id":43797,"link":"https://fucknmb.com/2017/06/01/Android-Gradle-Plugin%E6%BA%90%E7%A0%81%E9%98%85%E8%AF%BB%E4%B8%8E%E7%BC%96%E8%AF%91/","niceDate":"1天前","origin":"","originId":7805,"publishTime":1547627296000,"title":"Android Gradle Plugin 源码阅读与编译","userId":16198,"visible":0,"zan":0},{"author":"mouxuefei","chapterId":402,"chapterName":"跨平台应用","courseId":13,"desc":"学习了两周的Flutter，准备写个小项目练练手","envelopePic":"http://www.wanandroid.com/blogimgs/2782dd28-86cf-41bc-bed9-cb2b235bbecd.png","id":43794,"link":"http://www.wanandroid.com/blog/show/2488","niceDate":"1天前","origin":"","originId":7780,"publishTime":1547627095000,"title":"flutter练手项目-玩Android","userId":16198,"visible":0,"zan":0}]
     * offset : 0
     * over : true
     * pageCount : 1
     * size : 20
     * total : 9
     */

    private int curPage;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;
    private List<CollectItem> datas;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<CollectItem> getDatas() {
        return datas;
    }

    public void setDatas(List<CollectItem> datas) {
        this.datas = datas;
    }
}
