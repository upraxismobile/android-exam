package upraxis.com.task.base;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import upraxis.com.task.listener.OnConfirmDialogListener;

/**
 * Created by rsbulanon on 6/14/17.
 */

public class BaseActivity extends AppCompatActivity {

    public void showConfirmDialog(final String action, final String title, final String message,
                           final String positiveText, final String negativeText, final boolean isCancelable,
                           final OnConfirmDialogListener onConfirmDialogListener) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        /** display dialog title */
        if (title != null) {
            builder.setTitle(title);
        }

        /** display dialog message */
        if (message != null) {
            builder.setMessage(message);
        }

        /** display positive button */
        builder.setPositiveButton(positiveText, (dialog, which) -> {
            if (onConfirmDialogListener != null) {
                onConfirmDialogListener.onConfirmed(action);
            }
        });

        /** display negative button */
        if (negativeText != null) {
            builder.setNegativeButton(positiveText, (dialog, which) -> {
                if (onConfirmDialogListener != null) {
                    onConfirmDialogListener.onCancelled(action);
                }
            });
        }

        builder.setCancelable(isCancelable);
        builder.create();
        builder.show();
    }
}
