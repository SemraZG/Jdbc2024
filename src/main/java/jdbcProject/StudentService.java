package jdbcProject;

import java.util.Scanner;

public class StudentService {
    //Student ile ilgili methodlari buarda tutacagiz

    Scanner input=new Scanner(System.in);

    private StudentRepo repo=new StudentRepo();


    //9-studnet icin tablo olusturuyorum
    public void createStudentTable(){
        repo.createTable();
    }


    //11-ogrenciyi kaydetme
    public void saveStudent(){
        System.out.println("AD: ");
        String name=input.nextLine().trim();
        System.out.println("SOYAD: ");
        String soyad=input.nextLine().trim();
        System.out.println("CITY: ");
        String city=input.nextLine().trim();
        System.out.println("YAS: ");
        int age=input.nextInt();
        input.nextLine();

        Student newStudent=new Student(name, soyad,city,age);

        repo.save(newStudent);
    }


    //13-tum ogrencileri listeleme
    public void getAllStudent() {
        repo.findAllStudent();
    }


    //15-id'si verilen studnet i silme
    public void deleteStudent(int id) {
        repo.delete(id);
    }

    //17-id'si verilen ogrenciyi bulma
    public Student getStudentById(int id){
       Student student= repo.findById(id);
       return student;
    }


    //id'si verilen ogrenciyi gosterme
    public void displayStudent(int id) {
        Student student=getStudentById(id);
        if (student==null){
            System.out.println("Student does not exist..");
        }else{
            System.out.println(student);
        }
    }

    //19-id'si verilen ogrenciyi guncelleme
    public void updateStudent(int id){
        //bu id ile kayitli ogrenci mevcut mu?
        Student foundStudent=getStudentById(id);
        if (foundStudent==null){
            System.out.println("Student does not exist by id: "+id);
        }else{
            System.out.println("AD: ");
            String name=input.nextLine().trim();
            System.out.println("SOYAD: ");
            String soyad=input.nextLine().trim();
            System.out.println("CITY: ");
            String city=input.nextLine().trim();
            System.out.println("YAS: ");
            int age=input.nextInt();
            input.nextLine();

            //id ile bulunan ogrenciyi guncelleyecegiz
            foundStudent.setName(name);
            foundStudent.setLastName(soyad);
            foundStudent.setCity(city);
            foundStudent.setAge(age);
            //id ayni kaldi degistirmedik, diger bilgilerini guncelledik

            repo.update(foundStudent);//guncelledigimiz bilgileri da databesede de guncelleyelim diye update() methodu olusturalim
        }
    }


}
