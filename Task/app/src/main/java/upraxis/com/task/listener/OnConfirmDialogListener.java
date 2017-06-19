package upraxis.com.task.listener;

/**
 * Created by rsbulanon on 6/19/17.
 */

public interface OnConfirmDialogListener {

    void onConfirmed(final String action);

    void onCancelled(final String action);
}
