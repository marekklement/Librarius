package cz.librarius.repository.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import cz.librarius.domain.User;

public class UserRepoImpl extends GenericRepository<User> implements UserRepository {
    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        TypedQuery<User> query = em
            .createNamedQuery("findUserByUsernameAndPassword", User.class)
            .setParameter("username", username)
            .setParameter("password", password);
        List<User> list = query.getResultList();
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
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
