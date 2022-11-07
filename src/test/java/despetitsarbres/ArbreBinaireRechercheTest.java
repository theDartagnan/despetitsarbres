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
import java.util.Iterator;
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
public class ArbreBinaireRechercheTest {

    public ArbreBinaireRechercheTest() {
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
    public void testConstruction() {
        ArbreBinaireRecherche<Integer> a = TestingFactory.creerABR(0);
        assertNotNull(a.getRacine(), "La racine de l'ABR ne devrait pas être nulle");
        assertNull(a.getSag(), "Le SAG de l'ABR devrait être nulle");
        assertNull(a.getSad(), "Le SAD de l'ABR devrait être nulle");
    }

    @Test
    public void testConstructionRacineNulle() {
        assertThrows(NullPointerException.class, () -> {
            ArbreBinaireRecherche<Integer> a = TestingFactory.creerABR(null);
        }, "Il ne devrait pas être possible de construire un ABR avec une racine nulle");
    }

    @Test
    public void testGetRacine() {
        Integer v = 5;
        Arbre arbre = TestingFactory.creerABR(v);
        assertEquals(v, arbre.getRacine(), "La racine n'a pas la bonne valeur");
    }

    @Test
    public void testSetRacine() {
        ArbreBinaireRecherche<Integer> a = TestingFactory.creerABR(0);
        Integer v2 = 2;
        assertThrows(UnsupportedOperationException.class, () -> {
            a.setRacine(v2);
        }, "Redéfinir la racine d'un ABR devrait être impossible");
    }

    @Test
    public void testGetForet() {
        ArbreBinaireRecherche<Integer> arbre = TestingFactory.creerABR(0);
        List<Arbre<Integer>> foret = arbre.getForet();
        assertNull(arbre.getSag(), "Le SAG devrait être nul");
        assertNull(arbre.getSad(), "Le SAD devrait être nul");
        assertNotNull(foret, "La forêt ne devrait pas être null");
        assertEquals(0, foret.size(), "l'arbre devrait avoir une foret de taille 0");
        assertTrue(arbre.isFeuille(), "L'arbre devrait être une feuille");
        arbre = TestingArbreFactory.generateArbreTestABR();
        foret = arbre.getForet();
        assertNotNull(arbre.getSag(), "Le SAG ne devrait pas être nul");
        assertNotNull(arbre.getSad(), "Le SAD ne devrait pas être nul");
        assertNotNull(foret, "La forêt ne devrait ne pas être null");
        assertEquals(2, foret.size(), "l'arbre devrait avoir une foret de taille 2");
        assertFalse(arbre.isFeuille(), "L'arbre ne devrait pas être une feuille");
    }

    @Test
    public void testUpdateForet() {
        ArbreBinaireRecherche<Integer> arbre = TestingFactory.creerABR(0);
        List<Arbre<Integer>> foret = arbre.getForet();
        assertThrows(UnsupportedOperationException.class, () -> {
            foret.add(TestingFactory.creerABR(3));
        }, "Modifier la forêt d'un ABR devrait être impossible");
        assertThrows(UnsupportedOperationException.class, () -> {
            foret.remove(0);
        }, "Modifier la forêt d'un ABR devrait être impossible");
    }

    /**
     * Test of isFeuille method, of class ArbreImpl.
     */
    @Test
    public void testIsFeuille() {
        ArbreBinaireRecherche<Integer> arbre = TestingFactory.creerABR(0);
        assertTrue(arbre.isFeuille(), "l'arbre devrait être une feuille");
        arbre = TestingFactory.creerABR(2, TestingFactory.creerABR(0), null);
        assertFalse(arbre.isFeuille(), "l'arbre ne devrait pas être une feuille");
    }

    @Test
    public void testGetNbNoeuds() {
        ArbreBinaireRecherche<Integer> arbre = TestingArbreFactory.generateArbreTestABR();
        int expectedNbFeuilles = TestingArbreFactory.getNbNoeudsArbreTestABR();
        assertEquals(expectedNbFeuilles, arbre.getNbNoeuds(), "Nombre de noeuds incorrect");
    }

    @Test
    public void testGetNbFeuilles() {
        ArbreBinaireRecherche<Integer> arbre = TestingArbreFactory.generateArbreTestABR();
        int expectedNbFeuilles = TestingArbreFactory.getNbFeuillesArbreTestABR();
        assertEquals(expectedNbFeuilles, arbre.getNbFeuilles(), "Nombre de feuilles incorrect");
    }

    @Test
    public void testGetHauteur() {
        ArbreBinaireRecherche<Integer> arbre = TestingArbreFactory.generateArbreTestABR();
        int expectedHauteurArbre = TestingArbreFactory.getHauteurArbreTestABR();
        assertEquals(expectedHauteurArbre, arbre.getHauteur(), "Hauteur incorrecte");
    }

    @Test
    public void testGetHauteurMoyenne() {
        ArbreBinaireRecherche<Integer> arbre = TestingArbreFactory.generateArbreTestABR();
        float expectedMean = TestingArbreFactory.getHauteurMoyenneArbreTestABR();
        float epsilon = 1e-7F;
        assertEquals(expectedMean, arbre.getHauteurMoyenne(), epsilon, "Hauteur moyenne invalide");
    }

    @Test
    public void testTraiterNoeudsParcoursPrefixe() {
        Arbre arbre = TestingArbreFactory.generateArbreTestABR();
        String expectedValue = TestingArbreFactory.parcoursPrefixeABR();
        final StringBuilder sb = new StringBuilder();
        arbre.traiterNoeudsParcoursPrefixe((v) -> sb.append(v).append(", "));
        final String computedValue = sb.substring(0, sb.length() - 2);
        assertEquals(expectedValue, computedValue, "Parcours invalide");
    }

    @Test
    public void testTraiterNoeudsParcoursSuffixe() {
        Arbre arbre = TestingArbreFactory.generateArbreTestABR();
        String expectedValue = TestingArbreFactory.parcoursSuffixeABR();
        final StringBuilder sb = new StringBuilder();
        arbre.traiterNoeudsParcoursSuffixe((v) -> sb.append(v).append(", "));
        final String computedValue = sb.substring(0, sb.length() - 2);
        assertEquals(expectedValue, computedValue, "Parcours invalide");
    }

    @Test
    public void testTraiterNoeudsParcoursLargeur() {
        Arbre arbre = TestingArbreFactory.generateArbreTestABR();
        String expectedValue = TestingArbreFactory.parcoursLargeurABR();
        final StringBuilder sb = new StringBuilder();
        arbre.traiterNoeudsParcoursLargeur((v) -> sb.append(v).append(", "));
        final String computedValue = sb.substring(0, sb.length() - 2);
        assertEquals(expectedValue, computedValue, "Parcours invalide");
    }

    @Test
    public void testIterateurValeurParcoursPrefixe() {
        Arbre arbre = TestingArbreFactory.generateArbreTestABR();
        String expectedValue = TestingArbreFactory.parcoursPrefixeABR();
        StringBuilder sb = new StringBuilder();
        Iterator<Object> it = arbre.iterateurParcoursPrefixe();
        while (it.hasNext()) {
            sb.append(it.next().toString()).append(", ");
        }
        String computedValue = sb.length() > 0 ? sb.substring(0, sb.length() - 2) : sb.toString();
        assertEquals(expectedValue, computedValue, "Parcours invalide");
    }

    @Test
    public void testIterateurValeurParcoursSuffixe() {
        Arbre arbre = TestingArbreFactory.generateArbreTestABR();
        String expectedValue = TestingArbreFactory.parcoursSuffixeABR();
        StringBuilder sb = new StringBuilder();
        Iterator<Object> it = arbre.iterateurParcoursSuffixe();
        while (it.hasNext()) {
            sb.append(it.next().toString()).append(", ");
        }
        String computedValue = sb.length() > 0 ? sb.substring(0, sb.length() - 2) : sb.toString();
        assertEquals(expectedValue, computedValue, "Parcours invalide");
    }

    @Test
    public void testIterateurValeurParcoursLargeur() {
        Arbre arbre = TestingArbreFactory.generateArbreTestABR();
        String expectedValue = TestingArbreFactory.parcoursLargeurABR();
        StringBuilder sb = new StringBuilder();
        Iterator<Object> it = arbre.iterateurParcoursLargeur();
        while (it.hasNext()) {
            sb.append(it.next().toString()).append(", ");
        }
        String computedValue = sb.length() > 0 ? sb.substring(0, sb.length() - 2) : sb.toString();
        assertEquals(expectedValue, computedValue, "Parcours invalide");
    }

    @Test
    public void testIterator() {
        Arbre arbre = TestingArbreFactory.generateArbreTestABR();
        String expectedValue = TestingArbreFactory.parcoursPrefixeABR();
        StringBuilder sb = new StringBuilder();
        for (Object value : arbre) {
            sb.append(value.toString()).append(", ");
        }
        String computedValue = sb.length() > 0 ? sb.substring(0, sb.length() - 2) : sb.toString();
        assertEquals(expectedValue, computedValue, "Parcours invalide");
    }

    @Test
    public void testRechercherSousArbre() {
        ArbreBinaireRecherche<Integer> arbre = TestingArbreFactory.generateArbreTestABR();
        ArbreBinaireRecherche<Integer> ssArbre = arbre.rechercherSousArbre(45);
        assertNotNull(ssArbre, "la recherche devrait être fructueuse");
        ssArbre = arbre.rechercherSousArbre(21);
        assertNotNull(ssArbre, "la recherche devrait être fructueuse");
        ssArbre = arbre.rechercherSousArbre(28);
        assertNull(ssArbre, "la recherche ne devrait pas être fructueuse");
    }

    @Test
    public void testContains() {
        ArbreBinaireRecherche<Integer> arbre = TestingArbreFactory.generateArbreTestABR();
        assertTrue(arbre.contains(45), "45 devrait être trouvé");
        assertTrue(arbre.contains(21), "21 devrait être trouvé");
        assertFalse(arbre.contains(28), "28 ne devrait pas être trouvé");
    }

    @Test
    public void testGetMin() {
        ArbreBinaireRecherche<Integer> arbre = TestingArbreFactory.generateArbreTestABR();
        assertEquals(TestingArbreFactory.getABRMin(), arbre.getMin());
    }

    @Test
    public void testGetMax() {
        ArbreBinaireRecherche<Integer> arbre = TestingArbreFactory.generateArbreTestABR();
        assertEquals(TestingArbreFactory.getABRMax(), arbre.getMax());
    }

    @Test
    public void testerArbreAjout() throws InsertionImpossibleException {
        final ArbreBinaireRecherche<Integer> abr = TestingFactory.creerABR(2);
        abr.ajouter(3).ajouter(4).ajouter(5).ajouter(1);
        assertEquals(2, abr.getRacine(), "Valeur de racine incohérente");
        assertNotNull(abr.getSag(), "root->sag ne devrait pas être null");
        assertNotNull(abr.getSad(), "root->sad ne devrait pas être null");
        assertEquals(1, abr.getSag().getRacine(), "Valeur incohérente");
        assertEquals(3, abr.getSad().getRacine(), "Valeur incohérente");
        assertTrue(abr.getSag().getSag() == null, "root->sag->sag devrait être null");
        assertTrue(abr.getSag().getSad() == null, "root->sag->sad devrait être null");

        assertTrue(abr.getSad().getSag() == null, "root->sad->sag devrait être null");
        assertNotNull(abr.getSad().getSad(), "root->sag->sad ne devrait pas être null");
        assertEquals(4, abr.getSad().getSad().getRacine(), "Valeur incohérente");

        assertTrue(abr.getSad().getSad().getSag() == null, "root->sad->sad->sag devrait être null");
        assertNotNull(abr.getSad().getSad().getSad(), "root->sag->sad->sad ne devrait pas être null");
        assertEquals(5, abr.getSad().getSad().getSad().getRacine(), "Valeur incohérente");
    }

    @Test
    public void testerArbreSupprimerRacineImpossible() {
        final ArbreBinaireRecherche<Integer> abr = TestingFactory.creerABR(2);
        assertThrows(SuppressionImpossibleException.class, () -> {
            abr.retirer(2);
        }, "Retirer la valeur d'un ABR ne contenant que cette valeur devrait être impossible");
    }

    @Test
    public void testerArbreSupprimerRacineSag() throws InsertionImpossibleException, SuppressionImpossibleException, ValeurNonTrouveeException {
        final ArbreBinaireRecherche<Integer> abr = TestingFactory.creerABR(3)
                .ajouter(2).ajouter(1);
        abr.retirer(3);
        assertEquals(2, abr.getRacine(), "La racine devrait être 2");
        assertNotNull(abr.getSag(), "root->sag ne devrait pas être null");
        assertTrue(abr.getSad() == null, "root->sad devrait être null");
        assertTrue(abr.getSag().getSag() == null, "root->sag->sag devrait être null");
        assertTrue(abr.getSag().getSad() == null, "root->sag->sad devrait être null");
        assertEquals(1, abr.getSag().getRacine(), "root->sag devrait être 1");
    }

    @Test
    public void testerArbreSupprimerRacineSad() throws InsertionImpossibleException, SuppressionImpossibleException, ValeurNonTrouveeException {
        final ArbreBinaireRecherche<Integer> abr = TestingFactory.creerABR(1)
                .ajouter(2).ajouter(3);
        abr.retirer(1);
        assertEquals(2, abr.getRacine(), "La racine devrait être 2");
        assertNotNull(abr.getSad(), "root->sad ne devrait pas être null");
        assertTrue(abr.getSag() == null, "root->sag devrait être null");
        assertTrue(abr.getSad().getSag() == null, "root->sad->sag devrait être null");
        assertTrue(abr.getSad().getSad() == null, "root->sad->sad devrait être null");
        assertEquals(3, abr.getSad().getRacine(), "root->sad devrait être 1");
    }

    @Test
    public void testerArbreSupprimerRacineSagSag() throws InsertionImpossibleException, SuppressionImpossibleException, ValeurNonTrouveeException {
        final ArbreBinaireRecherche<Integer> abr = TestingFactory.creerABR(4)
                .ajouter(2).ajouter(6).ajouter(1).ajouter(3).ajouter(5).ajouter(7);
        abr.retirer(4);
        assertEquals(3, abr.getRacine(), "La racine devrait être 3");
        final ArbreBinaireRecherche<Integer> rSag = abr.getSag();
        final ArbreBinaireRecherche<Integer> rSad = abr.getSad();
        assertNotNull(rSag, "root->sag ne devrait pas être null");
        assertEquals(2, rSag.getRacine(), "root->sag devrait être égal à 2");
        assertNotNull(rSad, "root->sad ne devrait pas être null");
        assertEquals(6, rSad.getRacine(), "root->sad devrait être égal à 6");

        final ArbreBinaireRecherche<Integer> rSagSag = rSag.getSag();
        final ArbreBinaireRecherche<Integer> rSagSad = rSag.getSad();
        assertNotNull(rSagSag, "root->sag->sag ne devrait pas être null");
        assertEquals(1, rSagSag.getRacine(), "root->sag->sad devrait être égal à 1");
        assertTrue(rSagSad == null, "root->sag->sad devrait être null");

        final ArbreBinaireRecherche<Integer> rSadSag = rSad.getSag();
        final ArbreBinaireRecherche<Integer> rSadSad = rSad.getSad();
        assertNotNull(rSadSag, "root->sad->sag ne devrait pas être null");
        assertEquals(5, rSadSag.getRacine(), "root->sad->sag devrait être égal à 5");
        assertNotNull(rSadSad, "root->sad->sad ne devrait pas être null");
        assertEquals(7, rSadSad.getRacine(), "root->sad->sad devrait être égal à 7");
    }

    @Test
    public void testerArbreSupprimerSagFeuille() throws InsertionImpossibleException, SuppressionImpossibleException, ValeurNonTrouveeException {
        final ArbreBinaireRecherche<Integer> abr
                = TestingFactory.creerABR(4).ajouter(2).ajouter(6)
                        .ajouter(1).ajouter(3).ajouter(5).ajouter(7);
        abr.retirer(1);
        assertEquals(4, abr.getRacine(), "La racine devrait être 4");
        final ArbreBinaireRecherche<Integer> rSag = abr.getSag();
        final ArbreBinaireRecherche<Integer> rSad = abr.getSad();
        assertNotNull(rSag, "root->sag ne devrait pas être null");
        assertEquals(2, rSag.getRacine(), "root->sag devrait être égal à 2");
        assertNotNull(rSad, "root->sad ne devrait pas être null");
        assertEquals(6, rSad.getRacine(), "root->sad devrait être égal à 6");

        final ArbreBinaireRecherche<Integer> rSagSag = rSag.getSag();
        final ArbreBinaireRecherche<Integer> rSagSad = rSag.getSad();
        assertTrue(rSagSag == null, "root->sag->sag devrait être null");
        assertNotNull(rSagSad, "root->sag->sad ne devrait pas être null");
        assertEquals(3, rSagSad.getRacine(), "root->sag->sad devrait être égal à 3");

        final ArbreBinaireRecherche<Integer> rSadSag = rSad.getSag();
        final ArbreBinaireRecherche<Integer> rSadSad = rSad.getSad();
        assertNotNull(rSadSag, "root->sad->sag ne devrait pas être null");
        assertEquals(5, rSadSag.getRacine(), "root->sad->sag devrait être égal à 5");
        assertNotNull(rSadSad, "root->sad->sad ne devrait pas être null");
        assertEquals(7, rSadSad.getRacine(), "root->sad->sad devrait être égal à 7");
    }

    @Test
    public void testerArbreSupprimerSag() throws InsertionImpossibleException, SuppressionImpossibleException, ValeurNonTrouveeException {
        final ArbreBinaireRecherche<Integer> abr
                = TestingFactory.creerABR(4).ajouter(2).ajouter(6)
                        .ajouter(1).ajouter(3).ajouter(5).ajouter(7);
        abr.retirer(2);
        assertEquals(4, abr.getRacine(), "La racine devrait être 4");
        final ArbreBinaireRecherche<Integer> rSag = abr.getSag();
        final ArbreBinaireRecherche<Integer> rSad = abr.getSad();
        assertNotNull(rSag, "root->sag ne devrait pas être null");
        assertEquals(1, rSag.getRacine(), "root->sag devrait être égal à 1");
        assertNotNull(rSad, "root->sad ne devrait pas être null");
        assertEquals(6, rSad.getRacine(), "root->sad devrait être égal à 6");

        final ArbreBinaireRecherche<Integer> rSagSag = rSag.getSag();
        final ArbreBinaireRecherche<Integer> rSagSad = rSag.getSad();
        assertTrue(rSagSag == null, "root->sag->sag devrait être null");
        assertNotNull(rSagSad, "root->sag->sad ne devrait pas être null");
        assertEquals(3, rSagSad.getRacine(), "root->sag->sad devrait être égal à 3");

        final ArbreBinaireRecherche<Integer> rSadSag = rSad.getSag();
        final ArbreBinaireRecherche<Integer> rSadSad = rSad.getSad();
        assertNotNull(rSadSag, "root->sad->sag ne devrait pas être null");
        assertEquals(5, rSadSag.getRacine(), "root->sad->sag devrait être égal à 5");
        assertNotNull(rSadSad, "root->sad->sad ne devrait pas être null");
        assertEquals(7, rSadSad.getRacine(), "root->sad->sad devrait être égal à 7");
    }

    @Test
    public void testerArbreSupprimerSadFeuille() throws InsertionImpossibleException, SuppressionImpossibleException, ValeurNonTrouveeException {
        final ArbreBinaireRecherche<Integer> abr = TestingFactory.creerABR(4)
                .ajouter(2).ajouter(6)
                .ajouter(1).ajouter(3).ajouter(5).ajouter(7);
        abr.retirer(7);
        assertEquals(4, abr.getRacine(), "La racine devrait être 4");
        final ArbreBinaireRecherche<Integer> rSag = abr.getSag();
        final ArbreBinaireRecherche<Integer> rSad = abr.getSad();
        assertNotNull(rSag, "root->sag ne devrait pas être null");
        assertEquals(2, rSag.getRacine(), "root->sag devrait être égal à 2");
        assertNotNull(rSad, "root->sad ne devrait pas être null");
        assertEquals(6, rSad.getRacine(), "root->sad devrait être égal à 6");

        final ArbreBinaireRecherche<Integer> rSagSag = rSag.getSag();
        final ArbreBinaireRecherche<Integer> rSagSad = rSag.getSad();
        assertNotNull(rSagSag, "root->sag->sag ne devrait pas être null");
        assertEquals(1, rSagSag.getRacine(), "root->sag->sag devrait être égal à 1");
        assertNotNull(rSagSad, "root->sag->sad ne devrait pas être null");
        assertEquals(3, rSagSad.getRacine(), "root->sag->sad devrait être égal à 3");

        final ArbreBinaireRecherche<Integer> rSadSag = rSad.getSag();
        final ArbreBinaireRecherche<Integer> rSadSad = rSad.getSad();
        assertNotNull(rSadSag, "root->sad->sag ne devrait pas être null");
        assertEquals(5, rSadSag.getRacine(), "root->sad->sag devrait être égal à 5");
        assertTrue(rSadSad == null, "root->sad->sad devrait être null");
    }

    @Test
    public void testerArbreSupprimerSad() throws InsertionImpossibleException, SuppressionImpossibleException, ValeurNonTrouveeException {
        final ArbreBinaireRecherche<Integer> abr
                = TestingFactory.creerABR(4).ajouter(2).ajouter(6)
                        .ajouter(1).ajouter(3).ajouter(5).ajouter(7);
        abr.retirer(6);
        assertEquals(4, abr.getRacine(), "La racine devrait être 4");
        final ArbreBinaireRecherche<Integer> rSag = abr.getSag();
        final ArbreBinaireRecherche<Integer> rSad = abr.getSad();
        assertNotNull(rSag, "root->sag ne devrait pas être null");
        assertEquals(2, rSag.getRacine(), "root->sag devrait être égal à 2");
        assertNotNull(rSad, "root->sad ne devrait pas être null");
        assertEquals(5, rSad.getRacine(), "root->sad devrait être égal à 5");

        final ArbreBinaireRecherche<Integer> rSagSag = rSag.getSag();
        final ArbreBinaireRecherche<Integer> rSagSad = rSag.getSad();
        assertNotNull(rSagSag, "root->sag->sag ne devrait pas être null");
        assertEquals(1, rSagSag.getRacine(), "root->sag->sag devrait être égal à 1");
        assertNotNull(rSagSad, "root->sag->sad ne devrait pas être null");
        assertEquals(3, rSagSad.getRacine(), "root->sag->sad devrait être égal à 3");

        final ArbreBinaireRecherche<Integer> rSadSag = rSad.getSag();
        final ArbreBinaireRecherche<Integer> rSadSad = rSad.getSad();
        assertTrue(rSadSag == null, "root->sad->sag devrait être null");
        assertNotNull(rSadSad, "root->sad->sad ne devrait pas être null");
        assertEquals(7, rSadSad.getRacine(), "root->sad->sad devrait être égal à 7");
    }
}
