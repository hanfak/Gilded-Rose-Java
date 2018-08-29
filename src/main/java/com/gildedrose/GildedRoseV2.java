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
            reduceQualityByOne(item);
        } else {
            reduceQualityByTwo(item);
        }
        item.sellIn -= 1;
    }

    private void reduceQualityByTwo(Item item) {
        item.quality -= 2;
        setQuailtytoZero(item);
    }

    private void reduceQualityByOne(Item item) {
        item.quality -= 1;
        setQuailtytoZero(item);
    }

    private void setQuailtytoZero(Item item) {
        if (item.quality <= 0) {
            item.quality = 0;
        }
    }
}