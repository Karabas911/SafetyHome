package com.karabynosh911.safetyhome;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karabynosh911.safetyhome.databinding.AuthenticationFragmentBinding;

public class AuthenticationFragment extends Fragment {

    private AuthenticationViewModel mViewModel;

    private AuthenticationFragmentBinding binding;

    public static AuthenticationFragment newInstance() {
        return new AuthenticationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.authentication_fragment, container, false);
        NavController navController = NavHostFragment.findNavController(this);
        binding.login.setOnClickListener(v -> navController.navigate(R.id.action_authentication_fragment_to_mainFragment));
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AuthenticationViewModel.class);
    }
}
