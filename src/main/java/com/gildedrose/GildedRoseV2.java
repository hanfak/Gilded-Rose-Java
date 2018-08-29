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
        if (item.sellIn > 0) {
            item.quality -= 1;
        } else {
            item.quality -= 2;
        }
        item.sellIn -= 1;
    }
}