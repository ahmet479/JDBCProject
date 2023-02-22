import java.sql.Connection;
import java.sql.Statement;

public class Runner {
    public static void main(String[] args) {
        //1. Adim: Driver'a kaydol
        //2.Adim: Database'e baglan
        Connection con = JdbcUtils.connectToDataBase("localhost", "arcane", "postgres", "2213");

        //3.Adim: Statement olustur
        Statement statement = JdbcUtils.createStatement();

        //4. Adim: Query (sorgu) olustur/calistir
//        JdbcUtils.execute("Create Table students(name VARCHAR(20),okul_no INT, address VARCHAR(80))");

//        JdbcUtils.execute("Create Table okul (okul_name VARCHAR(20),okul_kapasitesi INT, address VARCHAR(80))");


        JdbcUtils.createTable("mudur1", "name VARCHAR(20)","ili INT ", "ogretmen_ismi VARCHAR(80)", "okul_no INT");

        //5. Adim: Baglanti ve Statement'i kapat
        JdbcUtils.closeConnectionVeStatement();

    }
}

