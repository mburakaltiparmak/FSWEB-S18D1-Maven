package com.workintech.sqldmljoins.repository;

import com.workintech.sqldmljoins.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OgrenciRepository extends JpaRepository<Ogrenci, Long> {


    //Kitap alan öğrencilerin öğrenci bilgilerini listeleyin..
    String QUESTION_2 = "select o.* from ogrenci as o inner join islem as i on i.ogrno = o.ogrno\n" +
            "inner join kitap as k on i.kitapno = k.kitapno;\n";
    @Query(value = QUESTION_2, nativeQuery = true)
    List<Ogrenci> findStudentsWithBook();


    //Kitap almayan öğrencileri listeleyin.
    String QUESTION_3 = "select o.* from ogrenci as o left join islem as i on i.ogrno = o.ogrno\n" +
            "where i.ogrno is null;";
    @Query(value = QUESTION_3, nativeQuery = true)
    List<Ogrenci> findStudentsWithNoBook();

    //10A veya 10B sınıfındaki öğrencileri sınıf ve okuduğu kitap sayısını getirin.
    String QUESTION_4 = "SELECT \n" +
            "    o.ad,\n" +
            "    o.sinif,\n" +
            "    COUNT(i.islemno) AS kitap_sayisi\n" +
            "FROM \n" +
            "    ogrenci AS o\n" +
            "INNER JOIN \n" +
            "    islem AS i \n" +
            "ON \n" +
            "    o.ogrno = i.ogrno\n" +
            "WHERE \n" +
            "    o.sinif = '10A' OR o.sinif = '10B'\n" +
            "GROUP BY \n" +
            "    o.ogrno, o.ad, o.sinif;\n";
    @Query(value = QUESTION_4, nativeQuery = true)
    List<KitapCount> findClassesWithBookCount();

    //Öğrenci tablosundaki öğrenci sayısını gösterin
    String QUESTION_5 = "select count(ogrno) as ogrenci_sayisi from ogrenci;";
    @Query(value = QUESTION_5, nativeQuery = true)
    Integer findStudentCount();

    //Öğrenci tablosunda kaç farklı isimde öğrenci olduğunu listeleyiniz.
    String QUESTION_6 = "select count(distinct ad) as isim_sayisi from ogrenci;";
    @Query(value = QUESTION_6, nativeQuery = true)
    Integer findUniqueStudentNameCount();

    //--İsme göre öğrenci sayılarının adedini bulunuz.
    //--Ali: 2, Mehmet: 3
    String QUESTION_7 = "SELECT \n" +
            "    ad AS isim, \n" +
            "    COUNT(*) AS ogrenci_sayisi\n" +
            "FROM \n" +
            "    ogrenci\n" +
            "GROUP BY \n" +
            "    ad\n" +
            "ORDER BY \n" +
            "    ogrenci_sayisi DESC;";
    @Query(value = QUESTION_7, nativeQuery = true)
    List<StudentNameCount> findStudentNameCount();


    String QUESTION_8 = "select count(ogrno) as ogrenci_sayisi,sinif  from ogrenci\n" +
            "group by sinif;";
    @Query(value = QUESTION_8, nativeQuery = true)
    List<StudentClassCount> findStudentClassCount();

    String QUESTION_9 = "SELECT \n" +
            "    o.ad,\n" +
            "    o.soyad,\n" +
            "    COUNT(i.islemno) AS kitap_sayisi\n" +
            "FROM \n" +
            "    ogrenci AS o\n" +
            "INNER JOIN \n" +
            "    islem AS i \n" +
            "ON \n" +
            "    o.ogrno = i.ogrno\n" +
            "GROUP BY \n" +
            "    o.ogrno, o.ad, o.sinif;";
    @Query(value = QUESTION_9, nativeQuery = true)
    List<StudentNameSurnameCount> findStudentNameSurnameCount();
}
