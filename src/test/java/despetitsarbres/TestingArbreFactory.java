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

/**
 *
 * @author Rémi Venant
 */
public final class TestingArbreFactory {

    public static Arbre<String> generateArbreTest1() {
        /*
                   a
              b        f   h
           c    d      g
                e
         */
        final Arbre<String> a = TestingFactory.creerArbre("a");
        final Arbre<String> b = TestingFactory.creerArbre("b");
        final Arbre<String> c = TestingFactory.creerArbre("c");
        final Arbre<String> d = TestingFactory.creerArbre("d");
        final Arbre<String> e = TestingFactory.creerArbre("e");
        final Arbre<String> f = TestingFactory.creerArbre("f");
        a.getForet().add(0, b);
        a.getForet().add(1, f);
        a.getForet().add(2, TestingFactory.creerArbre("h"));
        b.getForet().add(0, c);
        b.getForet().add(1, d);
        d.getForet().add(0, e);
        f.getForet().add(0, TestingFactory.creerArbre("g"));
        return a;
    }

    public static int getHauteurArbreTest1() {
        return 3;
    }

    public static float getHauteurMoyenneArbreTest1() {
        return (2F + 3F + 2F + 1F) / 4F;
    }

    public static int getNbNoeudsArbreTest1() {
        return 8;
    }

    public static int getNbFeuillesArbreTest1() {
        return 4;
    }

    public static String parcoursPrefixe() {
        return "a, b, c, d, e, f, g, h";
    }

    public static String parcoursSuffixe() {
        return "c, e, d, b, g, f, h, a";
    }

    public static String parcoursLargeur() {
        return "a, b, f, h, c, d, g, e";
    }

    public static ArbreBinaireRecherche<Integer> generateArbreTestABR() {
        /*
                30
           20         40
         18   22     35  45
          19 21 23  33  44  46
                 27    43     47
         */
        return TestingFactory.creerABR(30,
                TestingFactory.creerABR(20,
                        TestingFactory.creerABR(18, null, TestingFactory.creerABR(19)),
                        TestingFactory.creerABR(22,
                                TestingFactory.creerABR(21),
                                TestingFactory.creerABR(23, null, TestingFactory.creerABR(27))
                        )
                ),
                TestingFactory.creerABR(40,
                        TestingFactory.creerABR(35, TestingFactory.creerABR(33), null),
                        TestingFactory.creerABR(45,
                                TestingFactory.creerABR(44, TestingFactory.creerABR(43), null),
                                TestingFactory.creerABR(46, null, TestingFactory.creerABR(47))
                        )
                )
        );
    }

    public static int getHauteurArbreTestABR() {
        return 4;
    }

    public static float getHauteurMoyenneArbreTestABR() {
        return (3F + 3F + 4F + 3F + 4F + 4F) / 6F;
    }

    public static int getNbNoeudsArbreTestABR() {
        return 16;
    }

    public static int getNbFeuillesArbreTestABR() {
        return 6;
    }

    public static String parcoursPrefixeABR() {
        return "30, 20, 18, 19, 22, 21, 23, 27, 40, 35, 33, 45, 44, 43, 46, 47";
    }

    public static String parcoursSuffixeABR() {
        return "19, 18, 21, 27, 23, 22, 20, 33, 35, 43, 44, 47, 46, 45, 40, 30";
    }

    public static String parcoursLargeurABR() {
        return "30, 20, 40, 18, 22, 35, 45, 19, 21, 23, 33, 44, 46, 27, 43, 47";
    }

    public static Integer getABRMin() {
        return 18;
    }

    public static Integer getABRMax() {
        return 47;
    }

    private TestingArbreFactory() {

    }
}
