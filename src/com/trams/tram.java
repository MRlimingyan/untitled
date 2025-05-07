package com.trams;

import java.io.Serializable;

public class tram implements Serializable {
    private String id;
    private String model;
    private String status;
    private String line;
  private static final long serialVersionUID = 1L;
    public tram(String model, String id, String status, String line) {
        this.model = model;
        this.id = id;
        this.status = status;
        this.line = line;
    }

    public String getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public String getStatus() {
        return status;
    }

    public String getLine() {
        return line;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLine(String line) {
        this.line = line;
    }

    @Override
    public String toString() {
        return
                "电车编号=" + id + "\n"+
                "型号=" + model +"\n"  +
                "状态=" + status+"\n" +
                "线路=" + line
                ;
    }
}
