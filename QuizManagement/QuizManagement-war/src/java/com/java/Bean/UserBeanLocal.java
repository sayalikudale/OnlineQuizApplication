/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.Bean;

import com.java.Entity.User;
import javax.ejb.Local;

/**
 *
 * @author sayali
 */
@Local
public interface UserBeanLocal {

    public boolean login(User user);

    public boolean register(User user);

}
