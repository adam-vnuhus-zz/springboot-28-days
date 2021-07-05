package com.onemount.onecinema.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.onemount.onecinema.model.Film;
import com.onemount.onecinema.model.Genre;
import com.onemount.onecinema.model.Language;
import com.onemount.onecinema.model.Rating;
import com.onemount.onecinema.repository.FilmRepository;
import com.onemount.onecinema.repository.GenreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmService {
    private static final String DD_MM_YYYY = "dd/MM/yyyy";

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private GenreRepository genreRepo;
    @Autowired
    private FilmRepository filmRepo;

    @Transactional
    public void generateSampleFilms() throws ParseException {
        Genre hanhDong = genreRepo.findByName("Hành động");
        Genre kinhDi = genreRepo.findByName("Kinh dị");
        Genre hai = genreRepo.findByName("Hài");
        Genre tinhCam = genreRepo.findByName("Tình cảm");
        Genre hoiHop = genreRepo.findByName("Hồi hộp");
        Genre tamLy = genreRepo.findByName("Tâm lý");
        Genre giaDinh = genreRepo.findByName("Gia đình");
        Genre thanThoai = genreRepo.findByName("Thần thoại");
        Genre khoaHocVienTuong = genreRepo.findByName("Khoa học viễn tưởng");
        Genre phieuLuu = genreRepo.findByName("Phiêu lưu");

        Film theIceRoad = Film.builder().title("The Ice Road").description(
                "After a remote diamond mine collapses in far northern Canada, a ‘big-rig’ ice road driver (Neeson) must lead an impossible rescue mission over a frozen ocean to save the trapped miners. Contending with thawing waters and a massive storm, they discover the real threat is one they never saw coming.")
                .director("").actors("Amber Midthunder, Holt McCallany, Laurence Fishburne, Liam Neeson")
                .beginShowDate(new SimpleDateFormat(DD_MM_YYYY).parse("01/07/2021")).language(Language.KR)
                .hasVietSubTitle(true).lengthInMinutes(128).rating(Rating.C18)
                .thumbnail(
                        "https://apiprod.amccinemas.com/incharge-assets/images/films/2928a78f-261e-4573-b9ba-79d1a1ec8d3d.jpeg?=1624451929617")
                .movieBanner(
                        "https://apiprod.amccinemas.com/incharge-assets/images/films/9f213627-f2b5-487e-8db5-9cb254fdb26c.jpeg?=1624452045856")
                .trailerBanner("https://www.youtube.com/embed/u0KzAdcYH_U")
                .build();
        theIceRoad.addGenre(hanhDong);
        theIceRoad.addGenre(kinhDi);

        Film theBossBaby2 = Film.builder().title("THE BOSS BABY2: Family Business").description(
                "The Templeton brothers have become adults and drifted away from each other, but a new boss baby with a cutting-edge approach is about to bring them together again - and inspire a new family business.")
                .director("")
                .actors("Amy Sedaris, James Marsden, Jeff Goldblum")
                .beginShowDate(new SimpleDateFormat(DD_MM_YYYY).parse("02/07/2021")).language(Language.VN)
                .hasVietSubTitle(false).lengthInMinutes(128).rating(Rating.C13)
                .thumbnail(
                        "https://apiprod.amccinemas.com/incharge-assets/images/films/1b094086-8efd-4376-a44c-111235faa0e3.png?=1608213379617")
                .movieBanner("https://apiprod.amccinemas.com/incharge-assets/images/films/936f88ac-e28a-4a4e-825f-e06575104a29.png?=1608213393619")
                .trailerBanner("https://www.youtube.com/embed/RJTVNLaaBb8")
                .build();
        theBossBaby2.addGenre(hai);
        theBossBaby2.addGenre(giaDinh);

        Film theForeverPurge = Film.builder().title("The Forever Purge").description(
                "All the rules are broken as a sect of lawless marauders decides that the annual Purge does not stop at daybreak and instead should never end.")
                .director("")
                .actors("")
                .beginShowDate(new SimpleDateFormat(DD_MM_YYYY).parse("01/08/2021")).language(Language.EN)
                .hasVietSubTitle(true).lengthInMinutes(101).rating(Rating.C18)
                .thumbnail(
                        "https://apiprod.amccinemas.com/incharge-assets/images/films/57f78012-ee10-4327-bb47-f10b82e19c11.jpeg?=1622626414046")
                .movieBanner("https://apiprod.amccinemas.com/incharge-assets/images/films/2f63ce56-98c2-41e4-9436-e63a1708e663.jpeg?=1622626444340")
                .trailerBanner("https://www.youtube.com/embed/assb3AR1xNc")
                .build();
        theForeverPurge.addGenre(hanhDong);
        theForeverPurge.addGenre(hoiHop);

        Film meshAna = Film.builder().title("Mesh Ana").description(
                "Khi hai kẻ thù truyền kiếp gặp nhau trong một trận chiến ngoạn mục, số phận của cả thế giới vẫn còn bị bỏ ngỏ… Bị đưa khỏi Đảo Đầu Lâu, Kong cùng Jia, một cô bé mồ côi có mối liên kết mạnh mẽ với mình và đội bảo vệ đặc biệt hướng về mái nhà mới. Bất ngờ, nhóm đụng độ phải Godzilla hùng mạnh, tạo ra một làn sóng hủy diệt trên toàn cầu. Thực chất, cuộc chiến giữa hai kẻ khổng lồ dưới sự thao túng của các thế lực vô hình mới chỉ là điểm khởi đầu để khám phá những bí ẩn nằm sâu trong tâm Trái đất.")
                .director("")
                .actors("Mohameed Abdulrhman, Tamer Hosni, Mohammed Aljwhari, Yasmen Albshbishi, Hajas Abduladem, Asem seliman, Majed AlkKodwani, Tamer Hosni, Hala Sheha, Esam Alsana, Hala Sheha , Hajas Abduladem, Faiz Almalki, Sawsan Bader, Majed Alkodwani, Esad Nassar")
                .beginShowDate(new SimpleDateFormat(DD_MM_YYYY).parse("26/08/2021")).language(Language.EN)
                .hasVietSubTitle(true).lengthInMinutes(113).rating(Rating.C13)
                .thumbnail(
                        "https://apiprod.amccinemas.com/incharge-assets/images/films/a45fb9b0-a893-421f-9bd0-e0b096bae13b.png?=1623319264879")
                .movieBanner("https://apiprod.amccinemas.com/incharge-assets/images/films/3aed19ea-e483-4159-bb89-6944a6898de9.png?=1623319514707")
                .trailerBanner("https://www.youtube.com/embed/Ex8u1tbPaAY")
                .build();
        meshAna.addGenre(hanhDong);
        meshAna.addGenre(thanThoai);

        Film luca = Film.builder().title("Luca").description(
                "On the Italian Riviera, an unlikely but strong friendship grows between a human being and a sea monster disguised as a human.")
                .director("")
                .actors("Jacob Tremblay, Maya Rudolph, Jack Dylan Grazer")
                .beginShowDate(new SimpleDateFormat(DD_MM_YYYY).parse("09/08/2021")).language(Language.EN)
                .hasVietSubTitle(true).lengthInMinutes(108).rating(Rating.C16)
                .thumbnail(
                        "https://apiprod.amccinemas.com/incharge-assets/images/films/f52ab1d4-a598-4510-8b31-c79abf792855.jpeg?=1621763493247")
                .movieBanner("https://apiprod.amccinemas.com/incharge-assets/images/films/33d227ef-4b36-4597-a8aa-b60c25e0d7f7.jpeg?=1621763507982")
                .trailerBanner("https://www.youtube.com/embed/t0nyTrMX9r4")
                        .build();
        luca.addGenre(hoiHop);
        luca.addGenre(khoaHocVienTuong);

        Film blackWidow = Film.builder().title("Black Widow")
                .description(
                        "A film about Natasha Romanoff in her quests between the films Civil War and Infinity War.")
                .director("Cate Shortland").actors("Scarlett Johansson, Florence Pugh, Robert Downey Jr...")
                .beginShowDate(new SimpleDateFormat(DD_MM_YYYY).parse("09/07/2021")).language(Language.EN)
                .hasVietSubTitle(true).lengthInMinutes(134).rating(Rating.C16)
                .thumbnail("https://apiprod.amccinemas.com/incharge-assets/images/films/57c0ffb0-8ad8-4640-8b82-78994b4af318.png?=1599156992272")
                .movieBanner(
                        "https://apiprod.amccinemas.com/incharge-assets/images/films/b6f00184-ab26-455b-8aec-a79fd194433a.jpeg?=1624266872429")
                .trailerBanner("https://www.youtube.com/embed/ybji16u608U").build();
        blackWidow.addGenre(hanhDong);
        blackWidow.addGenre(phieuLuu);

        Film theHitman = Film.builder().title("The Hitman's Wife's Bodyguard").description(
                "Still unlicensed and under scrutiny, Bryce is forced into action by Darius's even more volatile wife, the infamous international con artist Sonia Kincaid (Salma Hayek). As Bryce is driven over the edge by his two most dangerous protectees, the trio get in over their heads in a global plot and soon find that they are all that stand between Europe and a vengeful and powerful madman (Antonio Banderas). Joining in the fun and deadly mayhem is Morgan Freeman as... well, you'll have to see.")
                .director("").actors("Antonio Banderas, Morgan Freeman, Ryan Reynolds, Salma Hayek, Samuel L. Jackson")
                .beginShowDate(new SimpleDateFormat(DD_MM_YYYY).parse("11/08/2021")).language(Language.VN)
                .hasVietSubTitle(false).lengthInMinutes(120).rating(Rating.C16)
                .thumbnail(
                        "https://apiprod.amccinemas.com/incharge-assets/images/films/a3aa546f-4642-4ee9-bcef-ad47b0e08f29.png?=1621439600982")
                .movieBanner("https://apiprod.amccinemas.com/incharge-assets/images/films/7ab581d9-adb9-456b-9271-f7f4240c8bc6.png?=1623874861664")
                .trailerBanner("https://www.youtube.com/embed/9StEsJF7RMI")
                        .build();
        theHitman.addGenre(tinhCam);
        theHitman.addGenre(hai);

        Film theJourney = Film.builder().title("The Journey (Arabic)").description(
                "A tale of historical fantasy from the heart of the Arabian Peninsula. The hero, Aws, has a mysterious past. Together with his peers, face an epic battle to defend their city and their families against a ruthless army general!")
                .director("").actors("Jasem AlNabhan, Abdo Chahine, Nassar AlNassar, Abdullah Elfiky, Racha Rezk")
                .beginShowDate(new SimpleDateFormat(DD_MM_YYYY).parse("03/08/2021")).language(Language.VN)
                .hasVietSubTitle(false).lengthInMinutes(150).rating(Rating.C16)
                .thumbnail("https://apiprod.amccinemas.com/incharge-assets/images/films/60c1fe52-c1c3-4f04-a329-cfde220b57bb.jpeg?=1622712596043")
                .movieBanner("https://apiprod.amccinemas.com/incharge-assets/images/films/d58a67a5-e267-4d2c-bc1b-14fbdcd657ba.jpeg?=1623955997023")
                .trailerBanner("https://www.youtube.com/embed/8smeLqEp84s")
                .build();
        theJourney.addGenre(tamLy);

        Film aQuietPlacePartII = Film.builder().title("A Quiet Place Part II").description(
                "Following the events at home, the Abbott family now face the terrors of the outside world. Forced to venture into the unknown, they realize the creatures that hunt by sound are not the only threats lurking beyond the sand path.")
                .director("").actors("Millicent Simmonds, Lauren-Ashley Cristiano, Djimon Hounsou")
                .beginShowDate(new SimpleDateFormat(DD_MM_YYYY).parse("03/07/2021")).language(Language.VN)
                .hasVietSubTitle(false).lengthInMinutes(150).rating(Rating.C16)
                .thumbnail(
                        "https://apiprod.amccinemas.com/incharge-assets/images/films/c7af0146-366f-46ef-a533-face8ecde774.jpeg?=1593604029979")
                .movieBanner(
                        "https://apiprod.amccinemas.com/incharge-assets/images/films/93f88fea-7781-4b1d-b5ec-18423ab1821a.jpeg?=1583246902546")
                .trailerBanner("https://www.youtube.com/embed/XEMwSdne6UE").build();
        aQuietPlacePartII.addGenre(tamLy);

        Film mamaHamel = Film.builder().title("Mama Hamel").description(
                "Somaya and Kamel, a couple in their 60’s, are still madly in love. They have two sons, Kamel (a urologist specialized in treating impotence in men), and Bassem (owner of an advertising company). The couple incessantly attempt to push Kamel and Bassem towards marriage but without success. A surprise occurs when Somaya announces that she is pregnant! Embarrassed by this news, Kamel and Bassem do everything within their means to convince their mother to let go of the pregnancy.")
                .director("").actors("Hamdi El Marghani, Mohamed Salam, Bayomi Fouad, Layla Eloui")
                .beginShowDate(new SimpleDateFormat(DD_MM_YYYY).parse("05/08/2021")).language(Language.VN)
                .hasVietSubTitle(false).lengthInMinutes(150).rating(Rating.C16)
                .thumbnail(
                        "https://apiprod.amccinemas.com/incharge-assets/images/films/5a18fd45-3110-4fcc-935e-9251bfadfc1b.png?=1621780993426")
                .movieBanner(
                        "https://apiprod.amccinemas.com/incharge-assets/images/films/ee8dacba-3c7d-440b-98ca-305da44ad7d1.png?=1621781005465")
                .trailerBanner("https://www.youtube.com/embed/VNc6DnyjPls").build();
        mamaHamel.addGenre(tamLy);

        Film theConjuring3 = Film.builder().title("The Conjuring 3: The Devil Made Me Do It").description(
                "A chilling story of terror, murder and unknown evil that shocked even experienced real-life paranormal investigators Ed and Lorraine Warren. One of the most sensational cases from their files, it starts with a fight for the soul of a young boy, then takes them beyond anything they'd ever seen before, to mark the first time in U.S. history that a murder suspect would claim demonic possession as a defense.")
                .director("").actors("Ruairi O'Connor, Patrick Wilson, Vera Farmiga")
                .beginShowDate(new SimpleDateFormat(DD_MM_YYYY).parse("06/08/2021")).language(Language.VN)
                .hasVietSubTitle(false).lengthInMinutes(150).rating(Rating.C16)
                .thumbnail(
                        "https://apiprod.amccinemas.com/incharge-assets/images/films/dbede1a4-7bbc-48eb-9a61-848d349a883f.jpeg?=1621419634999")
                .movieBanner(
                        "https://apiprod.amccinemas.com/incharge-assets/images/films/d0f29260-1836-4177-b835-2f91a05307a8.jpeg?=1621419670961")
                .trailerBanner("https://www.youtube.com/embed/xA5EufasOSs").build();
        theConjuring3.addGenre(tamLy);

        Film cruella = Film.builder().title("Cruella").description(
                "Before she becomes Cruella de Vil? Teenaged Estella has a dream. She wishes to become a fashion designer, having been gifted with talent, innovation, and ambition all in equal measures. But life seems intent on making sure her dreams never come true. Having wound up penniless and orphaned in London at 12, 4 years later Estella runs wild through the city streets with her best friends and partners-in-(petty)-crime, Horace and Jasper, two amateur thieves.")
                .director("").actors("Emma Stone, Mark Strong, Emma Thompson")
                .beginShowDate(new SimpleDateFormat(DD_MM_YYYY).parse("07/08/2021")).language(Language.VN)
                .hasVietSubTitle(false).lengthInMinutes(150).rating(Rating.C16)
                .thumbnail(
                        "https://apiprod.amccinemas.com/incharge-assets/images/films/d64dccb0-7e30-4668-ab8a-1e520255dd38.jpeg?=1620652504729")
                .movieBanner(
                        "https://apiprod.amccinemas.com/incharge-assets/images/films/bb726813-486f-4444-bb10-7203e69065c6.jpeg?=1620652415736")
                .trailerBanner("https://www.youtube.com/embed/XKgDh5O9Z48").build();
        cruella.addGenre(tamLy);

        Film fastFurious9 = Film.builder().title("Fast & Furious 9").description(
                "Dom Toretto is leading a quiet life off the grid with Letty and his son, little Brian, but they know that danger always lurks just over their peaceful horizon. This time, that threat will force Dom to confront the sins of his past if he's going to save those he loves most.")
                .director("").actors("Amber Sienna, Michelle Rodriguez, Vin Diesel")
                .beginShowDate(new SimpleDateFormat(DD_MM_YYYY).parse("08/08/2021")).language(Language.VN)
                .hasVietSubTitle(false).lengthInMinutes(150).rating(Rating.C16)
                .thumbnail(
                        "https://apiprod.amccinemas.com/incharge-assets/images/films/ee968b64-1475-4fc9-9a7e-9ac596ca50ab.jpeg?=1619391589268")
                .movieBanner(
                        "https://apiprod.amccinemas.com/incharge-assets/images/films/27e45667-8c73-430e-8044-fe192cd20836.jpeg?=1619391746547")
                .trailerBanner("https://www.youtube.com/embed/hOXcELqrCgM").build();
        fastFurious9.addGenre(tamLy);

        em.persist(theIceRoad);
        em.persist(theBossBaby2);
        em.persist(theForeverPurge);
        em.persist(meshAna);
        em.persist(luca);
        em.persist(blackWidow);
        em.persist(theHitman);
        em.persist(theJourney);
        em.persist(aQuietPlacePartII);
        em.persist(mamaHamel);
        em.persist(theConjuring3);
        em.persist(cruella);
        em.persist(fastFurious9);
    }

    public List<Film> findAll() {
        return filmRepo.findAll();
    }
}
