package com.metro.metromobile.fragments;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.metro.metromobile.R;
import com.metro.metromobile.shared.base.BaseActivity;
import com.metro.metromobile.shared.base.BaseFragment;
import com.metro.metromobile.utils.manager.SessionManager;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class EarnPointsFragment extends BaseActivity {
    @BindView(R.id.inviteCodeTv)
    TextView inviteCode;

    @BindView(R.id.inviteDescTv)
    TextView inviteDesc;

    @BindView(R.id.inviteTitleTv)
    TextView inviteTitle;

    @BindView(R.id.copyInvite)
    TextView copyLink;

    ClipboardManager clipboardManager;
    ClipData clipData;


    public EarnPointsFragment() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_earn_points);

        inviteCode.setText(SessionManager.getUserCode());
        inviteDesc.setText(SessionManager.getInviteDescription());
        inviteTitle.setText(SessionManager.getInviteTitle());
        clipboardManager = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);

        copyLink.setOnClickListener(view1 -> {
            clipData = ClipData.newPlainText("text","mmobileservices.com/i/"+SessionManager.getUserCode());
            clipboardManager.setPrimaryClip(clipData);
            showInfoMessageToast("Code copied to clipboard!");
        });

    }

    public void goBack(View view) {
        super.onBackPressed();
    }
}
