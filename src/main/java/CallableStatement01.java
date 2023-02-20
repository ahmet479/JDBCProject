import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.*;

public class CallableStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        /*
        Java'da methodlar return type sahibi olsa da olmasa da method olarak adlandirilir.
        SQL'de ise returrn ediliyor "function" denir. Return yapmiyorsa "procedure" olarak adlandirilir.
         */

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/arcane", "postgres", "2213");
        Statement st = con.createStatement();

      //CallableStatement ile function cagirmayi parametrelendiririz.
      //1.Adim:Functioninin kodunu yaz
        String sql1 = "Create or Replace Function toplamaF(x Numeric, y Numeric)Returns Numeric language plpgsql As $$ Begin Return x+y; End $$";

        //Create or Replace Function==> functioni olustur veya var ise degistir
        //plpgsql ==> producure language postgre sql
       //2.Adim:  Fonction'i calistir.
        st.execute(sql1);

        //3.Adim: Fonction'i cagir.
        CallableStatement cs1=con.prepareCall("{? = call toplamaF (?, ?)}"); // ilk parametre  (?) return type icindir.

        //4.Adim: Return icin registerOurParameter(), parametrelere icin set()........methodlarimizi uygulariz.
        cs1.registerOutParameter(1, Types.NUMERIC);

        cs1.setInt(2,6);
        cs1.setInt(3,8);

       //5.Adim: execute() ile CallableStatement'i calistir.
        cs1.execute();

        //6.Adim: Sonucu cagirmak icin return data type bakilir.
        BigDecimal toplam = cs1.getBigDecimal(1);
        System.out.println("toplam = " + toplam);

//2.Ornek: Koninin hacmini hesaplayan bir function yazin // konu hacmi 3.14*r*r*h/3
        //1.Adim: Function'inin kodunu yaz
        String sql2 = "Create or Replace Function koniHacmiF(r Numeric, h Numeric) Returns Numeric language plpgsql As $$ Begin Return 3.14*r*r*h/3; End $$";
        //2.Adim: Function'i calistir
        st.execute(sql2);
        //3.Adim: Function'i cagir
        CallableStatement cs2 = con.prepareCall("{? = call koniHacmiF (?, ?)}");
        //4.Adim: Return icin registerOurParameter(), parametrelere icin set() ...... methodlarimizi uygulariz
        cs2.registerOutParameter(1, Types.NUMERIC);

        cs2.setInt(2, 4);
        cs2.setInt(3, 10);
        //5.Adim: execute() ile CallableStatement'i calistir
        cs2.execute();
        //6.Adim: Sonucu cagirmak icin return data type bakilir
        BigDecimal koniHacmi = cs2.getBigDecimal(1);
        System.out.println("koniHacmi = " + koniHacmi);
        System.out.printf("%.4f", koniHacmi);
    }
}
