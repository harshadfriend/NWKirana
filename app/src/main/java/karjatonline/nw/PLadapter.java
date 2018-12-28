package karjatonline.nw;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.util.ArrayList;

import karjatonline.nw.R;

/**
 * Created by Dell on 09-Mar-18.
 */

public class PLadapter extends ArrayAdapter{
    Context context;
    int layoutResourceId;
    String[][] data=null;

    //List<string_item> data=null;

    public PLadapter(Context context, int resource, String[][] objects) {
        //public myadapter(Context context, int resource, List<string_item> objects) {
//        super(context, resource, objects);
        super(context,resource,objects);
        this.context = context;
        //this.data = objects;
        this.data=objects;
        this.layoutResourceId = resource;
    }

    static class myHolder {
        TextView tvPname;
        TextView tvPrate;
        TextView tvPstock;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        myHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);

            holder = new myHolder();
            holder.tvPname=convertView.findViewById(R.id.tvPname);
            holder.tvPrate=convertView.findViewById(R.id.tvPrate);

            holder.tvPstock=convertView.findViewById(R.id.tvPstock);



            convertView.setTag(holder);


        } else {
            holder = (myHolder) convertView.getTag();
        }



        Log.d("logg","date"+data[position][0]);

        holder.tvPstock.setTypeface(Typeface.DEFAULT_BOLD);
        holder.tvPrate.setTypeface(Typeface.DEFAULT_BOLD);
        holder.tvPname.setTypeface(Typeface.DEFAULT_BOLD);

        holder.tvPname.setText(" "+data[position][0]);
        holder.tvPrate.setText(" "+data[position][1]);
        holder.tvPstock.setText(data[position][2]);




        return convertView;
    }
}
