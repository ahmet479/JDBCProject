import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/arcane", "postgres", "2213");
        Statement st = con.createStatement();
/*
PreparedStatement bir tane interface'tir. Birden cok kez calistirilabilen onceden derlenmis bir SQL kodunu temsil eder.
PreparedStatement, Parametrelendirilmis SQL sorgulari(query) ile calisir. Bu sorguyu 0 veya daha fazla parametre ile kullanabiliriz
 */
  //1. Orn: Prepared Statement kullanarak company adi IBM olan number_of_employees degerini 9999 olarak guncelleyin

   //1. Adim: PreparedStatement Query olustur.(paramatre yerine ? yazdik)
        String sql1 = "Update companies Set number_of_employees =? Where company =?";

    //2.Adim: PreparedStament object olustur
   PreparedStatement ps1 = con.prepareStatement(sql1);
    //3.Adim: setInt(), setString() .....methodlarini kullanarak soru isaretleri yerine deger gonder
     ps1.setInt(1, 9999);
     ps1.setString(2, "IBM");

    //4.Adim: Query'i calistir
    int guncellenenSatirSayisi = ps1.executeUpdate();
    System.out.println("guncellenenSatirSayisi = " + guncellenenSatirSayisi);

    String sql1a= "Select * from companies";
    ResultSet resultSet1 = st.executeQuery(sql1a);

     while (resultSet1.next()){
         System.out.println(resultSet1.getString("company_id")+ "---"+resultSet1.getString("company")+"--"+ resultSet1.getString("number_of_employees"));
      }
//2. Orn: PreparedStatement kullanarak company adi GOOGLE olan number_of_employees degerini 5555 olarak guncelleyin
     ps1.setInt(1, 5555);
     ps1.setString(2, "GOOGLE");
     System.out.println("---------------------------");

     int guncellenenSatirSayisi2 =  ps1.executeUpdate();
     System.out.println("guncellenenSatirSayisi2 = " + guncellenenSatirSayisi2);

      ResultSet rs2=  st.executeQuery(sql1a);
      while (rs2.next()){
          System.out.println(rs2.getString("company_id")+ "---"+rs2.getString("company")+"--"+ rs2.getString("number_of_employees"));

      }
    }
}
