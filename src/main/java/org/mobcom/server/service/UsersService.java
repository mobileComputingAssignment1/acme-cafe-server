package org.mobcom.server.service;

import javassist.NotFoundException;
import org.mobcom.server.lib.User;
import org.mobcom.server.persistence.UserEntity;
import org.mobcom.server.service.mappers.UserMapper;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class UsersService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    private EntityManager em = emf.createEntityManager();

    public User getUser(String userId){
        UserEntity userEntity = em.find(UserEntity.class, userId);
        return UserMapper.fromUserEntity(userEntity);
    }

    public List<User> getAllUsers(){
        TypedQuery<UserEntity> query = em.createNamedQuery(UserEntity.FIND_ALL, UserEntity.class);

        return query.getResultStream()
                .map(UserMapper::fromUserEntity)
                .collect(Collectors.toList());

    }

    public User createUser(User user){
        UserEntity entity = new UserEntity();
        entity = UserMapper.toUserEntity(user);

        String uuid = UUID.randomUUID().toString();
        entity.setId(uuid);

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

    public User loginUser (User user) {
        TypedQuery<UserEntity> query = em.createNamedQuery(UserEntity.FIND_BY_USER_NAME, UserEntity.class);
        query.setParameter("userName", user.getUserName());

        try {
            UserEntity entity = query.getSingleResult();
            User userWithUserName = UserMapper.fromUserEntity(entity);

            if (user.getPassword().equals(userWithUserName.getPassword())){
                return userWithUserName;
            }
        } catch (NoResultException e) {
//            throw new NoResultException("Wrong Password for username");
            return null;
        }
        catch (NonUniqueResultException e) {
            throw new NonUniqueResultException("Returned multiple results for unique pair!");
        }

        return null;
    }
}
