package gdg.com.gknm.adapter.base;

/**
 * 通用适配器Adapter写法
 * Created by ${Tom}on 2016/3/25.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class CommonAdapter<T> extends BaseAdapter {
    //为了使得子类可以访问，这里修改包访问级别
    protected Context context;
    protected LayoutInflater layoutInflater;
    protected List<T> data;
    protected int layoutId;

    public CommonAdapter(Context context, List<T> data, int layoutId) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
        this.layoutId = layoutId;
    }

    public void setList(List<T> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public List<T> getList() {
        return data;
    }


    @Override
    public int getCount() {
        return data==null?0: data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //获取ViewHolder对象
        CommonViewHolder myViewHolder = new CommonViewHolder(context, convertView, layoutId, parent, position);
        //需要用户复写的方法，设置所对于的View所对应的数据
        setConverView(position, myViewHolder, data.get(position));
        return myViewHolder.getConvertView();
    }

    //用户需要实现的方法
    public abstract void setConverView(int position, CommonViewHolder myViewHolder, T t);

}