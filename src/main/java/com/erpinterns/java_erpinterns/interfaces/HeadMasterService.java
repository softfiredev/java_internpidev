package com.erpinterns.java_erpinterns.interfaces;

import com.erpinterns.java_erpinterns.models.HeadMasterDepartment;
import com.erpinterns.java_erpinterns.models.User;

import java.util.List;

public interface HeadMasterService {
    void addHeadmaster(HeadMasterDepartment headmaster);
    void updateRoleToHeadmaster(int userId);
    List<User> getUsersByRole(String role);

    List<String> getInternFirstNames();

    int getUserIdByFirstName(String firstName);

    List<HeadMasterDepartment> getAllHeadmasters();

    void deleteHeadmaster(int userId);
    void updateRoleToIntern(int userId);
}
