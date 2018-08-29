package com.gildedrose;

import java.util.Arrays;
import java.util.List;

class GildedRoseV2 {
    Item[] items;

    public GildedRoseV2(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        List<Item> items = Arrays.asList(this.items);
        items.forEach(this::reduceItemSellinAndQuality);
    }

    private void reduceItemSellinAndQuality(Item item) {
        item.quality -= 1;
        item.sellIn -= 1;
    }
}