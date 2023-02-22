
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1. Adim: Driver'a kayd ol
        Class.forName("org.postgresql.Driver");

        //2. Adim: Database'e baglan
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/arcane", "postgres", "2213");

        //3.Adim: Statement olustur.
        Statement st = con.createStatement();

        System.out.println("Baglanti Basarili");

        //4.Adim: Query(sorgu) olustur

        //1: Ornek: "workers" adinda bir table olusturup "worker_id, worker_name, worker_salary" sutunlarini ekleyin


        boolean sql1 = st.execute("Create Table workers(worker_id VARCHAR(20),worker_name VARCHAR(20),worker_salary INT)");
        System.out.println("sql1 = " + sql1);  //false dondurur cunku data cagirmiyoruz
        /*
        execute() DDL -data definition language-(create, drop, alter table) ve DQL -data query language - (select) icin kullanabiliriz
        !)Eger execute() methodu DDL icin kullanilirsa 'false' return eder
        !!)Eger execute() methodu DQL icin kullanilirsa ResultSet (sonuc/data/veri) alindiginda 'ture' aksi halde 'false' dondurur
         */

        //2.//2. Ornek: Table'a worker_address sutunu ekleyerek alter yapin

        String sql2 = "Alter Table workers Add worker_address VARCHAR(80)";
        boolean sql2a = st.execute(sql2);
        System.out.println("sql2a = " + sql2a);

        //3.Ornek: workers table'ini silin
        String sql3 = "Drop Table workers";
        boolean sql3a = st.execute(sql3);
        System.out.println("sql3a = " + sql3a);

        //5.Adim: Baglanti ve Statement'i kapat
        con.close();
        st.close();

    }
}
