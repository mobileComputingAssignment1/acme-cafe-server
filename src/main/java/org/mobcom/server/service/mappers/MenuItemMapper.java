package org.mobcom.server.service.mappers;

import org.mobcom.server.lib.MenuItem;
import org.mobcom.server.persistence.MenuItemEntity;

public class MenuItemMapper {
    public static MenuItem fromMenuItemEntity(MenuItemEntity entity) {
        MenuItem menuItem = new MenuItem();
        menuItem.setId(entity.getId());
        menuItem.setName(entity.getName());
        menuItem.setPrice(entity.getPrice());
        menuItem.setTimestamp(entity.getTimestamp());

        return menuItem;
    }

    public static MenuItemEntity toMenuItemEntity(MenuItem menuItem) {
        MenuItemEntity entity = new MenuItemEntity();
        entity.setId(menuItem.getId());
        entity.setName(menuItem.getName());
        entity.setPrice(menuItem.getPrice());
        entity.setTimestamp(menuItem.getTimestamp());

        return entity;
    }
}
