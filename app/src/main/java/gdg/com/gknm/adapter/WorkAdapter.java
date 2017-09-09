package gdg.com.gknm.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import gdg.com.gknm.R;

/**
 * Created by GUO.DG on 2017-9-5.
 *
 */

public class WorkAdapter extends BaseAdapter {

    private Context context;

    int[] icons;
    private List<String> mList ;

    private List<Integer> nList = new ArrayList<>();
    String[] name = new String[]{"监控报警","监督检查","历史问题查看","现场检查监督指导"};
    public WorkAdapter(Context context) {
        this.context = context;
        initIcon();
    }

    private void initIcon() {
                nList.add(R.mipmap.jkbj);
                nList.add(R.mipmap.jdjc);
                nList.add(R.mipmap.lswt);
                nList.add(R.mipmap.jdzd);
    }

    @Override
    public int getCount() {
        return nList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.grid_attention_item, null);
        TextView iv_name = (TextView) view.findViewById(R.id.iv_home_item_name);
        ImageView iv_icon = (ImageView) view.findViewById(R.id.iv_home_item_icon);

        iv_name.setText(name[position]);
        iv_icon.setImageResource(nList.get(position));

        return view;
    }
}
