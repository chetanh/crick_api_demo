package com.android.demo.ui.match_list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.android.demo.R;
import com.android.demo.databinding.MatchCardViewBinding;
import com.android.demo.framework.models.Match;

import java.util.List;

public class MatchRecyclerViewAdapter extends RecyclerView.Adapter<MatchRecyclerViewAdapter.MatchViewHolder> {

    private List<Match> matchArrayList;
    private MatchItemClickListener matchItemClickListener;


    MatchRecyclerViewAdapter(MatchItemClickListener matchItemClickListener) {
        this.matchItemClickListener = matchItemClickListener;
    }

    @NonNull
    @Override
    public MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new MatchViewHolder(DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.match_card_view,
                null, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, final int position) {

        //set data
        holder.matchCardViewBinding.setListener(matchItemClickListener);
        holder.matchCardViewBinding.setMatch(matchArrayList.get(position));

    }

    @Override
    public int getItemCount() {
        return matchArrayList == null ? 0 : matchArrayList.size();
    }


    void setMatchList(final List<Match> matchArrayList) {
        if (this.matchArrayList == null) {
            this.matchArrayList = matchArrayList;
            notifyItemRangeInserted(0, matchArrayList.size());
        } else {
            //update data in view
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return MatchRecyclerViewAdapter.this.matchArrayList.size();
                }

                @Override
                public int getNewListSize() {
                    return matchArrayList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {

                    return MatchRecyclerViewAdapter.this.matchArrayList.get(oldItemPosition).getUniqueId().equals(
                            matchArrayList.get(newItemPosition).getUniqueId()
                    );
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    /**
                     * TODO:
                     * here we can check the score also against unique ID of project
                     * to show updated score on UI.
                     * Currently we do not get score.
                     */

                    Match project = matchArrayList.get(newItemPosition);
                    Match old = matchArrayList.get(oldItemPosition);
                    return project.getUniqueId().equals(old.getUniqueId());
                }
            });
            this.matchArrayList = matchArrayList;
            result.dispatchUpdatesTo(this);
        }
    }

    public interface MatchItemClickListener {
        void onMatchRecyclerViewItemClick(Match match);
    }

    static class MatchViewHolder extends RecyclerView.ViewHolder {

        final MatchCardViewBinding matchCardViewBinding;

        MatchViewHolder(MatchCardViewBinding matchCardViewBinding) {
            super(matchCardViewBinding.getRoot());
            this.matchCardViewBinding = matchCardViewBinding;
        }
    }
}