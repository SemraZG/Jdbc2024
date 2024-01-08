package jdbcProject;

import java.sql.*;

public class StudentRepo {
    //database islemlerini bu classda yapacagiz

    //Connection, Statement ve PreparedStatement'lari olusturalim

    private Connection conn;
    private Statement st;
    private PreparedStatement prst;

    //5-Connection olusturma icin ayri bir method olusturalim
    private void setConnection(){
        try {
            this.conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/SemraJdbc", "SemraJdbc","Semra.55");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //6-statement olusturmak iicn ayri bir method olusturalim
    private void setStatement(){
        try {
            this.st=this.conn.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //7-Preparedstatement olusturmak iicn ayri bir method olusturalim
    private void setPreparedStatement(String sql){
        try {
            this.prst=conn.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //8-ogrenciyi kaydetmek icin once  bir tablo olusturuyorum
    public void createTable(){
        setConnection();
        setStatement();
        try {
            st.execute("CREATE TABLE IF NOT EXISTS t_students(" +
                    "id SERIAL UNIQUE," +
                    "name VARCHAR(50) NOT NULL CHECK(LENGTH(name)>0)," +//CHECK(LENGTH(name)>0)-->empty deger almamasini saglar
                    "lastname VARCHAR(50) NOT NULL CHECK(LENGTH(lastname)>0)," +
                    "city VARCHAR(50) NOT NULL CHECK(LENGTH(city)>0)," +
                    "age INT NOT NULL CHECK(age>0)" +
                    ")");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            try {
                st.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    //12-tabloya kayit ekleme
    public void save(Student student) {
        setConnection();
        String sql="INSERT INTO t_students(name,lastname,city,age) VALUES(?,?,?,?)";
        //id degerini serial yaptigimiz iicn otomatik sirali deger verileck,
        // sutunalrdan biirni bile girmezsek diger girmek istediklerimizi tek tek parantez iicnde belirtmeliyiz
        setConnection();
        setPreparedStatement(sql);
        try {
            prst.setString(1, student.getName());
            prst.setString(2,student.getLastName());
            prst.setString(3, student.getCity());
            prst.setInt(4,student.getAge());

            prst.executeUpdate();
            System.out.println("Saved succesfully...");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                prst.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    //14-Databasedeki tablodaki kayitlari getirme ve yazdirma
    public void findAllStudent() {
        setConnection();
        setStatement();
        String query="SELECT*FROM t_students";
        System.out.println("====================ALL STUDENTS================");
        //st.execute(query);//execute(query)-->geriye kayit donup donmedigini true veya false olrak donduurur bu yuzden executeQuery(query) kullnyrm
        try {
            ResultSet resultSet=st.executeQuery(query);
            while (resultSet.next()){
                System.out.print("id: "+resultSet.getInt("id"));
                System.out.print(" -ad: "+resultSet.getString("name"));
                System.out.print(" -soyad: "+resultSet.getString("lastname"));
                System.out.print(" -sehir: "+resultSet.getString("city"));
                System.out.print(" -yas: "+resultSet.getInt("age"));
                System.out.println();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                st.close();
                conn.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    //16-tablodan id'si verilen kaydi silme
    public void delete(int id) {
        setConnection();
        //1.yol
        //String query="DELETE FROM t_students WHERE id="+id;
        //st.executeUpdate(query);
        //2.yol-->preparedstatement ile
        String query="DELETE FROM t_students WHERE id= ? ";
        setPreparedStatement(query);
        try {
            prst.setInt(1,id);
            int deleted=prst.executeUpdate();
            if (deleted>0){
                System.out.println("Student is deleted succesfully by id"+id);
            }else{
                System.out.println("Student could not find by id"+id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                st.close();
                conn.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    //18-id'si verilen kaydi tablodan getirme
    public Student findById(int id) {
        Student student=null;
        setConnection();
        String sql="SELECT * FROM t_students WHERE id=?";
        setPreparedStatement(sql);
        try {
            prst.setInt(1,id);
           ResultSet resultSet= prst.executeQuery();
           if (resultSet.next()){
               student=new Student();
               student.setId(resultSet.getInt("id"));
               student.setName(resultSet.getString("name"));
               student.setLastName(resultSet.getString("lastname"));
               student.setCity(resultSet.getString("city"));
               student.setAge(resultSet.getInt("age"));
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {//try da calissan catch blogu da calissa finally blok her halukatrda caalisir
            try {
                st.close();
                conn.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return student;
    }


    //20-id'si verilen kaydi databasede guncelleme
    public void update(Student foundStudent) {
        setConnection();
        String sql="UPDATE t_students SET name=?, lastname=?, city=?, age=? WHERE id=?";
        setPreparedStatement(sql);
        try {
            prst.setString(1, foundStudent.getName());
            prst.setString(2, foundStudent.getLastName());
            prst.setString(3, foundStudent.getCity());
            prst.setInt(4,foundStudent.getAge());
            prst.setInt(5,foundStudent.getId());

            prst.executeUpdate();
            if (prst.executeUpdate()>0){
                System.out.println("Updated succesfully...");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                st.close();
                conn.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

