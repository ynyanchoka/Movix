package com.monari.movixs.util;

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position); //called when an item has been dismissed with a swipe motion. The parameter position represents the location of the dismissed item.
}
