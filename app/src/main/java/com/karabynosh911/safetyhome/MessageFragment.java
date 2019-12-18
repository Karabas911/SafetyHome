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

import com.karabynosh911.safetyhome.databinding.MessageFragmentBinding;

import java.util.List;

public class MessageFragment extends Fragment {

    private MessageViewModel mViewModel;

    private MessageAdapter adapter = new MessageAdapter();

    public static MessageFragment newInstance() {
        return new MessageFragment();
    }

    private MessageFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.message_fragment, container, false);
        binding.messages.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.messages.setAdapter(adapter);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MessageViewModel.class);
        mViewModel.getMessagesData().observe(getViewLifecycleOwner(), this::updateMessages);
    }

    private void updateMessages(List<Message> messages) {
        adapter.update(messages);
    }

}
