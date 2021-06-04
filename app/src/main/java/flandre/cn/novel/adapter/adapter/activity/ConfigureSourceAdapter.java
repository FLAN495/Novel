package flandre.cn.novel.adapter.adapter.activity;

import android.support.annotation.Nullable;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import flandre.cn.novel.R;
import flandre.cn.novel.bean.serializable.SourceItem;
import flandre.cn.novel.utils.tools.NovelConfigure;
import flandre.cn.novel.utils.tools.NovelConfigureManager;

import java.util.List;

public class ConfigureSourceAdapter extends BaseQuickAdapter<SourceItem, BaseViewHolder> implements BaseQuickAdapter.OnItemChildClickListener {
    private int current;  // 当前选择的源的索引, 保存时根据此设置配置文件

    public ConfigureSourceAdapter(int layoutResId, @Nullable List<SourceItem> data) {
        super(layoutResId, data);
        setOnItemChildClickListener(this);
    }

    public int getCurrent() {
        return current;
    }

    @Override
    protected void convert(BaseViewHolder helper, SourceItem item) {
        NovelConfigure configure = NovelConfigureManager.getConfigure();
        helper.setText(R.id.name, item.getName());
        helper.setTextColor(R.id.name, configure.getNameTheme());
        helper.setBackgroundRes(R.id.choice, configure.getMode() ==
                NovelConfigure.DAY ? R.drawable.choice_day : R.drawable.choice_night);
        helper.addOnClickListener(R.id.item);
        helper.setVisible(R.id.choice, false);

        if (item.getName().equals(configure.getNowSourceKey())) {
            current = helper.getLayoutPosition();
            helper.setVisible(R.id.choice, true);
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        adapter.getViewByPosition(current, R.id.choice).setVisibility(View.INVISIBLE);
        adapter.getViewByPosition(position, R.id.choice).setVisibility(View.VISIBLE);
        current = position;
    }
}
