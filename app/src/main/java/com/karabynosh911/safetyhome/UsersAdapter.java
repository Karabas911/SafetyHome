package com.karabynosh911.safetyhome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.karabynosh911.safetyhome.databinding.ItemUserBinding;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    List<User> usersList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_user, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(usersList.get(position));
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public void update(List<User> users) {
        usersList = users;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemUserBinding binding;

        public ViewHolder(View v) {
            super(v);
            binding = DataBindingUtil.bind(itemView);
        }

        void bind(User user) {
            binding.userName.setText(user.name);
            if (user.isAdmin) {
                binding.userRole.setVisibility(View.VISIBLE);
            } else {
                binding.userRole.setVisibility(View.GONE);
            }
        }
    }
}
