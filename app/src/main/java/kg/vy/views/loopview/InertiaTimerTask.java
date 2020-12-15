// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kg.vy.views.loopview;

// Referenced classes of package com.qingchifan.view:
//            LoopView

import android.util.Log;

import kg.vy.views.loopview.LoopView;
import kg.vy.views.loopview.MessageHandler;

final class InertiaTimerTask implements Runnable {

    float a;
    final float velocityY;
    final kg.vy.views.loopview.LoopView loopView;

    InertiaTimerTask(kg.vy.views.loopview.LoopView loopview, float velocityY) {
        super();
        loopView = loopview;
        this.velocityY = velocityY;
        a = Integer.MAX_VALUE;
    }

    @Override
    public final void run() {
        if (a == Integer.MAX_VALUE) {
            if (Math.abs(velocityY) > 2000F) {
                if (velocityY > 0.0F) {
                    a = 2000F;
                } else {
                    a = -2000F;
                }
            } else {
                a = velocityY;
            }
        }
        if (Math.abs(a) >= 0.0F && Math.abs(a) <= 20F) {
            Log.i("gy","WHAT_SMOOTH_SCROLL_INERTIA");
            loopView.handler.sendEmptyMessageDelayed(kg.vy.views.loopview.MessageHandler.WHAT_SMOOTH_SCROLL_INERTIA,60);
            loopView.cancelFuture();
            loopView.handler.sendEmptyMessage(kg.vy.views.loopview.MessageHandler.WHAT_SMOOTH_SCROLL);
            return;
        }
        int i = (int) ((a * 10F) / 1000F);
        LoopView loopview = loopView;
        loopview.totalScrollY = loopview.totalScrollY - i;
        if (!loopView.isLoop) {
            float itemHeight = loopView.lineSpacingMultiplier * loopView.itemTextHeight;
            if (loopView.totalScrollY <= (int) ((float) (-loopView.initPosition) * itemHeight)) {
                a = 40F;
                loopView.totalScrollY = (int) ((float) (-loopView.initPosition) * itemHeight);
            } else if (loopView.totalScrollY >= (int) ((float) (loopView.items.size() - 1 - loopView.initPosition) * itemHeight)) {
                loopView.totalScrollY = (int) ((float) (loopView.items.size() - 1 - loopView.initPosition) * itemHeight);
                a = -40F;
            }
        }
        if (a < 0.0F) {
            a = a + 20F;
        } else {
            a = a - 20F;
        }
        loopView.handler.sendEmptyMessage(kg.vy.views.loopview.MessageHandler.WHAT_INVALIDATE_LOOP_VIEW);
    }
}
