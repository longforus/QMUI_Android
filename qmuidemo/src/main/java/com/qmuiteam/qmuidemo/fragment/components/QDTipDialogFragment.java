package com.qmuiteam.qmuidemo.fragment.components;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.widget.Toast;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.qmuiteam.qmui.widget.dialog.Tips;
import com.qmuiteam.qmuidemo.manager.QDDataManager;
import com.qmuiteam.qmuidemo.base.BaseFragment;
import com.qmuiteam.qmuidemo.model.QDItemDescription;
import com.qmuiteam.qmuidemo.R;
import com.qmuiteam.qmuidemo.lib.annotation.Widget;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * {@link QMUITipDialog} 的使用示例。
 * Created by Kayo on 2016/11/21.
 */
@Widget(widgetClass = QMUITipDialog.class, iconRes = R.mipmap.icon_grid_tip_dialog)
public class QDTipDialogFragment extends BaseFragment {

    @BindView(R.id.topbar) QMUITopBar mTopBar;
    @BindView(R.id.listview) ListView mListView;

    private QDItemDescription mQDItemDescription;

    @Override
    protected View onCreateView() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_listview, null);
        ButterKnife.bind(this, root);

        mQDItemDescription = QDDataManager.getInstance().getDescription(this.getClass());
        initTopBar();

        initListView();

        return root;
    }

    private void initTopBar() {
        mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });

        mTopBar.setTitle(mQDItemDescription.getName());
    }

    private void initListView() {
        String[] listItems = new String[]{
                "Loading 类型提示框",
                "成功提示类型提示框",
                "失败提示类型提示框",
                "信息提示类型提示框",
                "单独图片类型提示框",
                "单独文字类型提示框",
                "自定义内容提示框"
        };
        List<String> data = new ArrayList<>();

        Collections.addAll(data, listItems);

        mListView.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.simple_list_item, data));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 QMUITipDialog tipDialog = null;
                switch (position) {
                    case 0:
                        Tips.loading(getContext()).showAndAutoDismiss();
                        //tipDialog = new QMUITipDialog.Builder(getContext())
                        //        .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                        //        .setTipWord("正在加载")
                        //        .create();
                        break;
                    case 1:
                        Tips.success(getContext()).showAndAutoDismiss();

                        break;
                    case 2:
                        Tips.fail(getContext()).showAndAutoDismiss();

                        break;
                    case 3:
                        Tips.info(getContext(),"别点了").showAndAutoDismiss();
                        break;
                    case 4:
                        tipDialog = new QMUITipDialog.Builder(getContext())
                                .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                                .create();
                        break;
                    case 5:
                        Tips.text(getContext(),"只有字").showAndAutoDismiss(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                Toast.makeText(getContext(),"dismiss",Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    case 6:
                        tipDialog = new QMUITipDialog.CustomBuilder(getContext())
                                .setContent(R.layout.tipdialog_custom)
                                .create();
                        break;
                    default:
                        tipDialog = new QMUITipDialog.Builder(getContext())
                                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                                .setTipWord("正在加载")
                                .create();
                }
                if (tipDialog!=null) {
                    tipDialog.show();
                    final QMUITipDialog finalTipDialog = tipDialog;
                    mListView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finalTipDialog.dismiss();
                        }
                    }, 1500);
                }
            }
        });
    }
}
