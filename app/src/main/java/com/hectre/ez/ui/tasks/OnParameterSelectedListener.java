package com.hectre.ez.ui.tasks;

import java.util.List;

/**
 * Created by steduda on 21/04/2016.
 * Project FlexibleAdapter.
 */
public interface OnParameterSelectedListener {

    void onParameterSelected(int itemType, int referencePosition, int childPosition);

    List<?> getReferenceList();

}