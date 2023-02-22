import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {
    private static Connection connection;
    private static Statement statement;
    //1. Adim: Driver'a kaydol
    //2.Adim: Database'e baglan
    public static Connection connectToDataBase(String hostName, String dbName, String userName, String password){

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://"+hostName+":5432/"+dbName, userName,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (connection!=null){
            System.out.println("Connection Basarili");
        }else{
            System.out.println("Connection Basarisiz");
        }


        return connection;
    }
    //3.Adim: Statement olustur
    public static Statement createStatement(){

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }
    //4.Adim: Query(sorgu) olustur/calistir.
    public static boolean execute(String sql){
        boolean isExecute;
        try {
          isExecute =  statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isExecute;
    }
    //5.Adim: Baglanti ve Statement'i kapat
    public static void closeConnectionVeStatement(){
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if (connection.isClosed() && statement.isClosed()){
                System.out.println("Connection veya Statement kapatildi");
            }else {
                System.out.println("Connection veya Statement hala acik");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
//Table olusturan bir method olust
    // "Create Table tableName(sutunAdi datatipi, .........)"

    public static void createTable(String tableName, String ... sutun_sayisi) {
        StringBuilder sutunIsmi_stb = new StringBuilder("");

        for (String herBirSutun : sutun_sayisi) {
            sutunIsmi_stb.append(herBirSutun).append(",");
        }
        sutunIsmi_stb.deleteCharAt(sutunIsmi_stb.length() - 1);
        System.out.println(sutunIsmi_stb);


        try {
            statement.execute("Create Table " + tableName + "(" + sutunIsmi_stb + ")");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}