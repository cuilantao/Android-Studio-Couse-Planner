package com.example.gift;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class dialog_for_time extends AppCompatDialogFragment {
    private List<String> time = new ArrayList<>();
    public static boolean finished = false;
    final List<String> mSelectedItems = new ArrayList<>();
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Where we track the selected items
        final String[] select_time = new String[]{
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday",
                "Saturday",
                "Sunday"
        };
        final boolean[] checkedtimearray = new boolean[]{
                false,
                false,
                false,
                false,
                false,
                false,
                false
        };
        final List<String> timelist = Arrays.asList(select_time);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Set the dialog title
        builder.setTitle("Select all that apply")
                // Specify the list array, the items to be selected by default (null for none),
                // and the listener through which to receive callbacks when items are selected
                .setMultiChoiceItems(select_time, null,
                        new DialogInterface.OnMultiChoiceClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which,
                                                boolean isChecked) {
                                checkedtimearray[which] = isChecked;
                                String currentitem = timelist.get(which);
                            }
                        })
                // Set the action buttons
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < checkedtimearray.length; i++){
                            boolean checked = checkedtimearray[i];
                            if (checked){
                                mSelectedItems.add(timelist.get(i));
                                time.add(timelist.get(i));
                            }
                        }
                        course_info.showtime(mSelectedItems);
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        return builder.create();
    }
}
