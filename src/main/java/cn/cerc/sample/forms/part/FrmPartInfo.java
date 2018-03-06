package cn.cerc.sample.forms.part;

import java.util.ArrayList;
import java.util.List;

import cn.cerc.jbean.client.LocalService;
import cn.cerc.jbean.form.IPage;
import cn.cerc.jdb.core.DataSet;
import cn.cerc.jdb.core.Record;
import cn.cerc.jmis.form.AbstractForm;
import cn.cerc.jmis.page.JspPage;
import cn.cerc.jmis.page.RedirectPage;

public class FrmPartInfo extends AbstractForm {
    @Override
    public IPage execute() {
        JspPage jspPage = new JspPage(this, "part/FrmPartInfo.jsp");
        // 查询商品
        LocalService svr = new LocalService(this);
        svr.setService("SvrPartInfo.search");
        if (!svr.exec()) {
            jspPage.setMessage(svr.getMessage());
            return jspPage;
        }
        DataSet ds = svr.getDataOut();
        List<PartInfo_Record> items = new ArrayList<>();
        while (ds.fetch()) {
            PartInfo_Record item = new PartInfo_Record();
            item.setCode(ds.getString("Code_"));
            item.setDesc(ds.getString("Desc_"));
            item.setSpec(ds.getString("Spec_"));
            item.setUnit(ds.getString("Unit_"));
            item.setStock(ds.getDouble("Stock_"));
            items.add(item);
        }
        jspPage.add("items", items);
        return jspPage;
    }

    public IPage delete() {
        JspPage jspPage = new JspPage(this, "part/FrmPartInfo.jsp");
        LocalService svr = new LocalService(this);
        svr.setService("SvrPartInfo.delete");
        String code = this.getRequest().getParameter("code");
        Record headIn = svr.getDataIn().getHead();
        headIn.setField("Code_", code);

        if (!svr.exec()) {
            jspPage.setMessage(svr.getMessage());
            return jspPage;
        }
        // return jspPage;
        return new RedirectPage(this, "FrmPartInfo");
    }

    @Override
    public boolean logon() {
        return true;
    }
}