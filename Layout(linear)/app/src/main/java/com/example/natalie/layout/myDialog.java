package com.example.natalie.layout;

/**
 * Created by Natalie on 08.10.2017.
 */
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class myDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Очень важное сообщение!")
                //.setIcon(R.drawable.enot)
                .setMessage("Срочно покормите енота!")
                .setPositiveButton("Будет сделано!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // кормим енота вкусняшками
                    }
                })
                .setNegativeButton("Он не голодный!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // пользователь отменил работу с диалогом
                    }
                });

        return builder.create();
    }
}
