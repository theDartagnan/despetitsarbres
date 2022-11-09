/*
 * Copyright (C) 2022 IUT Laval - Le Mans Université.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package despetitsarbres.tablesHachage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Rémi Venant
 */
public class TableColTest {

    protected TableHachage<String, String> map;

    public TableColTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        this.map = TestedTableHachageFactory.createTableCol(5, 5);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testPutGet() {
        this.map.put("k1", "v1");
        this.map.put("k2", "v2");
        this.map.put("k3", "v3");

        this.map.put("k1", "v4");

        this.map.put("kNull", null);

        assertEquals("v2", this.map.get("k2"));
        assertEquals("v3", this.map.get("k3"));

        assertEquals("v4", this.map.get("k1"));

        assertEquals(null, this.map.get("kNull"));

        assertEquals(null, this.map.get("kunknown"));
    }

    @Test
    public void testPutKeyNull() {
        var assertThrows = assertThrows(NullPointerException.class, () -> {
            this.map.put(null, "v4");
        });
    }

    @Test
    public void testPutMapComplete() {
        for (int i = 0; i < 10; i++) {
            this.map.put("k" + i, "val");
        }
        var assertThrows = assertThrows(OutOfMemoryError.class, () -> {
            this.map.put("k10", "val");
        });
    }

    @Test
    public void testSize() {
        assertEquals(0, this.map.size());
        for (int i = 0; i < 5; i++) {
            this.map.put("k" + i, "val");
        }
        this.map.put("k3", "val2");
        this.map.put("k4", "val2");

        assertEquals(5, this.map.size());
    }

    @Test
    public void testContains() {
        this.map.put("k1", "v1");
        this.map.put("k2", "v2");
        assertTrue(this.map.contains("k1"));
        assertTrue(this.map.contains("k2"));
        assertFalse(this.map.contains("k3"));
    }

    @Test
    public void testRemove() {
        this.map.put("k1", "v1");
        this.map.put("k2", "v2");
        String vk1 = this.map.remove("k1");
        assertEquals("v1", vk1);
        String vk3 = this.map.remove("k3");
        assertEquals(null, vk3);
        this.map.put("k4", "v4");
        assertEquals(2, this.map.size());
        assertTrue(this.map.contains("k2"));
        assertTrue(this.map.contains("k4"));
    }

    @Test
    public void testRemoveKeyNull() {
        var assertThrows = assertThrows(NullPointerException.class, () -> {
            this.map.remove(null);
        });
    }

    @Test
    public void testIterator() {
        Iterator<EntreeTableHachage<String, String>> it = this.map.iterator();
        assertFalse(it.hasNext());
        this.map.put("k1", "v1");
        this.map.put("k2", "v2");
        it = this.map.iterator();
        int nbElems = 0;
        while (it.hasNext()) {
            EntreeTableHachage<String, String> e = it.next();
            if (e.getKey().equals("k1")) {
                assertEquals("v1", e.getValue());
            } else if (e.getKey().equals("k2")) {
                assertEquals("v2", e.getValue());
            } else {
                assertFalse(true, "Bad iteration");
            }
            nbElems++;
        }
        assertEquals(2, nbElems);
    }

    @Test
    public void testIteratorException() {
        this.map.put("k1", "v1");
        this.map.put("k2", "v2");
        final Iterator<EntreeTableHachage<String, String>> it = this.map.iterator();
        while (it.hasNext()) {
            it.next();
        }
        var assertThrows = assertThrows(NoSuchElementException.class, () -> {
            it.next();
        });
    }

    @Test
    public void testIteratorRemove() {
        this.map.put("k1", "v1");
        this.map.put("k2", "v2");
        this.map.put("k3", "v3");
        this.map.put("k4", "v4");
        this.map.put("k5", "v5");
        final Iterator<EntreeTableHachage<String, String>> it = this.map.iterator();
        while (it.hasNext()) {
            EntreeTableHachage<String, String> e = it.next();
            if (e.getKey().equals("k2") || e.getKey().equals("k4")) {
                it.remove();
            }
        }
        assertEquals(3, this.map.size());
    }

    @Test
    public void testIteratorRemoveExceptions() {
        this.map.put("k1", "v1");
        this.map.put("k2", "v2");
        final Iterator<EntreeTableHachage<String, String>> it = this.map.iterator();
        var assertThrows = assertThrows(IllegalStateException.class, () -> {
            it.remove();
        });
        it.next();
        it.remove();
        assertThrows = assertThrows(IllegalStateException.class, () -> {
            it.remove();
        });
        it.next();
        it.remove();
    }

    @Test
    public void testIteratorEmptyList() {
        this.map.put("k1", "val1");
        this.map.put("k2", "val1");
        this.map.remove("k1");
        this.map.remove("k2");
        assertFalse(this.map.iterator().hasNext());
    }
}
