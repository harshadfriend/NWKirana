package karjatonline.nw;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Dell on 09-Mar-18.
 */

public class OrderListadapter extends ArrayAdapter{
    Context context;
    int layoutResourceId;
    String[][] data=null;

    //List<string_item> data=null;

    public OrderListadapter(Context context, int resource, String[][] objects) {
        //public myadapter(Context context, int resource, List<string_item> objects) {
//        super(context, resource, objects);
        super(context,resource,objects);
        this.context = context;
        //this.data = objects;
        this.data=objects;
        this.layoutResourceId = resource;
    }

    static class myHolder {
        TextView tvOLSrNo;
        TextView tvOLdate;
        TextView tvOLtotal;
        TextView tvOLstatus;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        myHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);

            holder = new myHolder();
            holder.tvOLSrNo=convertView.findViewById(R.id.tvOLASrNo);
            holder.tvOLdate=convertView.findViewById(R.id.tvOLAdate);
            holder.tvOLtotal=convertView.findViewById(R.id.tvOLAtotal);
            holder.tvOLstatus=convertView.findViewById(R.id.tvOLApaidstatus);

            convertView.setTag(holder);


        } else {
            holder = (myHolder) convertView.getTag();
        }



        Log.d("logg","date"+data[position][0]);

//        holder.tvPstock.setTypeface(Typeface.DEFAULT_BOLD);
//        holder.tvPrate.setTypeface(Typeface.DEFAULT_BOLD);
//        holder.tvPname.setTypeface(Typeface.DEFAULT_BOLD);

        holder.tvOLSrNo.setText(""+(position+1));
        holder.tvOLdate.setText(" "+data[position][0]);
        holder.tvOLtotal.setText(" "+data[position][1]+"/-Rs");
        //holder.tvOLstatus.setText(data[position][2]);




        return convertView;
    }
}
