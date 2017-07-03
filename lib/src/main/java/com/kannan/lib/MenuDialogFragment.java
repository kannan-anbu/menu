package com.kannan.lib;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by kannan on 28/6/17.
 */

public class MenuDialogFragment extends DialogFragment {

    private static final String BUNDLE_MENU_OPTIONS = "bundle_menu_options";

    private MenuDialogFragmentOptions mMenuOptions;
    private AbstractMenuSystem mMenuSystem;
    private RelativeLayout mRootContainer;


    public static MenuDialogFragment newInstance(MenuDialogFragmentOptions options) {
        MenuDialogFragment fragment = new MenuDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_MENU_OPTIONS, options);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_FRAME, R.style.MenuDialogFragmentStyle);
        if (getArguments() != null) {
            mMenuOptions = (MenuDialogFragmentOptions) getArguments().getSerializable(BUNDLE_MENU_OPTIONS);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.menu_dialog_fragment, container, false);

        mRootContainer = (RelativeLayout) root.findViewById(R.id.root_container);
        initMenuSystem();
        mRootContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMenuSystem.toggleMenu();
            }
        });

        return root;
    }




    private void initMenuSystem() {
        mMenuSystem = new ListMenuSystem(getContext(), mRootContainer, mMenuOptions.getMenuItems());
        mMenuSystem.setMenuGravity(mMenuOptions.getMenuGravity());
        mMenuSystem.setDividerSpacing((int) mMenuOptions.getDividerSpacingDP());
        mMenuSystem.setMenuAnimationDirection(mMenuOptions.getMenuAnimationDirection());
        mMenuSystem.setMenuOrientation(mMenuOptions.getMenuOrientation());
        mMenuSystem.build();
        mMenuSystem.toggleMenu();
    }
}
