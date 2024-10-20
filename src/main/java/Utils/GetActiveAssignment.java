/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author HP
 */
public class GetActiveAssignment {

    public String getAssignmentBadge(Timestamp duedate) {
        LocalDateTime dueDate = duedate.toLocalDateTime();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String displayDueDate = dueDate.format(formatter);
        String badgeClass = "badge badge-warning";
        String badgeText = "Due Date: " + displayDueDate;
        if (now.isAfter(dueDate)) {
            badgeText = "Expired";
            badgeClass = "badge badge-danger";
        }
        return "<span class=\"" + badgeClass + "\">" + badgeText + "</span>";
    }

    public boolean getAssignmentIsDue(Timestamp duedate) {
        LocalDateTime dueDate = duedate.toLocalDateTime();
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(dueDate)) {
            return true;
        }
        return false;
    }
}
