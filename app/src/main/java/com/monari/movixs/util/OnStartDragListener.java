package com.monari.movixs.util;

import androidx.recyclerview.widget.RecyclerView;
//called when the user begins a 'drag-and-drop' interaction with the touchscreen. viewHolder represents the RecyclerView holder corresponding to the object being dragged.
public interface OnStartDragListener {
    void onStartDrag(RecyclerView.ViewHolder viewHolder);

}
