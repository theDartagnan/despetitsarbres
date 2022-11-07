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
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Rémi Venant
 */
public class ArbreTest {

    public ArbreTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testGetRacine() {
        String v = "value";
        Arbre arbre = TestingFactory.creerArbre(v);
        assertEquals(v, arbre.getRacine(), "Racine incorrect");
    }

    @Test
    public void testGetForet() {
        Arbre<String> arbre = TestingFactory.creerArbre("value");
        assertNotNull(arbre.getForet(), "La forêt ne devrait pas être nulle");
        List<Arbre<String>> f1 = new LinkedList<>();
        arbre = TestingFactory.creerArbre("valeur", f1);
        assertSame(f1, arbre.getForet(), "Forêt incorrecte");
    }

    @Test
    public void testIsFeuille() {
        Arbre<String> arbre = TestingFactory.creerArbre("value");
        assertTrue(arbre.isFeuille(), "l'arbre devrait être une feuille");
        arbre.getForet().add(TestingFactory.creerArbre("value2"));
        assertFalse(arbre.isFeuille(), "l'arbre ne devrait pas être une feuille");
    }

    @Test
    public void testGetNbNoeuds() {
        Arbre<String> arbre = TestingArbreFactory.generateArbreTest1();
        Integer expectedNbFeuilles = TestingArbreFactory.getNbNoeudsArbreTest1();
        assertEquals(expectedNbFeuilles, arbre.getNbNoeuds(), "Nombre de noeuds incorrect");
    }

    @Test
    public void testGetNbFeuilles() {
        Arbre arbre = TestingArbreFactory.generateArbreTest1();
        Integer expectedNbFeuilles = TestingArbreFactory.getNbFeuillesArbreTest1();
        assertEquals(expectedNbFeuilles, arbre.getNbFeuilles(), "Nombre de feuilles incorrect");
    }

    @Test
    public void testGetHauteur() {
        Arbre arbre = TestingArbreFactory.generateArbreTest1();
        int expectedHauteurArbre = TestingArbreFactory.getHauteurArbreTest1();
        assertEquals(expectedHauteurArbre, arbre.getHauteur(), "Hauteur incorrecte");
    }

    @Test
    public void testGetHauteurMoyenne() {
        Arbre arbre = TestingArbreFactory.generateArbreTest1();
        float expectedMean = TestingArbreFactory.getHauteurMoyenneArbreTest1();
        float epsilon = 1e-7F;
        assertEquals(expectedMean, arbre.getHauteurMoyenne(), epsilon, "Hauteur moyenne invalide");
    }

    @Test
    public void testTraiterNoeudsParcoursPrefixe() {
        Arbre<String> arbre = TestingArbreFactory.generateArbreTest1();
        String expectedValue = TestingArbreFactory.parcoursPrefixe();
        final StringBuilder sb = new StringBuilder();
        arbre.traiterNoeudsParcoursPrefixe((v) -> sb.append(v).append(", "));
        final String computedValue = sb.substring(0, sb.length() - 2);
        assertEquals(expectedValue, computedValue, "Parcours invalide");
    }

    @Test
    public void testTraiterNoeudsParcoursSuffixe() {
        Arbre<String> arbre = TestingArbreFactory.generateArbreTest1();
        String expectedValue = TestingArbreFactory.parcoursSuffixe();
        final StringBuilder sb = new StringBuilder();
        arbre.traiterNoeudsParcoursSuffixe((v) -> sb.append(v).append(", "));
        final String computedValue = sb.substring(0, sb.length() - 2);
        assertEquals(expectedValue, computedValue, "Parcours invalide");
    }

    @Test
    public void testTraiterNoeudsParcoursLargeur() {
        Arbre<String> arbre = TestingArbreFactory.generateArbreTest1();
        String expectedValue = TestingArbreFactory.parcoursLargeur();
        final StringBuilder sb = new StringBuilder();
        arbre.traiterNoeudsParcoursLargeur((v) -> sb.append(v).append(", "));
        final String computedValue = sb.substring(0, sb.length() - 2);
        assertEquals(expectedValue, computedValue, "Parcours invalide");
    }

    @Test
    public void testIterateurParcoursPrefixe() {
        Arbre<String> arbre = TestingArbreFactory.generateArbreTest1();
        String expectedValue = TestingArbreFactory.parcoursPrefixe();
        final StringBuilder sb = new StringBuilder();
        Iterator<String> it = arbre.iterateurParcoursPrefixe();
        while (it.hasNext()) {
            sb.append(it.next()).append(", ");
        }
        final String computedValue = sb.substring(0, sb.length() - 2);
        assertEquals(expectedValue, computedValue, "Parcours invalide");
    }

    @Test
    public void testIterateurParcoursSuffixe() {
        Arbre<String> arbre = TestingArbreFactory.generateArbreTest1();
        String expectedValue = TestingArbreFactory.parcoursSuffixe();
        final StringBuilder sb = new StringBuilder();
        Iterator<String> it = arbre.iterateurParcoursSuffixe();
        while (it.hasNext()) {
            sb.append(it.next()).append(", ");
        }
        final String computedValue = sb.substring(0, sb.length() - 2);
        assertEquals(expectedValue, computedValue, "Parcours invalide");
    }

    @Test
    public void testIterateurParcoursParcoursLargeur() {
        Arbre<String> arbre = TestingArbreFactory.generateArbreTest1();
        String expectedValue = TestingArbreFactory.parcoursLargeur();
        final StringBuilder sb = new StringBuilder();
        Iterator<String> it = arbre.iterateurParcoursLargeur();
        while (it.hasNext()) {
            sb.append(it.next()).append(", ");
        }
        final String computedValue = sb.substring(0, sb.length() - 2);
        assertEquals(expectedValue, computedValue, "Parcours invalide");
    }

    @Test
    public void testIterateurParDefaut() {
        Arbre<String> arbre = TestingArbreFactory.generateArbreTest1();
        String expectedValue = TestingArbreFactory.parcoursPrefixe();
        final StringBuilder sb = new StringBuilder();
        for (String valeur : arbre) {
            sb.append(valeur).append(", ");
        }
        final String computedValue = sb.substring(0, sb.length() - 2);
        assertEquals(expectedValue, computedValue, "Parcours invalide");
    }
}
