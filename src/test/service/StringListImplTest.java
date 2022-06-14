package test.service;

import com.company.exception.ElementNotFound;
import com.company.exception.MyIllegalArgumentException;
import com.company.exception.MyIndexOfBoundExceptions;
import com.company.service.StringList;
import com.company.service.impl.StringListImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static test.constant.Constant.*;

class StringListImplTest {

    private final StringList out = new StringListImpl();

    @Test
    void addCorrectItem() {
        String result = out.add(DEFAULT_ITEM0);
        assertTrue(result.contains(DEFAULT_ITEM0));
        assertEquals(DEFAULT_ITEM0, result);
    }

    @Test
    void addNullItem() {
        assertThrows(MyIllegalArgumentException.class, () -> out.add(NULL));
    }

    @Test
    void testAddUsingIndex() {
        String result = out.add(DEFAULT_INDEX0, DEFAULT_ITEM1);
        assertTrue(result.contains(DEFAULT_ITEM1));
        assertEquals(DEFAULT_ITEM1, result);
    }

    @Test
    void testAddNullUsingItem() {
        assertThrows(MyIllegalArgumentException.class, () -> out.add(DEFAULT_INDEX0, NULL));
    }

    @Test
    void testAddUsingIllegalIndex() {
        assertThrows(MyIndexOfBoundExceptions.class, () -> out.add(ILLEGAL_INDEX, DEFAULT_ITEM1));
    }

    @Test
    void setUsingCorrectIndex() {
        String result = out.set(DEFAULT_INDEX0, DEFAULT_ITEM0);
        assertTrue(result.contains(DEFAULT_ITEM0));
        assertEquals(DEFAULT_ITEM0, result);
    }

    @Test
    void setUsingIllegalIndex() {
        assertThrows(MyIndexOfBoundExceptions.class, () -> out.set(ILLEGAL_INDEX, DEFAULT_ITEM0));
    }

    @Test
    void setNullUsingItem() {
        assertThrows(MyIllegalArgumentException.class, () -> out.set(DEFAULT_INDEX0, NULL));
    }

    @Test
    void removeCorrectItem() {
        out.add(DEFAULT_ITEM0);
        String result = out.remove(DEFAULT_ITEM0);
        assertEquals(DEFAULT_ITEM0, result);
    }

    @Test
    void removeNotFoundItem() {
        out.add(DEFAULT_ITEM0);
        assertThrows(ElementNotFound.class, () -> out.remove(DEFAULT_ITEM1));
    }

    @Test
    void removeNullItem() {
        out.add(DEFAULT_ITEM0);
        assertThrows(MyIllegalArgumentException.class, () -> out.remove(NULL));
    }

    @Test
    void testRemoveUsingCorrectIndex() {
        out.add(DEFAULT_ITEM0);
        String result = out.remove(DEFAULT_INDEX0);
        assertEquals(NULL, result);
    }

    @Test
    void testRemoveUsingNotCorrectIndex() {
        out.add(DEFAULT_INDEX0, DEFAULT_ITEM0);
        assertThrows(MyIndexOfBoundExceptions.class, () -> out.remove(ILLEGAL_INDEX));
    }


    @Test
    void ifContainsItem() {
        out.add(DEFAULT_ITEM1);
        boolean result = out.contains(DEFAULT_ITEM1);
        assertEquals(IF_CONTAINS_ITEM, result);
    }

    @Test
    void ifNotContainsItem() {
        out.add(DEFAULT_ITEM1);
        boolean result = out.contains(DEFAULT_ITEM2);
        assertEquals(IF_NOT_CONTAINS_ITEM, result);
    }

    @Test
    void findIndexOfItem() {
        out.add(DEFAULT_ITEM1);
        int result = out.indexOf(DEFAULT_ITEM1);
        assertEquals(DEFAULT_INDEX0, result);
    }

    @Test
    void findIndexOfItemIfItemNotFound() {
        out.add(DEFAULT_ITEM1);
        int result = out.indexOf(DEFAULT_ITEM0);
        assertEquals(ILLEGAL_INDEX, result);
    }

    @Test
    void findLastIndexOfItem() {
        out.add(DEFAULT_ITEM1);
        int result = out.indexOf(DEFAULT_ITEM1);
        assertEquals(DEFAULT_INDEX0, result);
    }

    @Test
    void findLastIndexOfItemIfItemNotFound() {
        out.add(DEFAULT_ITEM1);
        int result = out.indexOf(DEFAULT_ITEM0);
        assertEquals(ILLEGAL_INDEX, result);
    }

    @Test
    void get() {
        out.add(DEFAULT_ITEM0);
        String result = out.get(DEFAULT_INDEX0);
        assertEquals(DEFAULT_ITEM0, result);
    }

    @Test
    void testEqualsIfOtherIsNull() {
        out.add(DEFAULT_ITEM0);
        assertThrows(MyIllegalArgumentException.class, () -> out.equals(null));
    }

    @Test
    void testEqualsIfObjectOneThisObjectTwo() {
        out.add(DEFAULT_ITEM0);
        StringList result = new StringListImpl();
        result = out;
        assertTrue(out.equals(result));
    }

    @Test
    void testEquals() {
        out.add(DEFAULT_ITEM0);
        StringList result = new StringListImpl();
        result.add(DEFAULT_ITEM0);
        assertTrue(out.equals(result));
    }

    @Test
    void testNotEquals() {
        out.add(DEFAULT_ITEM0);
        out.add(DEFAULT_ITEM1);
        out.add(DEFAULT_ITEM2);
        out.add(DEFAULT_ITEM3);
        StringList result = new StringListImpl();
        result.add(DEFAULT_ITEM1);
        result.add(DEFAULT_ITEM2);
        result.add(DEFAULT_ITEM3);
        assertFalse(out.equals(result));
    }

    @Test
    void size() {
        out.add(DEFAULT_ITEM0);
        out.add(DEFAULT_ITEM1);
        out.add(DEFAULT_ITEM2);
        out.add(DEFAULT_ITEM3);
        out.remove(DEFAULT_ITEM1);
        out.remove(DEFAULT_INDEX2);
        int result = out.size();
        assertEquals(TWO_ELEM, result);
    }

    @Test
    void isEmpty() {
        boolean result = out.isEmpty();
        assertTrue(result);
    }

    @Test
    void isNotEmpty() {
        out.add(DEFAULT_ITEM0);
        boolean result = out.isEmpty();
        assertFalse(result);
    }

    @Test
    void clear() {
        out.add(DEFAULT_ITEM0);
        out.add(DEFAULT_ITEM1);
        out.clear();
        boolean result = out.isEmpty();
        assertTrue(result);
    }


}