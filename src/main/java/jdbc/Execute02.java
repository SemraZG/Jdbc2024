package jdbc;
import java.sql.*;

public class Execute02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // Database'e bağlan
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SemraJdbc", "SemraJdbc", "Semra.55");

        //Statement oluştur
        Statement st = con.createStatement();

        //ÖRNEK 1:id'si 5 ile 10 arasında olan ülkelerin "country_name" bilgisini listeleyiniz.
        String sql1="select country_name from countries where id between 5 and 10";
        boolean query1=st.execute(sql1);
        System.out.println("query1 = " + query1);//select bize bir sonuc dondurdugu icin booleanin sonucu true oldu
        //verileri konsola alabilmek icin
        ResultSet rs1=st.executeQuery(sql1);
        while (rs1.next()){
            System.out.println("Ulke adi: "+ rs1.getString("country_name"));//sutun adi veya sutunun indexini yazabiliriz
        }

        System.out.println("--------------------ÖRNEK 2------------------------");

        //ÖRNEK 2: phone_code'u 200 den büyük olan ülkelerin "phone_code" ve "country_name" bilgisini listeleyiniz.
        String sql2="SELECT phone_code, country_name FROM countries WHERE phone_code>200";
        ResultSet rs2=st.executeQuery(sql2);
        while (rs2.next()){
            System.out.println("tel kodu: "+rs2.getInt("phone_code")+"---ulke adi: "+rs2.getString("country_name"));
        }


        System.out.println("--------------------ÖRNEK 3------------------------");

        //ÖRNEK 3:developers tablosunda "salary" değeri minimum olan developerların tüm bilgilerini gösteriniz.
        ResultSet rs3= st.executeQuery("SELECT * FROM developers WHERE salary=(SELECT MIN(salary) FROM developers)");
        while (rs3.next()){
            System.out.println("id: "+rs3.getInt("id")+
                    "isim: "+rs3.getString("name")+
                    "--- maas: "+rs3.getDouble("salary")+
                    "--- prog_dili: "+rs3.getString("prog_lang"));

        }

        System.out.println("--------------------ÖRNEK 4------------------------");
        //ÖRNEK 4:Puanı taban puanlarının ortalamasından yüksek olan öğrencilerin isim ve puanlarını listeleyiniz.

        ResultSet rs4=st.executeQuery("SELECT isim,puan FROM ogrenciler " +
                "WHERE puan > (SELECT AVG(taban_puani) FROM bolumler ) ORDER BY puan DESC");

        while (rs4.next()){

            System.out.println("isim: "+rs4.getString("isim")+"---- puanı: "+rs4.getInt("puan"));

        }





        }
    }
