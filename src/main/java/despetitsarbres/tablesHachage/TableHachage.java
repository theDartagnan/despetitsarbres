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

/**
 *
 * @author Rémi Venant
 * @param <K> type of key
 * @param <V> type of value
 */
public interface TableHachage<K, V> extends Iterable<EntreeTableHachage<K, V>> {

    /**
     * Put an entry (a pair key-value) in the table. If the key already exists, overrides its
     * associated value.
     *
     * @param key the key
     * @param value the value
     * @throws NullPointerException if the key is null
     * @throws OutOfMemoryError if the table is full (if applicable)
     */
    void put(K key, V value);

    /**
     * Remove an entry (a pair key-value) from the table. Does nothing if the key does not exist.
     *
     * @param key the key
     * @return the associated removed value, or null if the key does not exist
     * @throws NullPointerException if the key is null
     */
    V remove(K key);

    /**
     * Retrieve a value associated to a key
     *
     * @param key the key
     * @return the associated value, or null if the key does not exist
     * @throws NullPointerException if the key is null
     */
    V get(K key);

    /**
     * Test if a key exists in the table
     *
     * @param key the key
     * @return True if the key exists, else otherwise
     * @throws NullPointerException if the key is null
     */
    boolean contains(K key);

    /**
     * Get the number of entries in the map (= the number of keys = the number of values)
     *
     * @return the size of the map
     */
    int size();
}
