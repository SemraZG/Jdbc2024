package jdbc;

import java.sql.*;

//executeUpdate():DML için kullanılır; INSERT INTO,UPDATE,DELETE
//return:(int) sorgunun sonucundan etkilenen kayıt sayısını verir
public class ExecuteUpdate01 {
    public static void main(String[] args) throws SQLException, SQLException {

        //2-ADIM:bağlantıyı oluşturma
        Connection con = DriverManager.
                getConnection("jdbc:postgresql://localhost:5432/SemraJdbc", "SemraJdbc", "Semra.55");

        //3-ADIM:statement oluşturma
        Statement st = con.createStatement();

        //  ÖRNEK1:developers tablosunda maaşı ortalama maaştan az olanların maaşını ortalama maaş ile güncelleyiniz
        String sql1="UPDATE developers SET salary=(SELECT AVG(salary) FROM developers)" +
                "WHERE salary<(SELECT AVG(salary) FROM developers)";
        int updatedNums=st.executeUpdate(sql1);
        System.out.println("updatedNums = " + updatedNums);
        ResultSet rs1=st.executeQuery("SELECT*FROM developers");
        while (rs1.next()){
            System.out.println(rs1.getString("name")+"-->"+rs1.getDouble("salary"));
        }


        st.close();
        con.close();

    }
}
