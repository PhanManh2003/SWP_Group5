
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Account;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author manhpthe172481
 */
public class AccountDAO extends DBContext implements I_DAO<Account> {

    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM dbo.Account";
        try (PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                accounts.add(getFromResultSet(rs));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return accounts;
    }

    @Override
    public Account getFromResultSet(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        account.setAccountID(resultSet.getInt("AccountID"));
        account.setEmail(resultSet.getString("Email"));
        account.setPassword(resultSet.getString("Password"));
        account.setRole(resultSet.getString("Role"));
        account.setStatus(resultSet.getString("Status"));
        account.setAccountStatus(resultSet.getString("AccountStatus"));
        account.setLastLogin(resultSet.getDate("LastLogin"));
        return account;
    }

    public Account findByEmailAndPass(Account t) {
        String sql = "SELECT * FROM dbo.Account WHERE [Email] = ? AND [Password] = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, t.getEmail());
            statement.setString(2, t.getPassword());
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return getFromResultSet(rs);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }


    public static void main(String[] args) {
        AccountDAO accountDAO = new AccountDAO();

        // Test the findByEmailAndPass method
        Account account = new Account();
        account.setEmail("john.doe@email.com");
        account.setPassword("hashed_password_1");
        Account foundAccount = accountDAO.findByEmailAndPass(account);
        if (foundAccount != null) {
            System.out.println("Found account: " + foundAccount.getEmail());
        } else {
            System.out.println("Account not found");
        }
    }


}
