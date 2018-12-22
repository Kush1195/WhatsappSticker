/*
 * Copyright (c) WhatsApp Inc. and its affiliates.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.example.samplestickerapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StickerPackListItemViewHolder extends RecyclerView.ViewHolder {

    View container;
    TextView titleView;
    TextView publisherView;
    TextView filesizeView;
    ImageView addButton;
    LinearLayout imageRowView;

    ImageView first_sticker,second_sticker,third_sticker,fourth_sticker,fifth_sticker;


    StickerPackListItemViewHolder(final View itemView) {
        super(itemView);
        container = itemView;
        titleView = itemView.findViewById(R.id.sticker_pack_title);
        publisherView = itemView.findViewById(R.id.sticker_pack_publisher);
        filesizeView = itemView.findViewById(R.id.sticker_pack_filesize);
        addButton = itemView.findViewById(R.id.add_button_on_list);
        imageRowView = itemView.findViewById(R.id.sticker_packs_list_item_image_list);

        first_sticker = itemView.findViewById(R.id.first_sticker);
        second_sticker = itemView.findViewById(R.id.second_sticker);
        third_sticker = itemView.findViewById(R.id.third_sticker);
        fourth_sticker = itemView.findViewById(R.id.fourth_sticker);
        fifth_sticker = itemView.findViewById(R.id.fifth_sticker);

        int width = itemView.getResources().getDisplayMetrics().widthPixels;
        int height = itemView.getResources().getDisplayMetrics().heightPixels;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width * 400 / 1080, height * 100 / 1920);

        titleView.setLayoutParams(layoutParams);

        LinearLayout.LayoutParams images = new LinearLayout.LayoutParams(width * 150 / 1080, height * 150 / 1920);
        images.setMargins(10,1,2,4);
        first_sticker.setLayoutParams(images);
        second_sticker.setLayoutParams(images);
        third_sticker.setLayoutParams(images);
        fourth_sticker.setLayoutParams(images);
        fifth_sticker.setLayoutParams(images);

    }
}