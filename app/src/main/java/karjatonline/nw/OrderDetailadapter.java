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

public class OrderDetailadapter extends ArrayAdapter{
    Context context;
    int layoutResourceId;
    String[][] data=null;

    //List<string_item> data=null;

    public OrderDetailadapter(Context context, int resource, String[][] objects) {
        //public myadapter(Context context, int resource, List<string_item> objects) {
//        super(context, resource, objects);
        super(context,resource,objects);
        this.context = context;
        //this.data = objects;
        this.data=objects;
        this.layoutResourceId = resource;
    }

    static class myHolder {
        TextView tvODAname;
        TextView tvODArate;
        TextView tvODAqty;
        TextView tvODAtotal;
        TextView tvODASrNo;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        myHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);

            holder = new myHolder();
            holder.tvODASrNo=convertView.findViewById(R.id.tvODASrNo);
            holder.tvODAname=convertView.findViewById(R.id.tvODAPname);
            holder.tvODArate=convertView.findViewById(R.id.tvODAPrate);
            holder.tvODAqty=convertView.findViewById(R.id.tvODAqty);
            holder.tvODAtotal=convertView.findViewById(R.id.tvODAtotal);

            convertView.setTag(holder);


        } else {
            holder = (myHolder) convertView.getTag();
        }



        Log.d("logg","date"+data[position][0]);

//        holder.tvPstock.setTypeface(Typeface.DEFAULT_BOLD);
//        holder.tvPrate.setTypeface(Typeface.DEFAULT_BOLD);
//        holder.tvPname.setTypeface(Typeface.DEFAULT_BOLD);

        holder.tvODASrNo.setText(""+(position+1));
        holder.tvODAname.setText(" "+data[position][0]);
        holder.tvODArate.setText(" "+data[position][1]);
        holder.tvODAqty.setText(data[position][2]);
        holder.tvODAtotal.setText(data[position][3]+"/-");

        return convertView;
    }
}
