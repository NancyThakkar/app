package io.priome;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TextFragment extends Fragment {

    private static final String KEY = "KEY_TEXT";

    public static TextFragment newInstance(String text) {
        TextFragment fragment = new TextFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY, text);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView view = (TextView) inflater.inflate(R.layout.fragment_text, null);
        String text = getArguments() != null && getArguments().containsKey(KEY) ? getArguments().getString(KEY) : "undefined";
        view.setText(text);
        return view;
    }
}
