package terminkalender.dao.classes;

import terminkalender.dao.interfaces.UserDAO;
import terminkalender.model.classes.UserImpl;
import terminkalender.model.interfaces.User;

public class UserDAOImpl extends ObjectDAOImpl implements UserDAO {

    public UserDAOImpl(){
        super();
    }

    @Override
    public int addUser(User user){
        initTransaction();
        transaction.begin();

        entityManager.persist(user);
        transaction.commit();
        int userID = user.getUserId();

        finishTransaction();
        return userID;
    }

    @Override
    public User getUser(int userId){
        initTransaction();
        transaction.begin();

        User user = entityManager.find(UserImpl.class, userId);
        if(user == null) {
            finishTransaction();
            throw new IllegalArgumentException("User existiert nicht!");
        }

        finishTransaction();
        return user;
    }

    @Override
    public void updateUser(User user){
        initTransaction();
        transaction.begin();

        User updateUser = entityManager.find(UserImpl.class, user.getUserId());
        if(updateUser == null){
            finishTransaction();
            throw new IllegalArgumentException("User existiert nicht!");
        }

        entityManager.merge(user);
        transaction.commit();

        finishTransaction();
    }

    @Override
    public void deleteUser(int userId){
        initTransaction();
        transaction.begin();

        User user = entityManager.find(UserImpl.class, userId);
        if(user == null) {
            finishTransaction();
            throw new IllegalArgumentException("User existiert nicht!");
        }
        entityManager.remove(user);
        transaction.commit();

        finishTransaction();
    }

    public boolean verifyUser(String password){
        return true;
    }
}


