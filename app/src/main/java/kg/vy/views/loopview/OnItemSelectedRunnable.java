// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kg.vy.views.loopview;

// Referenced classes of package com.qingchifan.view:
//            LoopView, OnItemSelectedListener

import android.util.Log;

final class OnItemSelectedRunnable implements Runnable {
    final kg.vy.views.loopview.LoopView loopView;

    OnItemSelectedRunnable(kg.vy.views.loopview.LoopView loopview) {
        loopView = loopview;
    }

    @Override
    public final void run() {
        loopView.onItemSelectedListener.onItemSelected(loopView.getSelectedItem());
    }
}
