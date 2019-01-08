package cz.librarius.repository;

import cz.librarius.domain.User;

import javax.persistence.TypedQuery;
import java.util.List;

public class UserRepoImpl extends GenericRepository<User> implements UserRepository{
    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        TypedQuery<User> query = em
                .createNamedQuery("findUserByUsernameAndPassword", User.class)
                .setParameter("username", username)
                .setParameter("password", password);
        List<User> list = query.getResultList();
        if (list.isEmpty()) return null;
        else return list.get(0);
    }

    @Override
    public User findByUsername(String username) {
//        TypedQuery<User> query = em
//                .createNamedQuery("findUserByUsername", User.class)
//                .setParameter("username", username);
//        return query.getSingleResult();
        return this.find(username);
    }
}
