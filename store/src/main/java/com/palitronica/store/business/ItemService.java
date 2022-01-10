package com.palitronica.store.business;

import com.palitronica.store.data.entity.Item;
import com.palitronica.store.data.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void saveItem(Item item){
        getItemRepository().save(item);
    }

    public ArrayList<Item> getAllItems(){
        return getItemRepository().findAll();
    }

    public void deleteItem(Item item){
        getItemRepository().delete(item);
    }

    public ItemRepository getItemRepository() {
        return itemRepository;
    }
}
