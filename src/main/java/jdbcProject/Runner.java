package jdbcProject;

import java.util.Scanner;

public class Runner {
    /*
   Student Management System:
   1-Herhangi bir egitim kurumu icin ogrenci yonetim uygulamasi gelistiriniz
   2-Kullanici
               -ogrenci kayit
               -ogrencileri ve ogrenciyi goruntuleme
               -id ile ogrenci guncelleme
               -id ile ogrenci silme
                                   islemlerini yapabilmeli

   3-ogrenci: id,name,lastname,city,age ozelliklerine sahiptir
    */
    public static void main(String[] args) {
        start();



    }

    //1.adim-->kullaniciya menu gosterelim
    //2.adum-->student classi olusturuyorum-->pojo class
    //3.adim-->StudentService classini olusturdum methodlar icin
    //4.adim-->database save yapacagimiz icin Repo classini olusturuyorum

    //akis---> client-->Runner-->Service Classi--> Repo Classi--> DataBase
    public static void start() {
        Scanner input = new Scanner(System.in);
        //10-service ve tablo olusturma
        StudentService service=new StudentService();
        service.createStudentTable();

        int select;
        int id ;

        do {
            System.out.println("===============================");
            System.out.println("=====Ogrenci Yonetim Paneli====");
            System.out.println("1-Ogrenci Kayit\n" +
                    "2-Tum Ogrencileri Listele\n" +
                    "3-Ogrenci Guncelle\n" +
                    "4-Ogrenci Sil\n" +
                    "5-Tek Bir Ogrenciyi Gosterme\n"+
                    "0-Cikis\n" +
                    "Islem seciniz");

                select = input.nextInt();
                input.nextLine();

                switch (select) {
                    case 1:
                        //Ogrenci Kayit
                        service.saveStudent();
                        service.getAllStudent();
                        break;
                    case 2:
                        //Tum Ogrencileri Listele
                        service.getAllStudent();
                        break;
                    case 3:
                        //ogrenci Guncelle
                        id=getId(input);
                        service.updateStudent(id);
                        break;
                    case 4:
                        //Ogrenci Sil
                        id=getId(input);
                        service.deleteStudent(id);
                        service.getAllStudent();
                        break;
                    case 5:
                        id=getId(input);
                        //Ogrenci bilgilerini yazdirma
                        service.displayStudent(id);
                    case 0:
                        //cikis
                        System.out.println("Iyi gunler dileriz...");
                        break;
                    default:
                        System.out.println("Hatali giris!!!");
                        break;
                }
        }while (select != 0) ;

    }

    private static int getId(Scanner input){
        System.out.println("Ogrenci Id giriniz");
        int id=input.nextInt();
        input.nextLine();
        return id;
    }
}

