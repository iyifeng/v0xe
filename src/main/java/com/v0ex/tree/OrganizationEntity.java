package com.v0ex.tree;

/**
 * Created by zbj on 18/3/1.
 */

public class OrganizationEntity {
    public int parentId;
    public int orgId;
    public String orgName;
    public int getParentId() {
        return parentId;
    }
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
    public int getOrgId() {
        return orgId;
    }
    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }
    public String getOrgName() {
        return orgName;
    }
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}

