package com.yangting.hibernate.double_1_1.primary.configurationFile;

/**
 * Created by 18435 on 2017/12/8.
 */
public class Manager {
    private String mgrId;
    private String mgrName;
    private Department department;

    public String getMgrId() {
        return mgrId;
    }

    public void setMgrId(String mgrId) {
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
