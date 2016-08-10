package br.com.levimendesestudos.avenuecode.utils;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import br.com.levimendesestudos.avenuecode.R;

public class ConfirmationDF<T> extends DialogFragment implements OnClickListener {

	private static final String ARG_TITLE       = "br.com.levimendesestudos.avenuecode.fragment.ConfirmationDialogFragment.ARG_TITLE";
	private static final String ARG_MESSAGE     = "br.com.levimendesestudos.avenuecode.fragment.ConfirmationDialogFragment.ARG_MESSAGE";
	private static final String ARG_BUTTON_TEXT = "br.com.levimendesestudos.avenuecode.fragment.ConfirmationDialogFragment.ARG_BUTTON_TEXT";

	private OnDialogOptionClickListener<T> clickListener;
	private T item;

	public interface OnDialogOptionClickListener<T extends Object> {
		void onDialogOptionPressed(T object);
	}

	public static <T> ConfirmationDF<T> newInstance(int title, int message, int buttonText){
		ConfirmationDF<T> fragment = new ConfirmationDF<>();

		Bundle args = new Bundle();
		args.putInt(ARG_TITLE,       title);
		args.putInt(ARG_MESSAGE,     message);
		args.putInt(ARG_BUTTON_TEXT, buttonText);
		fragment.setArguments(args);

		return fragment;
	}

	public void addItem(T item){
		this.item = item;
	}

	public void setOnDialogOptionClickListener(OnDialogOptionClickListener<T> clickListener){
		this.clickListener = clickListener;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Bundle args    = getArguments();
		int title      = args.getInt(ARG_TITLE);
		int message    = args.getInt(ARG_MESSAGE);
		int buttonText = args.getInt(ARG_BUTTON_TEXT);

		AlertDialog.Builder  alert = new AlertDialog.Builder(getActivity());

		alert.setTitle(title);
		alert.setMessage(message);
		alert.setPositiveButton(buttonText, this);
		alert.setNegativeButton(R.string.cancel, null);

		return alert.create();
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		if(this.clickListener != null){
			this.clickListener.onDialogOptionPressed(item);
		}
	}
}