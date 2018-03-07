package cn.cerc.sample.forms.tran;

import java.util.ArrayList;
import java.util.List;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.DataSet;
import cn.cerc.jdb.core.Record;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jmis.page.RedirectPage;

public class FrmTranABS extends AbstractForm {
    @Override
    public IPage execute() {
        JspPage jspPage = new JspPage(this, "tran/FrmTranABS.jsp");
        // 查询AB单单头数据
        LocalService svr = new LocalService(this);
        svr.setService("SvrTranAB.tranHsearch");
        if (!svr.exec()) {
            jspPage.setMessage(svr.getMessage());
            return jspPage;
        }
        DataSet ds = svr.getDataOut();
        List<TranH_Record> items = new ArrayList<>();
        while (ds.fetch()) {
            TranH_Record item = new TranH_Record();
            item.setTb(ds.getString("TB_"));
            item.setTbno(ds.getString("TBNo_"));
            item.setTbDate(ds.getString("TBDate_"));
            item.setSupName(ds.getString("SupName_"));
            item.setDeptName(ds.getString("DeptName_"));
            item.setAppUser(ds.getString("AppUser_"));
            item.setAppDate(ds.getString("AppDate_"));
            // item.setStock(ds.getDouble("Stock_"));
            items.add(item);
        }
        jspPage.add("items", items);
        return jspPage;
    }

    public IPage delete() {
        JspPage jspPage = new JspPage(this, "tran/FrmTranABS.jsp");
        LocalService svr = new LocalService(this);
        svr.setService("SvrTranAB.delete");
        String tbno = this.getRequest().getParameter("tbno");
        Record headIn = svr.getDataIn().getHead();
        headIn.setField("TBNo_", tbno);

        if (!svr.exec()) {
            jspPage.setMessage(svr.getMessage());
            return jspPage;
        }
        // return jspPage;
        return new RedirectPage(this, "FrmTranABS");
    }

    @Override
    public boolean logon() {
        return true;
    }
}