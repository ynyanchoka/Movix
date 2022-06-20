package com.monari.movixs.util;

public interface ItemTouchHelperViewHolder {
    void onItemSelected();//updating the appearance of a selected item while the user is dragging-and-dropping it.
    void onItemClear();// remove the 'selected' state (and therefore the corresponding changes in appearance) from an item.

}
