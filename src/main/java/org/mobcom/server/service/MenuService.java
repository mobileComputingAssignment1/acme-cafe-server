package org.mobcom.server.service;

import org.mobcom.server.lib.MenuItem;
import org.mobcom.server.persistence.MenuItemEntity;
import org.mobcom.server.service.mappers.MenuItemMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class MenuService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    private EntityManager em = emf.createEntityManager();

    public List<MenuItem> getMenu() {
        TypedQuery<MenuItemEntity> query = em.createNamedQuery(MenuItemEntity.FIND_ALL, MenuItemEntity.class);

        return query.getResultStream()
                .map(MenuItemMapper::fromMenuItemEntity)
                .collect(Collectors.toList());
    }

    public MenuItem getMenuItem(String menuItemId) {

        MenuItemEntity menuItemEntity = em.find(MenuItemEntity.class, menuItemId);
        return MenuItemMapper.fromMenuItemEntity(menuItemEntity);
    }
}



