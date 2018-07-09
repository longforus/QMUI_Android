package com.qmuiteam.qmui.widget.dialog;

import android.content.Context;

/**
 * Description : 简化{@link QMUITipDialog}的调用
 *
 * @author XQ Yang
 * @date 2018/7/9  14:59
 */
public class Tips {

    public static QMUITipDialog loading(Context context) {
        return loading(context,"正在读取");
    }

    public static QMUITipDialog loading(Context context,String msg) {
        return new QMUITipDialog.Builder(context)
            .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
            .setTipWord(msg)
            .create();
    }

    public static QMUITipDialog success(Context context) {
        return success(context,"操作成功");
    }

    public static QMUITipDialog success(Context context,String msg) {
        return new QMUITipDialog.Builder(context)
            .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
            .setTipWord(msg)
            .create();
    }
    public static QMUITipDialog fail(Context context) {
        return fail(context,"操作失败");
    }

    public static QMUITipDialog fail(Context context,String msg) {
        return new QMUITipDialog.Builder(context)
            .setIconType(QMUITipDialog.Builder.ICON_TYPE_FAIL)
            .setTipWord(msg)
            .create();
    }

    public static QMUITipDialog info(Context context,String msg) {
        return new QMUITipDialog.Builder(context)
            .setIconType(QMUITipDialog.Builder.ICON_TYPE_INFO)
            .setTipWord(msg)
            .create();
    }

    public static QMUITipDialog text(Context context,String msg) {
        return new QMUITipDialog.Builder(context)
            .setTipWord(msg)
            .create();
    }


}
