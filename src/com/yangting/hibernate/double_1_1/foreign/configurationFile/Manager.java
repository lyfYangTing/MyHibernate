package com.yangting.hibernate.double_1_1.foreign.configurationFile;

/**
 * Created by 18435 on 2017/12/1.
 */
public class Manager {
    private Integer mgrId;
    private String mgrName;
    private Department department;

    public Integer getMgrId() {
        return mgrId;
    }

    public void setMgrId(Integer mgrId) {
        this.mgrId = mgrId;
    }

    public String getMgrName() {
        return mgrName;
    }

    public void setMgrName(String mgrName) {
        this.mgrName = mgrName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
