package com.karabynosh911.safetyhome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.karabynosh911.safetyhome.databinding.MainFragmentBinding;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;

    private MainFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false);
        initUI();
        initHomeFragment();
        return binding.getRoot();
    }

    private void initHomeFragment() {
        getChildFragmentManager()
                .beginTransaction()
                .add(binding.fragmentContainer.getId(),
                        HomeFragment.newInstance())
                .commit();
        binding.toolbar.setTitleTextColor(ContextCompat.getColor(requireContext(), android.R.color.white));
        binding.toolbar.setTitle("Home security");
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    private void initUI() {
        binding.showUsers.setOnClickListener(v -> onShowUsersClicked());
        binding.showMessages.setOnClickListener(v -> onShowMessagesClicked());
        binding.showHome.setOnClickListener(v -> onShowHomeClicked());
    }

    private void onShowUsersClicked() {
        Fragment fragment = getChildFragmentManager().findFragmentById(binding.fragmentContainer.getId());
        if (fragment instanceof UsersFragment) {
            return;
        } else {
            getChildFragmentManager()
                    .beginTransaction()
                    .replace(binding.fragmentContainer.getId(),
                            UsersFragment.newInstance())
                    .commit();
        }
        binding.toolbar.setTitle("Users");
    }

    private void onShowMessagesClicked() {
        Fragment fragment = getChildFragmentManager().findFragmentById(binding.fragmentContainer.getId());
        if (fragment instanceof MessageFragment) {
            return;
        } else {
            getChildFragmentManager()
                    .beginTransaction()
                    .replace(binding.fragmentContainer.getId(),
                            MessageFragment.newInstance())
                    .commit();
        }
        binding.toolbar.setTitle("Messages");
    }

    private void onShowHomeClicked() {
        Fragment fragment = getChildFragmentManager().findFragmentById(binding.fragmentContainer.getId());
        if (fragment instanceof HomeFragment) {
            return;
        } else {
            getChildFragmentManager()
                    .beginTransaction()
                    .replace(binding.fragmentContainer.getId(),
                            HomeFragment.newInstance())
                    .commit();
        }
        binding.toolbar.setTitle("Home security");
    }
}
