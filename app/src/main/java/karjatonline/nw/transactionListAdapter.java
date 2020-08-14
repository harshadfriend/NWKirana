package karjatonline.nw;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Dell on 09-Mar-18.
 */

public class transactionListAdapter extends ArrayAdapter{
    Context context;
    int layoutResourceId;
    String[][] data=null;

    String dburl;
    Firebase firebase;
    DatabaseReference dbRef;

    //List<string_item> data=null;

    public transactionListAdapter(Context context, int resource, String[][] objects) {
        //public myadapter(Context context, int resource, List<string_item> objects) {
//        super(context, resource, objects);
        super(context,resource,objects);
        this.context = context;
        //this.data = objects;
        this.data=objects;
        this.layoutResourceId = resource;

        dburl=context.getResources().getString(R.string.url);
        firebase=new Firebase(dburl);
        dbRef = FirebaseDatabase.getInstance().getReference();
    }

    static class myHolder {
        TextView tvOLSrNo;
        TextView tvOLdate;
        TextView tvOLtotal;
        TextView tvOLstatus;
        ImageView ivDelete;
    }

    @SuppressLint("SetTextI18n")
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
            holder.ivDelete=convertView.findViewById(R.id.ivDelete);

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

        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(context).setTitle("Delete ?")
                        .setMessage("Delete selected transaction ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                firebase.child("transactions").child(data[position][3]).child(data[position][2]).removeValue();
                                Toast.makeText(context, "Successfully deleted !", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
            }
        });


        return convertView;
    }
}
