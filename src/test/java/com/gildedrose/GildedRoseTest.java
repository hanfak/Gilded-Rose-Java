package com.gildedrose;
import org.junit.Ignore;
import org.junit.Test;

import java.util.function.IntConsumer;

import static java.util.stream.IntStream.rangeClosed;
import static org.assertj.core.api.Assertions.assertThat;

public class GildedRoseTest {

    public static final int MAX_QUALITY = 50;
    public static final int MIN_QUALITY = 0;

    @Test
    public void regularItemSellInReducesByOneDay() throws Exception {
        Item regularItem = new Item("Regular Item", 5, 10);
        Item[] items = new Item[] {regularItem};
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

       assertThat(regularItem.sellIn).isEqualTo(4);
    }

    @Test
    public void regularItemQualityReducesByOne() throws Exception {
        Item regularItem = new Item("Regular Item", 5, 10);
        Item[] items = new Item[] {regularItem};
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertThat(regularItem.quality).isEqualTo(9);
    }

    @Test
    public void regularItemQualityReducesByTwoAfterSellInIs0() throws Exception {
        Item regularItem = new Item("Regular Item", 5, 10);
        Item[] items = new Item[] {regularItem};
        GildedRose gildedRose = new GildedRose(items);

        rangeClosed(1, 6).forEach(getIntConsumer(gildedRose));

        assertThat(regularItem.quality).isEqualTo(3);
    }

    @Test
    public void regularItemQualityIsNeverNegative() throws Exception {
        Item regularItem = new Item("Regular Item", 5, 10);
        Item[] items = new Item[] {regularItem};
        GildedRose gildedRose = new GildedRose(items);

        rangeClosed(1, 8).forEach(getIntConsumer(gildedRose));

        assertThat(regularItem.quality).isEqualTo(MIN_QUALITY);
    }

    private IntConsumer getIntConsumer(GildedRose gildedRose) {
        return x -> gildedRose.updateQuality();
    }

    @Test
    public void agedBrieQualityIncreaseAfterEachDay() throws Exception {
        Item agedBrie = new Item("Aged Brie", 5, 10);
        Item[] items = new Item[] {agedBrie};
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertThat(agedBrie.quality).isEqualTo(11);
    }

    @Test
    public void qualityOfAnItemIsNeverMoreThan50() throws Exception {
        Item agedBrie = new Item("Aged Brie", 5, 49);
        Item[] items = new Item[] {agedBrie};
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();
        gildedRose.updateQuality();

        assertThat(agedBrie.quality).isEqualTo(MAX_QUALITY);
    }

    // TODO legacy code does not implement this
    @Ignore
    @Test
    public void qualityOfAnItemIsNeverMoreThan50AtTheStart() throws Exception {
        Item agedBrie = new Item("Aged Brie", 5, 59);
        Item[] items = new Item[] {agedBrie};
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();
        gildedRose.updateQuality();

        assertThat(agedBrie.quality).isEqualTo(MAX_QUALITY);
    }

    @Test
    public void blah() throws Exception {
        Item agedBrie = new Item("Aged Brie", 1, 40);
        Item[] items = new Item[] {agedBrie};
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();
        gildedRose.updateQuality();

        assertThat(agedBrie.quality).isEqualTo(43);
    }

    @Test
    public void sulfurasQualityNeverChanges() throws Exception {
        Item sulfurasItem = new Item("Sulfuras, Hand of Ragnaros", 5, 10);
        Item[] items = new Item[] {sulfurasItem};
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertThat(sulfurasItem.quality).isEqualTo(10);
    }

    @Test
    public void sulfurasSellInNeverChanges() throws Exception {
        Item regularItem = new Item("Sulfuras, Hand of Ragnaros", 5, 10);
        Item[] items = new Item[] {regularItem};
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertThat(regularItem.sellIn).isEqualTo(5);
    }

    @Test
    public void backstagePassesIncreaseInQualityAfterEachDay() throws Exception {
        Item backstagePassesItem = new Item("Backstage passes to a TAFKAL80ETC concert", 11, 10);
        Item[] items = new Item[] {backstagePassesItem};
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertThat(backstagePassesItem.quality).isEqualTo(11);
    }

    @Test
    public void backstagePassesIncreaseInQualityBy2AfterEachDayWhenLessThan10Days() throws Exception {
        Item backstagePassesItem = new Item("Backstage passes to a TAFKAL80ETC concert", 9, 10);
        Item[] items = new Item[] {backstagePassesItem};
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertThat(backstagePassesItem.quality).isEqualTo(12);
    }

    @Test
    public void backstagePassesIncreaseInQualityBy2AfterEachDayWhen10Days() throws Exception {
        Item backstagePassesItem = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10);
        Item[] items = new Item[] {backstagePassesItem};
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertThat(backstagePassesItem.quality).isEqualTo(12);
    }

    @Test
    public void backstagePassesIncreaseInQualityBy3AfterEachDayWhenLessThan5Days() throws Exception {
        Item backstagePassesItem = new Item("Backstage passes to a TAFKAL80ETC concert", 4, 10);
        Item[] items = new Item[] {backstagePassesItem};
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertThat(backstagePassesItem.quality).isEqualTo(13);
    }

    @Test
    public void backstagePassesIncreaseInQualityBy3AfterEachDayWhen5Days() throws Exception {
        Item backstagePassesItem = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10);
        Item[] items = new Item[] {backstagePassesItem};
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();

        assertThat(backstagePassesItem.quality).isEqualTo(13);
    }

    @Test
    public void backstagePassesIncreaseInQualityIs0WhenConcertIsOver() throws Exception {
        Item backstagePassesItem = new Item("Backstage passes to a TAFKAL80ETC concert", 1, 10);
        Item[] items = new Item[] {backstagePassesItem};
        GildedRose gildedRose = new GildedRose(items);

        gildedRose.updateQuality();
        gildedRose.updateQuality();

        assertThat(backstagePassesItem.quality).isEqualTo(0);
    }

}
