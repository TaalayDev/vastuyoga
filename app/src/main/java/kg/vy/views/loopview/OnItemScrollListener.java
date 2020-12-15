package kg.vy.views.loopview;

public interface OnItemScrollListener {
    /**
     * 滚轮滚动状态变化监听
     * @param loopView
     * @param currentPassItem 当前经过的item
     * @param oldScrollState  上一次滚动状态
     * @param scrollState  当前滚动状态
     * @param totalScrollY 滚动距离
     */
    void onItemScrollStateChanged(kg.vy.views.loopview.LoopView loopView, int currentPassItem, int oldScrollState, int scrollState, int totalScrollY);

    /***
     * 滚轮滚动监听
     * @param loopView
     * @param currentPassItem 当前经过的item
     * @param scrollState 当前滚动状态
     * @param totalScrollY 滚动距离
     */
    void onItemScrolling(kg.vy.views.loopview.LoopView loopView, int currentPassItem, int scrollState, int totalScrollY);
}
