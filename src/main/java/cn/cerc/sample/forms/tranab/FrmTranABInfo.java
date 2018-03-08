package cn.cerc.sample.forms.tranab;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.DataSet;
import cn.cerc.jdb.core.Record;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jmis.page.RedirectPage;
import cn.cerc.sample.forms.tran.TranH_Record;

public class FrmTranABInfo extends AbstractForm {
    @Override
    public IPage execute() {
        JspPage jspPage = new JspPage(this, "tranab/FrmTranABInfo.jsp");
        // 取得传过来的数据
        String tbno = this.getRequest().getParameter("tbno");
        // 查询AB单单头数据
        LocalService svr = new LocalService(this);
        svr.setService("SvrTranAB.searchSingleTranH");
        Record headIn = svr.getDataIn().getHead();
        headIn.setField("TBNo_", tbno);
        if (!svr.exec()) {
            jspPage.setMessage(svr.getMessage());
            return jspPage;
        }
        DataSet ds = svr.getDataOut();
        if (!ds.eof()) {
            TranH_Record item = new TranH_Record();
            item.setTbno(ds.getString("TBNo_"));
            item.setTbDate(ds.getString("TBDate_"));
            item.setSupName(ds.getString("SupName_"));
            item.setDeptName(ds.getString("DeptName_"));
            item.setAppUser(ds.getString("AppUser_"));
            item.setAppDate(ds.getString("AppDate_"));
            jspPage.add("item", item);
        }

        // AB单单身数据
        LocalService svrTranb = new LocalService(this);
        svrTranb.setService("SvrTranAB.searchSingleTranB");
        Record headIn2 = svrTranb.getDataIn().getHead();
        headIn2.setField("TBNo_", tbno);
        if (!svrTranb.exec()) {
            jspPage.setMessage(svrTranb.getMessage());
            return jspPage;
        }
        /*
         * DataSet dsTranb = svrTranb.getDataOut(); List<TranB_Record> items = new
         * ArrayList<>();
         * 
         * while (dsTranb.fetch()) { TranB_Record tranBitem = new TranB_Record(); //
         * tranBitem.setIt(dsTranb.getString("It_"));
         * tranBitem.setTbno(dsTranb.getString("TBNo_"));
         * tranBitem.setCode(dsTranb.getString("Code_"));
         * tranBitem.setCode(dsTranb.getString("Code_"));
         * tranBitem.setDesc(dsTranb.getString("Desc_")); //
         * tranBitem.setSpec(dsTranb.getString("Spec_")); //
         * tranBitem.setUnit(dsTranb.getString("Unit_")); //
         * tranBitem.setNum(dsTranb.getDouble("Num_")); items.add(tranBitem); }
         * jspPage.add("items", items);
         */
        jspPage.add("dataSet", svrTranb.getDataOut());
        return jspPage;
    }

    public IPage delete() {
        JspPage jspPage = new JspPage(this, "tranab/FrmTranABInfo.jsp");
        /*
         * LocalService svr = new LocalService(this);
         * svr.setService("SvrTranAB.delete"); String tbno =
         * this.getRequest().getParameter("tbno"); Record headIn =
         * svr.getDataIn().getHead(); headIn.setField("TBNo_", tbno);
         * 
         * if (!svr.exec()) { jspPage.setMessage(svr.getMessage()); return jspPage; } //
         * return jspPage;
         * 
         */
        return new RedirectPage(this, "FrmTranABInfo");
    }

    @Override
    public boolean logon() {
        return true;
    }
}