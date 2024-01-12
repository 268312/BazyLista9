import entity.ProductsEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import javax.swing.text.html.parser.Entity;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = null;
        em = emf.createEntityManager();

        //a) Wyświetl procent produktów, które dostarczają więcej niż 50% sumy żelaza i wapnia. Zwróć
        //wynik do dwóch miejsc po przecinku.
        var queryCountAllProducts = "((SELECT COUNT(p2) FROM ProductsEntity p2))";
        var queryWhereClause = "p.calcium + p.iron > 50";
        var queryStringA = String.format("SELECT ROUND(100.0 * COUNT(p) / %s, 2) FROM ProductsEntity p WHERE %s", queryCountAllProducts, queryWhereClause);
        Query queryA;
        queryA = em.createQuery(queryStringA);
        Double resultA = (Double) queryA.getSingleResult();
        System.out.println("a)" + resultA);

//        b) Wyświetl średnią wartość kaloryczną produktów z bekonem w nazwie
        System.out.println("");

        var queryStringB = "SELECT AVG(calories) AS calories_average FROM ProductsEntity WHERE itemName LIKE '%bacon%'";
        Query queryB = em.createQuery(queryStringB);
        Double resultB = (Double) queryB.getSingleResult();

        System.out.println("b)" + resultB);

//        c) Dla każdej z kategorii wyświetl produkt o największej wartości cholesterolu

//        var stringC = "SELECT p.category, MAX(p.cholesterole) FROM ProductsEntity p GROUP BY p.category";
//        Query queryC = em.createQuery(stringC);
//        List<Object[]> resultC = queryC.getResultList();
//        for (Object[] r : resultC) {
//            String tmp = (String) r[0];
//            String cat = tmp. ();
//            int chol = (int) r[1];
//
//            System.out.println(cat + ": " + chol);
//        }
        // ten podpunkt nie jest dokończony bo miałam problem

//        d) Wyświetl liczbę kaw (Mocha lub Coffee w nazwie) niezawierających błonnika.

        System.out.println("");

        var queryStringD = "SELECT COUNT(*)  FROM ProductsEntity p WHERE (p.itemName LIKE '%Mocha%' OR p.itemName LIKE '%Coffee%') AND p.fiber = 0";
        Query queryD = em.createQuery(queryStringD);
        Long resultD = (Long) queryD.getSingleResult();

        System.out.println("d)" + resultD);
//        f) Wyświetl liczbę różnych wartości węglowodanów

        System.out.println("");

        var queryStringF = "SELECT DISTINCT carbs FROM ProductsEntity";
        Query queryF = em.createQuery(queryStringF);
        List<Integer> resultListF = queryF.getResultList();
        System.out.println("c)");
        for ( Integer result : resultListF){
            System.out.println(result);
        }





    }


}
