package com.yangting.hibernate.double_1_1.foreign.configurationFile;

/**
 * Created by 18435 on 2017/12/1.
 */
public class Department {
    private Integer deptId;
    private String deptName;
    private Manager manager;

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
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
