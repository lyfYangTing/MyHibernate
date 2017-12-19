package com.yangting.hibernate.double_1_1.primary.configurationFile;

/**
 * Created by 18435 on 2017/12/8.
 */
public class Department {
    private String deptId;
    private String deptName;
    private Manager manager;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
