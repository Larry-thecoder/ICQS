/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import swp391.db.DBUtils;
/**
 *
 * @author cdkhu
 */
public class UserDAO {
    
    public UserDTO checkLogin(String username, String password) throws SQLException{
        UserDTO user = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try{
            con = DBUtils.makeConnection();
            if(con != null){
                String sql = "SELECT * FROM User WHERE username = ? AND password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if(rs.next()){
                    int userid = rs.getInt("userId");
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    String email = rs.getString("email");
                    String phoneNumber = rs.getString("phoneNumber");
                    Date dateOfBirth = rs.getDate("dateOfBirth");
                    String image = rs.getString("image");
                    boolean status = rs.getBoolean("status");
                    int roleid = rs.getInt("roleId");
                    user = new UserDTO(userid, firstName, lastName, email, password, phoneNumber, dateOfBirth, image, status, roleid);
                }
            }
        }catch(Exception e){
            
        }finally{
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
            
            if(con != null){
                con.close();
            }
        }
        
        return user;
    }
    
}
