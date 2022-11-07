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

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author Rémi Venant
 * @param <V> type de valeurs stockées dans l'arbre.
 */
public interface Arbre<V> extends Iterable<V> {

    /**
     * Fournit la valeur de la racine de l'arbre.
     *
     * @return la racine
     */
    V getRacine();

    /**
     * Définit la valeur de la racine de l'arbe.
     *
     * @param valeur la valeur
     */
    void setRacine(V valeur);

    /**
     * Fournit les sous-arbres.
     *
     * @return la forêt
     */
    List<Arbre<V>> getForet();

    /**
     * Indique si l'arbre est une feuille.
     *
     * @return vrai si la foret du noeud est vide, faux sinon
     */
    boolean isFeuille();

    /**
     * Fournit le nombre de noeuds de l'arbre.
     *
     * @return le nombre de noeuds
     */
    int getNbNoeuds();

    /**
     * Fournit le nombre de feuilles de l'arbre.
     *
     * @return le nombre de feuilles
     */
    int getNbFeuilles();

    /**
     * Calcul la hauteur de l'arbre. La hauteur est la longueur du chemin de plus long entre la
     * racine et les feuilles de l'arbre.
     *
     * @return la hauteur de l'arbre
     */
    int getHauteur();

    /**
     * Calcul la hauteur moyenne de l'arbre. La hauteur moyenne est la moyenne des longueurs des
     * chemins de l'arbre entre sa racine est ses feuilles.
     *
     * @return la hateur moyenne
     */
    float getHauteurMoyenne();

    /**
     * Parcours l'arbre en profondeur préfixe et applique le traitement fourni sur chaque valeur de
     * noeuds.
     *
     * @param traitementNoeud le traitement à appliquer à chaque noeud
     */
    void traiterNoeudsParcoursPrefixe(Consumer<V> traitementNoeud);

    /**
     * Parcours l'arbre en profondeur suffixe et applique le traitement fourni sur chaque valeur de
     * noeuds.
     *
     * @param traitementNoeud le traitement à appliquer à chaque noeud
     */
    void traiterNoeudsParcoursSuffixe(Consumer<V> traitementNoeud);

    /**
     * Parcours l'arbre en largeur et applique le traitement fourni sur chaque valeur de noeuds.
     *
     * @param traitementNoeud le traitement à appliquer à chaque noeud
     */
    void traiterNoeudsParcoursLargeur(Consumer<V> traitementNoeud);

    /**
     * Génère un intérateur des valeurs des noeuds via un parcour préfixe.
     *
     * @return l'iterateur
     */
    Iterator<V> iterateurParcoursPrefixe();

    /**
     * Génère un intérateur des valeurs des noeuds via un parcour suffixe.
     *
     * @return l'iterateur
     */
    Iterator<V> iterateurParcoursSuffixe();

    /**
     * Génère un intérateur des valeurs des noeuds via un parcour en largeur.
     *
     * @return l'iterateur
     */
    Iterator<V> iterateurParcoursLargeur();
}
