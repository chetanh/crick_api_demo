package com.android.demo.ui.match_list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.android.demo.R;
import com.android.demo.databinding.OldMatchCardViewBinding;
import com.android.demo.framework.models.OldMatch;

import java.util.List;

public class OldMatchRecyclerViewAdapter extends RecyclerView.Adapter<OldMatchRecyclerViewAdapter.OldMatchViewHolder> {

    private List<OldMatch> matchArrayList;
    private OldMatchItemClickListener oldMatchItemClickListener;


    OldMatchRecyclerViewAdapter(OldMatchItemClickListener oldMatchItemClickListener) {
        this.oldMatchItemClickListener = oldMatchItemClickListener;
    }

    @NonNull
    @Override
    public OldMatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new OldMatchViewHolder(DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.old_match_card_view,
                null, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OldMatchViewHolder holder, final int position) {

        OldMatch oldMatch = matchArrayList.get(position);
        String title = oldMatch.getTitle();

        //set data
        holder.oldMatchCardViewBinding.setOldListener(oldMatchItemClickListener);
        holder.oldMatchCardViewBinding.setOldMatch(matchArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return matchArrayList == null ? 0 : matchArrayList.size();
    }


    void setOldMatchList(final List<OldMatch> oldMatchList) {
        if (this.matchArrayList == null) {
            this.matchArrayList = oldMatchList;
            notifyItemRangeInserted(0, matchArrayList.size());
        } else {
            //update data in view
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return OldMatchRecyclerViewAdapter.this.matchArrayList.size();
                }

                @Override
                public int getNewListSize() {
                    return oldMatchList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return OldMatchRecyclerViewAdapter.this.matchArrayList.get(oldItemPosition).getUniqueId().equals(
                            oldMatchList.get(newItemPosition).getUniqueId()
                    );
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    OldMatch newPositionOldMatch = oldMatchList.get(newItemPosition);
                    OldMatch oldPositionOldMatch = oldMatchList.get(oldItemPosition);
                    return newPositionOldMatch.getUniqueId().equals(oldPositionOldMatch.getUniqueId());
                }
            });
            this.matchArrayList = oldMatchList;
            result.dispatchUpdatesTo(this);
        }
    }

    public interface OldMatchItemClickListener {
        void onOldMatchRecyclerViewItemClick(OldMatch oldMatch);
    }

    static class OldMatchViewHolder extends RecyclerView.ViewHolder {

        final OldMatchCardViewBinding oldMatchCardViewBinding;

        OldMatchViewHolder(OldMatchCardViewBinding oldMatchCardViewBinding) {
            super(oldMatchCardViewBinding.getRoot());
            this.oldMatchCardViewBinding = oldMatchCardViewBinding;
        }
    }
}