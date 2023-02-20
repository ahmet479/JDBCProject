import java.sql.*;

public class ExecuteQuery01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
            //1. Adim: Driver'a kayd ol
            Class.forName("org.postgresql.Driver");

            //2. Adim: Database'e baglan
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/arcane", "postgres", "2213");

            //3.Adim: Statement olustur.
            Statement st = con.createStatement();

            //1. Orn: region id'si 1 olan "country name" degerlerini cagirin

            String sql1 = "select country_name from countries where region_id=1";
            boolean sonuc = st.execute(sql1);
        System.out.println("sonuc = " + sonuc);


        //Record/satirlari gormek icin ExecuteQuery() methodunu kullanmaliyiz
        ResultSet rs1 =st.executeQuery(sql1);
        System.out.println("rs1 = " + rs1);

        while (rs1.next()){
            rs1.getString(1);  // veya rs1.getString("country_name");
            System.out.println(rs1.getString(1));


        }
        //2. orn: "region_id'nin 2'den buyuk oldugu "country_id" ve "country_name" degerlerini cagirin
      String sql2 = "select country_id, country_name from countries where region_id>2;";

        ResultSet rs2 = st.executeQuery(sql2);
        System.out.println("......................");

        while (rs2.next()){

            System.out.println(rs2.getString("country_id")+"...." + rs2.getString("country_name"));
        }

        //3. orn: "number_of_employees" degeri en dusuk olan satirin tum degerlerini yazdirin.
        String sql3 = "SELECT * from companies where number_of_employees =10000";  //10000 alan rakam hardcoding onun yerine asagidakini yaz
        String sql3a = "SELECT * from companies where number_of_employees =(select min(number_of_employees) from companies)";
        ResultSet rs3 = st.executeQuery(sql3);
        while (rs3.next()){
            System.out.println(rs3.getString(1)+"__"+rs3.getString(2)+"--"+rs3.getString(3));

        }
        con.close();
        st.close();
        rs3.close();

    }
}