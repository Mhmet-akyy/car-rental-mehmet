<<<<<<< HEAD
# 🚗 Car Rental System

Bu proje, **Java 21** ve **PostgreSQL 16** kullanılarak geliştirilmiş terminal tabanlı bir **Araç Kiralama Sistemi**dir.  
Amaç, katmanlı mimari, veritabanı işlemleri, transaction yönetimi ve modern yazılım geliştirme prensiplerini uygulamaktır.

---

## ✨ Özellikler

- **Kimlik Doğrulama**
  - E-posta + şifre (SHA-256 ile hashlenmiş).
  - Roller: `ADMIN`, `CUSTOMER`, `CORPORATE`.

- **Admin İşlemleri**
  - Araç ekleme / güncelleme / silme / listeleme.

- **Customer İşlemleri**
  - Araç listeleme (filtreleme, sayfalama).
  - Araç kiralama, iptal etme.
  - Kiralama kayıtlarını görme.

- **İş Kuralları**
  - `CORPORATE` kullanıcılar min. 30 gün kiralama yapabilir.
  - Araç değeri > 2.000.000 TL → kullanıcı yaşı ≥ 30 ve %10 depozito şartı.
  - Tarih çakışmaları engellenir.

- **Transaction Yönetimi**
  - Kiralama oluşturma tek transaction içinde yapılır (`commit` / `rollback`).

---

## 🗂️ Katmanlı Mimari

=======
# car-rental-mehmet
Terminal-based Car Rental Project — Java 21 + PostgreSQL
>>>>>>> bdf2ba44bb66f26e61807432e8c40d75a858179e
