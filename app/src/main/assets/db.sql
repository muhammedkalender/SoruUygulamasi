BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `questions` (
	`id`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`category_id`	INTEGER NOT NULL,
	`difficulty`	INTEGER DEFAULT 0,
	`type`	INTEGER DEFAULT 0,
	`question`	TEXT NOT NULL,
	`answer_a`	TEXT NOT NULL,
	`answer_b`	TEXT,
	`answer_c`	TEXT,
	`answer_d`	TEXT,
	`quested`	INTEGER DEFAULT 0,
	`is_correct`	INTEGER DEFAULT -1,
	`is_skipped`	INTEGER DEFAULT 0,
	`total_quested`	INTEGER DEFAULT 0,
	`total_correct`	INTEGER DEFAULT 0,
	`total_skip`	INTEGER DEFAULT 0,
	`active`	INTEGER DEFAULT 0,
	`answered_a`	INTEGER DEFAULT 0,
	`answered_b`	INTEGER DEFAULT 0,
	`answered_c`	INTEGER DEFAULT 0,
	`answered_d`	INTEGER DEFAULT 0
);
INSERT INTO `questions` VALUES (1,1,0,0,'[[DOUBLE_QUOTE]]Sinekli Bakkal[[DOUBLE_QUOTE]] Romanının Yazarı Aşağıdakilerden Hangisidir? ','Halide Edip Adıvar ','Reşat Nuri Güntekin ','Ziya Gökalp ','Ömer Seyfettin ',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (2,1,0,0,'Aşağıda Verilen İlk Çağ Uygarlıklarından Hangisi Yazıyı İcat Etmiştir? ','Sümerler ','Hititler ','Elamlar ','Urartular ',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (3,1,0,0,'Tsunami Felaketinde En Fazla Zarar Gören Güney Asya Ülkesi Hangsidir ?','Endonezya','Sri Lanka ',' Tayland ','Hindistan ',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (4,1,0,0,'2003 Yılında Euro Vizyon Şarkı Yarışmasında Ülkemizi Temsil Eden Ve
Yarışmada Birinci Gelen Sanatçımız Kimdir? 
','Sertap Erener ','Grup Athena ','Şebnem Paker ','Ajda Pekkan ',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (5,1,0,0,'Mustafa Kemal Atatürk’ün Nüfusa Kayıtlı Olduğu İl Hangisidir? ','Gaziantep','İstanbul ','Ankara','Bursa',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (6,1,0,0,'Aşağıdakilerden Hangisi Dünya Sağlık Örgütünün Kısaltılmış İsmidir? ','WHO','UNICEF','NATO','UHW',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (7,1,0,0,'Romen Rakamında Hangi Sayı Yoktur?','0','50','100','1000',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (8,1,0,0,'Bir Gün Kaç Saniyedir? ','86400','88600','86000','84800',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (9,1,0,0,'Üç Büyük Dince Kutsal Sayılan Şehir Hangisidir? ',' Kudüs ','Mekke','Roma','İstanbul',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (10,1,0,0,'Hangi İlimizde Demiryolu Yoktur? ','Muğla','Kütahya','Aydın','Batman ',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (11,1,0,0,'Hangi Ülkenin İki Tane Başkenti Vardır? ','Güney Afrika ','Senegal ','El Salvador ','Venezuela ',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (12,1,0,0,'Cevdet Bey Ve Oğulları Eseri Kime Aittir? ','Orhan Pamuk ','Yahya Kemal Bayatlı ','Atilla İlhan ','Samipaşazade Sezai ',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (13,1,0,0,'Ülkelerden Hangisinin Nüfusu Daha Fazladır ','Almanya','İspanya ','Türkiye ','Fransa',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (14,1,0,0,'Aspirinin Hammaddesi Nedir? ','Söğüt','Kavak','Meşe','Gürgen',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (15,1,0,0,'Günümüzde Nüfusu En Fazla Olan İslam Ülkesi Hangisidir? ','Endonezya','Pakistan','Hindistan','Türkiye',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (16,1,0,0,'Avustralya Adasının En Tanınmış Hayvanının İsmi Nedir? ','Kanguru','Maymun','Kuş','Aslan',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (17,1,0,0,'Fransa İle İngiltere’yi Bir Birine Bağlayan Tünelin Adı Nedir? ','Manch Tüneli ','Laerdal Tüneli','Seikan Tüneli','Lotschberg Tüneli',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (18,1,0,0,'Futbol Toplam Kaç Kişi İle Oynanır? ','22','10','11','12',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (19,1,0,0,'Ankara İlimizin Araba Ve Şehir Kod Numarası Kaçtır? ','6','5','34','35',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (20,1,0,0,'Bakü Hangi Devletin Başkentidir? ','Azerbaycan','Makedonya','Gürcistan','İran',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (21,1,0,0,'Endonezya Devleti Hangi Kıtadadır? ','Asya','Avustralya','Afrika','Antarktika',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (22,1,0,0,'Genellikle Yeni İsmi İle Anılan Hatay İlimizin Eski İsmi Nedir? ','Antakya','Ararat','Amasesia','Adalia',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (23,1,0,0,'Türkiye’nin En Büyük Gölü Hangisidir? ','Van Gölü','Eber Gölü','Beyşehir Gölü','Tuz Gölü',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (24,1,0,0,'Türkiye’nin En Yüksek Dağı Hangisidir? ','Ağrı Dağı','Erciyes Dağı','Uludağ','Nemrut Dağı',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (25,1,0,0,'Toros Dağları Türkiye’nin Hangi Bölgesindedir? ','Akdeniz','Ege ','Karadeniz ','Marmara',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (26,1,0,0,'Hollanda’nın Başkenti Nedir? ','Amsterdam ','Rotterdam','Lahey','Utrecht',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (27,1,0,0,'Dünyanın En Uzun Nehrinin Adı Nedir? ','Nil Nehri ','Amazon Nehri','Yangtze Nehri','Kongo Nehri',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (28,1,0,0,'Semerkant Hangi Ülkenin Sınırları İçindedir? ','Özbekistan','Azerbaycan','Gürcistan','Bulgaristan',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (29,1,0,0,'Kaç Yılda Bir Şubat Ayı 29 Çeker? ','4','3','2','5',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (30,1,0,0,'Dünyada en çok bulunan element hangisidir? ','Oksijen','Karbon','Hidrojen','Azot',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (31,1,0,0,'Fatih Sultan Mehmet’in babası kimdir? ','II. Murat','Kanuni Sultan Süleyman','Yıldırım Beyazıt','I. Mehmet',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (32,1,0,0,'Ankara ne zaman başkent olmuştur? ','1923','1926','1919','1929',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (33,1,0,0,'1986 yılında yaşanan Türkiye’nin etkilendiği Çernobil Santrali kazası nerede yaşandı?','Ukrayna','Azerbaycan','Çin','Danimarka',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (34,1,0,0,'2007 yılında Türkiye’de klonlanan ilk koyunun adı nedir? ','Oyalı','Selver','Dolly','Kınalı',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (35,1,0,0,'Hangisi periyodik tabloda bulunan bir element değildir?','Su','Kalsiyum','Azot','Oksijen',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (36,1,0,0,'NATO’ya üye olduğumuz tarih aşağıdakilerden hangisidir? ','1952','1950','1946','1948',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (37,1,0,0,'Birleşmiş Milletlerin Genel Merkezi nerededir? ','New York','Londra','Cenevre','Paris',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (38,1,0,0,'Bir yılda kaç hafta var? ','52','12','30','17',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (39,1,0,0,'H2O ….. formülüdür. Noktalı yere ne gelmelidir? ','SU','HAVA','PETROL','TUZ',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (40,1,0,0,'Türkiye’nin 5. büyük takımı Bursaspor´un renkleri aşağıdakilerden hangisidir? ','YEŞİL – BEYAZ','YEŞİL – SİYAH','SİYAH – BEYAZ','SARI – KIRMIZI',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (41,1,0,0,'İstiklâl Marşı’mızın yazarı kimdir?','Mehmet Akif Ersoy','Osman Zeki Üngör','M. Kemal ATATÜRK','Hamdullah Suphi Tanrıöver',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (42,1,0,0,'Telefonun mucidi kimdir? ','Alexander Graham Bell','Thomas Watson','Nikola Tesla‎','Benjamin Franklin',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (43,1,0,0,'2010’da yapılan  Dünya Münazara Yarışması’na ev sahipliği yapan ülke aşağıdakilerden hangisidir?','Türkiye','Almanya','İngiltere','Yunanistan',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (44,1,0,0,'Avrupa Birliği"nin en fazla önemsediği, her alanda geleceğin teknolojisi olarak adlandırılan bilim dalının adı nedir?','Nanoteknoloji','Nükleer enerji','Güneş enerjisi',' Astronomi',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (45,1,0,0,'UEFA Kupasını alan ilk Türk takımı hangisidir?','Galatasaray','Beşiktaş',' Trabzonspor',' Fenerbahçe',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (46,1,0,0,'Dünyanın ilk haritasını çizen ünlü Türk denizcisi kimdir?',' Piri Reis','Çaka Beyi','Fatih Sultan Süleyman','Picasso',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (47,1,0,0,'Tarihte Türk adıyla kurulan ilk Türk devleti hangisidir?','Göktürkler',' Hunlar','Hazarlar','Osmanlı Devleti',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (48,1,0,0,'Amerika Kıtası’nı ikiye ayıran önemli su geçitinin adı nedir?','Panama','Süveyş Kanalı','Ümit Burnu','Cebelitarık',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (49,1,0,0,'Kuzey Atlantik Paktı[[SINGLE_QUOTE]]nın kısa yazılışı nedir?','NATO','WHO','UNİCEF','UNESCO',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (50,1,0,0,'Nobel ödülleri hangi ülkede verilmektedir?','İsveç',' Almanya','Amerika Birleşik Devletleri','İngiltere',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (51,1,0,0,'İlk atom bombası hangi şehre atılmıştır?','Hiroşima',' Shanghai',' Hong Kong','Tokyo',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (52,1,0,0,'2010 Kültür Başkenti seçilen ilimiz hangisidir?','İstanbul','Ankara','Adana','İzmir',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (53,1,0,0,'Türkiye’nin uluslar arası telefon kodu aşağıdakilerden hangisidir?','90','1','49','32',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (54,1,0,0,'Altın Palmiye Sinema Ödülü hangi film festivalinde verilmektedir?','Cannes',' Altın Portakal',' Berlin','Auston',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (55,1,0,0,'Türkiye’de erozyonla mücadele amacıyla kurulan vakfın kısa adı nedir?','TEMA','AKUT','TEV','AÇEV',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (56,1,0,0,'Türk tarihinin en ünlü mimarı kimdir?','Mimar Sinan','Zaha Hadid','Mimar Hayreddin',' Necip Dinç',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (57,1,0,0,'Türk Medeni Kanunu hangi ülkenin medeni kanunundan esinlenerek hazırlanmıştır?','İsviçre','Hollanda',' Danimarka',' Almanya',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (58,1,0,0,'Hababam Sınıfı Filminde [[DOUBLE_QUOTE]]İnek Şaban[[DOUBLE_QUOTE]] karakterini canlandıran sanatçı kimdir?','Kemal Sunal','Halit Akçatepe',' Ediz Hun','Tarık Akan',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (59,1,0,0,'Bulgaristan’ın başkenti neresidir?',' Sofya',' Tahran','Atina','Erivan',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (60,1,0,0,'Mimar Sinan’ın Ustalık Dönemi eseri sayılan Edirne’deki eserinin adı nedir?','Selimiye','Sultan Ahmet','Aya Sofya','Topkapı Sarayı',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (61,1,0,0,'Dünya Ekonomik Forumu yıllık olağan toplantısı hangi ülkede yapılmıştır?',' İsviçre','İngiltere','Almanya','Fransa',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (62,1,0,0,'Müzik ezgileri yazılırken sesleri gösteren işaretlere ne ad verilir ?','Nota','Ritim','Ahenk','Beste',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (63,1,0,0,'Avrupa Birliğini iki kez halk oylamasiyla reddeden ülke hangisidir ?','Norveç','Polonya','Ukranya ','Hırvatistan ',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (64,1,0,0,'Kadınlara seçme ve seçilme hakkı tanıyan ilk ülke hangisidir ?','Türkiye','Almanya','Amerika Birleşik Devletleri','İngiltere',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (65,1,0,0,'İnternet üzerinde en fazla kullanılan arama motoru hangisidir ?','Google','Yandex','Opera','Firefox',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (66,1,0,0,'Pisagor teoremi hangi bilim dalıyla ilgilidir ?','Matematik','Fizik ','Kimya ','Biyoloji',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (67,1,0,0,'En geniş ormanlık alana sahip bölgemiz hangisidir ?','Karadeniz','Ege ','Akdeniz','Doğu Anadolu',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (68,1,0,0,'Türkiyede bir tek Erzurumda çıkarılan madenin adı nedir ?','Oltu Taşı','Akik','Apatit','Yıldıztaşı',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (69,1,0,0,'Elektrik akımı ölçü birimi?','Amper','Volt','Watt','Ohm',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (70,1,0,0,'Tarihi Aspendos Tiyatrosu hangi ilimizdedir?','Antalya','Muğla','İzmir','Aydın',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (71,1,0,0,'Hangi sporcumuz futbol dalında altın ayakkabı almıştır?','Tanju Çolak','Aykut Kocaman','Hakan Şükür','Sergen Yalçın',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (72,1,0,0,'2011 Dünya Üniversitelerarası Kış Olimpiyatları hangi ilimizde yapıldı ?','Erzurum','Van','Kars','Kayseri',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (73,1,0,0,'Yaprak Dökümü adlı romanın yazarı kimdir?','Reşat Nuri Güntekin','Osman Zeki Üngör','Orhan Pamuk','Yaşar Kemal',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (74,1,0,0,'2007 Avrupa Basketbol Şampiyonası hangi ülkede yapılmıştır?','İspanya','İtalya','Amerika Birleşik Devletleri','Fransa',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (75,1,0,0,'Musul hangi antlaşmayla Irak’a bırakılmıştır?','Ankara','Lozan','Bucaş','Kerden',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (76,1,0,0,'Avrupa üyesi olduğu halde Avro yerine kendi para birimini kullanmaya devam eden ülke hangisidir?','İngiltere','Fransa','Almanya','Norveç',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (77,1,0,0,'İpeği ve şeftalisi ile meşhur ilimiz hangisidir?','Bursa','Malatya','Amasya','Sakarya',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (78,1,0,0,'Türkiye’nin kuzeyden geçen en uç şehri hangisidir?','Sinop','Samsun','Rize','Trabzon',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (79,1,0,0,'Güneşe en yakın gezegen hangisidir?','Merkür','Venüs','Jüpiter','Mars',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (80,1,0,0,'Dünyanın en büyük hayvanı nedir?','Mavi Balina','Köpek Balığı','Su Aygırı','Yılan',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (81,1,0,0,'Hangi kan grubu herkesten kan alabilir?','AB','A','B','0',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (82,1,0,0,'Hangi kan grubu herkese kan verir?','0','AB','B','A',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (83,1,0,0,'Dünya’nın en yüksek tepesi hangisidir?','Everest','Kangchenjunga','Lhotse','Makalu',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (84,1,0,0,'Battaniyeleri ile dünyaca meşhur ilimiz hangisidir?','Siirt','Şırnak','Erzincan','Van',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (85,1,0,0,'Ülkemizde şeker pancarı üretiminin en fazla yapıldığı bölgemiz hangisidir?','İç Anadolu Bölgesi','Doğu Anadolu Bölgesi','Güneydoğu Anadolu Bölgesi','Karadeniz',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (86,1,0,0,'’’Sanat Güneşi’’ olarak adlandırdığımız ünlü sanatçımız kimdir?','Zeki Müren','Müzeyyen Senar','Emel Sayın','Safiye Ayla',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (87,1,0,0,'Horozları ile meşhur olan ilimiz hangisidir?','Denizli','Muğla','Antalya','Aydın',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (88,1,0,0,'Dünyada insanları cimri olarak adlandırılan ülke hangisidir?','İşkoçya','Polonya','Norveç','Moldova',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (89,1,0,0,'Kuduz aşısını kim bulmuştur?','Luis Pastör','Edward Jenner','Smorodintsev','Hillemann ',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (90,1,0,0,'Amerika ve Asya kıtaları arasındaki okyanus’un adı nedir?','Büyük ','Atlas ','Hint ','Arktik ',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (91,1,0,0,'Vücuduna orantılı en küçük kafalı kuş hangisidir?','Devekuşu','Turna','Leylek','Kaz',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (92,1,0,0,'Rumeli Hisarını hangi padişah yapmıştır?','Fatih Sultan Mehmet','Yıldırım Bayezid','I. Murad','Yavuz Sultan Selim',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (93,1,0,0,'Türkiye ile Yunanistan’ı birbirine bağlayan sınır kapısının adı nedir?','İpsala','Kapıkule','Sarp','Akkaya',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (94,1,0,0,'Osmanlı Devleti’nde devlet memurlarının pantolon ve fes giyme zorunluluğunu hangi padişah getirmiştir?',' II.Mahmut','Yıldırım Bayezid','I. Murad','Yavuz Sultan Selim',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (95,1,0,0,'Uzaydan bakıldığında görülebilen dünyadaki tek yapının adı nedir?','Çin Seddi','Şangay Kulesi','Potala Sarayı','Mutianyu',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (96,1,0,0,'Dünyanın en derin gölü hangi göldür?','Baykal Gölü','Tanganika Gölü','Büyük Esir Gölü','Loch Ness',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (97,1,0,0,'Çok partili rejim hangi tarihten itibaren gerçekleşmiştir?','1945','1952','1955','1940',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (98,1,0,0,' İlk yerli tiyatro eseri kime aittir?','Şinasi','Cemal Reşit Rey','Aziz Nesin','Sadık Şendil',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (99,1,0,0,'Dünyanın en büyük adasının adı nedir ?','Grönland','Borneo','Madagaskar','Sumatra',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (100,2,0,0,'[[DOUBLE_QUOTE]]Manisa Dağı[[DOUBLE_QUOTE]] diye de bilinen ve en yüksek noktası 1.513 metreye ulaşan dağımızın diğer adı nedir? ','Spil Dağı','Madran Dağı','Ahır Dağı','Bulkaz Dağı',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (101,2,0,0,'Yeni dünya kimi coğrafyacıların hangi kıta için dile getirdikleri kavramın adıdır? ','Amerika','Asya','Avrupa','Afrika ',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (102,2,0,0,' 6.650 km uzunluğunda olan dünyanın en büyük ırmakları arasında gösterilen Afrika[[SINGLE_QUOTE]]daki nehrin adı nedir? ','Nil Nehri','Kongo Nehri','Nijer Nehri','Turuncu Nehir',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (103,2,0,0,'Adıyaman[[SINGLE_QUOTE]]da bulunan tarihi ve turistik dağ merkezinin adı nedir? ','Nemrut','Akdağ','Dibek Dağı','Borik Dağı',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (104,2,0,0,'Afrika[[SINGLE_QUOTE]]nın en yüksek dağının adı nedir?','Kilimanjaro Dağı','Kenya Dağı','Nawenzi','Mount Stanley',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (105,2,0,0,'Ağrı dağının yüksekliği kaç metredir? ','5137','4217','3019','5248',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (106,2,0,0,'Akarsuların taşıdığı toprakları deniz kenarında biriktirmesiyle oluşan düzlüklere ne denir? ','Delta ovası','Yayla','Düzlek','Düzlük',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (107,2,0,0,'Akarsuyun, az eğimli olduğu yerlerde yatağı içerisinde oluşan büklümlere ya da kıvrımlara ne isim verilir? ','Menderes','Havza','Dere','Çay',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (108,2,0,0,'Akdeniz Bölgesiyle özdeşleşmiş olan sıradağlarımızın adı nedir?','Toroslar','Musa Dağı','Tahtalı Dağı','Olimpos Dağı',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (109,2,0,0,'Akdeniz ikliminin en karakteristik meyve türünün adı nedir? ','Turunçgiller','Yeşilgiller','Tropikgiller','Subtropikaller',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (110,2,0,0,'Akdeniz[[SINGLE_QUOTE]]in İtalya ve Balkan yarımadası arasında kalan parçasına ne isim verilir?','Adriyatik Denizi','Biga ','Dilek','Bozburun',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (111,2,0,0,'Aliağa rafinerisi hangi ilimiz sınırları içerisindedir?','İzmir','Muğla','Aydın','Denizli',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (112,2,0,0,'Altay Dağları hangi kıtadadır?','Asya ','Avrupa','Afrika','Antarktika',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (113,2,0,0,'Altın boynuz limanı olarak bilinen yer neresidir? ','Haliç Limanı','Karaköy Limanı','Kuşadası Limanı','Gemport Limanı',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (114,2,0,0,'Altınbaşak Ovası diye bilinen meşhur ovamız hangisidir?','Harran Ovası','Kızılırmak','Sakarya Nehri','Gediz Nehri',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (115,2,0,0,'Amerika ile Kanada arasında yer alan ve yüksekliği 48 metre olan dünyanın en büyük şelalesinin adı nedir?','Niagara','Victoria','Kaieteur','Detian',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (116,2,0,0,'Amerika Kıtası[[SINGLE_QUOTE]]nı İkiye Ayıran Önemli Su Geçidinin Adı Nedir?','Panama ','Messina','Kiel','Süveyş',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (117,2,0,0,'Ana Sıradağların Kenarlarında Yer Alan Yüzeysel Şekillere Ne Ad Verilir?','Öntepe','Ortatepe','Küçüktepe','Büyüktepe',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (118,2,0,0,'Antalya sınırları içerisinde bulunan meşhur dağların adı nedir? ','Bey Dağları','Kaz Dağları','Süphan Dağlari','Kaçkar Dağları',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (119,2,0,0,'Antalya[[SINGLE_QUOTE]]nın kaş ilçesinden çıplak gözle görülebilen Yunanistan[[SINGLE_QUOTE]]a bağlı ada hangisidir?','Meis','Suluada','Kekova ','Beş Adalar',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (120,2,0,0,'Antalya[[SINGLE_QUOTE]]nın Şelalesi İle Aynı Adı Taşıyan İlçesinin Adı Nedir?','Manavgat','Düden','Kurşunlu','Sapadere',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (121,2,0,0,'Çöl iklimi görülen bölgelerde fosfat üretimi yaygındır. Buna göre, aşağıdaki ülkelerden hangisinde bu maden üretilemez?','Portekiz','Şili','Suriye','Tunus',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (122,2,0,0,'Aritmetik nüfus yoğunluğunun fazla olduğu bazı ülkelerde tarım alanları geniştir. Aşağıdakilerden hangisi, buna örnek gösterilemez?','Japonya','Arjantin','Irak','Hindistan',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (123,2,0,0,'Ortadoğu ülkeleri genelde petrol geliri çok yüksek olan ülkelerdir. Aşağıdakilerden hangisi bunlardan değildir?','Ürdün','Irak','Suudi Arabistan','İran',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (124,2,0,0,'Yazların sıcak ve kurak geçtiği, sahillerin uzun olduğu ülkelerde yaz turizmi gelişmiştir. Buna göre, aşağıdaki ülkelerden hangisinde bu durum görülmez?','İngiltere','Türkiye','Fransa','İtalya',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (125,2,0,0,'Yükseltinin fazla olduğu ülkelere ait haritalarda izohips sayısı daha fazladır. Buna göre, aşağıdaki hangi ülkede izohips sayısı daha fazladır?','Tibet','İngiltere','Çin','Fransa',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (126,2,0,0,'Gelişmiş ülkelerde nüfus dağılımı daha dengelidir. Buna göre, aşağıdaki hangi ülke buna örnek gösterilebilir?','İngiltere','Cezayir','Afganistan','Suriye',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (127,2,0,0,'İklim koşullarının tarımsal üretime elverişsiz olduğu ülkelerde konserve tüketimi fazladır. Buna göre, aşağıdaki ülkelerden hangisinde konserve tüketimi daha fazladır?','Norveç','İtalya','Rusya','Fransa',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (128,2,0,0,'Yerşekillerinin engebeli olduğu ülkelerde hidroelektrik potansiyeli yüksektir. Buna göre, aşağıdaki ülkelerden hangisinde bu durum görülmez?','Mısır','İtalya','Rusya','Norveç',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (129,2,0,0,'Japonya, endüstri alanında gelişmiş bir ülkedir. Aşağıdakilerden hangisi, Japonya`da gelişen endüstri kollarından değildir?','Tekstil','Gemi yapımı','Ağır iş makinaları','Elektronik eşya',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (130,2,0,0,'Renklendirme yöntemiyle çizilmiş Türkiye fiziki haritasında yeşil tonların yoğun olduğu coğrafi bölgemiz hangisidir? ','Marmara','Akdeniz','Ege ','Doğu',0,-1,0,0,0,0,0,0,0,0,0);
INSERT INTO `questions` VALUES (131,2,0,0,'Aynı boyuttaki kağıtlaraayrı ayrı çizilen 7 coğrafi bölgemizden hangisinde ayrıntı daha fazladır?','Güneydoğu Anadolu','Akdeniz ','Ege ','Karadeniz',0,-1,0,0,0,0,0,0,0,0,0);
CREATE TABLE IF NOT EXISTS `categories` (
	`id`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`name`	TEXT NOT NULL,
	`parent_id`	INTEGER DEFAULT 0,
	`has_child`	INTEGER DEFAULT 0,
	`icon`	TEXT,
	`color`	TEXT
);
INSERT INTO `categories` VALUES (1,'Genel Kültür',0,0,'','');
INSERT INTO `categories` VALUES (2,'Coğrafya',0,0,'','');
COMMIT;
