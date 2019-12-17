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

import com.karabynosh911.safetyhome.databinding.HomeFragmentBinding;

public class HomeFragment extends Fragment {

    public static final String TAG = "HomeFragment";

    private HomeViewModel viewModel;

    private HomeFragmentBinding binding;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false);
        binding.activate.setOnClickListener(v -> {
            viewModel.activateSecurity();
            binding.progress.setVisibility(View.VISIBLE);
        });
        binding.deactivate.setOnClickListener(v -> {
                    binding.progress.setVisibility(View.VISIBLE);
                    viewModel.deactivateSecurity();
                }
        );
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        viewModel.getStatusData().observe(this, this::onStatusChanged);
    }

    private void onStatusChanged(Integer status) {
        int drawableResource;
        if (status == 1) {
            drawableResource = R.drawable.ic_house;
            binding.statusImage.setImageDrawable(ContextCompat.getDrawable(requireContext(), drawableResource));
            binding.passwordTitle.setText("Protection activated");
            binding.passwordTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorPrimary));
            binding.deactivate.setVisibility(View.VISIBLE);
            binding.activate.setVisibility(View.GONE);
        } else if (status == 2) {
            drawableResource = R.drawable.ic_house_not_protected;
            binding.statusImage.setImageDrawable(ContextCompat.getDrawable(requireContext(), drawableResource));
            binding.passwordTitle.setText("Protection deactivated");
            binding.passwordTitle.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.holo_red_dark));
            binding.deactivate.setVisibility(View.GONE);
            binding.activate.setVisibility(View.VISIBLE);
        }
        binding.progress.setVisibility(View.GONE);
    }

}
