/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rz;

import java.io.Serializable;

/**
 *
 * @author wrz19
 */
public class UserBean extends Object implements Serializable{
        
    private String username;
    private int userid;
    
    public UserBean() {
        username = "";
    }
    
    public String getusername() {
        return username;
    }
    
    public void setusername(String value) {
        username=value;
    }
    
    public int getuserid() {
        return userid;
    }
    
    public void setuserid(int value) {
        userid=value;
    }
}
