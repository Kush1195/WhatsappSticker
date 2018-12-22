package com.example.samplestickerapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.Formatter;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class StickerAdapter extends RecyclerView.Adapter<StickerPackListItemViewHolder>
{
    @NonNull
    private List<StickerPack> stickerPacks;
    @NonNull
    private final StickerPackListAdapter.OnAddButtonClickedListener onAddButtonClickedListener;

    StickerAdapter(@NonNull List<StickerPack> stickerPacks, @NonNull StickerPackListAdapter.OnAddButtonClickedListener onAddButtonClickedListener) {
        this.stickerPacks = stickerPacks;
        this.onAddButtonClickedListener = onAddButtonClickedListener;
    }

    @NonNull
    @Override
    public StickerPackListItemViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int i) {
        final Context context = viewGroup.getContext();
        final LayoutInflater layoutInflater = LayoutInflater.from(context);
        final View stickerPackRow = layoutInflater.inflate(R.layout.sticker_adapter_layout, viewGroup, false);
        return new StickerPackListItemViewHolder(stickerPackRow);
    }

    @Override
    public void onBindViewHolder(@NonNull StickerPackListItemViewHolder viewHolder, int index)
    {
        StickerPack pack = stickerPacks.get(index);
        final Context context = viewHolder.publisherView.getContext();
        viewHolder.publisherView.setText(pack.publisher);
        viewHolder.filesizeView.setText(Formatter.formatShortFileSize(context, pack.getTotalSize()));

        viewHolder.titleView.setText(pack.name);
        viewHolder.container.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), StickerPackDetailsActivity.class);
            intent.putExtra(StickerPackDetailsActivity.EXTRA_SHOW_UP_BUTTON, true);
            intent.putExtra(StickerPackDetailsActivity.EXTRA_STICKER_PACK_DATA, pack);
            view.getContext().startActivity(intent);
        });
        viewHolder.first_sticker.setImageURI(StickerPackLoader.getStickerAssetUri(pack.identifier, pack.getStickers().get(0).imageFileName));
        viewHolder.second_sticker.setImageURI(StickerPackLoader.getStickerAssetUri(pack.identifier, pack.getStickers().get(1).imageFileName));
        viewHolder.third_sticker.setImageURI(StickerPackLoader.getStickerAssetUri(pack.identifier, pack.getStickers().get(2).imageFileName));
        viewHolder.fourth_sticker.setImageURI(StickerPackLoader.getStickerAssetUri(pack.identifier, pack.getStickers().get(3).imageFileName));
        viewHolder.fifth_sticker.setImageURI(StickerPackLoader.getStickerAssetUri(pack.identifier, pack.getStickers().get(4).imageFileName));

        setAddButtonAppearance(viewHolder.addButton, pack);
    }
    private void setAddButtonAppearance(ImageView addButton, StickerPack pack) {
        if (pack.getIsWhitelisted()) {
            addButton.setImageResource(R.drawable.sticker_3rdparty_added);
            addButton.setClickable(false);
            addButton.setOnClickListener(null);
            setBackground(addButton, null);
        } else {
            addButton.setImageResource(R.drawable.sticker_3rdparty_add);
            addButton.setOnClickListener(v -> onAddButtonClickedListener.onAddButtonClicked(pack));
            TypedValue outValue = new TypedValue();
            addButton.getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
            addButton.setBackgroundResource(outValue.resourceId);
        }
    }

    private void setBackground(View view, Drawable background) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(background);
        } else {
            view.setBackgroundDrawable(background);
        }
    }
    @Override
    public int getItemCount() {
        return stickerPacks.size();
    }


    public void setStickerPackList(List<StickerPack> stickerPackList) {
        this.stickerPacks = stickerPackList;
    }
}
