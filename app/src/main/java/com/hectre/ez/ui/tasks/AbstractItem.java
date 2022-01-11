package com.hectre.ez.ui.tasks;

import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.viewholders.FlexibleViewHolder;
import okhttp3.internal.concurrent.Task;


public abstract class AbstractItem<VH extends FlexibleViewHolder>
        extends AbstractFlexibleItem<VH> {

    protected String id;
    protected String title;
    /* number of times this item has been refreshed */
    protected int updates;
    protected TaskModel taskModel;


    @Override
    public boolean equals(Object inObject) {
        if (inObject instanceof AbstractItem) {
            AbstractItem inItem = (AbstractItem) inObject;
            return this.id.equals(inItem.id);
        }
        return false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public TaskModel getTaskModel() {
        return taskModel;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", title=" + title;
    }

}