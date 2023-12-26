package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//onc epom.xml e dependency i ekledik
        //1. Adım: Driver'a kaydol
        Class.forName("org.postgresql.Driver");//java 7 ile birlikte gerek kalmadi

        //2. Adım: Database'e bağlan
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SemraJdbc","SemraJdbc","Semra.55");

        //3. Adım: Statement oluştur
        Statement st = con.createStatement();

        System.out.println("Connection Success");

        //4. Adim: sorguyu olusturma
        //ÖRNEK 1:"workers" adında bir tablo oluşturup "worker_id,worker_name,salary" sütunlarını ekleyiniz.
        boolean sql1=st.execute("create table if not exists workers(\n" +
                "\tworker_id int,\n" +
                "\tworker_name varchar(50),\n" +
                "\tsalary real\n" +
                ")");
        System.out.println("sql1 = " + sql1);//sorgu sonuucnda bir veri dondurnedigi icin false dondurur

         /*
        execute:tüm sorguları çalıştırmak için kullanılır
         sorgunun sonucunda ResultSet alınıyorsa TRUE döndürür, aksi halde FALSE döndürür.
         1-DDL -->FALSE
         2-DQL -->TRUE
        genellikle DDL için kullanılır.

         */

        //ÖRNEK 2:"workers" tablosuna VARCHAR(20) tipinde "city" sütununu ekleyiniz.
        //st.execute("ALTER TABLE workers ADD COLUMN city VARCHAR(20)");
        //örnek 2 yi yoruma alalım.
        //ÖRNEK 3:"workers" tablosunu SCHEMAdan siliniz.
        st.execute("DROP TABLE workers");

        st.close();
        con.close();

    }

}
