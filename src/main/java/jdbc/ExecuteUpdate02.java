package jdbc;

import java.sql.*;

public class ExecuteUpdate02 {
    public static void main(String[] args) throws SQLException {

        //2-ADIM:bağlantıyı oluşturma
        Connection con = DriverManager.
                getConnection("jdbc:postgresql://localhost:5432/SemraJdbc", "SemraJdbc", "Semra.55");

        //3-ADIM:statement oluşturma
        Statement st = con.createStatement();


        //ÖRNEK2:developers tablosuna yeni bir developer ekleyiniz.
       int insertedNums= st.executeUpdate("INSERT INTO developers(name,salary,prog_lang) VALUES('Jack',5000,'Java')");
        System.out.println("insertedNums = " + insertedNums);
        ResultSet rs=st.executeQuery("SELECT*FROM developers");
        while(rs.next()){
            System.out.println(rs.getString("name")+"-->"+rs.getDouble("salary"));
        }

        //ÖRNEK3:developers tablosundan id'si 9 olanı siliniz.
        int deleted=st.executeUpdate("DELETE FROM developers WHERE id=9");
        System.out.println("silinen kayıt sayısı:"+deleted);

        //ÖRNEK4:developers tablosundan id'si 15 olanı siliniz.
        int deleted2=st.executeUpdate("DELETE FROM developers WHERE id=15");
        System.out.println("silinen kayıt sayısı:"+deleted2);

        st.close();
        con.close();
    }
}