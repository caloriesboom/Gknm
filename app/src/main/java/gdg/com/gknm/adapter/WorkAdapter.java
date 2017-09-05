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
 */

public class WorkAdapter extends BaseAdapter {

    private Context context;

    int[] icons;
    private List<String> mList ;

    private List<Integer> nList = new ArrayList<>();
    private List<String> nameList = new ArrayList<>();
    String[] name = new String[]{"企业信息","实时工况","超标报表","异常报警"};
    public WorkAdapter(Context context, List<String> mList) {
        this.mList =mList;

        this.context = context;
        initIcon();
    }

    private void initIcon() {
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).equals("gn_0001")) {
                nList.add(R.mipmap.jiankongbaojing);
            } else if (mList.get(i).equals("gn_0002")) {
                nList.add(R.mipmap.jiandujiancha);
            } else if (mList.get(i).equals("gn_0003")) {
                nList.add(R.mipmap.lishiwentichakan);
            } else if (mList.get(i).equals("gn_0004")) {
                nList.add(R.mipmap.xianchangjiancha);
            }
// else if (mList.get(i).equals("gn_0005")) {
//                nList.add(R.drawable.zhongyaoyichangbaojing_1);
//            } else if (mList.get(i).equals("gn_0006")) {
//                nList.add(R.drawable.gongkuangchaxun_1);
//            }

        }

    }

    @Override
    public int getCount() {
        return mList.size();
        //  return icons.length;
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

        //iv_icon.setImageResource(icons[position]);
        iv_name.setText(name[position]);
        iv_icon.setImageResource(nList.get(position));

        return view;
    }
}
