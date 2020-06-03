package Interfaces;

import Entities.DalUser;

public interface CRUD_User {
    void create(DalUser user);
    DalUser read(String userName, String passWord);
    void update(DalUser user);
    void delete(DalUser user);
}
