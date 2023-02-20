import java.sql.*;

public class ExecuteUpdate01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/arcane", "postgres", "2213");
        Statement st = con.createStatement();

        //1.ornek: number_of_employees degeri ortalama calisan sayisindan az olan number_of_employees degerlerini 16000 olarak UPDATE edin

        String sql1 = "Update companies Set number_of_employees =16000 Where number_of_employees < (Select Avg(number_of_employees) From companies)";

        int updateEdilenSatirSayisi = st.executeUpdate(sql1);
        System.out.println("updateEdilenSatirSayisi = " + updateEdilenSatirSayisi);

        String sql1a = "Select * from companies";
        ResultSet resultSet1 = st.executeQuery(sql1a);

        while (resultSet1.next()){
            System.out.println(resultSet1.getString("company_id")+"---"+resultSet1.getString("company")+"---"+ resultSet1.getString("number_of_employees"));

        }
        con.close();
        st.close();
        resultSet1.close();

    }
}
