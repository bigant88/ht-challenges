package com.hectre.ez.ui.tasks

import android.os.Handler
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.flexibleadapter.FlexibleAdapter
import android.os.Looper
import android.os.Message

/**
 * This is a custom implementation extending FlexibleAdapter. `AbstractFlexibleItem` is
 * used as most common Item for ALL view types.
 *
 * Binding is delegated via items (AutoMap), you <u>cannot</u> implement
 * `getItemViewType, onCreateViewHolder, onBindViewHolder`.
 *
 * @see AbstractFlexibleItem
 */
class TasksAdapter(items: List<AbstractFlexibleItem<*>?>?, listeners: Any?) :
    FlexibleAdapter<AbstractFlexibleItem<*>?>(items, listeners, true) {
    override fun updateDataSet(items: List<AbstractFlexibleItem<*>?>?, animate: Boolean) {
        // NOTE: To have views/items not changed, set them into "items" before passing the final
        // list to the Adapter.

        // Overwrite the list and fully notify the change, pass false to not animate changes.
        // Watch out! The original list must a copy.
        super.updateDataSet(items, animate)

        // onPostUpdate() will automatically be called at the end of the Asynchronous update
        // process. Manipulate the list inside that method only or you won't see the changes.
    }

    override fun onCreateBubbleText(position: Int): String {
        var position = position
        position -= if (position < scrollableHeaders.size) {
            return "Top"
        } else if (position >= itemCount - scrollableFooters.size) {
            return "Bottom"
        } else {
            scrollableHeaders.size + 1
        }
        // TODO FOR YOU: The basic value, usually, is the first letter
        // return getItem(position).getBubbleText(position);

        // For me the position is (position + 1):
        return super.onCreateBubbleText(position)
    }

    /**
     * Showcase to reuse the internal Handler.
     *
     * **IMPORTANT:** In order to preserve the internal calls, this custom Callback
     * <u>must</u> extends [HandlerCallback]
     * which implements [Handler.Callback],
     * therefore you <u>must</u> call `super().handleMessage(message)`.
     *
     * This handler can launch asynchronous tasks.
     * If you catch the reserved "what", keep in mind that this code should be executed
     * <u>before</u> that task has been completed.
     *
     * **Note:** numbers 0-9 are reserved for the Adapter, use others for new values.
     */
    private inner class MyHandlerCallback : HandlerCallback() {
        override fun handleMessage(message: Message): Boolean {
            val done = super.handleMessage(message)
            when (message.what) {
                1, 2, 3, 8 -> return done
                10, 11 -> return true
            }
            return false
        }
    }

    companion object {
        private val TAG = TasksAdapter::class.java.simpleName
    }

    init {
        // stableIds ? true = Items implement hashCode() so they can have stableIds!

        // In case you need a Handler, do this:
        // - Overrides the internal Handler with a custom callback that extends the internal one
        mHandler = Handler(Looper.getMainLooper(), MyHandlerCallback())
    }
}