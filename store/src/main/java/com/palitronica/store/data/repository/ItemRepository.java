package com.palitronica.store.data.repository;

import com.palitronica.store.data.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findByItemId(Long itemId);
    @Override
    ArrayList<Item> findAll();
}
