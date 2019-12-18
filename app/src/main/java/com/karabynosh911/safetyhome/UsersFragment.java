package com.karabynosh911.safetyhome;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karabynosh911.safetyhome.databinding.UsersFragmentBinding;

import java.util.List;

public class UsersFragment extends Fragment {

    private UsersViewModel mViewModel;

    private UsersFragmentBinding binding;

    private UsersAdapter adapter= new UsersAdapter();

    public static UsersFragment newInstance() {
        return new UsersFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.users_fragment, container, false);
        binding.users.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.users.setAdapter(adapter);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(UsersViewModel.class);
        mViewModel.getUserData().observe(getViewLifecycleOwner(), this::updateUsers);
    }

    private void updateUsers(List<User> users) {
        adapter.update(users);
    }

}
