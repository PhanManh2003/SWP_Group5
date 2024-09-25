
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
        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                accounts.add(getFromResultSet(rs));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return accounts;
    }

    @Override
    public boolean update(Account t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(Account t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int insert(Account account) {
        String sql = "INSERT INTO dbo.Account (Email, Password, Role, Status, AccountStatus, LastLogin) "
                + "VALUES (?, ?, ?, ?, ?, getDate())";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, account.getEmail());
            statement.setString(2, account.getPassword());
            statement.setString(3, account.getRole());
            statement.setString(4, account.getStatus());
            statement.setString(5, account.getAccountStatus());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating account failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating account failed, no ID obtained.");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error inserting account: " + ex.getMessage());
            return -1; // Return -1 to indicate failure
        }
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

    public Account findByEmail(Account t) {
        String sql = "SELECT * FROM dbo.Account WHERE [Email] = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, t.getEmail());
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

    public boolean activateAccount(int accountId) {
        String sql = "UPDATE dbo.Account SET Status = 'Active' WHERE AccountID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, accountId);

            int affectedRows = statement.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException ex) {
            System.out.println("Error activating account: " + ex.getMessage());
            return false;
        }
    }

    public boolean updatePassword(Account account) {
        String sql = "UPDATE dbo.Account SET Password = ? WHERE Email = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, account.getPassword());
            statement.setString(2, account.getEmail());
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            System.out.println("Error updating password: " + ex.getMessage());
            return false;
        }
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
