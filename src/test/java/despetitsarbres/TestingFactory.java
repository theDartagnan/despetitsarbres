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
package despetitsarbres;

import java.util.List;

/**
 *
 * @author Rémi Venant
 */
public final class TestingFactory {

    public final static <V> Arbre<V> creerArbre(V valeur) {
        throw new UnsupportedOperationException("Not supported yet."); //TODO: instancier votre arbre avec la valeur de racine "valeur" ici.
    }

    public final static <V> Arbre<V> creerArbre(V valeur, List<Arbre<V>> foret) {
        throw new UnsupportedOperationException("Not supported yet."); //TODO: instancier votre arbre avec la valeur de racine "valeur" et la foret "foret" ici.
    }

    public final static ArbreBinaireRecherche<Integer> creerABR(Integer valeur) {
        throw new UnsupportedOperationException("Not supported yet."); //TODO: instancier votre ABR avec la valeur de racine "valeur" et la foret "foret" ici.
    }

    public final static ArbreBinaireRecherche<Integer> creerABR(Integer valeur,
            ArbreBinaireRecherche<Integer> sag, ArbreBinaireRecherche<Integer> sad) {
        throw new UnsupportedOperationException("Not supported yet."); //TODO: instancier votre ABR avec la valeur de racine "valeur" et son SAG et SAD ici.
    }

    private TestingFactory() {
    }
}
