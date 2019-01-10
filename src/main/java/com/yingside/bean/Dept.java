package com.yingside.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Dept implements Serializable {
    private int deptId;
    private String deptName;
    private String deptInfo;
    private Date deptCreateDate;
    private List<Employee> employeeList;

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptInfo() {
        return deptInfo;
    }

    public void setDeptInfo(String deptInfo) {
        this.deptInfo = deptInfo;
    }

    public Date getDeptCreateDate() {
        return deptCreateDate;
    }

    public void setDeptCreateDate(Date deptCreateDate) {
        this.deptCreateDate = deptCreateDate;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                ", deptInfo='" + deptInfo + '\'' +
                ", deptCreateDate=" + deptCreateDate +
                //", employeeList=" + employeeList +
                '}';
    }
}
