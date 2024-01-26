/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import swp391.db.DBUtils;
/**
 *
 * @author cdkhu
 */
public class UserDAO {
    private List<UserDTO> listUser;
    
    public List<UserDTO> getListUser(){
        return listUser;
    }
    
    public UserDTO checkLogin(String email, String password) throws SQLException{
        UserDTO user = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try{
            con = DBUtils.makeConnection();
            if(con != null){
                String sql = "SELECT * FROM User WHERE email = ? AND password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if(rs.next()){
                    int userID = rs.getInt("userID");
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    String phoneNumber = rs.getString("phoneNumber");
                    Date dateOfBirth = rs.getDate("dateOfBirth");
                    String image = rs.getString("image");
                    int status = rs.getInt("status");
                    int roleID = rs.getInt("roleID");
                    int notificationID = rs.getInt("notificationID");
                    user = new UserDTO(userID, firstName, lastName, email, password, phoneNumber, dateOfBirth, image, status, roleID, notificationID);
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
    
    public void searchUserFirstName(String searchFirstName) throws SQLException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try{
            con = DBUtils.makeConnection();
            if(con != null){
                String sql = "SELECT * FROM User WHERE firstName like? ORDER BY FirstName";
                stm = con.prepareStatement(sql);
                stm.setString(1,"%"+ searchFirstName +"%");
                rs = stm.executeQuery();
                while(rs.next()){
                    int userID = rs.getInt("userID");
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String phoneNumber = rs.getString("phoneNumber");
                    Date dateOfBirth = rs.getDate("dateOfBirth");
                    String image = rs.getString("image");
                    int status = rs.getInt("status");
                    int roleID = rs.getInt("roleID");
                    int notificationID = rs.getInt("notificationID");
                    
                    UserDTO dto = new UserDTO(userID, firstName, lastName, email, password, phoneNumber, dateOfBirth, image, status, roleID, notificationID);
                    if(this.listUser == null){
                        this.listUser = new ArrayList<>();
                    }
                    
                    this.listUser.add(dto);
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
    }
    
}
