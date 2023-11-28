package backend.spring.dao.impl;

import backend.spring.dao.UserDao;
import backend.spring.model.User;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    public static List<User> users;

    //실제 데이터는 DB에서 가져오는게 맞으나 .... 아직 DB가 없으니 임시로 세팅해놓음
    static {
        users = new ArrayList<>();
        users.add(new User(1,"testName1","testId1", "1234"));
        users.add(new User(2,"testName2","testId2", "1234"));
        users.add(new User(3,"testName3","testId3", "1234"));
        users.add(new User(4,"testName4","testId4", "1234"));
        users.add(new User(5,"testName5","testId5", "1234"));
    }

    // Select all user.
    @Override
    public List<User> getAllUsers() {
        return users;
    }

    // Select one user by userId
    @Override
    public User getUserByUserId(String userId) {
        return users
                .stream()
                .filter(user -> user.getUserId().equals(userId))
                .findAny()
                .orElse(new User(-1, "", "", ""));
    }

    // Insert User
    @Override
    public User insertUser(User user) {
        users.add(user);

        return user;
    }

    // Modify User
    @Override
    public void updateUser(String userId,User user) {
        users.stream()
                .filter(curUser -> curUser.getUserId().equals(userId))
                .findAny()
                .orElse(new User(-1, "", "", ""))
                .setUserName(user.getUserName());
    }

    // Delete User
    @Override
    public void deleteUser(String userId) {
        users.removeIf(user -> user.getUserId().equals(userId));
    }
}