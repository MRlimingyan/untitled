package com.trams;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class fixRecords implements Serializable {
    private LocalDate fixdate;
    private String type;
    private String staff;
    private String tramId;
    private static final long serialVersionUID = 1L;
    public fixRecords(LocalDate fixdate, String type, String staff, String tramId) {
        this.fixdate = fixdate;
        this.type = type;
        this.staff = staff;
        this.tramId = tramId;
    }

    public LocalDate getFixdate() {
        return fixdate;
    }

    public void setFixdate(LocalDate fixdate) {
        this.fixdate = fixdate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public String getTramId() {
        return tramId;
    }

    public void setTramId(String tramId) {
        this.tramId = tramId;
    }

    @Override
    public String toString() {
        return
                "维护日期 ：" + fixdate+"\n" +
                "维护类型 ：" + type + "\n" +
                "维护人员 ：" + staff + "\n" +
                "电车编号 ：" + tramId
                ;
    }
}
