package com.hectre.ez.ui.tasks;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hectre.ez.R;

import java.io.Serializable;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.flexibleadapter.items.ISectionable;
import eu.davidea.viewholders.FlexibleViewHolder;


public class TaskItemViewHolder extends AbstractItem<TaskItemViewHolder.TaskViewHolder>
        implements ISectionable<TaskItemViewHolder.TaskViewHolder, HeaderItem>, Serializable {

    /* The header of this item */
    HeaderItem header;

    public TaskItemViewHolder(HeaderItem header, TaskModel taskModel) {
        this.header = header;
        this.taskModel = taskModel;
    }

    @Override
    public HeaderItem getHeader() {
        return header;
    }

    @Override
    public void setHeader(HeaderItem header) {
        this.header = header;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.recycler_task_item;
    }

    @Override
    public TaskViewHolder createViewHolder(View view, FlexibleAdapter adapter) {
        return new TaskViewHolder(view, adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter<IFlexible> adapter, TaskViewHolder holder, int position, List<Object> payloads) {
        holder.onBind(getTaskModel());
    }


    static final class TaskViewHolder extends FlexibleViewHolder {

        TextView mTitle;
        Context mContext;

        TaskViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter);
            this.mContext = view.getContext();
            this.mTitle = view.findViewById(R.id.staff_name);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(mContext, "Click on " + mTitle.getText() + " position " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
            super.onClick(view);
        }

        public void onBind(TaskModel taskModel) {
            mTitle.setText(taskModel.getStaff().getFirstName() + " " + taskModel.getStaff().getLastName());
        }
    }

    @Override
    public String toString() {
        return "SimpleItem[" + super.toString() + "]";
    }

}