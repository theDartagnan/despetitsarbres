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

import despetitsarbres.exceptions.InsertionImpossibleException;
import despetitsarbres.exceptions.SuppressionImpossibleException;
import despetitsarbres.exceptions.ValeurNonTrouveeException;

/**
 *
 * @author Rémi Venant
 * @param <K> Type des valeurs des noeud de l'ABR (les valeurs doivent être comparable entre elles).
 */
public interface ArbreBinaireRecherche<K extends Comparable<K>> extends Arbre<K> {

    /**
     * Fournit le sous-arbre gauche.
     *
     * @return le sag de l'ABR (null si l'ABR n'a pas de sag)
     */
    ArbreBinaireRecherche<K> getSag();

    /**
     * Fournit le sous-arbre droit.
     *
     * @return le sad de l'ABR (null si l'ABR n'a pas de sag)
     */
    ArbreBinaireRecherche<K> getSad();

    /**
     * Recherche l'arbre (arbre courant ou sous-arbre) dont la racine est de valeur cle.
     *
     * @param cle la clé recherchée
     * @return l'arbre dont la racine a pour valeur clé : null si aucun arbre correspond, ou l'arbre
     * ou un sous-arbre
     */
    ArbreBinaireRecherche<K> rechercherSousArbre(K cle);

    /**
     * Indique si l'arbre contient la cle.
     *
     * @param cle la clé à rechercher
     * @return vrai si la clé existe, faut sinon
     */
    boolean contains(K cle);

    /**
     * Fournit la cle de valeur minimale de l'arbre.
     *
     * @return la clé de plus petite valeur contenue dans l'arbre
     */
    K getMin();

    /**
     * Fournit la cle de valeur maximale de l'arbre.
     *
     * @return la clé de plus grande valeur contenue dans l'arbre
     */
    K getMax();

    /**
     * Ajoute une clé dans l'arbre, si celle-ci n'est pas nulle ou n'est déjà présente.
     *
     * @param cle la clé à ajouter
     * @return l'arbre mis à jour
     * @throws InsertionImpossibleException si la clé est nulle
     */
    ArbreBinaireRecherche<K> ajouter(K cle) throws InsertionImpossibleException;

    /**
     * Retire une clé de l'arbre.
     *
     * @param cle la clé à retirer
     * @return l'arbre mis à jour
     * @throws ValeurNonTrouveeException si la clé n'existe pas
     * @throws SuppressionImpossibleException si l'arbre est une feuille de valeur clé ou si la clé
     * est nulle
     */
    ArbreBinaireRecherche<K> retirer(K cle) throws ValeurNonTrouveeException,
            SuppressionImpossibleException;
}
