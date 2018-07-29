package BLL;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.example.schedulermobile.R;

import java.util.List;

public class AdapterTask extends BaseAdapter {

    private List<Task> list;
    private LayoutInflater layoutInflater;

    public AdapterTask(Context context, List<Task> list) {
        this.list = list;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Task getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View newView = view;
        if (newView == null) {
            newView = layoutInflater.inflate(R.layout.listitem_layout, viewGroup, false);
        }

        Task task = getTask(i);
        String[] date = task.getDeadline().split("/");
        TextView textView = (TextView) newView.findViewById(R.id.textView3);
        TextView textView1 = (TextView) newView.findViewById(R.id.textView4);
        TextView textView2 = (TextView) newView.findViewById(R.id.textView5);
        TextView textView3 = (TextView) newView.findViewById(R.id.textView6);

        textView.setText(task.getTask());
        textView1.setText(task.getDescription());
        textView2.setText(date[0]);
        textView3.setText(date[1]);


        return newView;
    }

    private Task getTask(int position) {
        return (Task) getItem(position);
    }


}
