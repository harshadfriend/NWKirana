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

public class NewOrderListadapter extends ArrayAdapter{
    Context context;
    int layoutResourceId;
    String[][] data=null;

    //List<string_item> data=null;

    public NewOrderListadapter(Context context, int resource, String[][] objects) {
        //public myadapter(Context context, int resource, List<string_item> objects) {
//        super(context, resource, objects);
        super(context,resource,objects);
        this.context = context;
        //this.data = objects;
        this.data=objects;
        this.layoutResourceId = resource;
    }

    static class myHolder {
        TextView tvNODAname;
        TextView tvNODArtNqt;
        TextView tvNODAtotal;
        TextView tvNODASrNo;
        TextView tvNODAdeleteItem;
        int index=0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        myHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);

            holder = new myHolder();
            holder.tvNODASrNo=convertView.findViewById(R.id.tvNODASrNo);
            holder.tvNODAname=convertView.findViewById(R.id.tvNODAPname);
            holder.tvNODArtNqt=convertView.findViewById(R.id.tvNODArtNqt);
            holder.tvNODAtotal=convertView.findViewById(R.id.tvNODAtotal);
            holder.tvNODAdeleteItem=convertView.findViewById(R.id.tvNODADeleteItem);

            convertView.setTag(holder);


        } else {
            holder = (myHolder) convertView.getTag();
        }



        Log.d("logg","date"+data[position][0]);

//        holder.tvPstock.setTypeface(Typeface.DEFAULT_BOLD);
//        holder.tvPrate.setTypeface(Typeface.DEFAULT_BOLD);
//        holder.tvPname.setTypeface(Typeface.DEFAULT_BOLD);

        holder.tvNODASrNo.setText(""+(position+1));
        holder.tvNODAname.setText(" "+data[position][0]);
        holder.tvNODArtNqt.setText(data[position][1]+"*"+data[position][2]);
        holder.tvNODAtotal.setText(data[position][3]+"/-");
        holder.index=Integer.parseInt(data[position][4]);

        holder.tvNODAdeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return convertView;
    }
}

