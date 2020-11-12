package org.mobcom.server.lib;



public class UserVoucher extends BaseType{
    private String voucherId;
    private User user;

    public String getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
