package com.example.collegeappfirstassemble.SocialMain.util;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SpacingItemDecorator extends RecyclerView.ItemDecoration{

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.bottom = verticalspaceheight;
    }

    private final int verticalspaceheight;

    public SpacingItemDecorator(int verticalspaceheight) {
        this.verticalspaceheight = verticalspaceheight;
    }
}

