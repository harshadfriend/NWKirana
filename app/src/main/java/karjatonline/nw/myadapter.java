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

public class myadapter extends ArrayAdapter{
    Context context;
    int layoutResourceId;
    String[][] data=null;

    //List<string_item> data=null;

    public myadapter(Context context, int resource, String[][] objects) {
        //public myadapter(Context context, int resource, List<string_item> objects) {
//        super(context, resource, objects);
        super(context,resource,objects);
        this.context = context;
        //this.data = objects;
        this.data=objects;
        this.layoutResourceId = resource;
    }

    static class myHolder {
        TextView tvPledgeNumber;
        TextView tvDate;
        TextView tvItem;
        TextView tvAmount;
        LinearLayout llPledgeDetail;
        TextView tvdeleteicon,tvediticon,tvRoi,tvGrosswt,tvNetwt,tvComments;
        String name,custkey;
        TextView tvDeadlineNP;
        TextView tvRelAmt,tvRelComments;
        //int noContact=0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        myHolder holder = null;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);

            holder = new myHolder();
      /*      holder.tvRelComments=convertView.findViewById(R.id.tvRelComments);
            holder.tvRelAmt=convertView.findViewById(R.id.tvRelAmtRec);

            holder.tvDeadlineNP=convertView.findViewById(R.id.tvDeadlineNP);
            holder.tvPledgeNumber=(TextView)convertView.findViewById(R.id.tvPledgeNumber);
            holder.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
            holder.tvItem = (TextView) convertView.findViewById(R.id.tvItem);
            holder.tvAmount = (TextView) convertView.findViewById(R.id.tvAmount);
            holder.tvdeleteicon=convertView.findViewById(R.id.tvdeleteicon);
            holder.tvediticon=convertView.findViewById(R.id.tvediticon);

            holder.tvComments=convertView.findViewById(R.id.tvcomments);
            holder.tvComments.setTypeface(null,Typeface.ITALIC);

            holder.tvRoi=convertView.findViewById(R.id.tvRoi);
            holder.tvNetwt=convertView.findViewById(R.id.tvNetwt);
            holder.tvGrosswt=convertView.findViewById(R.id.tvGrosswt);

            holder.llPledgeDetail=(LinearLayout)convertView.findViewById(R.id.llPledgeDetail);


            convertView.setTag(holder);
*/

        } else {
            holder = (myHolder) convertView.getTag();
        }

        if(data[position][4].equals("Released")) {
            holder.tvdeleteicon.setVisibility(View.GONE);
            holder.tvRelComments.setVisibility(View.VISIBLE);
            holder.tvRelAmt.setVisibility(View.VISIBLE);
//            holder.tvPledgeNumber.setBackgroundColor(Color.rgb(175,0,0));
            holder.tvPledgeNumber.setBackgroundResource(R.drawable.redbutton);
        }

        Log.d("logg","date"+data[position][0]);
    /*    holder.tvPledgeNumber.setText(""+(position+1));
//        holder.tvPledgeNumber.setText("kj");
        holder.tvDate.setText(" "+data[position][1]);
        holder.tvItem.setText(" "+data[position][2]);
        holder.tvAmount.setTypeface(null, Typeface.BOLD);
        holder.tvAmount.setText(" "+data[position][3]+"/-");

        holder.name=data[position][5];
        holder.custkey=data[position][6];

        holder.tvRoi.setText(" "+data[position][7]);
        holder.tvGrosswt.setText(" "+data[position][8]);
        holder.tvNetwt.setText(" "+data[position][9]);
        holder.tvComments.setText(" "+data[position][10]);
        holder.tvDeadlineNP.setText("Deadline: "+data[position][11]);
        holder.tvRelAmt.setText(""+data[position][12]);
        holder.tvRelComments.setText(""+data[position][13]);

        holder.llPledgeDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "Item Clicked "+data[position][0], Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getContext(),PledgeDetails.class);
                i.putExtra("key",data[position][0]);
                i.putExtra("pledgeno",""+(position+1));
                i.putExtra("name",data[position][5]);
                i.putExtra("date",data[position][1]);
                i.putExtra("princamount",data[position][3]);
                i.putExtra("status",data[position][4]);
                i.putExtra("item",data[position][2]);
                i.putExtra("custkey",data[position][6]);
                /*if(data[position][4].equals("Released"))
                    Toast.makeText(context, "Pledge already released !", Toast.LENGTH_SHORT).show();
                else*/
 /*                   context.startActivity(i);
            }
        });

        holder.tvdeleteicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(context).setTitle("Delete Pledge ?")
                        .setMessage("This will delete the selected pledge !\nContinue ?").
                        setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(context, "Delete Successful !", Toast.LENGTH_SHORT).show();
                                new Firebase("https://jewelleryshop-30ff9.firebaseio.com/").child("pledge").child(data[position][6])
                                        .child(data[position][0]).setValue(null);
                                new Firebase("https://jewelleryshop-30ff9.firebaseio.com/").child("transactions").child(data[position][6])
                                        .child(data[position][0]).setValue(null);
                                new Firebase("https://jewelleryshop-30ff9.firebaseio.com/").child("amount").child(data[position][6])
                                        .child(data[position][0]).setValue(null);
                                new Firebase("https://jewelleryshop-30ff9.firebaseio.com/").child("interest").child(data[position][6])
                                        .child(data[position][0]).setValue(null);
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
                */

//               Toast.makeText(context, "Delete Clicked "+data[position][0], Toast.LENGTH_SHORT).show();
//                    Toast.makeText(context, "Delete Successful !", Toast.LENGTH_SHORT).show();
//                    new Firebase("https://jewelleryshop-30ff9.firebaseio.com/").child("pledge").child(data[position][6])
//                            .child(data[position][0]).setValue(null);
//                    new Firebase("https://jewelleryshop-30ff9.firebaseio.com/").child("transactions").child(data[position][6])
//                            .child(data[position][0]).setValue(null);
//                    new Firebase("https://jewelleryshop-30ff9.firebaseio.com/").child("amount").child(data[position][6])
//                            .child(data[position][0]).setValue(null);
//                new Firebase("https://jewelleryshop-30ff9.firebaseio.com/").child("interest").child(data[position][6])
//                        .child(data[position][0]).setValue(null);
    /*        }
        });

        holder.tvediticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Edit not enabled yet", Toast.LENGTH_SHORT).show();
            }
        });


/*
        holder.txtName.setText(stringItem.name);
        holder.btnMobile.setText(stringItem.mobile);
        holder.txtAdd.setText(stringItem.address);*/


        return convertView;
    }
}
