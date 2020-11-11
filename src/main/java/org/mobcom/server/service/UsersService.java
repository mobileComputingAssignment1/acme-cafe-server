package org.mobcom.server.service;

import org.mobcom.server.lib.User;
import org.mobcom.server.persistence.UserEntity;
import org.mobcom.server.service.mappers.UserMapper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

public class UsersService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    private EntityManager em = emf.createEntityManager();

    public User getUser(String userId){
        UserEntity userEntity = em.find(UserEntity.class, userId);
        return UserMapper.fromUserEntity(userEntity);
    }

    public List<User> getAllUsers(){
        TypedQuery<UserEntity> query = em.createNamedQuery(UserEntity.findAll, UserEntity.class);

        return query.getResultStream()
                .map(UserMapper::fromUserEntity)
                .collect(Collectors.toList());

    }

    public User createUser(User user){
        UserEntity entity = new UserEntity();
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setUserName(user.getUserName());
        entity.setPassword(user.getPassword());


        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }

        return UserMapper.fromUserEntity(entity);

    }
}
