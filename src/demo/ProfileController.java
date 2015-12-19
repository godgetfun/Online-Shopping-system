/*
 * Copyright (c) 2008, 2012 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle Corporation nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package demo;
import demo.model.User;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
//import nox.Registration_successfull;

/**
 * Profile Controller.
 */
public class ProfileController extends AnchorPane implements Initializable {
    String s1,s2,s3,s4,s5,s6;

    @FXML
    private TextField user;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private TextArea address;
    @FXML
    private CheckBox subscribed;
    @FXML
    private PasswordField password1;
     @FXML
    private PasswordField password2;
    @FXML
    private Hyperlink logout;
    @FXML 
    private Button save;
    
    @FXML 
    private Label success;
    
    private Main application;
    
    public void setApp(Main application){
        
        this.application = application;
        User loggedUser = application.getLoggedUser();
        user.setText(loggedUser.getId());
        email.setText(loggedUser.getEmail());
        phone.setText(loggedUser.getPhone());
        if (loggedUser.getAddress() != null) {
            address.setText(loggedUser.getAddress());
        }
        subscribed.setSelected(loggedUser.isSubscribed());
        success.setOpacity(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    public void processLogout(ActionEvent event) {
        if (application == null){
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            return;
        }
        
        application.userLogout();
    }
    
    public void saveProfile(ActionEvent event) throws ClassNotFoundException, SQLException {
        if (application == null){
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            animateMessage();
            
            return;
        }
        
        User loggedUser = application.getLoggedUser();
        loggedUser.setEmail(email.getText());
        loggedUser.setPhone(phone.getText());
        loggedUser.setSubscribed(subscribed.isSelected());
        loggedUser.setAddress(address.getText());
        animateMessage();
            s1 = user.getText();
            s2  = phone.getText();
             s3 = email.getText();
            s4  = address.getText();
            s5 = password1.getText();
            s6 = password1.getText();
                Connection c;
                Statement stmt;
              //  ResultSet rs;
                Class.forName("com.mysql.jdbc.Driver");
                    c = DriverManager.getConnection ("jdbc:mysql://localhost:3306/dataa", "root", "freak");        
                stmt = c.createStatement();
                String sql;
          //         sql = "select * from REGISTER where (username = '" + s1 + "' and password = '" + s2 + "')";
         //       sql = "INSERT INTO REGISTER VALUES(s1,s2,s3,s4,s5,s6)";
              
                    sql = "insert into register values( '"+s1+"','"+s2+"' ,'"+s3+"' ,'"+s4+"', '"+s5+"', '"+s6+"')";
                    int rs = stmt.executeUpdate(sql);
                
                        //stmt.executeQuery(sql);
               
                
                this.setVisible(false);
                //eshop.home ob = new eshop.home();
                application.gotoCart();
                //new nox.Registration_successfull1().setVisible(true);
                //ob.user(s1);
               // ob.setVisible(true);
    }
    
    public void resetProfile(ActionEvent event){
        if (application == null){
            return;
        }
        email.setText("");
        phone.setText("");
        subscribed.setSelected(false);
        address.setText("");
        password1.setText("");
        success.setOpacity(0.0);
        
    }

    private void animateMessage() {
        FadeTransition ft = new FadeTransition(Duration.millis(10000), success);
        ft.setFromValue(0.0);
        ft.setToValue(1);
        ft.play();
    }
}
