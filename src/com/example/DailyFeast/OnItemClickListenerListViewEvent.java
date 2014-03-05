package com.example.DailyFeast;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class OnItemClickListenerListViewEvent implements OnItemClickListener {

    AlertDialog alertDialogStores;
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Context context = view.getContext();

        TextView textViewEvent = ((TextView) view.findViewById(R.id.textViewEvent));

        // get the clicked item name
        String listEventText = textViewEvent.getText().toString();
        // get the clicked item ID
        String listEventId = textViewEvent.getTag().toString();

        // put the Event information in a pop up
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        // set title
        alertDialogBuilder.setTitle(listEventText);
        // set dialog message
        alertDialogBuilder
                .setMessage(listEventId)
                .setCancelable(false)
                .setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }


}




