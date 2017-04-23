package test.com.softuni.data_structures;

import main.com.softuni.bashsoft.contracts.SimpleOrderedBag;
import main.com.softuni.bashsoft.data_structures.SimpleSortedList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SimpleOrderedBagTests {

    private SimpleOrderedBag<String> names;

    @Before
    public void setUp() {
        this.names = new SimpleSortedList<String>(String.class);
    }

    @Test
    public void shouldWorkProperlyWithEmptyConstructor() {
        this.names = new SimpleSortedList<String>(String.class);
        Assert.assertEquals(16, this.names.capacity());
        Assert.assertEquals(0, this.names.size());
    }

    @Test
    public void shouldWorkProperlyWithInitialCapacity() {
        this.names = new SimpleSortedList<String>(String.class, 20);
        Assert.assertEquals(20, this.names.capacity());
        Assert.assertEquals(0, this.names.size());
    }

    @Test
    public void shouldWorkProperlyWithInitialComparer() {
        this.names = new SimpleSortedList<String>(String.class,
                new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareTo(o2);
                    }
                });
        Assert.assertEquals(16, this.names.capacity());
        Assert.assertEquals(0, this.names.size());
    }

    @Test
    public void shouldWorkProperlyWithAllParams() {
        this.names = new SimpleSortedList<String>(String.class,
                new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareTo(o2);
                    }
                },
                20);
        Assert.assertEquals(20, this.names.capacity());
        Assert.assertEquals(0, this.names.size());
    }

    @Test
    public void testAddIncreasesSize() {
        this.names.add("Nasko");
        Assert.assertEquals(1, this.names.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAddingNullElement() {
        this.names.add(null);
    }

    @Test
    public void testAddUnsortedDataIsHeldSorted() {
        this.names.add("Rosen");
        this.names.add("Georgi");
        this.names.add("Balkan");
        Assert.assertEquals("Balkan, Georgi, Rosen",this.names.joinWith(", "));
        Assert.assertEquals(3, this.names.size());
    }

    @Test
    public void testAddingMoreThanInitialCapacity() {
        for (int i = 0; i < 17; i++) {
            this.names.add("Name" + i);
        }
        Assert.assertEquals("Size is not the expected.", 17, this.names.size());
        Assert.assertEquals("Capacity is not the expected.", 32, this.names.capacity());
    }

    @Test
    public void testAddingAllFromCollectionIncreasesSize() {
        List<String> elements = new ArrayList<>(Arrays.asList("Dimitar", "Balkan"));
        this.names.addAll(elements);
        Assert.assertEquals(2, this.names.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddingAllFromNullThrowsException() {
        this.names.addAll(null);
    }

    @Test
    public void testAddAllKeepsSorted() {
        this.names.addAll(new ArrayList<>(Arrays.asList("Dimitar", "Balkan")));
        Assert.assertEquals("Balkan, Dimitar", this.names.joinWith(", "));
    }

    @Test
    public void testRemovingValidElementDecreasesSize() {
        this.names.addAll(new ArrayList<>(Arrays.asList("Dimitar", "Balkan")));
        this.names.remove("Dimitar");
        Assert.assertEquals(1, this.names.size());
    }

    @Test
    public void testRemoveValidElementRemovesSelectedOne() {
        this.names.addAll(new ArrayList<>(Arrays.asList("Ivan", "Nasko")));
        this.names.remove("Ivan");
        Assert.assertEquals("Nasko", this.names.joinWith(" "));
    }

    @Test(expected = NullPointerException.class)
    public void testRemovingNullThrowsException() {
        this.names.add("Ivan");
        this.names.remove(null);
    }

    @Test(expected = NullPointerException.class)
    public void testJoinWithNull() {
        this.names.addAll(new ArrayList<>(Arrays.asList("Gergana", "Ana", "Ivan")));
        this.names.joinWith(null);
    }

    @Test
    public void testJoinWorksFine() {
        this.names.addAll(new ArrayList<>(Arrays.asList("Gergana", "Ana", "Ivan")));
        Assert.assertEquals("Ana, Gergana, Ivan", this.names.joinWith(", "));
    }

}