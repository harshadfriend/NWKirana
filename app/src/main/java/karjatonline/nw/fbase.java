package karjatonline.nw;

/**
 * Created by family on 4/3/18.
 */
public class fbase {
    private String name;
    private String city,mobile,date,item;
    private String count;
    private String type,status;
    private String pkey;
    private String dateintpaid,interest,months;
    private String roi,grosswt,netwt,comments;


    private String amount,princamount;

    private String ckey,tkey;
    private String deadlinedate;
    private String releaseAmtReceived;
    private String releasecomments;
    private String pendingamount;
    private String pquantity,prate,pname;
    public fbase(){
    }


    /*
    public String get(){
        return ;
    }

    public void set(String ){
        this.=;
    }
    */

    public String getPquantity(){
        return pquantity;
    }

    public void setPquantity(String pquantity){
        this.pquantity=pquantity;
    }

    public String getPname(){
        return pname;
    }

    public void setPname(String pname){
        this.pname=pname;
    }

    public String getPrate(){
        return prate;
    }

    public void setPrate(String prate){
        this.prate=prate;
    }

    public String getPendingamount(){
        return pendingamount;
    }

    public void setPendingamount(String pendingamount){
        this.pendingamount=pendingamount;
    }

    public String getReleasecomments(){
        return releasecomments;
    }

    public void setReleasecomments(String releasecomments){
        this.releasecomments=releasecomments;
    }

    public String getReleaseAmtReceived(){
        return releaseAmtReceived;
    }

    public void setReleaseAmtReceived(String releaseAmtReceived){
        this.releaseAmtReceived=releaseAmtReceived;
    }

    public String getDeadlinedate(){
        return deadlinedate;
    }

    public void setDeadlinedate(String deadlinedate){
        this.deadlinedate=deadlinedate;
    }

    public String getckey(){
        return ckey;
    }

    public void setckey(String ckey){
        this.ckey=ckey;
    }

    public String gettkey(){
        return tkey;
    }

    public void settkey(String tkey){
        this.tkey=tkey;
    }

    public String getroi(){
        return roi;
    }

    public void setroi(String roi){
        this.roi=roi;
    }

    public String getgrosswt(){
        return grosswt;
    }

    public void setgrosswt(String grosswt){
        this.grosswt=grosswt;
    }

    public String getnetwt(){
        return netwt;
    }

    public void setnetwt(String netwt){
        this.netwt=netwt;
    }

    public String getcomments(){
        return comments;
    }

    public void setcomments(String comments){
        this.comments=comments;
    }



    public String getmonths(){
        return months;
    }

    public void setmonths(String months){
        this.months=months;
    }

    public String getInterest(){
        return interest;
    }

    public void setInterest(String interest){
        this.interest=interest;
    }

    public String getdateintpaid(){
        return dateintpaid;
    }

    public void setdateintpaid(String dateintpaid){
        this.dateintpaid=dateintpaid;
    }

    public String getpkey(){
        return pkey;
    }

    public void setpkey(String pkey){
        this.pkey=pkey;
    }

    public String getcount(){
        return count;
    }

    public void setcount(String count){
        this.count=count;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date=date;
    }

    public String getItem(){
        return item;
    }

    public void setItem(String item){
        this.item=item;
    }

    public String getAmount(){
        return amount;
    }

    public void setAmount(String amount){
        this.amount=amount;
    }

    public String getprincamount(){
        return princamount;
    }

    public void setprincamount(String princamount){
        this.princamount=princamount;
    }


    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

    public String getcity(){
        return city;
    }
    public void setcity(String city){
        this.city=city;
    }

    public String getmobile(){
        return mobile;
    }

    public void setmobile(String mobile){
        this.mobile=mobile;
    }

    public String gettype(){  // add or subtract
        return type;
    }

    public void settype(String type){
        this.type=type;
    }

    public String getstatus(){ //released or unreleased
        return status;
    }

    public void setstatus(String status){
        this.status=status;
    }


}

