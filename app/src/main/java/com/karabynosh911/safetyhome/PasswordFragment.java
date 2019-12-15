package com.karabynosh911.safetyhome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.karabynosh911.safetyhome.databinding.PasswordFragmentBinding;

public class PasswordFragment extends Fragment {

    private PasswordViewModel mViewModel;

    private PasswordFragmentBinding binding;

    public static PasswordFragment newInstance() {
        return new PasswordFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.password_fragment, container, false);
        NavController navController = NavHostFragment.findNavController(this);
        binding.proceed.setOnClickListener(v -> navController.navigate(R.id.action_password_fragment_to_mainFragment));
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PasswordViewModel.class);
    }
}
